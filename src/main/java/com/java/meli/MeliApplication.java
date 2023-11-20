package com.java.meli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;


@SpringBootApplication
@RestController
public class MeliApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeliApplication.class, args);
    }

    @GetMapping("/health")
    public String hello() {
        return "ok";
    }

//	@Bean
//	BeanFactoryPostProcessor beanFactoryPostProcessor(ApplicationContext beanRegistry) {
//		return beanFactory -> {
//			genericApplicationContext((BeanDefinitionRegistry) ((AnnotationConfigServletWebServerApplicationContext) beanRegistry).getBeanFactory());
//		};
//	}
//
//	void genericApplicationContext(BeanDefinitionRegistry beanRegistry) {
//		ClassPathBeanDefinitionScanner beanDefinitionScanner = new ClassPathBeanDefinitionScanner(beanRegistry);
////		beanDefinitionScanner.addIncludeFilter(removeModelAndEntitiesFilter());
//		beanDefinitionScanner.scan("com.java.meli");
//	}

}
