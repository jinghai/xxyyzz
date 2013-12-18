package com.ipet.android.ui.adapter;


import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ipet.R;
import com.ipet.android.ui.utils.StringUtils;
import com.ipet.android.vo.ConversationMessage;
import com.loopj.android.image.SmartImageView;

public class ListConversationAdapter extends BaseAdapter {
	private List<ConversationMessage> list;
	private LayoutInflater inflater;
	private ListView listView;
	private Context context;
	public ListConversationAdapter(Context context,ListView listView,List<ConversationMessage> list) {
		this.context = context;
		this.list = list;
		this.listView = listView;
		this.inflater = LayoutInflater.from(context);
		initEvent();
	}

	private void initEvent() {
		listView.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> view, View itemView, int position, long id) {
				// TODO Auto-generated method stub
				String text = list.get(position).getName();
				ListConversationAdapter.this.clearNum(position);
				Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
			};
			
		});
		
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list == null ? 0 : list.size();
	}

	@Override
	public Object getItem(int positon) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
	
	public class ViewHolder {
		public TextView name;
		public SmartImageView avatar;
		public TextView content;
		public TextView created_at;
		public TextView new_msg_num;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder holder;
		if (view == null) {
			holder = new ViewHolder();  
			view = inflater.inflate(R.layout.list_conversation_item, null);
			holder.name = (TextView) view.findViewById(R.id.conversation_name);
			holder.avatar = (SmartImageView) view.findViewById(R.id.conversation_avatar);
			holder.content = (TextView) view.findViewById(R.id.conversation_content);
			holder.created_at = (TextView) view.findViewById(R.id.date_time);
			holder.new_msg_num = (TextView) view.findViewById(R.id.new_msg_num);
			view.setTag(holder);
		}else{
			holder = (ViewHolder) view.getTag();
		}
		
		ConversationMessage msg = list.get(position);
		String num = msg.getNew_msg_num();
		
		if(!StringUtils.isEmpty(num) && Integer.valueOf(msg.getNew_msg_num())> 0){
			holder.new_msg_num.setText(msg.getNew_msg_num());
			holder.new_msg_num.setVisibility(View.VISIBLE);
		}else{
			holder.new_msg_num.setVisibility(View.GONE);
		}
		
		String url = msg.getAvatar();
		if(!StringUtils.isEmpty(url)){
			holder.avatar.setImageUrl(url);
		}		
		
		
		holder.name.setText(msg.getName());
		holder.content.setText(msg.getContent());
		holder.created_at.setText(msg.getCreated_at());
		return view;
	}
	
	public void clearNum(int position){
		this.list.get(position).setNew_msg_num("0");
		this.notifyDataSetChanged();	
	}
	
	public void prependList(List<ConversationMessage> list){
		this.list.addAll(0,list);
		this.notifyDataSetChanged();
	}
	
	public void appendList(List<ConversationMessage> list){
		this.list.addAll(list);
		this.notifyDataSetChanged();
	}

}
