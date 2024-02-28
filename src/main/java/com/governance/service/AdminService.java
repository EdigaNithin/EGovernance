package com.governance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.governance.DTO.AdminDTO;
import com.governance.entity.Admin;
import com.governance.exception.AdminNotFoundException;
import com.governance.exception.CustomAdminException;
import com.governance.repository.AdminRepository;

import jakarta.validation.Valid;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepo;

	public Admin createAdmin(@Valid AdminDTO admin) throws CustomAdminException {
		if (adminRepo.existsByEmail(admin.getEmail())) {
			throw new CustomAdminException("Admin already exists");
		}

		Admin adminEntity = new Admin();
		adminEntity.setName(admin.getName());
		adminEntity.setPassword(admin.getPassword());
		adminEntity.setEmail(admin.getEmail().toLowerCase());

		return adminRepo.save(adminEntity);
	}

	public void deleteAdmin(long id) throws AdminNotFoundException {
		Admin admin = adminRepo.findById(id).orElseThrow(() -> new AdminNotFoundException("Admin Not Found"));
		adminRepo.deleteById(id);
	}

	public Admin loginAdmin(@Valid String email, String password) throws AdminNotFoundException {
		Admin admin = adminRepo.findByEmail(email.toLowerCase())
				.orElseThrow(() -> new AdminNotFoundException("Admin not found"));

		if (!admin.getPassword().equals(password)) {
			throw new AdminNotFoundException("Invalid password");
		}

		return admin;
	}

	public Admin updateAdmin(Long id, AdminDTO admin) throws AdminNotFoundException {
		Admin existingAdmin = adminRepo.findById(id).orElseThrow(() -> new AdminNotFoundException("Admin not found"));

		existingAdmin.setName(admin.getName());
		existingAdmin.setEmail(admin.getEmail());
		existingAdmin.setPassword(admin.getPassword());

		return adminRepo.save(existingAdmin);
	}
}
