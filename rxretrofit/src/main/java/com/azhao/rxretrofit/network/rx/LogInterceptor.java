package com.azhao.rxretrofit.network.rx;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * @author azhao
 * $date 6/10/2019
 * $desc 日志拦截
 */
public class LogInterceptor implements Interceptor {
	
	@Override
	public okhttp3.Response intercept(Chain chain) throws IOException {
		String tag = "LogInterceptor";
		Request request = chain.request();
		long startTime = System.currentTimeMillis();
		okhttp3.Response response = chain.proceed(chain.request());
		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;
		ResponseBody body1 = response.body();
		if (body1 == null) {
			RxLog.e(tag, "body is null !!!");
			return null;
		}
		okhttp3.MediaType mediaType = body1.contentType();
		String content = body1.string();
		
		RxLog.e(tag, "\n");
		RxLog.e(tag, "-----------------Start----------------------------");
		RxLog.e(tag, "| " + request.toString());
		String method = request.method();
		if ("POST".equals(method)) {
			StringBuilder sb = new StringBuilder();
			if (request.body() instanceof FormBody) {
				FormBody body = (FormBody) request.body();
				for (int i = 0; i < body.size(); i++) {
					sb.append(body.encodedName(i));
					sb.append("=");
					sb.append(body.encodedValue(i));
					sb.append(",");
				}
				
//				sb.delete(sb.length() - 1, sb.length());
				RxLog.e(tag, "| RequestParams:{" + sb.toString() + "}");
				
			}
		}
		
		RxLog.e(tag, "| Response:" + content);
		RxLog.e(tag, "----------End:" + duration + "毫秒----------");
		RxLog.e(tag, "---------------------------------------------------");
		return response
				   .newBuilder()
				   .body(okhttp3.ResponseBody.create(mediaType, content))
				   .build();
	}
}

