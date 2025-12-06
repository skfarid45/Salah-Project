package com.salah_falah.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "audit_log")
@Getter
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class AuditLog {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	  private String entity;
	  private Long entityId;
	  private String action;
	  @Column(columnDefinition = "TEXT")
	  private String details;
	  private String actor;
	  private String ipAddr;
	  private LocalDateTime createdAt;

	  @PrePersist
	  public void prePersist() { createdAt = LocalDateTime.now(); }
	
	
}
