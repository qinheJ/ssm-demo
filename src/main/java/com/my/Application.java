package com.my;

import com.my.aop.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author QinHe at 2019-07-29
 */
public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-aop.xml");
        User user = (User) applicationContext.getBean("user");
        user.say();
        System.out.println("end");
    }
}
