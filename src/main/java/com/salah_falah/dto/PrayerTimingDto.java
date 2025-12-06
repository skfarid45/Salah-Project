package com.salah_falah.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import org.antlr.v4.runtime.misc.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
public class PrayerTimingDto {

	 	@NotNull
	    private LocalDate date;
	    private LocalTime fajr;
	    private LocalTime dhuhr;
	    private LocalTime asr;
	    private LocalTime maghrib;
	    private LocalTime isha;
	    private LocalTime sunrise;
	
}
