package com.dio.parking.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dio.parking.model.Parking;
import com.dio.parking.repository.ParkingRepository;
import com.dio.parking.service.exception.ParkingNotFoundException;

@Service
public class ParkingService {
	
	private ParkingRepository parkingRepository;
	
	public ParkingService(ParkingRepository parkingRepository) {		
		this.parkingRepository = parkingRepository;
	}
		
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Parking> findAll(){
		return parkingRepository.findAll();
	}
	
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Parking findById(String id) {
		return parkingRepository.findById(id).orElseThrow(() -> 
				new ParkingNotFoundException(id));		
	}
	
	@Transactional
	public Parking create(Parking parkingCreate) {
		String id = getUUID();
		parkingCreate.setId(id);
		parkingCreate.setEntryDate(LocalDateTime.now());
		parkingRepository.save(parkingCreate); 
		return parkingCreate;
	}
				
	@Transactional
	public Parking update(String id, Parking parkingUpdate) {
		Parking parking = findById(id);
		parking.setColor(parkingUpdate.getColor());
		parking.setLicense(parkingUpdate.getLicense());
		parking.setModel(parkingUpdate.getModel());
		parking.setState(parkingUpdate.getState());
		parkingRepository.save(parking);
		return parking;
	}
	
	@Transactional
	public void delete(String id) {
		findById(id);
		parkingRepository.deleteById(id);
	}
	
	@Transactional
	public Parking checkOut(String id) {
		Parking parking = findById(id);
		parking.setExitDate(LocalDateTime.now());
		parking.setBill(ParkingCheckOut.billCalculate(parking));
		parkingRepository.save(parking);
		return parking;
		
	}
	
	private static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	
}
