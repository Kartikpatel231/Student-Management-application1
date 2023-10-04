package com.mycompany.studentmanagementapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Configuration
//@EnableWebMvc
    public class MvcConfig implements WebMvcConfigurer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        try {
            registry
                    .addResourceHandler("/**")
                    .addResourceLocations("classpath:/static/")
                    .resourceChain(true)
                    .addResolver(new CustomPathResourceResolver());
        } catch (Exception e) {
            // Handle the exception here and log it
            logger.error("An error occurred while configuring resource handling.", e);
        }
    }
}
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.ViewResolver;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
// import org.springframework.web.servlet.view.JstlView;
// import org.springframework.web.servlet.view.UrlBasedViewResolver;
//
//@Configuration
//    public class MvcConfig implements WebMvcConfigurer {
//        @Bean
//        public ViewResolver viewResolver() {
//            UrlBasedViewResolver resolver = new UrlBasedViewResolver();
//            resolver.setViewClass(JstlView.class);
//            resolver.setPrefix("/static/");
//            resolver.setSuffix(".html");
//            return resolver;
//        }
//
//    }
//
//
