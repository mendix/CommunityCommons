package communitycommons.actions;

import java.math.BigDecimal;

public class DistanceBetweenGeoPointsCalculator {
	
	public BigDecimal calculateDistance(double lat1, double lng1, double lat2, double lng2, double earthRadius) {	
		double dLat = Math.toRadians(lat2-lat1);
		double dLng = Math.toRadians(lng2-lng1);
		double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	        Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
	        Math.sin(dLng/2) * Math.sin(dLng/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		BigDecimal dist = BigDecimal.valueOf(earthRadius * c);

		return dist;
	}

}
