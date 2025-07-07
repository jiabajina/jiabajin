package wt.ssm.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Spring MVC配置类，负责配置视图解析器、静态资源处理和默认Servlet处理
 * 该配置类启用了Web MVC支持并扫描控制器组件
 */
@Configuration
@EnableWebMvc
@ComponentScan("wt.ssm.controller")
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * 配置JSP视图解析器
     * @return InternalResourceViewResolver实例，设置JSP文件后缀为.jsp
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setSuffix(".jsp");
        return resolver;
    }

    /**
     * 启用默认Servlet处理静态资源
     * 允许Spring MVC与容器的默认Servlet协作处理静态资源请求
     * @param configurer DefaultServletHandlerConfigurer配置器
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 配置静态资源处理器映射
     * 将/img/**请求映射到/static/img/目录
     * 将/js/**请求映射到/static/js/目录
     * 将/css/**请求映射到/static/css/目录
     * @param registry 资源处理器注册表
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")
                .addResourceLocations("/static/img/");
        registry.addResourceHandler("/js/**").addResourceLocations("/static/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("/static/css/");
    }
}