package com.azhao.rxretrofit.network.rx.listener;

/**
 * @author azhao
 * $date   2019/11/19
 * $desc
 */
public interface RxUrlListener {
	/**
	 * 正式服的Url
	 * @return String
	 */
	 String releaseUrl();
	
	/**
	 * 测试服的Url
	 * @return String
	 */
	String debugUrl();
}
