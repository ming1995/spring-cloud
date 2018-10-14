package com.ljm.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 打印请求日志过滤器
 * 还可用过滤器完成安全认证等操作
 * @author lijunming
 * @date 2018/9/24 下午8:02
 */
@Component
public class PreRequestLogFilter extends ZuulFilter {

    private static  final Logger LOGGER= LoggerFactory.getLogger(PreRequestLogFilter.class);
    //是否启动
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx=RequestContext.getCurrentContext();
        HttpServletRequest request=ctx.getRequest();
        LOGGER.info("send {}  request  to {}",request.getMethod(),request.getRequestURL().toString());
        return null;
    }

    //过滤器类型
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    //过滤器执行顺序
    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER-1;
    }
}
