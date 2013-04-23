package de.whs.stapp.data.storage;

import java.util.List;

import static de.whs.stapp.data.storage.DatabaseConnector.*;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * 
 * @author Christoph Inhestern
 * 
 * Klasse f�r den Zugriff auf die Datenbank und die zugeh�rigen Funktionen
 * 
 */

class StappDbAdapter implements DatabaseAdapter {

    private static final String DBNAME = "stappDatabase.db";
    private static final int DBVERSION = 1;
    private DatabaseConnector dbConn;
    private SQLiteDatabase stappDb; 
    /**
     * Der Konstruktor legt eine Instantz des DatabaseConnectors an. 
     * @param context Der Context in dem die Klasse instantiiert wird.
     */
    public StappDbAdapter(Context context){
    	dbConn = new DatabaseConnector(context, DBNAME, null, DBVERSION);
    }

    
    /**
    * @author Christoph Inhestern
    * �ffnet die Datenbank und legt Sie an falls erforderlich. Dies geschieht in der Methode
    * getWritableDatabase().
    */
    public void open() {
    	stappDb = dbConn.getWritableDatabase();
    }
    
    
    /**
    * @author Christoph Inhestern
    * Schlie�t die Verbindung zur Datenbank nach der Benutzung.
    */
	public void close() {
    	stappDb.close();
    }
    
    
    @Override
	public TrainingSession newTrainingSession() {
    	
    	throw new UnsupportedOperationException("createNewTrainingUnit not yet implemented");
	}


	/**
	 * @author Marcus B�scher
	 * Die Methode legt einen neuen Eintrag f�r eine Trainingseinheit
	 * in der Datenbank (in der Tabelle "TrainingUnits") an.
	 * 
	 * @param trainingUnitId ist der PK zu der TrainingUnit
	 * @param unit ist das zu speichernde Tupel
	 */
	public void insertNewTrainingUnit(TrainingSession unit) {
		ContentValues val = new ContentValues();
		// TODO date
		// val.put(dbConn.tuClmDate, unit.getDate());
		val.put(dbConn.tuClmDuration, unit.getDurationInMinutes());
		val.put(dbConn.tuClmDistance, unit.getDistanceInMeters());
		stappDb.insert(TAB_TRAINING_UNITS, null, val);
	}


	@Override
	/**
	 * @author Marcus B�scher
	 * Die Methode speichert zu einer angegebenen ID die TrainingDetails in die
	 * TrackedData-Relation.
	 * 
	 * @param trainingUnitId ist die ID zu den zu speichernden Details.
	 * @param detail sind die Messwerte, die es zu speichern gilt.
	 */
	public void storeSessionDetail(int trainingUnitId, SessionDetail detail) {
		ContentValues val = new ContentValues();
		val.put(dbConn.tdClmIdTrainingUnit, trainingUnitId);
		val.put(dbConn.tdClmHeartrate, detail.getHeartRate());
		val.put(dbConn.tuClmDistance, detail.getDistanceInMeter());
		val.put(dbConn.tdClmSpeed, detail.getSpeedInMeterPerSecond());
		val.put(dbConn.tdClmStrides, detail.getNumberOfStrides());
		stappDb.insert(dbConn.tabTrackedData, null, val);		
	}


	/**
	 * @author Marcus B�scher
	 * Die Methode entfernt zur ID den Eintrag aus der TrainingUnit-Relation, sowie alle
	 * Eintr�ge aus der TrackedData-Relation.
	 * 
	 * @param trainingsUnitId ist die ID der zu l�schenden Trainingseinheit.	
	 */
	@Override
	public void deleteTrainingSession(int trainingsUnitId) {
		
		stappDb.delete(dbConn.tabTrackedData, 
				dbConn.tdClmIdTrainingUnit +"=" +trainingsUnitId, null);
	
		stappDb.delete(TAB_TRAINING_UNITS, 
				dbConn.tuClmIdTrainingUnit + "=" +trainingsUnitId, null);		
		
	}


	
    /**
     * @author Christoph Inhestern
     * Liefert eine Liste aller Trainingseinheiten zur�ck.
     * @return
     */
    public List<TrainingSession> getSessionHistory() {
    	throw new UnsupportedOperationException("getTrainingUnitsOverview not yet implemented");
    }


	@Override
	public List<SessionDetail> getSessionDetails(int trainingSessionId) {
		// TODO Auto-generated method stub
		return null;
	}
}
