package com.ipet.android.ui;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.ipet.R;
import com.ipet.android.task.FeedbackAsyncTask;
import com.ipet.android.ui.utils.StringUtils;

public class FeedbackActivity extends Activity {
	private View feedback_btn;
	private EditText edit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);

		feedback_btn = this.findViewById(R.id.feedback_btn);
		edit = (EditText) this.findViewById(R.id.editText);

		feedback_btn.setOnClickListener(sumbit);
	}

	private final OnClickListener sumbit = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Editable text = FeedbackActivity.this.edit.getText();
			String str = text.toString();
			if (StringUtils.isEmpty(str)) {
				Toast.makeText(FeedbackActivity.this, R.string.feedback_no_empty, Toast.LENGTH_SHORT).show();
				return;
			}

			new FeedbackAsyncTask(FeedbackActivity.this).execute(str);
		}

	};
}
