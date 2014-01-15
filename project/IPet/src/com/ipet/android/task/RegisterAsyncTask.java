package com.ipet.android.task;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.ipet.R;
import com.ipet.android.MyApp;
import com.ipet.android.ui.RegisterActivity;
import com.ipet.client.api.domain.IpetUser;

public class RegisterAsyncTask extends AsyncTask<Integer, Integer, Integer> {

    public final static int RESULT_SUCCESS = 0; // 成功
    public final static int RESULT_FAILURE_NETWORK = 1; // 网络错误
    public final static int RESULT_FAILURE_SAME_ACCOUNT = 2; // 邮箱被注册了
    public final static int RESULT_FAILURE_OTHER = 3; // 未知错误

    private final RegisterActivity activity;
    private ProgressDialog progress;
    private final String account;
    private final String password;
    private final IpetUser user;

    public RegisterAsyncTask(RegisterActivity activity) {
        this.activity = activity;
        account = activity.getAccount();
        password = activity.getPassword();
        user = activity.getUser();
    }

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
        Log.i("RegisterAsyncTask", "开始执行异步线程");
        String str = this.activity.getResources().getString(R.string.reg_loading);
        this.progress = ProgressDialog.show(this.activity, null, str);
    }

    @Override
    protected Integer doInBackground(Integer... params) {
        // TODO Auto-generated method stub
        int result = RESULT_FAILURE_OTHER;
        try {
            // 这里进行业务处理

            Log.v("REG", account + password + user.getDisplayName());

            MyApp application = (MyApp) this.activity.getApplication();
            IpetUser u = application.getApi().getAccountApi().register(account, password);
            application.setUser(u);
            result = RESULT_SUCCESS; // 操作成功

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(Integer result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        this.progress.hide();

        switch (result.intValue()) {
            case RESULT_SUCCESS: {
                this.activity.goMain();
                break;
            }
            case RESULT_FAILURE_SAME_ACCOUNT: {
                this.activity.showError(R.string.reg_failure_sameAccount);
                this.activity.reset();
                break;
            }
            case RESULT_FAILURE_OTHER: {
                this.activity.showError(R.string.reg_failure_other);
                break;
            }
        }
    }

}
