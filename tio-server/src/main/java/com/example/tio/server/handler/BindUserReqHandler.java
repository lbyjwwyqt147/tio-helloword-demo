package com.example.tio.server.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.tio.common.Const;
import com.example.tio.common.HelloPacket;

import com.example.tio.server.intf.AbsShowcaseBsHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;

/***
 * 绑定用户 handler
 * @author Administrator
 *
 */
public class BindUserReqHandler extends AbsShowcaseBsHandler<String> {
	private static Logger log = LoggerFactory.getLogger(BindUserReqHandler.class);

	public BindUserReqHandler() {
		
	}

	public Object handler(HelloPacket packet, String bsBody, ChannelContext channelContext)
			throws Exception {
		System.out.println(" 服务器端　受到　客户端传来的消息 :　" + JSON.toJSONString(bsBody));
		JSONObject jsonObject = JSONObject.parseObject(bsBody);
		String userid = jsonObject.getString("userid");
		String bsid = jsonObject.getString("bsid");
		String goupid = jsonObject.getString("goupid");
		System.out.println(" 对客户端:"+userid+" 进行信息绑定　............... ");
		Tio.bindBsId(channelContext,bsid); //绑定bsid
		Tio.bindGroup(channelContext,goupid); //绑定组
		Tio.bindUser(channelContext,userid); //绑定user
	/*	//绑定一个客户端业务id
		channelContext.groupContext.bsIds.bind(channelContext, bsid);
		//绑定一个客户端userid
		channelContext.groupContext.users.bind(userid, channelContext);
		//绑定一个群组
		channelContext.groupContext.groups.bind(goupid, channelContext);*/
		return null;
	}

	@Override
	public Class<String> bodyClass() {
		return String.class;
	}

}
