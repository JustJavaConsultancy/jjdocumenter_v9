package com.alumni.transcripts.repository;

import com.alumni.transcripts.model.Alumni;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumniRepository extends JpaRepository<Alumni, Long> {
    Alumni findByEmail(String email);
}
