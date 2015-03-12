package com.example.searchin;

import java.io.File;

public class MyFile implements Comparable<Object> {
	private File file;
	private String extension;
	private int type;
	private int id;

	private final String[] formats[] = { ListOfFormats.applicationFormats,
			ListOfFormats.audioFormats, ListOfFormats.compressedFormats,
			ListOfFormats.documentFormats, ListOfFormats.imageFormats,
			ListOfFormats.videoFormats };

	public MyFile(File file) {
		this.file = file;

		String fileName = file.getName();
		extension = fileName.substring(fileName.lastIndexOf(".") + 1,
				fileName.length());

		this.type = getType();
		this.id = type;
	}

	public MyFile(int type, int id) {
		this.file = null;
		this.type = type;
		
		this.extension = "";
		
		if(type == 7)
			this.extension = "cts";
		this.id = id;
	}

	private int getType() {
		for (int j = 0; j < formats.length; j++) {
			String[] currentFormat = formats[j];
			for (int k = 0; k < currentFormat.length; k++)
				if (currentFormat[k].equals(extension)) {
					return j + 1;
				}
		}
		return -1;
	}

	public File getFile() {
		return file;
	}

	public String getExtension() {
		return extension;
	}

	public String[][] getFormats() {
		return formats;
	}

	public int getCategory() {
		return this.type;
	}

	public String getNameWithoutExtension() {
		if (file.getName().length() - extension.length() - 1 > 0)
			return file.getName().substring(0,
					file.getName().length() - extension.length() - 1);
		return file.getName();
	}

	public String getName() {
		return file.getName();
	}
	
	public int getId() {
		return id;
	}

	@Override
	public int compareTo(Object arg0) {
		if (arg0 != null)
			if (arg0 instanceof String) {
				if (((String) arg0).compareToIgnoreCase(getName()) < 0)
					return -1;
				if (((String) arg0).compareToIgnoreCase(getName()) > 0)
					return 1;
			} else if (arg0 instanceof MyFile
					&& ((MyFile) arg0).getName() != null) {
				if (((MyFile) arg0).getName().compareToIgnoreCase(getName()) < 0)
					return -1;
				if (((MyFile) arg0).getName().compareToIgnoreCase(getName()) > 0)
					return 1;
			} else if (arg0 instanceof MyContact
					&& ((MyContact) arg0).getName() != null) {
				if (((MyContact) arg0).getName().compareToIgnoreCase(getName()) < 0)
					return -1;
				if (((MyContact) arg0).getName().compareToIgnoreCase(getName()) > 0)
					return 1;
			}
		return 0;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof MyFile
				&& ((MyFile) o).compareTo(getNameWithoutExtension()) == 0)
			return true;
		if (o instanceof String
				&& ((String) o).compareToIgnoreCase(getNameWithoutExtension()) == 0)
			return true;
		return false;
	}
}
