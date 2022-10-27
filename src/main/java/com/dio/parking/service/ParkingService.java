package com.dio.parking.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dio.parking.model.Parking;

@Service
public class ParkingService {
	
	private static Map<String, Parking> parkingMap = new HashMap<>();
	
	static {
		
		var id = getUUID();
		var id1 = getUUID();
		Parking parking = new Parking(id, "NYY-5613", "BA", "CELTA", "PRATA");
		Parking parking1 = new Parking(id1, "FMM-1212", "BA", "FOCUS", "PRATA");
		parkingMap.put(id, parking);
		parkingMap.put(id1, parking1);
	}
	
	public List<Parking> findAll(){
		return parkingMap.values().stream().collect(Collectors.toList());
	}
	
	public Parking findById(String id) {
		return parkingMap.get(id);
	}
	
	public Parking create(Parking parkingCreate) {
		String id = getUUID();
		parkingCreate.setId(id);
		parkingCreate.setEntryDate(LocalDateTime.now());
		parkingMap.put(id, parkingCreate); 
		return parkingCreate;
	}
	
	private static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
