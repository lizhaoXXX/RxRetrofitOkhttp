package com.azhao.rxretrofit.network.rx.subscribe;

import com.azhao.rxretrofit.network.rx.BaseDisposable;
import com.azhao.rxretrofit.network.rx.MainFunction;
import com.azhao.rxretrofit.network.rx.RxLog;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author azhao
 * $date    2019/10/24
 * $desc    注意，如果数据为null，则不会走onResult的回调，可以覆写onEmpty()，
 * 或者使用EmptySubscriber()
 */
public abstract class BaseSubscriber<T> implements Observer<T> {
	
	private BaseDisposable baseDisposable;
	private Disposable     mDisposable;
	private String         logName;
	
	public BaseSubscriber(BaseDisposable baseDisposable) {
		this.baseDisposable = baseDisposable;
		logName = baseDisposable.getImplementName();
	}
	
	@Override
	public void onSubscribe(Disposable d) {
		onDisposable(d);
		mDisposable = d;
		baseDisposable.addDisposable(d);
	}
	
	@Override
	public void onNext(T t) {
		RxLog.i(logName, "-- onNext --");
		if (t instanceof String) {
			String ss = (String) t;
			RxLog.e(logName, "onResult = " + ss);
			if (!MainFunction.NO_DATA.equals(ss)) {
				onResult(t);
			} else {
				onEmpty();
			}
		} else {
			onResult(t);
		}
	}
	
	public void onEmpty() {
		//没有数据
	}
	
	@Override
	public void onError(Throwable t) {
		RxLog.e(logName, "Throwable = " + t.toString());
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
	 * @param t t
	 */
	abstract public void onResult(T t);
	
	/**
	 * 释放
	 *
	 * @param d Disposable
	 */
	public void onDisposable(Disposable d){
	
	}
	
	abstract public void onSubscriberError(Throwable t);
}
