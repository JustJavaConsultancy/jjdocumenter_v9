package com.alumni.transcripts.service;

import com.alumni.transcripts.model.Alumni;
import com.alumni.transcripts.repository.AlumniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlumniService {

    @Autowired
    private AlumniRepository alumniRepository;

    public Alumni registerAlumni(Alumni alumni) {
        // Add any business logic here
        return alumniRepository.save(alumni);
    }

    public Alumni loginAlumni(String email, String password) {
        Alumni alumni = alumniRepository.findByEmail(email);
        if (alumni != null && alumni.getPassword().equals(password)) {
            return alumni;
        }
        return null;
    }

    public boolean resetPassword(String email) {
        Alumni alumni = alumniRepository.findByEmail(email);
        if (alumni != null) {
            // Logic to send reset password link
            return true;
        }
        return false;
    }
}
