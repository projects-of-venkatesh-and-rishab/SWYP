package com.example.searchinn;

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

	private ListOfFormats formats = new ListOfFormats(MainActivity.this);

	public static AVLTree tree = new AVLTree();

	public static AVLTree applicationTree = new AVLTree();
	public static AVLTree audioTree = new AVLTree();
	public static AVLTree compressedTree = new AVLTree();
	public static AVLTree contactTree = new AVLTree();
	public static AVLTree imageTree = new AVLTree();
	public static AVLTree otherTree = new AVLTree();
	public static AVLTree videoTree = new AVLTree();
	public static AVLTree documentsTree = new AVLTree();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		formats.listFiles(Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/");

		formats.getContacts();

		tree.insert(formats.getApplicationList());
		tree.insert(formats.getAudioList());
		tree.insert(formats.getCompressedList());
		tree.insert(formats.getContactList());
		tree.insert(formats.getImageList());
		tree.insert(formats.getOtherList());
		tree.insert(formats.getVideoList());
		tree.insert(formats.getDocumentsList());

		applicationTree.insert(formats.getApplicationList());
		audioTree.insert(formats.getAudioList());
		compressedTree.insert(formats.getCompressedList());
		contactTree.insert(formats.getContactList());
		imageTree.insert(formats.getImageList());
		otherTree.insert(formats.getOtherList());
		videoTree.insert(formats.getVideoList());
		documentsTree.insert(formats.getDocumentsList());

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

		System.out.print(tree);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
