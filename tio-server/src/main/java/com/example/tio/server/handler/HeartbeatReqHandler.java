package com.example.tio.server.handler;

import com.example.tio.common.HelloPacket;
import com.example.tio.server.intf.AbsShowcaseBsHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;


/**
 * 心跳处理
 */
public class HeartbeatReqHandler extends AbsShowcaseBsHandler<String> {
	private static Logger log = LoggerFactory.getLogger(HeartbeatReqHandler.class);


	public static void main(String[] args) {

	}


	public HeartbeatReqHandler() {
	}


	@Override
	public Class<String> bodyClass() {
		return String.class;
	}


	public Object handler(HelloPacket packet, String bsBody, ChannelContext channelContext) throws Exception {
		//心跳消息,啥也不用做
		return null;
	}
}
