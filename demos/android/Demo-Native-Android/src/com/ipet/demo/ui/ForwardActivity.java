/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.demo.ui;

import android.app.Activity;
import android.os.Bundle;

/**
 *
 * @author xiaojinghai
 */
public class ForwardActivity extends Activity {

    private static final String TAG = ForwardActivity.class.getName();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // ToDo add your GUI initialization code here
    }

    private void backAndFinish() {
        this.finish();
    }

}
