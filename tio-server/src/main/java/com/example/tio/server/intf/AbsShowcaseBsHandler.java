package com.example.tio.server.intf;


import com.example.tio.common.Const;
import com.example.tio.common.HelloPacket;
import org.tio.core.ChannelContext;

import com.alibaba.fastjson.JSONObject;



public abstract class AbsShowcaseBsHandler<T> implements ShowcaseBsHandlerIntf {

	public AbsShowcaseBsHandler() {
	}

	public abstract Class<T> bodyClass();

	public Object handler(HelloPacket packet, ChannelContext channelContext) throws Exception {
		System.out.println("服务器　AbsShowcaseBsHandler.handler 方法 开始处理客户端传来的数据信息......................");
		String jsonStr = null;
		T bsBody = null;
		if (packet.getBody() != null) {
			jsonStr = new String(packet.getBody(), Const.CHARSET);
			bsBody = JSONObject.parseObject(jsonStr,bodyClass());
		}

		return handler(packet, bsBody, channelContext);
	}

	public abstract Object handler(HelloPacket packet, T bsBody, ChannelContext channelContext) throws Exception;

}
