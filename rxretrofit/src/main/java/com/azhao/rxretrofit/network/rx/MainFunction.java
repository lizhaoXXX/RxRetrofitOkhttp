package com.azhao.rxretrofit.network.rx;

import android.text.TextUtils;

import org.json.JSONObject;

import io.reactivex.functions.Function;

/**
 * @author azhao
 * $date 2019/10/23
 * $desc
 */
public class MainFunction implements Function<String, String> {
	public static final String NO_DATA = "no_data";
	
	@Override
	public String apply(String s) throws Exception {
		RxLog.i("MainFunction", s);
		JSONObject jsonObject = new JSONObject(s);
		if (jsonObject.has("msg") && jsonObject.has("code")) {
			String msg = jsonObject.optString("msg");
			String code = jsonObject.optString("code");
			String success = "success";
			String codeNumber = "1";
			if (success.equals(msg) && codeNumber.equals(code)) {
				//正常
				if (jsonObject.has("data")) {
					String data = jsonObject.optString("data");
					if (!TextUtils.isEmpty(data) && !"{}".equals(data)) {
						return data;
					} else {
						return NO_DATA;
					}
				}
				
			} else if ("1".equals(code) && "无数据".equals(msg)) {
				//无数据
				return NO_DATA;
			} else if ("0".equals(code)) {
				//无数据
			} else if ("0".equals(code)) {
			
			}
		}
		return s;
	}
}
