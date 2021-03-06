package com.anta.currency_for_employer.repository;

import com.anta.currency_for_employer.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    /**
     * Find role by name
     * @param name role name
     * @return found role
     */
    Role findByName(String name);
}
