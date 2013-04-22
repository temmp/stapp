package de.whs.stapp.data.bluetooth;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;

/**
 * Repr�sentiert das Bluetooth Device.
 * @author Chris
 */
public class BluetoothDevice implements DataTracker {
	
	private BTServiceConnection connection;

	/**
	 * Erstellt eine neue Instanz der {@link BluetoothDevice} Klasse.
	 */
	public BluetoothDevice() {
		connection = new BTServiceConnection();
	}
	
	/**
	 * Stellt eine Verbindung mit dem Bluetooth-Device (Sensor) her.
	 * @param activity Die Main Activity.
	 */
	public void connect(Activity activity) {
				
		if (isBTServiceRunning(activity))
			connection.connect();
		else
			bindBluetoothService(activity);		
    }
	
	private void bindBluetoothService(Activity activity) {
		if (activity == null)
			throw new IllegalArgumentException("activity cannot be null");
		
		Intent intent = new Intent(activity, BTCommunicationService.class);
		activity.bindService(intent, connection, Context.BIND_AUTO_CREATE);	
	}
	
	/**
	 * Gibt den Verbindungsstatus zur�ck.
	 * @return connectionState - Verbindungsstatus
	 */
	public ConnectionState getConnectionState() {
		return connection.getConnectionState();
	}

	@Override
	public void registerListener(TrackedDataListener listener) {
		if (listener == null)
			throw new IllegalArgumentException("listener cannot be null");
		
		connection.registerListener(listener);		
	}

	@Override
	public void unregisterListener(TrackedDataListener listener) {
		if (listener == null)
			throw new IllegalArgumentException("listener cannot be null");
		
		connection.unregisterListener(listener);
	}
	
	private boolean isBTServiceRunning(Activity activity) {
	    ActivityManager manager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
	    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
	        if ("de.whs.stapp.data.bluetooth.BTCommunicationService"
	        		.equals(service.service.getClassName())) {
	            return true;
	        }
	    }
	    return false;
	}
}