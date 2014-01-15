package com.ipet.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ipet.R;
import com.ipet.android.MyApp;

import com.ipet.android.ui.utils.AnimUtils;
import com.ipet.android.ui.utils.BitmapUtils;
import com.ipet.client.api.domain.IpetUser;
import com.loopj.android.image.SmartImageTask;
import com.loopj.android.image.SmartImageView;

public class MainMeFragment extends Fragment {

    private FragmentActivity activity;
    private SmartImageView avatar;
    private TextView me_name;
    private TextView me_feeds_num;
    private TextView me_followers_num;
    private TextView me_fans_num;
    private TextView me_friends_num;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_tab_me, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        this.activity = getActivity();

        avatar = (SmartImageView) this.activity.findViewById(R.id.me_avatar);

        // BitmapUtils.setRoundedCornerImageView((ImageView)
        // MainMeFragment.this.avatar);
        View info_layout = this.activity.findViewById(R.id.me_info_layout);
        info_layout.setOnClickListener(onLayoutClickListener);

        View fans_layout = this.activity.findViewById(R.id.me_fans_layout);
        fans_layout.setOnClickListener(onLayoutClickListener);

        View feeds_layout = this.activity.findViewById(R.id.me_feeds_layout);
        feeds_layout.setOnClickListener(onLayoutClickListener);

        View followers_layout = this.activity.findViewById(R.id.me_followers_layout);
        followers_layout.setOnClickListener(onLayoutClickListener);

        View friends_layout = this.activity.findViewById(R.id.me_friends_layout);
        friends_layout.setOnClickListener(onLayoutClickListener);

        me_name = (TextView) this.activity.findViewById(R.id.me_name);

        me_feeds_num = (TextView) this.activity.findViewById(R.id.me_feeds_num);

        me_followers_num = (TextView) this.activity.findViewById(R.id.me_followers_num);

        me_fans_num = (TextView) this.activity.findViewById(R.id.me_fans_num);

        me_friends_num = (TextView) this.activity.findViewById(R.id.me_friends_num);

    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        IpetUser user = ((MyApp) this.activity.getApplication()).getUser();
        String userImgURI = user.getAvatar32();
        avatar.setImageUrl(userImgURI, R.drawable.list_default_avatar_boy, new SmartImageTask.OnCompleteListener() {
            @Override
            public void onComplete() {
                // TODO Auto-generated method stub
                BitmapUtils.setRoundedCornerImageView(MainMeFragment.this.avatar);
            }
        });
        me_name.setText(user.getDisplayName());
        me_feeds_num.setText(user.getPhotoCount());
        me_followers_num.setText(user.getFollowerCount());
        me_fans_num.setText(user.getSubscibeCount());
        me_friends_num.setText(user.getFriendCount());
    }

    private final OnClickListener onLayoutClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.me_info_layout: {
                    Intent intent = new Intent(MainMeFragment.this.activity, SetUserInfoActivity.class);
                    startActivity(intent);
                    AnimUtils.pushLeftToRight(MainMeFragment.this.activity);
                    break;
                }

                case R.id.me_feeds_layout: {
                    Toast.makeText(activity, "Feeds", Toast.LENGTH_LONG).show();
                    break;
                }

                case R.id.me_fans_layout: {
                    Toast.makeText(activity, "Fans", Toast.LENGTH_LONG).show();
                    break;
                }

                case R.id.me_followers_layout: {
                    Toast.makeText(activity, "Followers", Toast.LENGTH_LONG).show();
                    break;
                }
                case R.id.me_friends_layout: {
                    Toast.makeText(activity, "Friends", Toast.LENGTH_LONG).show();
                    break;
                }

            }
        }

    };

}
