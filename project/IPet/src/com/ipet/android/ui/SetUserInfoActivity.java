package com.ipet.android.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.ipet.R;
import com.ipet.android.sdk.core.IpetApi;
import com.ipet.android.sdk.domain.IpetUser;
import com.ipet.android.task.UploadAvatorAsyncTask;
import com.ipet.android.ui.common.SimpleTitleBar;
import com.ipet.android.ui.event.BackAndFinishClick;
import com.ipet.android.ui.utils.DeviceUtils;
import com.ipet.android.ui.utils.DialogUtils;
import com.ipet.android.ui.utils.PathUtils;
import com.loopj.android.image.SmartImageView;
import java.io.File;

public class SetUserInfoActivity extends Activity {

    public final static String TAG = "SetUserInfoActivity";
    private static final int REQUEST_CODE_PHOTORESOULT = 20;

    private SmartImageView imageView;
    private TextView nickname = null;
    private TextView account = null;
    private IpetUser user;
    private Dialog dialog;
    private String mImageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_user_info);

        SimpleTitleBar titleBar = (SimpleTitleBar) findViewById(R.id.me_set_info_titlebar);
        titleBar.setLeftViewClick(new BackAndFinishClick(this));

        View nicknameView = this.findViewById(R.id.me_info_nickname_layout);
        nicknameView.setOnClickListener(onClickListener);

        View uploadAvatorView = this.findViewById(R.id.me_info_avator_layout);
        uploadAvatorView.setOnClickListener(onClickListener);

        nickname = (TextView) nicknameView.findViewById(R.id.nickname);
        account = (TextView) this.findViewById(R.id.account);

    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        IpetApi api = IpetApi.init(this);
        user = api.getUserApi().getUser(api.getCurrUserId());

        imageView = (SmartImageView) this.findViewById(R.id.imageMyHead);
        imageView.setImageUrl(user.getAvatar48(), R.drawable.list_default_avatar_boy);

        Log.i(TAG, user.getAvatar48());

        account.setText(user.getLoginName());
        nickname.setText(user.getDisplayName());
    }

    private final OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (v.getId()) {

                case R.id.me_info_avator_layout: {
                    showCameraDialog(v);
                    break;
                }
                case R.id.me_info_nickname_layout: {
                    Intent intent = new Intent(SetUserInfoActivity.this, SetUserNickNameActivity.class);
                    startActivity(intent);
                    break;
                }
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return true;
    }

    public void showCameraDialog(View v) {
        OnClickListener[] Listener = new OnClickListener[]{takePhotoClick, pickPhotoClick};
        this.dialog = DialogUtils.bottomPopupDialog(this, Listener, R.array.alert_camera, getString(R.string.camera_title), this.dialog);
    }

    private final OnClickListener takePhotoClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            mImageName = "head.jpg";
            DeviceUtils.takePicture(SetUserInfoActivity.this, PathUtils.getCarmerDir(), SetUserInfoActivity.this.mImageName);
            SetUserInfoActivity.this.dialog.cancel();
        }
    };

    private final OnClickListener pickPhotoClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            mImageName = "head.jpg";
            DeviceUtils.chooserSysPics(SetUserInfoActivity.this);
            SetUserInfoActivity.this.dialog.cancel();
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("Photo", "finish");
        Log.i("Photo", "requestCode" + requestCode);
        Log.i("Photo", "resultCode" + resultCode);

        String path = PathUtils.getCarmerDir() + this.mImageName;
        File picture = new File(path);
        Uri pathUri = Uri.fromFile(picture);

        if (requestCode == DeviceUtils.REQUEST_CODE_PICK_IMAGE) {
            if (resultCode == FragmentActivity.RESULT_OK) {
                Uri uri = data.getData();
                Log.i("Photo", "finish" + uri);
                startPhotoZoom(uri, pathUri);
            }
        }

        if (requestCode == DeviceUtils.REQUEST_CODE_TAKE_IMAGE) {
            if (resultCode == FragmentActivity.RESULT_OK) {
                Log.i("Photo", "finish" + picture);
                startPhotoZoom(Uri.fromFile(picture), pathUri);
            }
        }

        if (requestCode == REQUEST_CODE_PHOTORESOULT) {
            Log.i("Photo", "crop" + pathUri);
            new UploadAvatorAsyncTask(this, picture, pathUri).execute();
        }

    }

    public void startPhotoZoom(Uri uri, Uri photoUri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 480);
        intent.putExtra("outputY", 480);
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("return-data", false);
        startActivityForResult(intent, REQUEST_CODE_PHOTORESOULT);
    }

    public void uploadFinish() {
        // TODO Auto-generated method stub
        IpetApi api = IpetApi.init(this);
        user = api.getUserApi().getUser(api.getCurrUserId());
        imageView.setImageUrl(user.getAvatar48(), R.drawable.list_default_avatar_boy);
    }

}
