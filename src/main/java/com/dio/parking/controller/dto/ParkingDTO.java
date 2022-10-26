package com.dio.parking.controller.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParkingDTO {
	
	private String id;
	private String license;
	private String state;
	private String model;
	private String color;
	private LocalDate entryDate;
	private LocalDate exitDate;
	private Double bill;
	
	
	
	
}
