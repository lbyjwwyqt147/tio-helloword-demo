package com.example.tio.client1;

import com.alibaba.fastjson.JSON;
import com.example.tio.common.Const;
import com.example.tio.common.HelloPacket;
import com.example.tio.common.Type;
import org.tio.client.TioClient;
import org.tio.client.ClientChannelContext;
import org.tio.client.ClientGroupContext;
import org.tio.client.ReconnConf;
import org.tio.client.intf.ClientAioHandler;
import org.tio.client.intf.ClientAioListener;
import org.tio.core.Tio;
import org.tio.core.Node;


/**
 *
 * @author tanyaowu
 *
 */
public class HelloClient1Starter {
	//服务器节点
	public static Node serverNode = new Node(Const.SERVER, Const.PORT);

	//handler, 包括编码、解码、消息处理
	public static ClientAioHandler tioClientHandler = new HelloClientTioHandler();

	//事件监听器，可以为null，但建议自己实现该接口，可以参考showcase了解些接口
	public static ClientAioListener aioListener = new HelloCilentTioListener() ;

	//断链后自动连接的，不想自动连接请设为null
	private static ReconnConf reconnConf = new ReconnConf(5000L);

	//一组连接共用的上下文对象
	public static ClientGroupContext clientGroupContext = new ClientGroupContext(tioClientHandler, aioListener, reconnConf);

	public static TioClient tioClient = null;
	public static ClientChannelContext clientChannelContext = null;

	/**
	 * 启动程序入口
	 */
	public static void main(String[] args) throws Exception {
		clientGroupContext.setHeartbeatTimeout(Const.TIMEOUT);
		tioClient = new TioClient(clientGroupContext);
		// 通过IP和端口方式 
		clientChannelContext = tioClient.connect(serverNode,"127.0.0.1",8010,6000);

		//连上后，发条消息玩玩
		//send();
	}

	private static void send() throws Exception {
		HelloPacket packet = new HelloPacket();
		packet.setType(Type.SEND_INFO);
		packet.setBody(JSON.toJSONString("8010　client hello world mocangli").getBytes());
		String userid = "mocangli";
		Tio.bindUser(clientChannelContext,userid);   //绑定的服务端无法获取到
		Tio.bindBsId(clientChannelContext, "yanritian"); //绑定的服务端无法获取到
		Tio.send(clientChannelContext, packet);
	
		
	}
	
}
