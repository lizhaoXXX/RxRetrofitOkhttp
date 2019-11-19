package com.azhao.rxretrofit.network.rx;

import android.util.Log;

import com.azhao.rxretrofit.BuildConfig;
/**
 * @author azhao
 * $date    2019/10/17
 * $desc    工具日志
 */
public class RxLog {
	
	/**
	 * 手动关闭日志，false关闭，true打开
	 */
	private static boolean mLogAble = isDebug();
	private static boolean isCLR    = isDebug();
	public static final boolean isDebug  = BuildConfig.DEBUG;
	
	private static boolean isDebug() {
		return true;
	}
	
	public RxLog() {
	}
	
	public static void v(String tag, String msg) {
		v(tag, 1, msg);
	}
	
	public static void v(String tag, int level, String msg) {
		v(tag, level, msg, (Throwable) null);
	}
	
	public static void v(String tag, String msg, Throwable ex) {
		v(tag, 1, msg, ex);
	}
	
	public static void v(String tag, int level, String msg, Throwable ex) {
		if (mLogAble) {
			Log.v(tag, msg + (ex == null ? "" : " " + ex.toString()));
		}
	}
	
	public static void d(String tag, String msg) {
		i(tag, 1, msg);
	}
	
	public static void d(String tag, int level, String msg) {
		d(tag, level, msg, (Throwable) null);
	}
	
	public static void d(String tag, String msg, Throwable ex) {
		d(tag, 1, msg, ex);
	}
	
	public static void d(String tag, int level, String msg, Throwable ex) {
		if (mLogAble) {
			Log.d(tag, msg + (ex == null ? "" : " " + ex.toString()));
		}
		
	}
	
	public static void i(String tag, String msg) {
		i(tag, 1, msg);
	}
	
	public static void i(String tag, int level, String msg) {
		i(tag, level, msg, (Throwable) null);
	}
	
	public static void i(String tag, String msg, Throwable ex) {
		i(tag, 1, msg, ex);
	}
	
	public static void i(String tag, int level, String msg, Throwable ex) {
		if (mLogAble) {
			Log.i(tag, msg + (ex == null ? "" : " " + ex.toString()));
		}
		
	}
	
	public static void w(String tag, String msg) {
		w(tag, 1, msg);
	}
	
	public static void w(String tag, int level, String msg) {
		w(tag, level, msg, (Throwable) null);
	}
	
	public static void w(String tag, String msg, Throwable ex) {
		w(tag, 1, msg, ex);
	}
	
	public static void w(String tag, int level, String msg, Throwable ex) {
		if (mLogAble) {
			Log.w(tag, msg + (ex == null ? "" : " " + ex.toString()));
		}
		
	}
	
	public static void e(String tag, String msg) {
		e(tag, 1, msg);
	}
	
	public static void e(String tag, int level, String msg) {
		e(tag, level, msg, (Throwable) null);
	}
	
	public static void e(String tag, String msg, Throwable ex) {
		e(tag, 1, msg, ex);
	}
	
	public static void e(String tag, int level, String msg, Throwable ex) {
		if (mLogAble) {
			Log.e(tag, msg + (ex == null ? "" : " " + ex.toString()));
		}
		
	}
	
	public static void setIsCLR(boolean clr) {
		isCLR = clr;
	}
	
	public static boolean isColorLevel() {
		return isCLR;
	}
	
}
