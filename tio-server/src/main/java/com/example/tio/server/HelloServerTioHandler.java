package com.example.tio.server;

import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSON;
import com.example.tio.common.HelloPacket;
import com.example.tio.common.ShowcaseAbsAioHandler;
import com.example.tio.common.Type;
import com.example.tio.server.handler.BindUserReqHandler;
import com.example.tio.server.handler.HeartbeatReqHandler;
import com.example.tio.server.handler.SendInfoReqHandler;
import com.example.tio.server.intf.AbsShowcaseBsHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.intf.Packet;
import org.tio.server.intf.ServerAioHandler;


public class HelloServerTioHandler extends ShowcaseAbsAioHandler implements ServerAioHandler {
	private static Logger log = LoggerFactory.getLogger(HelloServerTioHandler.class);


	private static Map<Byte, AbsShowcaseBsHandler<?>> handlerMap = new ConcurrentHashMap<Byte, AbsShowcaseBsHandler<?>>();
	static {
		handlerMap.put(Type.HEART_BEAT_REQ, new HeartbeatReqHandler());
		handlerMap.put(Type.BIND_USER, new BindUserReqHandler());
		handlerMap.put(Type.SEND_INFO, new SendInfoReqHandler());
	}

	/**
	 * 处理消息
	 */
	public void handler(Packet packet, ChannelContext channelContext) throws Exception {
		HelloPacket showcasePacket = (HelloPacket) packet;
		System.out.println(JSON.toJSONString(showcasePacket));
		Byte type = showcasePacket.getType();
		AbsShowcaseBsHandler<?> showcaseBsHandler = handlerMap.get(type);
		if (showcaseBsHandler == null) {
			log.error("{}, 找不到处理类，type:{}", channelContext, type);
			return;
		}
		showcaseBsHandler.handler(showcasePacket, channelContext);
		return;
	}
}
