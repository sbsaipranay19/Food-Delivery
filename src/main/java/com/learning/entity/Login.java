package com.learning.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "login")
public class Login {
	@Id
	@Column(name = "id")
	private String id;
	@NotBlank
	private String password;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "regId", nullable = false, foreignKey = @ForeignKey(name = "fk_logregId"))
	@JsonProperty(access = Access.WRITE_ONLY)
	private Register register;
}
