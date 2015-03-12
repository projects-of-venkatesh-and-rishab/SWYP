package com.example.searchin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

public class ListOfFormats {
	public static final String[] imageFormats = { "jpg", "gif", "png", "bmp",
			"webp" };
	public static final String[] videoFormats = { "3gp", "mp4", "mkv", "ts",
			"webm" };
	public static final String[] audioFormats = { "m4a", "mp3", "flac", "mid",
			"xmf", "mxmf", "ota", "imy", "rtx", "wav", "rtttl", "ogg", "aac" };
	public static final String[] documentFormats = { "pdf", "txt",
			"doc", "xls", "ppt" };
	public static final String[] applicationFormats = { "apk", "exe", "dmg" };
	public static final String[] compressedFormats = { "zip", "rar" };

	private List<MyFile> imageList = new ArrayList<MyFile>();
	private List<MyFile> videoList = new ArrayList<MyFile>();
	private List<MyFile> audioList = new ArrayList<MyFile>();
	private List<MyFile> documentsList = new ArrayList<MyFile>();
	private List<MyFile> applicationList = new ArrayList<MyFile>();
	private List<MyFile> compressedList = new ArrayList<MyFile>();
	private List<MyFile> otherList = new ArrayList<MyFile>();
	private List<MyContact> contactList = new ArrayList<MyContact>();

	private Context context;

	public ListOfFormats(Context context) {
		this.context = context;
	}

	public void listFiles(String path) {
		File[] contents = new File(path).listFiles();
		if (contents != null && contents.length > 0)
			for (int i = 0; i < contents.length; i++) {
				if (contents[i].isDirectory())
					listFiles(contents[i].getAbsolutePath());
				else {
					// if (tree != null)
					// tree.insert(contents[i].getName());
					MyFile mFile = new MyFile(contents[i]);
					int category = mFile.getCategory();
					switch (category) {
					case 1:
						this.getApplicationList().add(mFile);
						break;
					case 2:
						this.getAudioList().add(mFile);
						break;
					case 3:
						this.getCompressedList().add(mFile);
						break;
					case 4:
						this.getDocumentsList().add(mFile);
						break;
					case 5:
						this.getImageList().add(mFile);
						break;
					case 6:
						this.getVideoList().add(mFile);
						break;
					case -1:
						this.getOtherList().add(mFile);
					}
				}
			}
	}

	public void getContacts() {
		Cursor cursor = context.getContentResolver().query(
				ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

		cursor.moveToFirst();

		while (cursor.moveToNext()) {
			MyContact mContact = new MyContact(
					Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))),
					cursor.getString(cursor
							.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
			this.getContactList().add(mContact);
			// if (tree != null)
			// tree.insert(mContact.getName());
		}

		cursor.close();
	}

	public List<MyFile> getApplicationList() {
		return applicationList;
	}

	public List<MyFile> getAudioList() {
		return audioList;
	}

	public List<MyFile> getCompressedList() {
		return compressedList;
	}

	public List<MyFile> getDocumentsList() {
		return documentsList;
	}

	public List<MyFile> getImageList() {
		return imageList;
	}

	public List<MyFile> getOtherList() {
		return otherList;
	}

	public List<MyFile> getVideoList() {
		return videoList;
	}

	public List<MyContact> getContactList() {
		return contactList;
	}

	public int size() {
		int size = applicationList.size() + audioList.size()
				+ compressedList.size() + documentsList.size()
				+ imageList.size() + otherList.size() + videoList.size()
				+ contactList.size();
		return size;
	}

}
