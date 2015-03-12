package com.example.searchin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity extends Activity {
	public static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		

		ImageButton ibApplication = (ImageButton) findViewById(R.id.imageButton_applications);
		ImageButton ibAudio = (ImageButton) findViewById(R.id.imageButton_music);
		ImageButton ibCompressed = (ImageButton) findViewById(R.id.imageButton_compressed);
		ImageButton ibContact = (ImageButton) findViewById(R.id.imageButton_contacts);
		ImageButton ibImage = (ImageButton) findViewById(R.id.imageButton_images);
		ImageButton ibOther = (ImageButton) findViewById(R.id.imageButton_others);
		ImageButton ibVideo = (ImageButton) findViewById(R.id.imageButton_videos);
		ImageButton ibDocuments = (ImageButton) findViewById(R.id.imageButton_Documents);
		if (ibApplication != null && ibAudio != null && ibCompressed != null
				&& ibContact != null && ibImage != null && ibOther != null
				&& ibVideo != null && ibDocuments !=null) {
			ibApplication.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(MainActivity.this,
							SecondActivity.class);
					intent.putExtra(TAG, "Application");
					startActivity(intent);
				}
			});

			ibAudio.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(MainActivity.this,
							SecondActivity.class);
					intent.putExtra(TAG, "Audio");
					startActivity(intent);
				}
			});

			ibCompressed.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(MainActivity.this,
							SecondActivity.class);
					intent.putExtra(TAG, "Compressed");
					startActivity(intent);
				}
			});

			ibContact.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(MainActivity.this,
							SecondActivity.class);
					intent.putExtra(TAG, "Contact");
					startActivity(intent);
				}
			});

			ibImage.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(MainActivity.this,
							SecondActivity.class);
					intent.putExtra(TAG, "Image");
					startActivity(intent);
				}
			});

			ibOther.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(MainActivity.this,
							SecondActivity.class);
					intent.putExtra(TAG, "Other");
					startActivity(intent);
				}
			});

			ibVideo.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(MainActivity.this,
							SecondActivity.class);
					intent.putExtra(TAG, "Video");
					startActivity(intent);
				}
			});
			ibDocuments.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(MainActivity.this,
							SecondActivity.class);
					intent.putExtra(TAG, "Documents");
					startActivity(intent);
				}
			});

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
