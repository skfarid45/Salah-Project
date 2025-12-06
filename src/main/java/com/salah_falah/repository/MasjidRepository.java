package com.salah_falah.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salah_falah.entity.Masjid;

public interface MasjidRepository extends JpaRepository<Masjid,Long>{
	List<Masjid> findByNameContainingIgnoreCase(String name);

}
