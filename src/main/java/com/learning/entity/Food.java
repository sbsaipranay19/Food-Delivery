package com.learning.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "food")

public class Food {
	public Food(String foodName,double foodCost,String foodPic){
		this.foodCost=foodCost;
		this.foodName=foodName;
		this.foodPic=foodPic;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long foodId;
	private String foodName;
	private double foodCost;
	private String foodPic;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "food_types", joinColumns = @JoinColumn(name = "foodId"), inverseJoinColumns = @JoinColumn(name = "id"))
	private Set<FoodType> foodtypes = new HashSet<FoodType>();

}
