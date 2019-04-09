package com.example.entity;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class Player {

	private int id;

	@NotBlank
	private String name;

	@Range(max=50, min=18)
	private int age;
	private String team;
	private String position;


}
