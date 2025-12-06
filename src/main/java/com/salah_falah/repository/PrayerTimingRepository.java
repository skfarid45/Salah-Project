package com.salah_falah.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salah_falah.entity.PrayerTiming;

public interface PrayerTimingRepository extends JpaRepository<PrayerTiming, Long> {

	Optional<PrayerTiming> findByMasjidIdAndDate(Long masjidId, LocalDate date);
    List<PrayerTiming> findByMasjidIdAndDateBetween(Long masjidId, LocalDate from, LocalDate to);
    List<PrayerTiming> findByMasjidId(Long masjidId);
}
