package com.ipet.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import com.ipet.demo.ui.IndexActivity;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getName();

    private Button uiBtn;
    private Button dbBtn;
    private Button fileBtn;
    private Button exitBtn;

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, TAG + ":onCreate");
        super.onCreate(savedInstanceState);
        this.initView();
        this.intListener();

    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, TAG + ":onDestroy");
        super.onDestroy();

    }

    private void initView() {
        this.setContentView(R.layout.main);
        this.uiBtn = (Button) findViewById(R.id.main_uiIndexBtn);
        this.dbBtn = (Button) findViewById(R.id.main_dbIndexBtn);
        this.fileBtn = (Button) findViewById(R.id.main_fileIndexBtn);
        this.exitBtn = (Button) findViewById(R.id.main_exitBtn);
    }

    private void intListener() {
        uiBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                forward(IndexActivity.class);
            }
        });

        dbBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                markText("暂未实现");
            }
        });

        fileBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                markText("暂未实现");
            }
        });

        exitBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Log.d(TAG, TAG + ":Exit OnClick");
                System.exit(RESULT_OK);
            }
        });

    }

    private void forward(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    private void markText(String str) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }
}
