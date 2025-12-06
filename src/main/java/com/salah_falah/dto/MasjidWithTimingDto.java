package com.salah_falah.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class MasjidWithTimingDto {

	 private String name;
	    private String address;
	    private Double latitude;
	    private Double longitude;
	    private Long cityId;

	    // Prayer Timing fields
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
	
	
	
}
