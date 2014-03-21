package com.ipet.android.ui.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ipet.R;
import com.ipet.android.Constant;

public class SimpleTitleBar extends LinearLayout {

	private ImageView leftImageBtn = null;
	private ImageView rightImageBtn = null;
	private TextView leftTextBtn = null;
	private TextView rightTextBtn = null;
	private TextView title = null;

	private int resLeftType = Constant.TITLE_TYPE_IMAGE;
	private int resRightType = Constant.TITLE_TYPE_IMAGE;
	
	public SimpleTitleBar(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public SimpleTitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// 加载自定义的layout
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// LayoutInflater inflater = getLayoutInflater();
		inflater.inflate(R.layout.title_bar_simple, this);
		this.parseAttr(context, attrs);
	}

	private void parseAttr(Context context, AttributeSet attrs) {
		TypedArray type = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);

		this.setCenterContainer(type);
		this.setLeftContainer(type);
		this.setRightContainer(type);

		type.recycle();
	}

	private void setCenterContainer(TypedArray type) {
		// 设置标题
		title = (TextView) findViewById(R.id.titlebar_title);
		title.setText(type.getString(R.styleable.TitleBar_title));

	}

	// left区域
	private void setLeftContainer(TypedArray type) {
		int visibilty = type.getInt(R.styleable.TitleBar_title_left_res_visibility, Constant.TITLE_VISIBILITY_GONE);
		FrameLayout leftContainer = (FrameLayout) findViewById(R.id.titlebar_left_container);
		this.setVisibility(leftContainer, visibilty);

		this.resLeftType = type.getInt(R.styleable.TitleBar_title_left_res_type, Constant.TITLE_TYPE_IMAGE);
		this.setLeftImageOrText(type, resLeftType);

	}

	// 设置是图标还是文字按钮
	private void setLeftImageOrText(TypedArray type, int resType) {
		RelativeLayout leftImage = (RelativeLayout) findViewById(R.id.titlebar_left_image_container);
		RelativeLayout leftText = (RelativeLayout) findViewById(R.id.titlebar_left_text_container);
		if (Constant.TITLE_TYPE_IMAGE == (resType)) {
			leftImage.setVisibility(VISIBLE);
			leftText.setVisibility(GONE);
			leftImageBtn = this.setImageBtn(type, R.id.titlebar_left_btn, R.styleable.TitleBar_title_left_image, R.drawable.common_tb_back);
		} else {
			leftImage.setVisibility(GONE);
			leftText.setVisibility(VISIBLE);
			// TODO 预留实现
		}
	}

	// 设置图标
	private ImageView setImageBtn(TypedArray type, int resId, int styleableAttrId, int imgResId) {
		ImageView imageBtn = (ImageView) findViewById(resId);
		int attrImage = type.getResourceId(styleableAttrId, imgResId);
		imageBtn.setImageResource(attrImage);
		return imageBtn;
	}

	private void setRightContainer(TypedArray type) {
		int visibilty = type.getInt(R.styleable.TitleBar_title_right_res_visibility, Constant.TITLE_VISIBILITY_GONE);
		FrameLayout leftContainer = (FrameLayout) findViewById(R.id.titlebar_right_container);
		this.setVisibility(leftContainer, visibilty);
		this.resRightType = type.getInt(R.styleable.TitleBar_title_right_res_type, Constant.TITLE_TYPE_IMAGE);
		this.setRightImageOrText(type, resRightType);

	}

	private void setRightImageOrText(TypedArray type, int resType) {
		RelativeLayout rightImage = (RelativeLayout) findViewById(R.id.titlebar_right_image_container);
		RelativeLayout rightText = (RelativeLayout) findViewById(R.id.titlebar_right_text_container);
		if (Constant.TITLE_TYPE_IMAGE == (resType)) {
			rightImage.setVisibility(VISIBLE);
			rightText.setVisibility(GONE);
			rightImageBtn = this.setImageBtn(type, R.id.titlebar_right_btn, R.styleable.TitleBar_title_right_image, R.drawable.actionbar_main_menu_selector);
		} else {
			rightImage.setVisibility(GONE);
			rightText.setVisibility(VISIBLE);
			// TODO:预留实现
			rightTextBtn = (TextView) findViewById(R.id.titlebar_right_text);
			String str = type.getString(R.styleable.TitleBar_title_right_text);
			rightTextBtn.setText(str);
		}

	}

	// 设置整体区域是否可见
	private void setVisibility(FrameLayout container, int visibilty) {
		switch (visibilty) {
		case Constant.TITLE_VISIBILITY_VISIBLE: {
			container.setVisibility(VISIBLE);
			break;
		}
		case Constant.TITLE_VISIBILITY_INVISIBLE: {
			container.setVisibility(INVISIBLE);
			break;
		}
		case Constant.TITLE_VISIBILITY_GONE: {
			container.setVisibility(GONE);
			break;
		}
		}
	}

	public View getLeftView() {
		if (Constant.TITLE_TYPE_IMAGE == resLeftType) {
			return this.leftImageBtn;
		}
		return null;
	}

	// 设置名称与返回按钮界面
	public void setLeftViewClick(OnClickListener l) {
		this.getLeftView().setOnClickListener(l);
	}

	public View getRightView() {
		if (Constant.TITLE_TYPE_IMAGE == (resRightType)) {
			return this.rightImageBtn;
		} else {
			return this.rightTextBtn;
		}

	}

	// 设置名称与返回按钮界面
	public void setRightViewClick(OnClickListener l) {
		this.getRightView().setOnClickListener(l);
	}

}
