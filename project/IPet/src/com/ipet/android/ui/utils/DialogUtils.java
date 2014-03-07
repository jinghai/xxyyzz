package com.ipet.android.ui.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ipet.R;

public class DialogUtils {

	public static Dialog bottomPopupDialog(Context context, View.OnClickListener[] listeners, int id, String title, Dialog d) {

		if (d != null && d.isShowing()) {
			return d;
		}

		final Dialog dialog = new Dialog(context, R.style.ttDialog);

		Window window = dialog.getWindow();
		WindowManager.LayoutParams params = new WindowManager.LayoutParams();
		int[] wh = DeviceUtils.terminalWH(context);
		params.x = 0;
		params.y = wh[1];

		window.setAttributes(params);
		window.setBackgroundDrawableResource(R.drawable.alert_dialog_background);

		View itemView = LayoutInflater.from(context).inflate(R.layout.popupwindow_view2, null);
		dialog.setContentView(itemView);
		dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		dialog.setCancelable(true);
		dialog.setCanceledOnTouchOutside(true);

		TextView item1 = (TextView) itemView.findViewById(R.id.item1);
		TextView item2 = (TextView) itemView.findViewById(R.id.item2);
		TextView item3 = (TextView) itemView.findViewById(R.id.item3);
		TextView item4 = (TextView) itemView.findViewById(R.id.item4);
		TextView item5 = (TextView) itemView.findViewById(R.id.item5);
		TextView titleView = (TextView) itemView.findViewById(R.id.title);
		String itemValues[] = context.getResources().getStringArray(id);
		TextView[] items = new TextView[] { item1, item2, item3, item4, item5 };

		for (int i = 0; i < items.length; i++) {
			if (i < listeners.length) {
				items[i].setText(itemValues[i]);
				items[i].setOnClickListener(listeners[i]);
			} else {
				items[i].setVisibility(View.GONE);
			}
		}

		if (title == null) {
			titleView.setVisibility(View.GONE);
		} else {
			titleView.setText(title);
		}

		TextView cancelView = (TextView) itemView.findViewById(R.id.cancel);// ȡ��
		cancelView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.cancel();
			}
		});

		if (dialog != null) {
			dialog.show();
		}

		return dialog;

	}

}
