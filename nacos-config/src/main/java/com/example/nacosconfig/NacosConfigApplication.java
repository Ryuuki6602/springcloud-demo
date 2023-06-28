package com.example.nacosconfig;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class NacosConfigApplication {


    // 从nacos配置中心加载配置
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(NacosConfigApplication.class, args);
        while(true) {
            //当动态配置刷新时，会更新到 Environment中，因此这里每隔一秒中从Environment中获取配置
            String userName = applicationContext.getEnvironment().getProperty("user.name");
            String userAge = applicationContext.getEnvironment().getProperty("user.age");
            String currentEnv = applicationContext.getEnvironment().getProperty("current.env");
            System.err.println("user name :" + userName + "; age: " + userAge + "; current env: " + currentEnv);
            TimeUnit.SECONDS.sleep(1);
        }
    }

}
