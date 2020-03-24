/*
 * Copyright
 */

package ru.market.config;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "ru.market.repository")
@ComponentScan(basePackages = "ru.market.model")
public class RootConfig {

    /**
     * Переводит (перехватывает) любые JPA
     * или Hibernate исключения в Spring исключения.
     */
    @Bean
    public BeanPostProcessor persistenceTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    /**
     * Возвращает объект класса CommonsMultipartResolver,
     * который сохраняет временные файлы
     * во временный каталог сервлет контейнера.
     */
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
