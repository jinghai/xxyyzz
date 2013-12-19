/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.demo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.ipet.demo.R;

/**
 *
 * @author xiaojinghai
 */
public class IndexActivity extends Activity {

    private static final String TAG = IndexActivity.class.getName();

    private Button lifecycleBtn;

    private Button forwardBtn;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        Log.d(TAG, TAG + ":onCreate");
        super.onCreate(icicle);
        this.setContentView(R.layout.ui_index);
        this.initView();
        this.intListener();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, TAG + ":onDestroy");
        super.onDestroy();

    }

    private void initView() {
        this.lifecycleBtn = (Button) findViewById(R.id.ui_index_lifecycleBtn);
        this.forwardBtn = (Button) findViewById(R.id.ui_index_forwardBtn);
    }

    private void intListener() {
        this.lifecycleBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                forward(LifecycleActivity.class);
            }
        });
        this.forwardBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                forward(ForwardActivity.class);
            }
        });
    }

    private void forward(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

}
