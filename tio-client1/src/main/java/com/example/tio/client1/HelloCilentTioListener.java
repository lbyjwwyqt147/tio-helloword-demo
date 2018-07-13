package com.example.tio.client1;

import com.alibaba.fastjson.JSON;
import com.example.tio.common.HelloPacket;
import com.example.tio.common.ShowcaseSessionContext;
import com.example.tio.common.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.client.intf.ClientAioListener;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.core.intf.Packet;

import java.util.concurrent.ConcurrentHashMap;

public class HelloCilentTioListener implements ClientAioListener {
	private static Logger log = LoggerFactory.getLogger(HelloCilentTioListener.class);

	public void onAfterConnected(ChannelContext channelContext, boolean isConnected, boolean isReconnect)
			throws Exception {
		log.info("onAfterConnected channelContext:{}, isConnected:{}, isReconnect:{}", channelContext, isConnected, isReconnect);

		System.out.println("8010 客户端和服务端链接上了........... 连接状态："+isConnected);
		//连接后，需要把连接会话对象设置给channelContext
		channelContext.setAttribute(new ShowcaseSessionContext());
		if(isConnected){  //如果和服务端连接成功　就马上向服务器端发送一个消息，告诉服务器端客户端的一些信息，把这些信息通过客户端传给服务端后服务端进行注册设置，这样服务端就知道发消息给那个客户端了
			HelloPacket packet = new HelloPacket();
			ConcurrentHashMap map = new ConcurrentHashMap();
			map.put("userid","client1_userid"); //userid
			map.put("bsid","client1_bsid"); // bsid
			map.put("goupid","client_group"); // goupid  这些参数信息都会在服务器端进行注册设置
			packet.setBody(JSON.toJSONString(map).getBytes());
			packet.setType(Type.BIND_USER);
			Tio.send(channelContext,packet);
		}
	}

	public void onAfterDecoded(ChannelContext arg0, Packet arg1, int arg2)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void onAfterHandled(ChannelContext arg0, Packet arg1, long arg2)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void onAfterReceivedBytes(ChannelContext arg0, int arg1)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void onAfterSent(ChannelContext arg0, Packet arg1, boolean arg2)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void onBeforeClose(ChannelContext arg0, Throwable arg1, String arg2,
			boolean arg3) throws Exception {
		System.out.println("8010 链接断开之前　执行　onBeforeClose　方法 .................. ");


	}

}
