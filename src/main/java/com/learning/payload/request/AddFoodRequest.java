package com.learning.payload.request;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AddFoodRequest {
	
	@NotBlank
	private String foodName;
	@NotNull
	private double foodCost;
	@NotBlank
	private String foodPic;
	private Set<String> foodTypes;

}
