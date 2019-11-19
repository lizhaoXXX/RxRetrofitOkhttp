package com.azhao.rxretrofit.network.rx;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author azhao
 * date 2019/1/17
 * $desc
 */
public interface BaseDisposable {
	/**
	 * 获取CompositeDisposable
	 *
	 * @return CompositeDisposable
	 */
	 CompositeDisposable getDisposable();
	
	/**
	 * 加入集合
	 *
	 * @param d Disposable
	 */
	 void addDisposable(io.reactivex.disposables.Disposable d);
	
	/**
	 * 取消订阅
	 */
	 void unsubscribe();
	
	String getImplementName();
}
