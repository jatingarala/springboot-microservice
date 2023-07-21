package com.employee.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.employee.dto.AddressDTO;


//http://localhost:8081/address-app/api/address/1

@FeignClient(name = "addressClient",url = "${addressservice.base.url}")
public interface AddressClient {

	@GetMapping("/address/{id}")
	AddressDTO getAddressByEmployeeId(@PathVariable("id") int id);
}
