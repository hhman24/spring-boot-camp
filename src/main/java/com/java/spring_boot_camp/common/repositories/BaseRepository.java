package com.java.spring_boot_camp.common.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository <T, ID extends Serializable> extends JpaRepository<T, ID> {

    default Optional<T> findByIdNotDeleted(ID id) {
        return findById(id);
    }
}
