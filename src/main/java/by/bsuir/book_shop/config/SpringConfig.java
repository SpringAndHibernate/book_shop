package by.bsuir.book_shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("by.bsuir.book_shop")
@EnableWebMvc
public class SpringConfig {

    @Bean
    public InternalResourceViewResolver viewResolver(){

        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/templates/");
        internalResourceViewResolver.setSuffix(".html");
        return internalResourceViewResolver;
    }
}
