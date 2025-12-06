package com.salah_falah.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.salah_falah.dto.MasjidDto;
import com.salah_falah.dto.PrayerTimingDto;
import com.salah_falah.entity.Masjid;
import com.salah_falah.entity.PrayerTiming;
import com.salah_falah.service.MasjidService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/masjids")
@RequiredArgsConstructor
public class MasjidController {

	private final MasjidService service;

	@GetMapping("/getall")
	public List<Masjid> getall() {
		return service.getall();
	}

	@GetMapping
	public ResponseEntity<List<Masjid>> listByCity(@RequestParam String name) {
		return ResponseEntity.ok(service.searchByName(name));
	}

	@PostMapping
	public ResponseEntity<Masjid> create(@RequestBody @Validated MasjidDto dto, HttpServletRequest req) {
		Masjid m = Masjid.builder().name(dto.getName()).address(dto.getAddress()).latitude(dto.getLatitude())
				.longitude(dto.getLongitude()).cityId(dto.getCityId()).build();
		Masjid saved = service.createMasjid(m);
		// log audit (omitted here - integrate AuditLogService)
		return ResponseEntity.ok(saved);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Masjid> update(@PathVariable Long id, @RequestBody MasjidDto dto) {
		Masjid u = Masjid.builder().name(dto.getName()).address(dto.getAddress()).latitude(dto.getLatitude())
				.longitude(dto.getLongitude()).build();
		return ResponseEntity.ok(service.updateMasjid(id, u));
	}

	@PostMapping("/{id}/timings")
	public ResponseEntity<PrayerTiming> upsertTiming(@PathVariable Long id, @RequestBody @Validated PrayerTimingDto dto,
			HttpServletRequest req) {

		PrayerTiming t = PrayerTiming.builder().date(dto.getDate()).fajr(dto.getFajr()).dhuhr(dto.getDhuhr())
				.asr(dto.getAsr()).maghrib(dto.getMaghrib()).isha(dto.getIsha()).sunrise(dto.getSunrise())
				.createdBy(req.getRemoteAddr()) // capture IP
				.build();

		PrayerTiming saved = service.upsertTiming(id, t);
		// create audit entry (omitted here)
		return ResponseEntity.ok(saved);
	}

	@GetMapping("/{id}/timings")
	public ResponseEntity<List<PrayerTiming>> getTimings(@PathVariable Long id,
			@RequestParam(required = false) String from, @RequestParam(required = false) String to) {
		// for simplicity return all
		return ResponseEntity.ok(service.getTimingsForMasjid(id));
	}

}
