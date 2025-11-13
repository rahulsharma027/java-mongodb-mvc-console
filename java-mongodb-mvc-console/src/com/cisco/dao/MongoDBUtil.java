package com.cisco.dao;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBUtil {

	private static final String CONNECTION_STRING = "mongodb://localhost:27017";
	private static final String DATABASE_NAME = "cisco_db";
	private static MongoDatabase database;
	public static MongoDatabase getDatabase() {
		CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
				MongoClientSettings.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())
				);
		try {
			MongoClient mongoClient = MongoClients.create(CONNECTION_STRING);
			// accessing the database with a converter
			database = mongoClient.getDatabase(DATABASE_NAME).withCodecRegistry(pojoCodecRegistry);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return database;
	}
}
