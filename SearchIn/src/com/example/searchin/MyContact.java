package com.example.searchin;

public class MyContact extends MyFile implements Comparable<Object> {
	private final int contactId;
	private final String name;
	private final static int type = 7;

	public MyContact() {
		super(type, 0);
		this.contactId = 0;
		this.name = "";
	}

	public MyContact(int contactId, String name) {
		super(type,contactId);
		this.contactId = contactId;
		this.name = name;
	}

	public int getContactId() {
		return contactId;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getNameWithoutExtension() {
		return name;
	}
	
	@Override
	public int getCategory() {
		return type;
	}

	@Override
	public int compareTo(Object arg0) {
		return super.compareTo(arg0);
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof MyContact && ((MyContact) o).compareTo(getNameWithoutExtension()) == 0)
			return true;
		if (o instanceof String
				&& ((String) o).compareToIgnoreCase(getNameWithoutExtension()) == 0)
			return true;
		return false;
	}
}
