package com.azhao.tool.ui;

import com.azhao.rxretrofit.network.rx.NetworkServer;

/**
 * @author azhao
 * $date   2019/11/19
 * $desc
 */
public class RxRetrofit {
	private        Object     cerObject = new Object();
	private static RxRetrofit apiManage;
	private        RxListener mRxListener;
	
	public static RxRetrofit getInstant() {
		if (apiManage == null) {
			synchronized (RxRetrofit.class) {
				if (apiManage == null) {
					apiManage = new RxRetrofit();
				}
			}
		}
		return apiManage;
	}
	
	public RxListener getDefaultNet() {
		//2. 单例
		if (mRxListener == null) {
			synchronized (cerObject) {
				if (mRxListener == null) {
					NetworkServer networkServer = new NetworkServer();
					mRxListener = networkServer.getDefaultNet().create(RxListener.class);
				}
			}
		}
		return mRxListener;
	}
}
