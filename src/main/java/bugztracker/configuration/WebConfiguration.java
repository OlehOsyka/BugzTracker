package bugztracker.configuration;

import bugztracker.filter.AuthenticationInterceptor;
import bugztracker.filter.AuthorizationInterceptor;
import bugztracker.util.HibernateLazyObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

import static java.util.Collections.singletonList;

/**
 * Created by oleg
 * Date: 29.11.15
 * Time: 17:08
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"bugztracker.controller"})
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(initJsonConverter());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorizationInterceptor()).
                addPathPatterns("/*").
                excludePathPatterns("/resources/");
        registry.addInterceptor(new AuthenticationInterceptor()).
                addPathPatterns("/*").
                excludePathPatterns("/resources/");
    }

    @Bean
    public InternalResourceViewResolver initViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public CommonsMultipartResolver initMultipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1024 * 1024 * 10);
        multipartResolver.setDefaultEncoding("UTF-8");
        return multipartResolver;
    }

    @Bean
    public HttpMessageConverter initJsonConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(singletonList(MediaType.APPLICATION_JSON));
        converter.setObjectMapper(new HibernateLazyObjectMapper());
        return converter;

    }
}
