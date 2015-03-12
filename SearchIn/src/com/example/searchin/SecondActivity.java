package com.example.searchin;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ListView;

public class SecondActivity extends Activity {

	private ListView listview;
	private ArrayList<MyFile> list;
	private ArrayList<MyFile> secondlist;
	private String identifier;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		identifier = getIntent().getStringExtra("MainActivity");
		listview = (ListView) findViewById(R.id.listView1);
		final EditText et = (EditText) findViewById(R.id.editText1);

		if (identifier.equals("Application"))
			list = LoadingScreenActivity.applicationTree.toFileList();
		else if (identifier.equals("Audio"))
			list = LoadingScreenActivity.audioTree.toFileList();
		else if (identifier.equals("Documents"))
			list = LoadingScreenActivity.documentsTree.toFileList();
		else if (identifier.equals("Compressed"))
			list = LoadingScreenActivity.compressedTree.toFileList();
		else if (identifier.equals("Contact"))
			list = LoadingScreenActivity.contactTree.toFileList();
		else if (identifier.equals("Image"))
			list = LoadingScreenActivity.imageTree.toFileList();
		else if (identifier.equals("Other"))
			list = LoadingScreenActivity.otherTree.toFileList();
		else if (identifier.equals("Video"))
			list = LoadingScreenActivity.videoTree.toFileList();
		else
			list = new ArrayList<MyFile>();

		
		MyBaseAdapter adapter = new MyBaseAdapter(this, list);
		listview.setAdapter(adapter);
		
		et.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				String searchString = et.getText().toString();
				
				if (identifier.equals("Application"))
					secondlist = LoadingScreenActivity.applicationTree.search(searchString);
				else if (identifier.equals("Audio"))
					secondlist = LoadingScreenActivity.audioTree.search(searchString);
				else if (identifier.equals("Documents"))
					secondlist = LoadingScreenActivity.documentsTree.search(searchString);
				else if (identifier.equals("Compressed"))
					secondlist = LoadingScreenActivity.compressedTree.search(searchString);
				else if (identifier.equals("Contact"))
					secondlist = LoadingScreenActivity.contactTree.search(searchString);
				else if (identifier.equals("Image"))
					secondlist = LoadingScreenActivity.imageTree.search(searchString);
				else if (identifier.equals("Other"))
					secondlist = LoadingScreenActivity.otherTree.search(searchString);
				else if (identifier.equals("Video"))
					secondlist = LoadingScreenActivity.videoTree.search(searchString);
				else
					secondlist = new ArrayList<MyFile>();
				
				if(searchString.equals(""))
					secondlist = list;
				
				MyBaseAdapter adapter = new MyBaseAdapter(SecondActivity.this, secondlist);
				listview.setAdapter(adapter);
				
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		// MenuItem searchItem = menu.findItem(R.id.action_search);
		// SearchView searchView = (SearchView)
		// menu.findItem(R.id.action_search)
		// .getActionView();
		return super.onCreateOptionsMenu(menu);
	}
}
