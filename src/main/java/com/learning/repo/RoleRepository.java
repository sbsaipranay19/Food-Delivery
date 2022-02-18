package com.learning.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.entity.EROLE;
import com.learning.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Optional<Role> findByRoleName(EROLE roleName);

}
