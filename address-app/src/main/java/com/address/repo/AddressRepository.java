package com.address.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.address.dto.AddressDTO;
import com.address.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
	
	@Query(nativeQuery = true, value="SELECT * FROM microservice.address ad\r\n"
			+ "join\r\n"
			+ "microservice.employee ea\r\n"
			+ "on ea.id = ad.employee_id\r\n"
			+ "where ad.employee_id=:employeeId")
	public Address findAddressByEmployeeId(@Param("employeeId") int employeeId);
}
