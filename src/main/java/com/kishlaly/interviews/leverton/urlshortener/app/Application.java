package com.kishlaly.interviews.leverton.urlshortener.app;

import com.kishlaly.interviews.leverton.urlshortener.controllers.MainController;
import com.kishlaly.interviews.leverton.urlshortener.service.Shortener;
import com.kishlaly.interviews.leverton.urlshortener.service.impl.SimpleShortener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Vladimir Kishlaly
 * @since 20.06.2017
 */
@SpringBootApplication
@ComponentScan(basePackageClasses = MainController.class)
@EnableJpaRepositories("com.kishlaly.interviews.leverton.urlshortener.repository")
@EntityScan("com.kishlaly.interviews.leverton.urlshortener.model")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    Shortener shortener() {
        return new SimpleShortener();
    }

}
