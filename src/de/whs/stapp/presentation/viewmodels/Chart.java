package de.whs.stapp.presentation.viewmodels;

import de.whs.stapp.data.access.ChartData;

/**
 * 
 * @author Chris
 *
 */
public class Chart extends StappViewModel {
	
	ChartData heartRateData;

	/**
	 * @return the data
	 */
	public String getHeartRateData() {
		return heartRateData.toJSON();
	}

	/**
	 * @param heartRateData the data to set
	 */
	public void setHeartRateData(ChartData heartRateData) {
		this.heartRateData = heartRateData;
	}
	
	@Override 
	public String toJSON(){
		
		StringBuilder jsonString = new StringBuilder();
		jsonString.append("{");
		jsonString.append("\"label\":\"Lauf\",");
		jsonString.append(getHeartRateData());
		jsonString.append("}");
		return jsonString.toString();		
	}
}
