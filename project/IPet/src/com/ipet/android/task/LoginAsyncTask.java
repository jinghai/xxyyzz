package com.ipet.android.task;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.ipet.R;
import com.ipet.android.MyApp;
import com.ipet.android.ui.LoginActivity;
import com.ipet.android.sdk.domain.IpetUser;

public class LoginAsyncTask extends AsyncTask<Integer, Integer, Integer> {

    public final static int RESULT_SUCCESS = 0;
    public final static int RESULT_FAILURE_NETWORK = 1;
    public final static int RESULT_FAILURE_AUTH = 2;
    public final static int RESULT_FAILURE_OTHER = 3;

    private LoginActivity activity;
    private String account;
    private String password;
    private ProgressDialog progress;

    public LoginAsyncTask(LoginActivity activity, String account, String password) {
        this.activity = activity;
        this.account = account;
        this.password = password;
    }

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
        Log.i("LoginAsyncTask", "开始执行异步线程");
        String str = this.activity.getResources().getString(R.string.login_loading);
        this.progress = ProgressDialog.show(this.activity, null, str);
    }

    @Override
    protected Integer doInBackground(Integer... params) {
        // TODO Auto-generated method stub
        int result = RESULT_FAILURE_OTHER;
        try {
            MyApp application = (MyApp) this.activity.getApplication();
            IpetUser u = application.getApi().getAccountApi().login(account, password);
            result = RESULT_SUCCESS;
            /*	
             Thread.sleep(1000);
			
             if(this.account.equals("admin") && this.password.equals("888")){
             result = RESULT_SUCCESS;
             }else{
             result = RESULT_FAILURE_AUTH;
             }
             */

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.progress.dismiss();
        return result;
    }

    @Override
    protected void onPostExecute(Integer result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);

        switch (result.intValue()) {
            case RESULT_SUCCESS: {
                this.activity.goMain();
                break;
            }
            case RESULT_FAILURE_AUTH: {
                this.activity.showError(R.string.login_failure_auth);
                break;
            }
            case RESULT_FAILURE_OTHER: {
                this.activity.showError(R.string.login_failure_other);
                break;
            }

        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        // TODO Auto-generated method stub
        super.onProgressUpdate(values);

    }

}
