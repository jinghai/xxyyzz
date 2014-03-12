package com.ipet.android.task;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.ipet.android.MyApp;
import com.ipet.android.sdk.domain.IpetPhoto;
import com.ipet.android.sdk.domain.IpetUser;
import com.ipet.android.ui.MainHomeFragment;

public class FeedAddAsyncTask extends AsyncTask<String, String, IpetPhoto> {

    private final Uri uri;
    private final MainHomeFragment fragment;
    private final File picture;

    public FeedAddAsyncTask(MainHomeFragment fragment, File picture, Uri uri) {
        this.uri = uri;
        this.fragment = fragment;
        this.picture = picture;
    }

    @Override
    protected IpetPhoto doInBackground(String... params) {
        // TODO Auto-generated method stub
        Log.i("uploadFile", "pictrue-->" + picture);
        IpetPhoto ipetPhoto = null;
        try {
            Thread.sleep(500);
            MyApp application = (MyApp) this.fragment.getActivity().getApplication();
            IpetUser user = application.getUser();
            String path = picture.getPath();
            Log.i("uploadFile", "path-->" + picture);
            ipetPhoto = application.getApi().getPhotoApi().publish("", path);
            Log.i("uploadFile", "SmallURL-->" + ipetPhoto.getSmallURL());
            //ipetPhoto.setAvatar48(user.getAvatar48());
            //ipetPhoto.setUserName(user.getDisplayName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ipetPhoto;
    }

    @Override
    protected void onPostExecute(IpetPhoto ipetPhoto) {
        if (ipetPhoto == null) {
            return;
        }

        List<IpetPhoto> list = new ArrayList<IpetPhoto>(0);
        list.add(ipetPhoto);
        this.fragment.getAdapter().prependList(list);
    }
}
