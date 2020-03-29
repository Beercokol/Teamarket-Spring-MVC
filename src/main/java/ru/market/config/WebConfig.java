/*
 * Copyright
 */

package ru.market.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(
        basePackages = {
                "ru.market.controller",
                "ru.market.config"
        }
)
@PropertySource("classpath:content.properties")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Value("${view.type}")
    private String contentType;

    @Value("${view.name-prefix}")
    private String prefix;

    @Value("${view.name-suffix}")
    private String suffix;

    @Value("${view.expose_beans_as_attributes}")
    private boolean exposeContextBeansAsAttributes;

    @Value("${resources.url}")
    private String resourcesUrl;

    @Value("${resources.location}")
    private String resourcesLocation;

    @Value("${login.url}")
    private String loginUrl;

    @Value("${login.view}")
    private String loginView;

    /**
     * Указывает Spring'у где находятся компоненты представления, и как их отображать.
     * Вьюшки будут лежать в директории /WEB-INF/views/ и иметь разширение *.jsp.
     */
    @Bean
    public ViewResolver viewResolver() {
        final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setContentType(this.contentType);
        viewResolver.setPrefix(this.prefix);
        viewResolver.setSuffix(this.suffix);
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setExposeContextBeansAsAttributes(true);
        return viewResolver;
    }

    /**
     * Указывает где будут хранится ресурсы.
     */
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry resource) {
        resource.addResourceHandler(this.resourcesUrl)
                .addResourceLocations(this.resourcesLocation);
    }

    /**
     * Настройка логин-контроллера.
     * Оказывает помощь в регистрации простого автоматизированного
     * логин-контроллера предварительно сконфигурированных с кодом
     * состояния и вьюшкой.
     */
    @Override
    public void addViewControllers(final ViewControllerRegistry viewController) {
        viewController.addViewController(this.loginUrl).setViewName(this.loginView);
        viewController.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}