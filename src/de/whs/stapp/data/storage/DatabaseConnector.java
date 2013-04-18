package de.whs.stapp.data.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 
 * @author Christoph
 * 
 * Die Klasse stellt eine Verbindung zur Datenbank her.
 * Existiert die Datenbank nicht, so wird sie erstellt und die Methode onCreate() gerufen.
 * 
 */

class DatabaseConnector extends SQLiteOpenHelper{

	public final String tabTrainingUnits = "TrainingUnits";
	public final String tuClmDate = "Date";
	public final String tuClmDuration = "Duration";
	public final String tuClmDistance = "Distance";
	
	public final String tabTrackedData = "TrackedData";
	public final String tdClmIdTrainingUnit = "_idTrainingUnit";
	public final String tdClmHeartrate = "Heartrate";
	public final String tdClmDistance = "Distance";
	public final String tdClmSpeed = "Speed";
	public final String tdClmStrides = "Strides";
	
	
	public DatabaseConnector(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String createTrainingUnits = "CREATE TABLE " + tabTrainingUnits + " ( " +
										"_id int, " +
										tuClmDate + " long, " +
										tuClmDistance + " int, " +
										tuClmDuration + " long, " +
										"PRIMARY KEY (_id))";
		
		String createTrackedData = "CREATE TABLE " + tabTrackedData + " ( " +
										"_id int," +
										tdClmIdTrainingUnit + " int, " +
										tdClmHeartrate + " int, " +
										tdClmDistance + " int, " +
										tdClmSpeed + " int, " +
										tdClmStrides + " int, " +
										"PRIMARY KEY (_id))";
		
		
		db.execSQL(createTrainingUnits);
		db.execSQL(createTrackedData);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Was soll beim erh�hen der DB Version passieren?
		
	}


}
