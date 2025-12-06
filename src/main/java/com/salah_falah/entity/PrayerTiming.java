package com.salah_falah.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "prayer_timing", uniqueConstraints = @UniqueConstraint(columnNames = { "masjid_id", "date" }))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrayerTiming {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "masjid_id")
	private Long masjidId;

	private LocalDate date;
	@JsonFormat(pattern = "hh:mm a")
	private LocalTime fajr;
	@JsonFormat(pattern = "hh:mm a")
	private LocalTime dhuhr;
	@JsonFormat(pattern = "hh:mm a")
	private LocalTime asr;
	@JsonFormat(pattern = "hh:mm a")
	private LocalTime maghrib;
	@JsonFormat(pattern = "hh:mm a")
	private LocalTime isha;
	@JsonFormat(pattern = "hh:mm a")
	private LocalTime sunrise;

	private String createdBy;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	@PrePersist
	public void prePersist() {
		createdAt = updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
		updatedAt = LocalDateTime.now();
	}

}
