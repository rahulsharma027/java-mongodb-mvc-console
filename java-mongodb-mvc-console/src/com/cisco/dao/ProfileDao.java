package com.cisco.dao;
import java.util.ArrayList;
import java.util.List;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.cisco.Employee;
import com.cisco.MongoDBConnection;
import com.cisco.beans.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
public class ProfileDao {
	
	// find by id
	public Profile findById(ObjectId id) {
		MongoCollection<Profile> collection = MongoDBUtil.getDatabase().getCollection("profiles", Profile.class);
		Profile profile = collection.find(Filters.eq("_id", id)).first();
		return profile;
	}
	
	
	// store
	public Profile save(Profile profile) {
		MongoCollection<Profile> collection = MongoDBUtil.getDatabase().getCollection("profiles", Profile.class);
		InsertOneResult result = collection.insertOne(profile);
		ObjectId insertedId = result.getInsertedId().asObjectId().getValue();
		return findById(insertedId);
	}
	
	//delete
	public void delete(ObjectId id) {
		try {
            MongoDatabase database = MongoDBUtil.getDatabase();
            MongoCollection<Profile> collection = database.getCollection("profiles", Profile.class);
            DeleteResult del_profile = collection.deleteOne(Filters.eq("_id", id));
            if(del_profile.getDeletedCount()!=0) {
            	System.out.println("Record Deleted Successfully");
            }
            else {
            	System.out.println("Record Not Deleted");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	//update
	public void updateSal(ObjectId id, long pho) {
		try {
            MongoDatabase database = MongoDBUtil.getDatabase();
            MongoCollection<Profile> collection = database.getCollection("profiles", Profile.class);
            UpdateResult update_sal = collection.updateOne(Filters.eq("_id", id), Updates.set("phone", pho));
            if(update_sal.getModifiedCount()!=0) {
            	System.out.println("Phone Updated Successfully");
            }
            else {
            	System.out.println("Record Not Updated");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	// findAll()
	public List<Profile> findAll(){
		List<Profile> list = null;
		try {
			// accessing the database
			MongoDatabase database = MongoDBUtil.getDatabase();
			MongoCollection<Profile> collection = database.getCollection("profiles", Profile.class);
			MongoCursor<Profile> cursor = collection.find().iterator();
			if(cursor.hasNext()) list = new ArrayList<>();
			while(cursor.hasNext()) {
				Profile user = cursor.next();
				list.add(user);
			}
			cursor.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
