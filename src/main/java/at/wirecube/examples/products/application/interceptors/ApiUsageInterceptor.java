package at.wirecube.examples.products.application.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component("apiUsageInterceptor")
public class ApiUsageInterceptor implements HandlerInterceptor {

  private static final Logger logger = LoggerFactory.getLogger(ApiUsageInterceptor.class);


  @Override
  public boolean preHandle(
      final HttpServletRequest request,
      final HttpServletResponse response,
      final Object handler) {

    String url = request.getServletPath();
    MDC.put("end_point", url);
    logger.info(request.getServletPath());
    return true;
  }
}