package com.ipet.android.ui;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ipet.R;
import com.ipet.android.ui.utils.BitmapUtils;

public class MainMeFragment extends Fragment {
    private FragmentActivity activity;
    private ImageView avatar;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_tab_me, container, false);
    }
    
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		this.activity = getActivity();
		
		avatar = (ImageView) this.activity.findViewById(R.id.me_avatar);
		BitmapUtils.setRoundedCornerImageView((ImageView)avatar);
	}
    
    

    
    
}
