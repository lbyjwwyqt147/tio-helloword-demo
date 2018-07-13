package com.example.tio.server;

import com.example.tio.common.ShowcaseSessionContext;
import org.tio.core.ChannelContext;
import org.tio.core.intf.Packet;
import org.tio.server.intf.ServerAioListener;

public class HelloServerTioListener implements ServerAioListener {

	public void onAfterConnected(ChannelContext channelContext, boolean isConnected, boolean isReconnect)
			throws Exception {
		System.out.println(" 服务端　＝　客户端和服务端链接上了........... 连接状态："+isConnected);

		/*//绑定一个客户端业务id
		channelContext.groupContext.bsIds.bind(channelContext, "shangguanhongxin");
		//绑定一个客户端userid
		channelContext.groupContext.users.bind("quezhouyihangdu", channelContext);
		//绑定一个群组
		channelContext.groupContext.groups.bind("groupid", channelContext);*/
		//连接后，需要把连接会话对象设置给channelContext
		channelContext.setAttribute(new ShowcaseSessionContext());


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
		System.out.println("　客户端："+arg0.userid+" 即将断开连接　执行　onBeforeClose　方法 .................. ");
		
	}

}
