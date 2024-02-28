package com.governance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.governance.entity.ServiceRequest;
import com.governance.entity.User;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {

	boolean existsByUserIdAndServiceName(Long userId, String serviceName);

	boolean existsByUserAndServiceName(User user, String serviceName);

	Optional<ServiceRequest> findByUserAndServiceName(User user, String serviceName);

	Optional<ServiceRequest> findByUserIdAndServiceName(Long userId, String serviceName);
}
