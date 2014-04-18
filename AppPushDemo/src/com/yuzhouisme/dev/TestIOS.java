package com.yuzhouisme.dev;

import javapns.back.PushNotificationManager;
import javapns.back.SSLConnectionHelper;
import javapns.data.Device;
import javapns.data.PayLoad;

public class TestIOS {

	public static void main(String[] args) {
		
		try {
			String deviceToken = "114081460f947492f880241f9ce453f4a898a99bba24757ccf784cce8e441c44";
			// Apple
			// Device手机获取的token

			System.out.println("deviceToken.length==" + deviceToken.length());

			PayLoad payLoad = new PayLoad();
			payLoad.addAlert("TestIOS AppPushDemo");// 内容
			payLoad.addBadge(1);// 小红圈
			payLoad.addSound("default");// 铃音

			PushNotificationManager pushManager = PushNotificationManager.getInstance();
			pushManager.addDevice("Apple Device", deviceToken);
			// Connect to APNs
			/************************************************
			 * 测试的服务器地址：gateway.sandbox.push.apple.com /端口2195
			 * 产品推送服务器地址：gateway.push.apple.com / 2195
			 ***************************************************/
			String host = "gateway.sandbox.push.apple.com";
			int port = 2195;
			String certificatePath = "/Users/infintius/Desktop/adips.p12";// 导出的证书
			String certificatePassword = "123456";// 此处注意导出的证书密码不能为空因为空密码会报错

			pushManager.initializeConnection(host, port, certificatePath,
					certificatePassword,
					SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);

			// Send Push
			Device client = pushManager.getDevice("Apple Device");

			System.out.println("推送消息===" 
					+ client.getToken() 
					+ "====="
					+ payLoad.toString() 
					+ "	client.getId()"
					+ client.getId());
			pushManager.sendNotification(client, payLoad);
			pushManager.stopConnection();

			pushManager.removeDevice("Apple Device");

		} catch (Exception e) {
			e.getLocalizedMessage();
			e.printStackTrace();
		}
	}
}
