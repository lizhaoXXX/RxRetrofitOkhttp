package com.azhao.rxretrofit.network.rx.ui;

import com.azhao.rxretrofit.network.rx.BaseDisposable;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author azhao
 * $date   2019/11/19
 * $desc  释放rx
 * 3. 要么继承 RxRetrofitActivity， 要么自己实现 BaseDisposable
 */
public abstract class RxRetrofitActivity extends AppCompatActivity implements BaseDisposable {
	private CompositeDisposable mCompositeDisposable;
	
	@Override
	public CompositeDisposable getDisposable() {
		if (this.mCompositeDisposable == null) {
			mCompositeDisposable = new CompositeDisposable();
		}
		return mCompositeDisposable;
	}
	
	@Override
	public void addDisposable(Disposable d) {
		if (this.mCompositeDisposable == null) {
			mCompositeDisposable = new CompositeDisposable();
		}
		
		if (!d.isDisposed()) {
			this.mCompositeDisposable.add(d);
		}
	}
	
	@Override
	public void unsubscribe() {
		//取消订阅
		if (this.mCompositeDisposable != null) {
			this.mCompositeDisposable.clear();
			mCompositeDisposable = null;
		}
	}
	
	@Override
	public String getImplementName() {
		return getClass().getSimpleName();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unsubscribe();
	}
}
