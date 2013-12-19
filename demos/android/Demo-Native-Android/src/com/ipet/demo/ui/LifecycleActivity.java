/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.demo.ui;

import android.app.Activity;
import static android.app.Activity.RESULT_OK;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.ipet.demo.R;

/**
 *
 * @author xiaojinghai
 */
public class LifecycleActivity extends Activity {

    private static final String TAG = LifecycleActivity.class.getName();

    private TextView messageText;

    private Button showMsgBtn;

    /**
     * 第一次被创建时调用 一般用于初始化视图，绑定静态资源，创建连接资源等，
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, TAG + ":onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_lifecycle);
        messageText = (TextView) findViewById(R.id.ui_lifecycle_messageText);
        messageText.append(TAG + ":onCreate\n");
        showMsgBtn = (Button) findViewById(R.id.ui_lifecycle_showMsgBtn);
        showMsgBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "测试", Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * 使用navigates停止之后，启动之前调用。
     */
    @Override
    protected void onRestart() {
        Log.d(TAG, TAG + ":onRestart");
        messageText.append(TAG + ":onRestart\n");
        super.onRestart();

    }

    /**
     * 即将可见
     */
    @Override
    protected void onStart() {
        Log.d(TAG, TAG + ":onStart");
        messageText.append(TAG + ":onStart\n");
        super.onStart();

    }

    /**
     * 获得焦点，此时可见。
     */
    @Override
    protected void onResume() {
        Log.d(TAG, TAG + ":onResume");
        messageText.append(TAG + ":onResume\n");
        super.onResume();

    }

    /**
     * 失去焦点，此时可见。例如弹出对话框，或屏幕关闭
     */
    @Override
    protected void onPause() {
        Log.d(TAG, TAG + ":onPause");
        messageText.append(TAG + ":onPause\n");
        super.onPause();

    }

    /**
     * 户不再可见时调用
     */
    @Override
    protected void onStop() {
        Log.d(TAG, TAG + ":onStop");
        messageText.append(TAG + ":onStop\n");
        super.onStop();

    }

    /**
     * 销毁Activity时候调用，一般做回收资源
     */
    @Override
    protected void onDestroy() {
        Log.d(TAG, TAG + ":onDestroy");
        super.onDestroy();

    }

}
