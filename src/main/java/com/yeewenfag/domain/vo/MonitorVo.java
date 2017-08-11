package com.yeewenfag.domain.vo;

import com.yeewenfag.domain.MonitorWithBLOBs;
import com.yeewenfag.domain.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MonitorVo extends MonitorWithBLOBs {

    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<String> getUrlList() {
        List<String> list = new ArrayList<>();

        if (this.getUrls() != null && !this.getUrls().equals("")) {
            String[] array = this.getUrls().split(",");
            list.addAll(Arrays.asList(array));
        }
        return list;
    }
}
