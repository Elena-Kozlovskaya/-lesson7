package com.kozlovskaya.springdata.lesson7.repositories;

import com.kozlovskaya.springdata.lesson7.entities.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    @Query("select r from Role r where r.name = :name")
    Collection<Role> findByRoleName(String name);

}
