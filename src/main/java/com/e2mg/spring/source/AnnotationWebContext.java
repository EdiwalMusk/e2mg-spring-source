package com.e2mg.spring.source;

import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.Controller;

import java.io.IOException;

public class AnnotationWebContext {

    public static void main(String[] args) throws IOException {
        AnnotationConfigServletWebServerApplicationContext annotationConfigServletWebApplicationContext = new AnnotationConfigServletWebServerApplicationContext(WebConfig.class);
        System.out.println(annotationConfigServletWebApplicationContext);
    }

    @Configuration
    static class WebConfig {

        @Bean
        public ServletWebServerFactory servletWebServerFactory() {
            return new TomcatServletWebServerFactory();
        }

        @Bean
        public DispatcherServlet dispatcherServlet() {
            return new DispatcherServlet();
        }

        @Bean
        public DispatcherServletRegistrationBean dispatcherServletRegistrationBean(DispatcherServlet dispatcherServlet) {
            return new DispatcherServletRegistrationBean(dispatcherServlet, "/");
        }

        @Bean("/hello")
        public Controller controller1() {
            return (httpServletRequest, httpServletResponse) -> {
                httpServletResponse.getWriter().write("hello");
                return null;
            };
        }
    }
}