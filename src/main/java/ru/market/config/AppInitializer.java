
/*
 * Copyright
 */

package ru.market.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * Возвращает конфигурацию, в которой инициализируем ViewResolver.
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    /**
     * Возвращает конфигурации, которые инициализируют Beans.
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
            RootConfig.class,
            DatabaseConfig.class,
            SecurityConfig.class
        };
    }

    /**
     * Настроили мэпинг сервлета на "/" и поэтому все запросы будут
     * перехвачены Диспетчером Сервлета Spring.
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * Настройка ссесии.
     */
    @Override
    public void onStartup(final ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        final Filter filter = new CharacterEncodingFilter();
        final FilterRegistration.Dynamic dynamic = servletContext.addFilter("encodingFilter", filter);
        dynamic.setInitParameter("encoding", "UTF-8");
        dynamic.setInitParameter("forceEncoding", "true");
        dynamic.addMappingForUrlPatterns(null, true, "/*");
    }

    /**
     * Включение исключений NoHandlerFound.
     */
    @Override
    protected DispatcherServlet createDispatcherServlet(final WebApplicationContext context) {
        final DispatcherServlet dispatcherServlet =
            (DispatcherServlet) super.createDispatcherServlet(context);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
        return dispatcherServlet;
    }
}
