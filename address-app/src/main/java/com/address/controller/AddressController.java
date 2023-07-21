package com.address.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.address.entity.Address;
import com.address.services.AddressService;

@RestController()
@RequestMapping("/address")
public class AddressController {

	@Autowired
	AddressService addressService;
	
	@GetMapping("/all")
	public ResponseEntity<?> getAddress(){
		
		return new ResponseEntity<>(addressService.getAllAddress(), HttpStatus.OK);
		
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addAddress(@RequestBody Address address){
		
		return new ResponseEntity<>(addressService.addAddress(address), HttpStatus.OK);
	}
	
	@GetMapping("/{empId}")
	public ResponseEntity<?> getAddressByEmployeeId(@PathVariable("empId") Integer empId){
		
		return new ResponseEntity<>(addressService.getAddressByEmployeeId(empId), HttpStatus.OK);
		
	}
}
