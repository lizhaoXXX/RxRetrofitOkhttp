# RxRetrofitOkhttp

一、引入

```
\\1. Add it in your root build.gradle at the end of repositories:

allprojects {
	repositories {
		maven { url 'https://jitpack.io' }
		}
	}
  
  
  
  \\2.  Add the dependency
  
dependencies {
	implementation 'com.github.lizhaoXXX:RxRetrofitOkhttp:1.0.4'
	}
 ```
  </br>
  二、使用
</br>

此项目demo的接口使用玩Android提供的开放接口，具体请看
https://www.wanandroid.com/blog/show/2
</br></br>

1. 和retrofit使用一样，创建一个接口
```
  public interface RxListener {
	@GET("wxarticle/chapters/json ")
	Observable<String> getChapters();
	
}
```
</br></br>

2.在application初始化网络请求参数

```
//在application初始化

RxNetworkManage.getInstant().setReleaseUrl("https://www.wanandroid.com/");
```
</br></br>

3. 继承RxRetrofitActivity，或者自己BaseDisposable

```
public class MainActivity extends RxRetrofitActivity {
}
```
</br></br>
4.使用

```
RxRetrofit.getInstant()
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
```
</br>
5. 如果你的项目还没引用rx，则需要导包

```
implementation 'io.reactivex.rxjava2:rxandroid:2.1.1'
implementation 'com.squareup.retrofit2:adapter-rxjava2:2.4.0'
```
