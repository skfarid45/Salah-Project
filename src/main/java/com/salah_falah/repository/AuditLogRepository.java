package com.salah_falah.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salah_falah.entity.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

	
	
	
}
