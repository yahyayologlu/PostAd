package com.example.postad.lookup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LookupRepo extends JpaRepository<GenericLookup, String> {

    List<GenericLookup> findByIntegrationCode(String code);

    List<GenericLookup> findByNameIsContaining(String name);

    List<GenericLookup> findByIntegrationCodeAndNameContains(String code, String name);

    @Query(value ="select g.* from generic_lookup g where g.class_type = ?1 order by g.name ", nativeQuery = true)
    List<GenericLookup> findByClassType(String classType);
}
