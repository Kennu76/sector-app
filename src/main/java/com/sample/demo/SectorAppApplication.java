package com.sample.demo;

import java.util.regex.Matcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SectorAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SectorAppApplication.class, args);


	String mainString = 
    "<option value=25>&nbsp;&nbsp;&nbsp;&nbsp;Business services</option> 
    <option value=35>&nbsp;&nbsp;&nbsp;&nbsp;Engineering</option>
    <option value=28>&nbsp;&nbsp;&nbsp;&nbsp;Information Technology and Telecommunications</option>
    <option value=22>&nbsp;&nbsp;&nbsp;&nbsp;Tourism</option>
    <option value=141>&nbsp;&nbsp;&nbsp;&nbsp;Translation services</option>
    <option value=21>&nbsp;&nbsp;&nbsp;&nbsp;Transport and Logistics</option>";
	Pattern MY_PATTERN = Pattern.compile(";(.*?)<");
		Matcher m = MY_PATTERN.matcher(mainString);
		while (m.find()) {
			String s = m.group(1);
			// s now contains "BAR"
		}
	}

}
