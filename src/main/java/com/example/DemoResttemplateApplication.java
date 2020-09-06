package com.example;

import com.example.biz.service.RestTemplateService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import javax.annotation.PostConstruct;

public class DemoResttemplateApplication {


    public static void main(String[] args) {

        BeanFactory ctx = new ClassPathXmlApplicationContext("/META-INF/spring/beans-client.xml");
        RestTemplateService restTemplateService = ctx.getBean(RestTemplateService.class);

        try{
            Thread.sleep(10000);
        }catch(InterruptedException ignored){}

        restTemplateService.func3();
    }

}
