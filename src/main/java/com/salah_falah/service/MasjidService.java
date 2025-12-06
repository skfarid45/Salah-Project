package com.salah_falah.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.salah_falah.entity.Masjid;
import com.salah_falah.entity.PrayerTiming;
import com.salah_falah.repository.MasjidRepository;
import com.salah_falah.repository.PrayerTimingRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MasjidService {

	
	private final MasjidRepository masjidRepo;
    private final PrayerTimingRepository timingRepo;
    
    public List<Masjid> getall(){
    	return masjidRepo.findAll();
    }

   
    
    public List<Masjid> searchByName(String name){
    	return masjidRepo.findByNameContainingIgnoreCase(name);
    }

    public Masjid createMasjid(Masjid m) {
        return masjidRepo.save(m);
    }

    public Masjid updateMasjid(Long id, Masjid updated) {
        Masjid m = masjidRepo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        m.setName(updated.getName());
        m.setAddress(updated.getAddress());
        m.setLatitude(updated.getLatitude());
        m.setLongitude(updated.getLongitude());
        return masjidRepo.save(m);
    }

    @Transactional
    public PrayerTiming upsertTiming(Long masjidId, PrayerTiming timing) {
        // if exists for date, update; else create
        return timingRepo.findByMasjidIdAndDate(masjidId, timing.getDate())
                .map(existing -> {
                    existing.setFajr(timing.getFajr());
                    existing.setDhuhr(timing.getDhuhr());
                    existing.setAsr(timing.getAsr());
                    existing.setMaghrib(timing.getMaghrib());
                    existing.setIsha(timing.getIsha());
                    existing.setSunrise(timing.getSunrise());
                    return timingRepo.save(existing);
                })
                .orElseGet(() -> {
                    timing.setMasjidId(masjidId);
                    return timingRepo.save(timing);
                });
    }

    public List<PrayerTiming> getTimingsForMasjid(Long masjidId) {
        return timingRepo.findByMasjidId(masjidId);
    }
	
	
    
    
    @Transactional
    public Map<String, Object> createMasjidWithTimings(Masjid masjid, PrayerTiming timing) {

        // 1. Save Masjid
        Masjid savedMasjid = masjidRepo.save(masjid);

        // 2. Save or Update Timing (Upsert)
        PrayerTiming savedTiming = timingRepo.findByMasjidIdAndDate(savedMasjid.getId(), timing.getDate())
                .map(existing -> {
                    existing.setFajr(timing.getFajr());
                    existing.setDhuhr(timing.getDhuhr());
                    existing.setAsr(timing.getAsr());
                    existing.setMaghrib(timing.getMaghrib());
                    existing.setIsha(timing.getIsha());
                    existing.setSunrise(timing.getSunrise());
                    return timingRepo.save(existing);
                })
                .orElseGet(() -> {
                    timing.setMasjidId(savedMasjid.getId());
                    return timingRepo.save(timing);
                });

        return Map.of(
                "masjid", savedMasjid,
                "timing", savedTiming
        );
    }

    
	
	
}
