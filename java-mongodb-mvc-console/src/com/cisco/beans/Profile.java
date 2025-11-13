package com.cisco.beans;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

public class Profile {
	@BsonId
	private ObjectId id;
	private String name;
	private long phone;
	private String gender;
	@Override
	public String toString() {
		return "Profile [id=" + id + ", name=" + name + ", phone=" + phone + ", gender=" + gender + "]";
	}
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Profile() {
		super();
	}
	
	
}
