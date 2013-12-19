/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.demo.db;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 *
 * @author xiaojinghai
 */
public class IndexActivity extends Activity {

    private static final String TAG = IndexActivity.class.getName();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        Log.d(TAG, TAG + ":onCreate");
        super.onCreate(icicle);
        // ToDo add your GUI initialization code here
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, TAG + ":onDestroy");
        super.onDestroy();

    }
}
