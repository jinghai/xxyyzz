package com.ipet.android.task;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.ipet.R;
import com.ipet.android.app.MyApplication;
import com.ipet.android.sdk.domain.IpetPhoto;
import com.ipet.android.ui.PublishFeedActivity;
import com.ipet.android.ui.utils.MailUtils;

public class FeedAddAsyncTask extends AsyncTask<String, String, Integer> {

    public final static String TAG = "FeedAddAsyncTask";
    public final static int RESULT_SUCCESS = 0;
    public final static int RESULT_FAILURE = 1;

    private final String path;
    private final PublishFeedActivity activity;
    private String str;
    private ProgressDialog progress;
    private IpetPhoto ipetPhoto = null;

    public FeedAddAsyncTask(PublishFeedActivity activity, String path, String str) {
        this.path = path;
        this.activity = activity;
        this.str = str;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        String str = this.activity.getResources().getString(R.string.publish_loading);
        this.progress = ProgressDialog.show(this.activity, null, str);
    }

    @Override
    protected Integer doInBackground(String... params) {
        // TODO Auto-generated method stub
        int result = RESULT_FAILURE;
        MyApplication application = (MyApplication) this.activity.getApplication();
        try {
            ipetPhoto = application.getApi().getPhotoApi().publish("", path);
            Log.i("publish", "id" + ipetPhoto.getId());
            ipetPhoto = application.getApi().getPhotoApi().publishText(ipetPhoto.getId(), str);
            result = RESULT_SUCCESS;
        } catch (Exception e) {
            String[] receivers = new String[]{"kongchun@ipetty.net", "wangweihua@ipetty.net", "luocanfeng@ipetty.net", "xiaojinghai@ipetty.net"};
            String subject = "[发图功能异常]-爱宠安卓";
            String body = e.getMessage();
            MailUtils.sendTextMail("service@ipetty.net", "dev@ipetty.net", subject, body, receivers);

            e.printStackTrace();
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        // application.getApi().getCommentApi().comment(ipetPhoto.getId(), str);
        this.progress.dismiss();
        return result;
    }

    @Override
    protected void onPostExecute(Integer result) {
        if (RESULT_FAILURE == result.intValue()) {
            Toast.makeText(this.activity, "发布失败", Toast.LENGTH_LONG).show();
            return;
        }
        this.activity.backAndfinish();
    }
}
