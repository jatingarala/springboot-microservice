package com.employee.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.employee.dto.AddressDTO;
import com.employee.dto.EmployeeDTO;
import com.employee.entity.Employee;
import com.employee.openfeignclient.AddressClient;
import com.employee.repo.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private WebClient webClient;
	
//	@Autowired
//	private DiscoveryClient discoveryClient;
//	
//	@Autowired
//	private LoadBalancerClient loadBalancerClient;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private AddressClient addressClient;
	
	@Value("${addressservice.base.url}")
	private String addressBaseUrl;

	public List<Employee> getAll(){
		
		return employeeRepository.findAll();
	}
	
	public Employee addData(Employee address) {
		
		return employeeRepository.save(address);
	}
	
	public EmployeeDTO getEmployeeDetails(int id) {
		
		Employee employee = employeeRepository.findById(id).get();
		
		EmployeeDTO empDto = mapper.map(employee, EmployeeDTO.class);
		
		AddressDTO addressDTO = addressClient.getAddressByEmployeeId(id).getBody();
		
		empDto.setAddressDTO(addressDTO);
		return empDto;
	}

	private AddressDTO callAddressServiceUsingWebClient(int id) {
						return webClient
								.get()
								.uri("/address/"+id)
								.retrieve()
								.bodyToMono(AddressDTO.class)
								.block();
	}

	private AddressDTO callAddressServiceUsingRestTemplate(int id) {
		
//		List<ServiceInstance> addressInstances = discoveryClient.getInstances("address-app");
//		ServiceInstance serviceInstance = addressInstances.get(0);
//		String uri = serviceInstance.getUri().toString();
		
//		ServiceInstance serviceInstance = loadBalancerClient.choose("address-app");
//		String uri = serviceInstance.getUri().toString();
//		// get context path set in address-app property file
//		String contextPath = serviceInstance.getMetadata().get("configPath");
//		
//		System.out.println("uri--------"+uri);
		 
		return restTemplate.getForObject("http://address-app/address-app/api/address/{id}", AddressDTO.class, id);
	}
}
