package com.ipet.android.task;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.ipet.R;
import com.ipet.android.MyApp;
import com.ipet.android.sdk.domain.IpetPhoto;
import com.ipet.android.ui.PublishFeedActivity;
import com.ipet.android.ui.utils.MailUtils;

public class FeedAddAsyncTask extends AsyncTask<String, String, IpetPhoto> {

    private final String path;
    private final PublishFeedActivity activity;
    private String str;
    private ProgressDialog progress;

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
    protected IpetPhoto doInBackground(String... params) {
        // TODO Auto-generated method stub

        Log.i("uploadFile", "pictrue-->" + path);
        IpetPhoto ipetPhoto = null;
        MyApp application = (MyApp) this.activity.getApplication();
        try {
            ipetPhoto = application.getApi().getPhotoApi().publish("", path);
            Log.i("publish", "id" + ipetPhoto.getId());
            ipetPhoto = application.getApi().getPhotoApi().publishText(ipetPhoto.getId(), str);
        } catch (Exception e) {
            String[] receivers = new String[]{"kongchun@ipetty.net", "wangweihua@ipetty.net", "luocanfeng@ipetty.net", "xiaojinghai@ipetty.net"};
            String subject = "[发图功能异常]-爱宠安卓";
            String body = e.getMessage();
            MailUtils.sendTextMail("service@ipetty.net", "service@ipetty.net", subject, body, receivers);
        }
        // application.getApi().getCommentApi().comment(ipetPhoto.getId(), str);
        this.progress.dismiss();
        return ipetPhoto;
    }

    @Override
    protected void onPostExecute(IpetPhoto ipetPhoto) {
        if (ipetPhoto == null) {
            Toast.makeText(this.activity, "发布失败", Toast.LENGTH_LONG).show();
            return;
        }

        this.activity.backAndfinish();
    }
}
