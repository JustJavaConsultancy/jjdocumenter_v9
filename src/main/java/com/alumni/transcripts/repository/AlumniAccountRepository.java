package com.alumni.transcripts.repository;

import com.alumni.transcripts.model.AlumniAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumniAccountRepository extends JpaRepository<AlumniAccount, Long> {
    AlumniAccount findByEmail(String email);
}
