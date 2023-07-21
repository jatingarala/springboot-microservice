package com.employee.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.dto.AddressDTO;
import com.employee.dto.EmployeeDTO;
import com.employee.entity.Employee;
import com.employee.feignclient.AddressClient;
import com.employee.repo.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private AddressClient addressClient;
	

	public List<Employee> getAll(){
		
		return employeeRepository.findAll();
	}
	
	public Employee addData(Employee address) {
		
		return employeeRepository.save(address);
	}
	
	public EmployeeDTO getEmployeeDetails(int id) {
		
		Employee employee = employeeRepository.findById(id).get();
		
		EmployeeDTO empDto = mapper.map(employee, EmployeeDTO.class);
		
		AddressDTO addressDTO = addressClient.getAddressByEmployeeId(id);
		
		empDto.setAddressDTO(addressDTO);
		return empDto;
	}


}
