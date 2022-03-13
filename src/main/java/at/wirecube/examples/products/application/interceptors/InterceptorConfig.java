package at.wirecube.examples.products.application.interceptors;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {


  private final ApiUsageInterceptor apiUsageInterceptor;
  private final LogBackInterceptor LockBackInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {

    register(registry, apiUsageInterceptor, 1);
    register(registry, LockBackInterceptor, 2);
  }

  private void register(InterceptorRegistry registry, HandlerInterceptor interceptor, int order) {
    registry.addInterceptor(interceptor)
        .order(order)
        .addPathPatterns("/**")
        .excludePathPatterns("/health")
        .excludePathPatterns("/docs");
  }

}