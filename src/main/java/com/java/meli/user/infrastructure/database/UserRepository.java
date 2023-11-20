package com.java.meli.user.infrastructure.database;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query("from UserEntity u where u.uuid = :uuid")
    UserEntity getByUuid(@Param("uuid") UUID uuid);


    @Query(value = "from UserEntity u where (:name is null or u.name ilike %:name%)")
    Page<UserEntity> findAll(@Param("name") String name, Pageable pageable);
}
