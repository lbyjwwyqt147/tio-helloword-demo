package com.example.tio.server.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.tio.common.HelloPacket;
import com.example.tio.server.intf.AbsShowcaseBsHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;

/***
 * 发送消息 handler
 * @author Administrator
 *
 */
public class SendInfoReqHandler extends AbsShowcaseBsHandler<String> {
	private static Logger log = LoggerFactory.getLogger(SendInfoReqHandler.class);

	public SendInfoReqHandler() {
		
	}

	public Object handler(HelloPacket packet, String bsBody, ChannelContext channelContext)
			throws Exception {
		String userid = channelContext.userid;
		System.out.println(" 服务器端　受到　客户端"+userid+"传来的消息 :　" + JSON.toJSONString(bsBody));
		System.out.println("userid :" + userid);

		//给客户端回执消息
		HelloPacket resppacket = new HelloPacket();
		resppacket.setBody(("服务器端收到了你("+userid+")的消息，你的消息是:" + bsBody).getBytes(HelloPacket.CHARSET));
		Tio.send(channelContext, resppacket);
		return null;
	}

	@Override
	public Class<String> bodyClass() {
		return String.class;
	}

}
