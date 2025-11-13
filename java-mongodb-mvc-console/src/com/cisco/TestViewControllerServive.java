package com.cisco;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.bson.types.ObjectId;

import com.cisco.beans.Profile;
import com.cisco.dao.ProfileDao;

public class TestViewControllerServive {
	public static void main(String[] args) {
		
		int option = 0;
		Scanner scan = new Scanner(System.in);
		// for storing profile in mongoDB
		ProfileDao dao = new ProfileDao();
		// for storing user obj
		Profile user = null;
		// ObjectId to search for records
		ObjectId userId = null;
		// findAll()
		List<Profile> users = null;
		// update phone
		long new_phone;
		do {
			System.out.println(" 1: Store \n 2: Find By Id \n 3: Find All \n 4: Update phone by id \n 5: Delete by id \n -1: Exit");
			option = scan.nextInt();
			switch(option) {
			case 1: 
				Profile profile = new Profile();
				System.out.println("Enter name");
				profile.setName(scan.next());
				System.out.println("Enter gender");
				profile.setGender(scan.next());
				System.out.println("Enter phone");
				profile.setPhone(scan.nextLong());
				Profile saved = dao.save(profile);
				System.out.println(saved);
				break;
			case 2: 
				System.out.println("Enter ObjectId : ");
				String id = scan.next();
				userId = new ObjectId(id);
				user = dao.findById(userId);
				if(user != null) {
					System.out.println(user);
				}
				else {
					System.out.println("User Not Found");
				}
				break;
			case 3: 
				users = dao.findAll();
				if(users != null) {
					for(Profile x : users) {
						System.out.println(x);
					}
				}
				else {
					System.out.println("Collection is Empty");
				}
				break;
			case 4: 
				System.out.println("Enter ObjectId of the Record for Updatation : ");
				userId = new ObjectId(scan.next());
				System.out.print("Enter New Phone No. : ");
				new_phone = scan.nextLong();
				dao.updateSal(userId, new_phone);
				break;
			case 5: 
				System.out.println("Enter ObjectId of the Record for deletion : ");
				userId = new ObjectId(scan.next());
				dao.delete(userId);
				break;
				
			}
		} while(option != -1);
		
		scan.close();
		
		
//		ProfileDao dao = new ProfileDao();
//		List<Profile> list = new ArrayList<>();
//		try {
//			list = dao.findAll();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		for(Profile profile : list) {
//			System.out.println(profile);
//		}
	}
}
