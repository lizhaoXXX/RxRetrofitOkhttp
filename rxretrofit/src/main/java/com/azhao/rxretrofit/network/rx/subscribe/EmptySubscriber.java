package com.azhao.rxretrofit.network.rx.subscribe;

import com.azhao.rxretrofit.network.rx.BaseDisposable;

/**
 * @author  azhao
 * $date    2019/10/24
 * $desc    对空数据进行了判断
 */
abstract public class EmptySubscriber<T> extends BaseSubscriber<T> {
	
	public EmptySubscriber(BaseDisposable baseDisposable) {
		super(baseDisposable);
	}
	
	@Override
	public void onEmpty() {
		super.onEmpty();
		this.onEmptyData();
	}
	public abstract void onEmptyData();
}
