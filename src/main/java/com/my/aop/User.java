package com.my.aop;

/**
 * @author QinHe at 2019-07-29
 */
public class User {
    private String name;

    public void say() {
        System.out.println("我的名字是：" + getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
