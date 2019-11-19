package com.azhao.rxretrofit.network.rx.subscribe;

import com.azhao.rxretrofit.network.rx.BaseDisposable;

/**
 * @author azhao
 * $date    2019/10/24
 * $desc    注意，如果数据为null，则不会走onResult的回调，可以覆写onEmpty()，
 * 或者使用EmptySubscriber()
 */
abstract public class NormalSubscriber<T> extends BaseSubscriber<T> {
	
	public NormalSubscriber(BaseDisposable baseDisposable) {
		super(baseDisposable);
	}
}
