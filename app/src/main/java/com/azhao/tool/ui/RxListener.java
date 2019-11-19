package com.azhao.tool.ui;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author  azhao
 * $date    2019/10/17
 * $desc    retrofit的接口使用
 */
public interface RxListener {
	@GET("wxarticle/chapters/json ")
	Observable<String> getChapters();
	
}
