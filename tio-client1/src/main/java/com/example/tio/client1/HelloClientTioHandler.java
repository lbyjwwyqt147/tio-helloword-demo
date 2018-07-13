package com.example.tio.client1;

import com.example.tio.common.HelloPacket;
import com.example.tio.common.ShowcaseAbsAioHandler;
import com.example.tio.common.Type;
import org.tio.client.intf.ClientAioHandler;
import org.tio.core.ChannelContext;
import org.tio.core.intf.Packet;

/**
 * 
 * @author tanyaowu
 */
public class HelloClientTioHandler extends ShowcaseAbsAioHandler implements ClientAioHandler {
	private static HelloPacket heartbeatPacket = new HelloPacket(Type.HEART_BEAT_REQ, null);

	/**
	 * 处理消息
	 */
	public void handler(Packet packet, ChannelContext channelContext) throws Exception {
		HelloPacket helloPacket = (HelloPacket) packet;
		byte[] body = helloPacket.getBody();
		if (body != null) {
			String str = new String(body, HelloPacket.CHARSET);
			System.out.println("8010 客户端收到服务端消息：" + str);
		}

		return;
	}

	/**
	 * 此方法如果返回null，框架层面则不会发心跳；如果返回非null，框架层面会定时发本方法返回的消息包
	 */
	public HelloPacket heartbeatPacket() {
		return heartbeatPacket;
	}
}
