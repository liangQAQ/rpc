package com.huangliang.netty.handler;

import com.huangliang.netty.config.Arg;
import com.huangliang.netty.config.MethodHelper;
import com.huangliang.netty.config.NettyUtil;
import com.huangliang.netty.mvc.ApplicationContext;
import com.huangliang.netty.mvc.HandlerMapping;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Map;

//http请求处理类
public class HttpRequestHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();
        FullHttpRequest req = (FullHttpRequest)msg;
        FullHttpResponse resp = null;
        Object result = null;
        String url =  req.uri();
        if(url.contains("?")){
            url = url.substring(0,url.lastIndexOf("?" ));
        }
        if(!ApplicationContext.mapping.containsKey(url)){
            //返回404
            resp = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.NOT_FOUND);
        }else{
            HandlerMapping handlerMapping = ApplicationContext.mapping.get(url);
            try {
                Method method = handlerMapping.getM();
                //从请求中获取参数key-value
                Map<String, String> requestParams = NettyUtil.getRequestParams(req);
                //从方法中获取形参名和形参类型
                List<Arg> methodArgNames = MethodHelper.getMethodArgNames(handlerMapping.getObject().getClass(), method);
                Object[] obj = new Object[method.getParameterCount()];
                int i = 0;
                for (Arg arg:methodArgNames) {
                    //这里偷懒没判断类型
                    obj[i] = requestParams.get(arg.getArgName());
                    i++;
                }
                result = method.invoke(handlerMapping.getObject(),obj);
//                result = handlerMapping.getM().invoke(handlerMapping.getObject(), new Object[]{"vv","dff"});
            }catch (Exception e){
                e.printStackTrace();
            }
            resp = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    Unpooled.copiedBuffer(result.toString(), CharsetUtil.UTF_8));
        }
        resp.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");
        resp.headers().set("Content-Length", resp.content().readableBytes());
        channel.writeAndFlush(resp);

    }
}
