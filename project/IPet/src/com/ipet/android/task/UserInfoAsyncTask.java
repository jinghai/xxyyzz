package com.ipet.android.task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.ipet.R;
import com.ipet.android.app.MyApplication;
import com.ipet.android.sdk.domain.IpetUser;
import com.ipet.android.sdk.domain.IpetUserUpdate;

public class UserInfoAsyncTask extends AsyncTask<String, String, Integer> {

    public final static String TAG = "UserInfoAsyncTask";
    public final static int RESULT_SUCCESS = 0;
    public final static int RESULT_FAILURE = 1;

    private final Activity activity;
    private ProgressDialog progress;
    private final IpetUserUpdate userUpdate;
    private IpetUser ipetUser;

    public UserInfoAsyncTask(Activity activity, IpetUserUpdate userUpdate) {
        this.activity = activity;
        this.userUpdate = userUpdate;
    }

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
        String str = this.activity.getResources().getString(R.string.save_loading);
        this.progress = ProgressDialog.show(this.activity, null, str);
    }

    @Override
    protected Integer doInBackground(String... params) {
        // TODO Auto-generated method stub
        int result = RESULT_FAILURE;
        try {
            MyApplication application = (MyApplication) this.activity.getApplication();
            ipetUser = application.getApi().getUserApi().updateUserInfo(userUpdate);
            result = RESULT_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        this.progress.dismiss();
        return result;
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);
        if (RESULT_FAILURE == result.intValue()) {
            Toast.makeText(this.activity, "更新失败", Toast.LENGTH_SHORT).show();
            return;
        }

        MyApplication application = (MyApplication) this.activity.getApplication();
        //application.setUser(ipetUser);
        this.activity.finish();
    }
}
