package com.javamentor.backend.repository;

import com.javamentor.backend.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Profile findByUserId(Long id);

    boolean existsByUserId(Long id);

}