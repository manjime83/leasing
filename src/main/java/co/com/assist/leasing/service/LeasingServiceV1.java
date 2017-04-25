package co.com.assist.leasing.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(serviceName = "LeasingService")
public class LeasingServiceV1 {

	@WebMethod
	@WebResult(name = "fahrenheit")
	public BigDecimal CelsiusToFahrenheit(@WebParam(name = "celsius") BigDecimal celsius) {
		if (celsius.compareTo(BigDecimal.valueOf(60)) > 0) {
			throw new RuntimeException();
		}

		try {
			Thread.sleep(celsius.longValue() * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return celsius.multiply(BigDecimal.valueOf(9)).divide(BigDecimal.valueOf(5), 2, RoundingMode.HALF_EVEN)
				.add(BigDecimal.valueOf(32));
	}

	@WebMethod
	@WebResult(name = "celsius")
	public BigDecimal FahrenheitToCelsius(@WebParam(name = "fahrenheit") BigDecimal fahrenheit) {
		return fahrenheit.subtract(BigDecimal.valueOf(32)).multiply(BigDecimal.valueOf(5)).divide(BigDecimal.valueOf(9),
				2, RoundingMode.HALF_EVEN);
	}

}