package gasStation;

import java.time.LocalDateTime;

public class Loading implements Comparable<Loading>{

	private String kolonkaId;
	private String fuelType;
	private int fuelQuantiy;
	private LocalDateTime loadingTime;
	
	public Loading(String kolonkaId, String fuelType, int fuelQuantiy, LocalDateTime loadingTime) {
		this.kolonkaId = kolonkaId;
		this.fuelType = fuelType;
		this.fuelQuantiy = fuelQuantiy;
		this.loadingTime = loadingTime;
	}
	
	@Override
	public int compareTo(Loading o) {
		return this.loadingTime.compareTo(o.loadingTime);
	}

	@Override
	public String toString() {
		return "Loading [fuelType=" + fuelType + ", fuelQuantiy=" + fuelQuantiy + ", loadingTime=" + loadingTime + "]";
	}
	
	
}
