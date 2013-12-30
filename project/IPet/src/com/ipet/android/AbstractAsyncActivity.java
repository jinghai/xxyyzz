/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.android;

import android.app.Activity;
import android.app.ProgressDialog;

/**
 * 抽象异步Activity接口实现
 *
 * @author xiaojinghai
 */
public abstract class AbstractAsyncActivity extends Activity implements AsyncActivity {

    protected static final String TAG = AbstractAsyncActivity.class.getSimpleName();

    private ProgressDialog progressDialog;

    private boolean destroyed = false;

    @Override
    public MyApp getApplicationContext() {
        return (MyApp) super.getApplicationContext();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.destroyed = true;
    }

    public void showLoadingProgressDialog() {
        //todo 从本地资源获取
        this.showProgressDialog("Loading...");
    }

    public void showProgressDialog(CharSequence message) {
        if (this.progressDialog == null) {
            this.progressDialog = new ProgressDialog(this);
            this.progressDialog.setIndeterminate(true);
        }

        this.progressDialog.setMessage(message);
        this.progressDialog.show();
    }

    public void dismissProgressDialog() {
        if (this.progressDialog != null && !this.destroyed) {
            this.progressDialog.dismiss();
        }
    }

}
