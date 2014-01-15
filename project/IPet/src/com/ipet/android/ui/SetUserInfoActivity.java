package com.ipet.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.ipet.R;
import com.ipet.android.MyApp;
import com.ipet.android.ui.common.SimpleTitleBar;
import com.ipet.android.ui.event.BackAndFinishClick;
import com.ipet.android.ui.utils.AnimUtils;
import com.ipet.client.api.domain.IpetUser;

public class SetUserInfoActivity extends Activity {

    private TextView nickname = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_user_info);

        SimpleTitleBar titleBar = (SimpleTitleBar) findViewById(R.id.me_set_info_titlebar);
        titleBar.setLeftViewClick(new BackAndFinishClick(this));

        View nicknameView = this.findViewById(R.id.me_info_nickname_layout);
        nicknameView.setOnClickListener(onClickListener);

        nickname = (TextView) nicknameView.findViewById(R.id.nickname);

    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        IpetUser user = ((MyApp) this.getApplication()).getUser();
        nickname.setText(user.getDisplayName());
    }

    private final OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (v.getId()) {
                case R.id.me_info_nickname_layout: {
                    Intent intent = new Intent(SetUserInfoActivity.this, SetUserNickNameActivity.class);
                    startActivity(intent);
                    AnimUtils.pushLeftToRight(SetUserInfoActivity.this);
                    break;
                }
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AnimUtils.backAndFinish(this);
            return true;
        }
        return true;
    }

}
