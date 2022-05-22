package com.cym.crowd.filter;

import com.cym.crowdfundingcommonutil.constant.AccessPassResources;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author cym
 * @date 2022-05-22  23:01
 * @description crowd项目网关过滤器
 */
@Component
@Slf4j
public class CrowdGateWayFilter implements GlobalFilter, Ordered {

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    //获取当前请求路径
    String path = exchange.getRequest().getPath().toString();
    //如果是访问静态资源或者特定放行请求则放行
    if (AccessPassResources.judgeCurrentServletPathWhetherStaticResource(path)) {
      return chain.filter(exchange);
    }

    exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
    return chain.filter(exchange);
  }

  @Override
  public int getOrder() {
    return 0;
  }
}
