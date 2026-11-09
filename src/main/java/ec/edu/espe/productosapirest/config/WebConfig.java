package ec.edu.espe.productosapirest.config;

import ec.edu.espe.productosapirest.interceptor.RegistroHttpInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final RegistroHttpInterceptor registro;
    public WebConfig(RegistroHttpInterceptor registro) {
        this.registro = registro;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(registro).addPathPatterns("/api/**");
    }
}
