package com.dio.parking.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import com.dio.parking.model.Parking;

public class ParkingCheckOut {
	
	public static final int ONE_HOUR = 60;
	public static final int ONE_DAY = 24 * ONE_HOUR;
	public static final double ONE_HOUR_VALUE = 5.00;
	public static final double ADDITIONAL_PER_HOUR_VALUE = 2.00;
	public static final double DAY_VALUE = 20.00;
	
	public static Double billCalculate(Parking parking) {
		return getBill(parking.getEntryDate(), parking.getExitDate());
	}
	
	private static Double getBill(LocalDateTime entry, LocalDateTime exit) {
		long minutes = entry.until(exit, ChronoUnit.MINUTES);
		Double bill = 0.0;
		
		if(minutes <= ONE_HOUR) {
			return ONE_HOUR_VALUE;
		}
		
		if(minutes <= ONE_DAY) {
			bill = ONE_HOUR_VALUE;
			int hours = (int) (minutes / ONE_HOUR);
			System.out.println(hours);
			for(int i=0; i < hours; i++) {
				bill += ADDITIONAL_PER_HOUR_VALUE;
			}
			return bill;
		}
		
		int days = (int)(minutes / ONE_DAY);
		System.out.println(days);
		for(int i=0; i < days; i++) {
			bill += DAY_VALUE;
		}
		return bill;
	}
}
