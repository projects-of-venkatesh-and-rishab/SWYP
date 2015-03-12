package com.example.searchinn;

import java.io.File;
import java.util.ArrayList;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Contacts.People;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyBaseAdapter extends BaseAdapter {

	Context context;
	ArrayList<MyFile> list;

	public MyBaseAdapter(Context context, ArrayList<MyFile> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		TextView tv;

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.row, null);

			tv = (TextView) convertView.findViewById(R.id.textView);

			convertView.setTag(tv);
		} else
			tv = (TextView) convertView.getTag();
		if (tv != null)
			tv.setText(list.get(position).getNameWithoutExtension());

		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String extension = list.get(position).getExtension();

				if (extension.equals("cts")) {
					Uri contactUri = ContentUris.withAppendedId(
							People.CONTENT_URI, list.get(position).getId());
					Intent i = new Intent(Intent.ACTION_VIEW, contactUri);

					context.startActivity(i);
				} else {
					File file = list.get(position).getFile();

					Intent intent = new Intent(Intent.ACTION_VIEW);
					Uri data = Uri.fromFile(file);

					String type = null;

					if (extension != null) {
						MimeTypeMap mime = MimeTypeMap.getSingleton();
						type = mime.getMimeTypeFromExtension(extension);
					}

					intent.setDataAndType(data, type);

					Intent j = Intent.createChooser(intent,
							"Choose an application to open with:");
					context.startActivity(j);
				}
			}
		});
		return convertView;
	}

}
