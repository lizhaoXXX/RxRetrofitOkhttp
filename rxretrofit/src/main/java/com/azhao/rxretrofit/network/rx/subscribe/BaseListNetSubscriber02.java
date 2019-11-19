package com.azhao.rxretrofit.network.rx.subscribe;

import android.content.Context;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author azhao
 * $date 2019/1/5
 * $desc 返回list集合数据
 */
abstract public class BaseListNetSubscriber02<T> implements Observer<List<T>> {
	
	private       Context    mContext;
	private       Disposable mDisposable;
	private final String     TAG = "BaseListNetSubscriber";
	
	@Override
	public void onSubscribe(Disposable d) {
		mDisposable = d;
		onDisposable(d);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void onNext(List<T> data) {
		onResult(data);
	
}

	
	
	@Override
	public void onError(Throwable t) {
		onSubscriberError(t);
		if (!mDisposable.isDisposed()) {
			mDisposable.dispose();
		}
		
		
		
	}
	
	@Override
	public void onComplete() {
		if (!mDisposable.isDisposed()) {
			mDisposable.dispose();
		}
	}
	
	
	
	/**
	 * 结果
	 *
	 * @param ts List
	 */
	abstract public void onResult(List<T> ts);
	
	/**
	 * 释放
	 *
	 * @param d Disposable
	 */
	abstract public void onDisposable(Disposable d);
	
	public void onSubscriberError(Throwable t) {
	}
	
}
