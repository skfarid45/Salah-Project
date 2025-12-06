package com.salah_falah.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@Table(name="masjid")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Masjid {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	private String name;
    private String address;
    private Double latitude;
    private Double longitude;
	

    @Column(name = "city_id")
    private Long cityId;

    private String externalRef;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() { createdAt = updatedAt = LocalDateTime.now(); }
    @PreUpdate
    public void preUpdate() { updatedAt = LocalDateTime.now(); }
	
	
	
}
