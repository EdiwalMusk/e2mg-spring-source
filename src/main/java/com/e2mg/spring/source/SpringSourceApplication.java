package com.e2mg.spring.source;

import com.e2mg.spring.source.core.Event;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Locale;
import java.util.stream.Stream;


@SpringBootApplication
public class SpringSourceApplication {

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(SpringSourceApplication.class, args);
        System.out.println(context.getMessage("type", null, Locale.ROOT));
        System.out.println(context.getMessage("type", null, Locale.CHINA));

        Resource[] resources = context.getResources("classpath*:a.txt");
        Stream.of(resources).forEach(System.out::println);
        System.out.println(resources.length);

        String value = context.getEnvironment().getProperty("a");
        System.out.println(value);

        context.publishEvent(new Event(1));
    }
}