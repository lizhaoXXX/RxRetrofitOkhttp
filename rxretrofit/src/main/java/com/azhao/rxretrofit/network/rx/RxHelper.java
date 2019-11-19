package com.azhao.rxretrofit.network.rx;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author azhao
 * $date 2019/1/5
 * $desc 线程切换的封装
 */
public class RxHelper {
	public static <T> ObservableTransformer<T, T> applySchedulers() {
		return new ObservableTransformer<T, T>() {
			@Override
			public ObservableSource<T> apply(Observable<T> upstream) {
				return upstream
						   .subscribeOn(Schedulers.io())
						   .observeOn(AndroidSchedulers.mainThread());
			}
		};
	}
	
	private static <T> Observable<T> createData(final T data) {
		return Observable.create(new ObservableOnSubscribe<T>() {
			@Override
			public void subscribe(ObservableEmitter<T> emitter) {
				try {
					emitter.onNext(data);
					emitter.onComplete();
				} catch (Exception e) {
					emitter.onError(e);
				}
				
			}
		});
	}
	
}
