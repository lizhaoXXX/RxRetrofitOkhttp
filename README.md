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
	implementation 'com.github.lizhaoXXX:RxRetrofitOkhttp:Tag'
	}
  
 ```
  </br>
  二、使用
</br>
此项目demo的接口使用玩Android提供的开放接口，具体请看
https://www.wanandroid.com/blog/show/2
  
  
  1. 和retrofit使用一样，创建一个接口
  ```
  public interface RxListener {
	@GET("wxarticle/chapters/json ")
	Observable<String> getChapters();
	
}
```

2.在application初始化网络请求参数
```
//在application初始化
		RxNetworkManage
			.getInstant()
			.setReleaseUrl("https://www.wanandroid.com/");
```

3. 继承RxRetrofitActivity，或者自己BaseDisposable
```
public class MainActivity extends RxRetrofitActivity {
}
```

4.使用
```

RxRetrofit
			.getInstant()
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
