package com.alumni.transcripts.service;

import com.alumni.transcripts.model.AlumniAccount;
import com.alumni.transcripts.repository.AlumniAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlumniAccountService {

    @Autowired
    private AlumniAccountRepository alumniAccountRepository;

    public void registerAlumni(AlumniAccount alumniAccount) {
        alumniAccountRepository.save(alumniAccount);
    }

    public AlumniAccount loginAlumni(String email, String password) {
        AlumniAccount alumniAccount = alumniAccountRepository.findByEmail(email);
        if (alumniAccount != null && alumniAccount.getPassword().equals(password)) {
            return alumniAccount;
        }
        return null;
    }

    public boolean resetPassword(String email) {
        AlumniAccount alumniAccount = alumniAccountRepository.findByEmail(email);
        if (alumniAccount != null) {
            // Logic to send password reset link to email
            return true;
        }
        return false;
    }
}
