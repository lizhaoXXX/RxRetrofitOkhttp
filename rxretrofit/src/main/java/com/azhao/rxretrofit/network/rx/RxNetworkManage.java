package com.azhao.rxretrofit.network.rx;

import com.azhao.rxretrofit.network.rx.listener.RxUrlListener;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * @author azhao
 * $date   2019/11/19
 * $desc   okhttp的参数配置
 */
public class RxNetworkManage implements RxUrlListener {
	private        String          releaseUrl;
	private        String          debugUrl;
	private static RxNetworkManage sRxNetwork;
	private        OkHttpClient    okHttpClient;
	private        boolean         changeUrl;
	private        long            connectTimeout = 0;
	private        long            readTimeout    = 0;
	private        long            writeTimeout   = 0;
	private        Interceptor     interceptor;
	private        boolean         isLog;
	
	public Interceptor getInterceptor() {
		return interceptor;
	}
	
	public long getConnectTimeout() {
		return connectTimeout;
	}
	
	public long getReadTimeout() {
		return readTimeout;
	}
	
	public long getWriteTimeout() {
		return writeTimeout;
	}
	
	public static RxNetworkManage getInstant() {
		if (sRxNetwork == null) {
			synchronized (RxNetworkManage.class) {
				if (sRxNetwork == null) {
					sRxNetwork = new RxNetworkManage();
				}
			}
		}
		return sRxNetwork;
	}
	
	@Override
	public String releaseUrl() {
		return releaseUrl;
	}
	
	@Override
	public String debugUrl() {
		return debugUrl;
	}
	
	public RxNetworkManage setReleaseUrl(String releaseUrl) {
		this.releaseUrl = releaseUrl;
		return this;
	}
	
	public RxNetworkManage setDebugUrl(String debugUrl) {
		this.changeUrl = true;
		this.debugUrl = debugUrl;
		return this;
	}
	
	public RxNetworkManage setOkHttpClient(OkHttpClient okHttpClient) {
		this.okHttpClient = okHttpClient;
		return this;
	}
	
	public OkHttpClient getOkHttpClient() {
		return okHttpClient;
	}
	
	public boolean isDebugUrl() {
		return this.changeUrl;
	}
	
	public RxNetworkManage setConnectTimeout(long connectTimeout) {
		this.connectTimeout = connectTimeout;
		return this;
	}
	
	public RxNetworkManage setReadTimeout(long readTimeout) {
		this.readTimeout = readTimeout;
		return this;
	}
	
	public RxNetworkManage setWriteTimeout(long writeTimeout) {
		this.writeTimeout = writeTimeout;
		return this;
	}
	
	public RxNetworkManage addInterceptor(Interceptor interceptor) {
		this.interceptor = interceptor;
		return this;
	}
	
	public RxNetworkManage showNetworkLog() {
		this.isLog = true;
		return this;
	}
	
	public boolean isLog() {
		return this.isLog;
	}
	
}
