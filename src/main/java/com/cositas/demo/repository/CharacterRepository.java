package com.cositas.demo.repository;

import com.cositas.demo.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    void removeByUuid(UUID uuid);

    Optional<Character> findByUuid(UUID uuid);
}
