package com.azhao.rxretrofit.network.rx;

import com.azhao.rxretrofit.network.rx.converterfactory.StringConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @author azhao
 * $date    2019/1/5
 * $desc 获取到string的数据，StringConverterFactory
 */
public class NetworkServer {
	public Retrofit getDefaultNet() {
		//基础的url
		//string的格式数据
		return new Retrofit.Builder()
				   //基础的url
				   .baseUrl(RxNetworkManage
								.getInstant()
								.releaseUrl())
				   .client(getClient())
				   .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				   //string的格式数据
				   .addConverterFactory(StringConverterFactory.create())
				   .build();
		
	}
	
	private OkHttpClient getClient() {
		OkHttpClient okHttpClient = RxNetworkManage
										.getInstant()
										.getOkHttpClient();
		if (okHttpClient == null) {
			OkHttpClient.Builder builder = new OkHttpClient.Builder();
			
			if (RxNetworkManage
					.getInstant()
					.isDebugUrl()) {
				builder.addInterceptor(headerInterceptor);
			}
			
			boolean log = RxNetworkManage
							  .getInstant()
							  .isLog();
			if (log) {
				builder.addInterceptor(new LogInterceptor());
			}
			
			Interceptor interceptor = RxNetworkManage
										  .getInstant()
										  .getInterceptor();
			if (interceptor != null) {
				builder.addInterceptor(interceptor);
			}
			
			long connectTimeout = RxNetworkManage
									  .getInstant()
									  .getConnectTimeout();
			if (connectTimeout != 0) {
				builder.connectTimeout(connectTimeout, TimeUnit.MILLISECONDS);
			}
			
			long readTimeout = RxNetworkManage
								   .getInstant()
								   .getReadTimeout();
			if (readTimeout != 0) {
				builder.readTimeout(readTimeout, TimeUnit.MILLISECONDS);
			}
			
			long writeTimeout = RxNetworkManage
									.getInstant()
									.getWriteTimeout();
			if (writeTimeout != 0) {
				builder.writeTimeout(writeTimeout, TimeUnit.MILLISECONDS);
			}
			
			return builder.build();
		} else {
			
			boolean log = RxNetworkManage
							  .getInstant()
							  .isLog();
			
			boolean debugUrl = RxNetworkManage
								   .getInstant()
								   .isDebugUrl();
			if (debugUrl || log) {
				OkHttpClient.Builder builder = okHttpClient.newBuilder();
				if (debugUrl) {
					builder.addInterceptor(headerInterceptor);
				}
				if (log) {
					builder.addInterceptor(new LogInterceptor());
				}
				return builder.build();
			} else {
				return okHttpClient;
			}
		}
	}
	
	private Interceptor headerInterceptor = new Interceptor() {
		@Override
		public Response intercept(Chain chain) throws IOException {
			//正式服务，测试服的切换
			Request request = chain.request();
			Request.Builder requestBuilder = request.newBuilder();
			// 这里是拦截下来的 url ，注意：这个 url 是全量url，也就是完整的url，并不是单纯的主域名地址。
			String url = request
							 .url()
							 .toString();
			RxLog.e("headerInterceptor", "url = " + url);
			if (RxLog.isDebug) {
				// 如果是测试服并且拦截下来的url中还包含正式服，将url前缀替换成测试服的url前缀
				if (url.contains(RxNetworkManage
									 .getInstant()
									 .releaseUrl())) {
					url = url.replace(RxNetworkManage
										  .getInstant()
										  .releaseUrl(), RxNetworkManage
															 .getInstant()
															 .debugUrl());
				}
			} else {
				if (url.contains(RxNetworkManage
									 .getInstant()
									 .debugUrl())) {
					url = url.replace(RxNetworkManage
										  .getInstant()
										  .debugUrl(), RxNetworkManage
														   .getInstant()
														   .releaseUrl());
				}
			}
			
			// 将新的 url 再设置回去
			request = requestBuilder
						  .url(url)
						  .build();
			return chain.proceed(request);
		}
	};
}

