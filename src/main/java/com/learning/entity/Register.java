package com.learning.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
@Table(name = "register")
public class Register {
	
	public Register(String username, String email, String password, String name, String address) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.name = name;
		this.address = address;
	}
	
	@Id
	@Column(name = "userId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Size(max = 50)
	@NotBlank
	private String email;
	@Size(max = 50)
	@NotBlank
	private String name;
	@Size(max = 100)
	@NotBlank
	private String password;
	private String address;
	private String username;
	
	@OneToOne(mappedBy = "register", cascade = CascadeType.ALL)
	private Login login;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "userId"),
	inverseJoinColumns = @JoinColumn(name  = "roleId"))
	private Set<Role> roles = new HashSet<Role>();
	

}
