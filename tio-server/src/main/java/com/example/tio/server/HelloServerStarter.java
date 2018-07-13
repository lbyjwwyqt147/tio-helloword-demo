package com.example.tio.server;

import java.io.IOException;

import com.example.tio.common.Const;
import com.example.tio.common.HelloPacket;
import org.tio.core.Tio;
import org.tio.server.TioServer;
import org.tio.server.ServerGroupContext;
import org.tio.server.intf.ServerAioHandler;
import org.tio.server.intf.ServerAioListener;

/**
 *
 * @author tanyaowu
 * 2017年4月4日 下午12:22:58
 */
public class HelloServerStarter {
	//handler, 包括编码、解码、消息处理
	public static ServerAioHandler aioHandler = new HelloServerTioHandler();

	//事件监听器，可以为null，但建议自己实现该接口，可以参考showcase了解些接口
	public static ServerAioListener aioListener = new HelloServerTioListener();

	//一组连接共用的上下文对象
	public static ServerGroupContext serverGroupContext = new ServerGroupContext("hello-tio-server", aioHandler, aioListener);

	//tioServer对象
	public static TioServer tioServer = new TioServer(serverGroupContext);

	//有时候需要绑定ip，不需要则null
	public static String serverIp = null;

	//监听的端口
	public static int serverPort = Const.PORT;

	/**
	 * 启动程序入口
	 */
	public static void main(String[] args) throws IOException {
		serverGroupContext.setHeartbeatTimeout(Const.TIMEOUT);
		tioServer.start(serverIp, serverPort);
		
		for (int i = 0; i < 100; i++) {
			send();
			System.out.println(" count = " + i);
		}
		
	}
	
	
	public static void send() {

		System.out.println(serverGroupContext.getId());
		
		try {
			Thread.sleep(6000L);
			

			//　使用　bsid　方式发送消息给客户端
			HelloPacket packet3 = new HelloPacket();
			packet3.setBody(" 通过绑定业务id方式  hello world shangguanhongxin === ".getBytes(HelloPacket.CHARSET));
			Tio.sendToBsId(serverGroupContext, "shangguanhongxin", packet3);

			//　使用 userid　方式发送消息给客户端
			HelloPacket packet4 = new HelloPacket();
			packet4.setBody(" 通过绑定userid方式  hello world quezhouyihangdu === ".getBytes(HelloPacket.CHARSET));
			Tio.sendToUser(serverGroupContext, "quezhouyihangdu", packet4);


			//　使用　group　方式发送消息给客户端
			HelloPacket packet5 = new HelloPacket();
			packet5.setBody(" 通过绑定group方式  hello world zhegeliukaizheng === ".getBytes(HelloPacket.CHARSET));
			Tio.sendToGroup(serverGroupContext, "groupid", packet5);


			//　使用　group　方式发送消息给客户端
			HelloPacket packet6 = new HelloPacket();
			packet6.setBody(" 发给客户端：client1_userid  hello world zhegeliukaizheng === ".getBytes(HelloPacket.CHARSET));
			Tio.sendToUser(serverGroupContext, "client1_userid", packet6);


			//　使用　group　方式发送消息给客户端
			HelloPacket packet８ = new HelloPacket();
			packet８.setBody(" 发给客户端：client２_userid  hello world zhegeliukaizheng === ".getBytes(HelloPacket.CHARSET));
			Tio.sendToUser(serverGroupContext, "client2_userid", packet８);


			//　使用　group　方式发送消息给客户端
			HelloPacket packet10 = new HelloPacket();
			packet10.setBody(" 发给客户端组：client_group  hello world zhegeliukaizheng === ".getBytes(HelloPacket.CHARSET));
			Tio.sendToGroup(serverGroupContext, "client_group", packet10);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}