package com.e2mg.spring.source.core;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class TestBeanFactory {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Config.class).setScope("singleton").getBeanDefinition();
        beanFactory.registerBeanDefinition("customConfig", beanDefinition);

        for (String name : beanFactory.getBeanDefinitionNames()) {
            System.out.println(name);
        }

        // 注册beanFactory后处理
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);

        for (String name : beanFactory.getBeanDefinitionNames()) {
            System.out.println(name);
        }

        // 执行beanFactory后处理 补充了一些bean的定义
        beanFactory.getBeansOfType(BeanFactoryPostProcessor.class).values().forEach((p) -> {
            p.postProcessBeanFactory(beanFactory);
        });

        for (String name : beanFactory.getBeanDefinitionNames()) {
            // 此处应该多出了bean1 bean2
            System.out.println(name);
        }

        // 执行bean后处理
        beanFactory.getBeansOfType(BeanPostProcessor.class).values().forEach(beanFactory::addBeanPostProcessor);

        // 准备单例对象
        beanFactory.preInstantiateSingletons();

        System.out.println(beanFactory.getBean(Bean1.class).getBean2());

        // beanFactory总结
        // 1、不会执行beanFactory后处理
        // 2、不添加bean后处理
        // 3、不初始化单例对象
        // 4、不解析${},#{}
    }

    @Configuration
    static class Config {

        @Bean
        public Bean1 bean1() {
            return new Bean1();
        }

        @Bean
        public Bean2 bean2() {
            return new Bean2();
        }
    }

    @Data
    static class Bean1 {

        @Autowired
        private Bean2 bean2;

        public Bean1() {
            System.out.println("constructor bean1!");
        }
    }

    static class Bean2 {
        public Bean2() {
        }
    }
}
