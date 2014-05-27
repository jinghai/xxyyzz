package com.ipet.android.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import com.ipet.R;
import com.ipet.android.app.MyApplication;
import com.ipet.android.sdk.core.IpetApi;
import com.ipet.android.sdk.domain.IpetUser;
import com.ipet.android.sdk.domain.IpetUserUpdate;
import com.ipet.android.task.UserInfoAsyncTask;
import com.ipet.android.ui.common.SimpleTitleBar;
import com.ipet.android.ui.event.BackAndFinishClick;
import com.ipet.android.ui.utils.StringUtils;

public class SetUserNickNameActivity extends Activity {

    private EditText nicknameView = null;
    private MyApplication application = null;
    private IpetUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_user_nick_name);

        application = (MyApplication) this.getApplication();

        SimpleTitleBar titleBar = (SimpleTitleBar) findViewById(R.id.me_set_info_nickname_titlebar);
        titleBar.setLeftViewClick(new BackAndFinishClick(this));

        titleBar.setRightViewClick(saveClick);

        nicknameView = (EditText) this.findViewById(R.id.nickname_edit);

    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        IpetApi api = IpetApi.init(this);
        user = api.getUserApi().getUser(api.getCurrUserId());
    }

    private final OnClickListener saveClick = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            String nickname = nicknameView.getText().toString();

            if (StringUtils.isEmpty(nickname)) {
                showError(R.string.nickname_empty);
                nicknameView.requestFocus();
                return;
            }

            IpetUserUpdate updateUser = new IpetUserUpdate();
            updateUser.setId(user.getId());
            updateUser.setDisplayName(nickname);
            updateUser.setEmail(user.getEmail());
            updateUser.setPhone(user.getPhone());
            new UserInfoAsyncTask(SetUserNickNameActivity.this, updateUser).execute();

        }
    };

    public void showError(int resId) {
		// TODO Auto-generated method stub
        // Toast.makeText(this, resId, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return true;
    }

}
