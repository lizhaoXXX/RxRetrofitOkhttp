package com.azhao.tool.ui;

import android.os.Bundle;

import com.azhao.rxretrofit.network.rx.RxHelper;
import com.azhao.rxretrofit.network.rx.RxNetworkManage;
import com.azhao.rxretrofit.network.rx.subscribe.NormalSubscriber;
import com.azhao.rxretrofit.network.rx.ui.RxRetrofitActivity;
import com.azhao.tool.R;

import androidx.annotation.Nullable;

/**
 * @author azhao
 * $date   2019/11/19
 * $desc   okhttp网络请求封装使用
 */
public class OkHttpActivity extends RxRetrofitActivity {
	
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_okhttp);
		
		//1. 在application初始化
		RxNetworkManage
			.getInstant()
			.setReleaseUrl("https://www.wanandroid.com/");
		
		
		//4. 使用
		RxRetrofit
			.getInstant()
			.getDefaultNet()
			.getChapters()
			.compose(RxHelper.<String>applySchedulers())
			.subscribe(new NormalSubscriber<String>(this) {
				@Override
				public void onResult(String s) {
				
				}
				
				@Override
				public void onSubscriberError(Throwable t) {
				
				}
			});
		
	}
}
