package com.ipet.android.app;

/**
 * 抽象异步Activity接口 增加一些统用方法
 * 
 * @author xiaojinghai
 */
public interface AsyncActivity {

	public MyApplication getApplicationContext();

	public void showLoadingProgressDialog();

	public void showProgressDialog(CharSequence message);

	public void dismissProgressDialog();

}
