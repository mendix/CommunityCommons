package communitycommons.actions;

import java.math.BigDecimal;

public class ValidateGeoDistanceCalculatorInput {
	
	public void validateValues(BigDecimal lat1, BigDecimal lng1, BigDecimal lat2, BigDecimal lng2, BigDecimal earthRadius) {
		
		if (lat1 == null || lng1 == null || lat2 == null || lng2 == null || earthRadius == null) {
			throw new GeolocationInputValidationException("One of the values is empty. Please ensure all parameters are configured.");
		}
	}

}