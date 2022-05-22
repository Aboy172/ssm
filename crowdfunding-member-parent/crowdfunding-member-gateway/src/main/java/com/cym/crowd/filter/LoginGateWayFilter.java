package com.cym.crowd.filter;


import com.cym.crowd.config.NotAuthUrlProperties;
import com.cym.crowdfundingcommonutil.constant.AccessPassResources;
import com.cym.crowdfundingcommonutil.constant.CrowdConstant;
import java.util.LinkedHashSet;
import java.util.Objects;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author cym
 * @date 2022-05-22  20:20
 * @description gateway过滤器
 */
@Component
@Slf4j
public class LoginGateWayFilter implements GlobalFilter, Ordered {

  @Resource
  private NotAuthUrlProperties notAuthUrlProperties;


  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    HttpCookie memberTicket = exchange.getRequest().getCookies()
        .getFirst(CrowdConstant.MEMBER_TICKET);
    String path = exchange.getRequest().getURI().getPath();
    ServerHttpResponse response = exchange.getResponse();
    String loginUrl = "http://localhost/";
    if (shouldSkip(path)) {
      return chain.filter(exchange);
    }
    //如果是访问静态资源或者memberTicket不为空放行
    if (AccessPassResources.judgeCurrentServletPathWhetherStaticResource(path)) {
      return chain.filter(exchange);
    }
    if (!Objects.isNull(memberTicket)) {
      //RespBean<String> redisMemberTicket = redisRemoteService.getRedisKeyValue(memberTicket);
      //if (redisMemberTicket == null) {
      //  response.setStatusCode(HttpStatus.UNAUTHORIZED);
      //  response.getHeaders().set(HttpHeaders.LOCATION, loginUrl);
      //  return response.setComplete();
      //}
      return chain.filter(exchange);
    }
    return responseBodyUtil(response, HttpStatus.UNAUTHORIZED, HttpHeaders.LOCATION, loginUrl);
  }

  @Override
  public int getOrder() {
    return 1;
  }

  public Mono<Void> responseBodyUtil(ServerHttpResponse response, HttpStatus status,
      String headerName, String headerValue) {
    response.setStatusCode(status);
    response.getHeaders().set(headerName, headerValue);
    response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");

    return response.setComplete();
  }

  private boolean shouldSkip(String currentUrl) {
    PathMatcher pathMatcher = new AntPathMatcher();
    LinkedHashSet<String> shouldSkipUrls = notAuthUrlProperties.getShouldSkipUrls();
    for (String skipPath : shouldSkipUrls) {
      if (pathMatcher.match(skipPath, currentUrl)) {
        return true;
      }
    }
    return false;
  }

}
