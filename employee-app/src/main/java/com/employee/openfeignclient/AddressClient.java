package com.employee.openfeignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.employee.dto.AddressDTO;

@FeignClient(name = "address-app", path = "/address-app/api")
public interface AddressClient {
	
	@GetMapping("/address/{empId}")
	public ResponseEntity<AddressDTO> getAddressByEmployeeId(@PathVariable("empId") Integer empId);

}
