package com.address.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.address.dto.AddressDTO;
import com.address.entity.Address;
import com.address.repo.AddressRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private ModelMapper mapper;

	public List<Address> getAllAddress() {

		return addressRepository.findAll();
	}

	public Address addAddress(Address address) {

		return addressRepository.save(address);
	}

	public Address getAddressById(Integer addressId) {

		return addressRepository.findById(addressId).get();
	}
	
	public AddressDTO getAddressByEmployeeId(Integer empId) {
		
		System.out.println("call getAddressByEmployeeId");
		Address address= addressRepository.findAddressByEmployeeId(empId);
		
		AddressDTO map = mapper.map(address, AddressDTO.class);

		return map;
	}
}
