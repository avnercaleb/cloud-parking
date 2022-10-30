package com.dio.parking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dio.parking.controller.dto.ParkingCreateDTO;
import com.dio.parking.controller.dto.ParkingDTO;
import com.dio.parking.controller.mapper.ParkingMapper;
import com.dio.parking.model.Parking;
import com.dio.parking.service.ParkingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/parking")
@Api(tags = "ParkingController")
public class ParkingController {
	
	
	private final ParkingService parkingService;
	private final ParkingMapper parkingMapper;
		
	
	
	public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {		
		this.parkingService = parkingService;
		this.parkingMapper = parkingMapper;
	}



	@GetMapping
	@ApiOperation("Retorna todos os carros estacionados")
	public ResponseEntity<List<ParkingDTO>> findAll(){
		List<Parking> parkingList = parkingService.findAll();
		List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/{id}")
	@ApiOperation("Retorna apenas um carro, selecionado por ID")
	public ResponseEntity<ParkingDTO> findById(@PathVariable String id){
		Parking parking = parkingService.findById(id);
		ParkingDTO result = parkingMapper.toParkingDTO(parking);
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation("Deleta um carro estacionado")
	public ResponseEntity<Parking> delete(@PathVariable String id){
		parkingService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	@ApiOperation("da entrada em um novo carro no estacionamento")
	public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto){
		Parking parkingCreate = parkingMapper.toParkingCreate(dto);
		Parking parking = parkingService.create(parkingCreate);
		ParkingDTO result = parkingMapper.toParkingDTO(parking);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@PutMapping("/{id}")
	@ApiOperation("Atualiza as informações de um carro")
	public ResponseEntity<ParkingDTO> update(@PathVariable String id, @RequestBody ParkingCreateDTO dto){
		Parking parkingCreate = parkingMapper.toParkingCreate(dto);
		Parking parking = parkingService.update(id,parkingCreate);
		ParkingDTO result = parkingMapper.toParkingDTO(parking);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@PostMapping("/{id}")
	@ApiOperation("Realiza o checkout do carro estacionado")
	public ResponseEntity<ParkingDTO> checkOut(@PathVariable String id){
		Parking parking = parkingService.checkOut(id);
		return ResponseEntity.ok(parkingMapper.toParkingDTO(parking));
				
	}
}
