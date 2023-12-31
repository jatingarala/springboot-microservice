package com.employee.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.employee.dto.AddressDTO;
import com.employee.dto.EmployeeDTO;
import com.employee.entity.Employee;
import com.employee.services.EmployeeService;


@RestController()
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	private RestTemplate resttemplate;
	
	@GetMapping("/all")
	public ResponseEntity<?> getAll(){
		
		ArrayList<Employee> employees = new ArrayList();
		
		employeeService.getAll().forEach(e->{
			AddressDTO addressDto = resttemplate.getForObject("http://localhost:8081/address/1", AddressDTO.class);
			
			e.setAddressDTO(addressDto);
			employees.add(e);
		});
		
 		
		return new ResponseEntity<>(employees, HttpStatus.OK);
		
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addData(@RequestBody Employee address){
		
		return new ResponseEntity<>(employeeService.addData(address), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDTO> getEmployeeDetails(@PathVariable("id") int id) {
	
		return new ResponseEntity<EmployeeDTO>(employeeService.getEmployeeDetails(id), HttpStatus.OK);
	}
}
