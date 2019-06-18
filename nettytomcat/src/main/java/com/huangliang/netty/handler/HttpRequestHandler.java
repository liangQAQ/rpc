package com.huangliang.netty.handler;

import com.huangliang.netty.mvc.ApplicationContext;
import com.huangliang.netty.mvc.HandlerMapping;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.lang.reflect.Method;

//http请求处理类
public class HttpRequestHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();
        FullHttpRequest req = (FullHttpRequest)msg;
        FullHttpResponse resp = null;
        String url =  req.uri();
        System.out.println("request url : "+ url);
        System.out.println("request content : "+ req);
        if(!ApplicationContext.mapping.containsKey(url)){
            //返回404
            resp = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.NOT_FOUND);
        }else{
            HandlerMapping handlerMapping = ApplicationContext.mapping.get(url);
            Object result = handlerMapping.getM().invoke(handlerMapping.getObject(),(Object)"sss");
            resp = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    Unpooled.copiedBuffer(result.toString(), CharsetUtil.UTF_8));
        }
        resp.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");
        resp.headers().set("Content-Length", resp.content().readableBytes());
        channel.writeAndFlush(resp);

    }
}
