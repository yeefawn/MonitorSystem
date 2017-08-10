package com.yeewenfag.shiro;

import com.yeewenfag.domain.User;
import com.yeewenfag.domain.vo.UserVo;
import com.yeewenfag.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author 发
 * @create 2017-08-09 21:30
 */
public class MonitorFormAuthenticationFilter extends FormAuthenticationFilter {

    private static Logger logger = LogManager.getLogger(MonitorFormAuthenticationFilter.class);

    @Autowired
    private UserService userService;

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        //从session获取正确验证码
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session =httpServletRequest.getSession();
        //取出session的验证码（正确的验证码）
        String validateCode = (String) session.getAttribute("validateCode");

        //取出页面的验证码
        //输入的验证和session中的验证进行对比
        String randomcode = httpServletRequest.getParameter("validateCode");
        if(randomcode != null && validateCode != null && !randomcode.equalsIgnoreCase(validateCode)){
            //如果校验失败，将验证码错误失败信息，通过shiroLoginFailure设置到request中
            httpServletRequest.setAttribute("shiroLoginFailure", "validateCode:error");
            //拒绝访问，不再校验账号和密码
            return true;
        }

        boolean result = super.onAccessDenied(request, response);

        if (randomcode != null && !result) {
            String IP = getRequestRealIp((HttpServletRequest) request);
            UserVo user = (UserVo) SecurityUtils.getSubject().getPrincipal();
            User statisticsMessage = new User();
            statisticsMessage.setLastLoginIp(IP);
            statisticsMessage.setLastLoginTime(new Date());
            statisticsMessage.setLoginCount(user.getLoginCount() + 1);
            try {
                userService.updateStatisticsMessage(user.getId(), statisticsMessage);
            } catch (Exception e) {
                logger.error(e);
                httpServletRequest.setAttribute("shiroLoginFailure", "unkownError");
                SecurityUtils.getSubject().logout();
                return true;
            }

        }

        return result;
    }

    // 获取真实IP
    private String getRequestRealIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0];
        }

        if (!checkIp(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (!checkIp(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (!checkIp(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        if (!checkIp(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private boolean checkIp(String ip) {
        return ip != null && ip.length() != 0 && !"unkown".equalsIgnoreCase(ip);
    }
}
