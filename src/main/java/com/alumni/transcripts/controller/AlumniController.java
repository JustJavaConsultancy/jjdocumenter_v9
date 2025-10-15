package com.alumni.transcripts.controller;

import com.alumni.transcripts.model.Alumni;
import com.alumni.transcripts.service.AlumniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/alumni")
public class AlumniController {

    @Autowired
    private AlumniService alumniService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("alumni", new Alumni());
        return "alumni/register";
    }

    @PostMapping("/register")
    public String registerAlumni(@ModelAttribute Alumni alumni, Model model) {
        Alumni registeredAlumni = alumniService.registerAlumni(alumni);
        model.addAttribute("alumni", registeredAlumni);
        return "alumni/success";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("alumni", new Alumni());
        return "alumni/login";
    }

    @PostMapping("/login")
    public String loginAlumni(@ModelAttribute Alumni alumni, Model model) {
        Alumni loggedInAlumni = alumniService.loginAlumni(alumni.getEmail(), alumni.getPassword());
        if (loggedInAlumni != null) {
            model.addAttribute("alumni", loggedInAlumni);
            return "alumni/dashboard";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "alumni/login";
        }
    }

    @GetMapping("/reset-password")
    public String showResetPasswordForm(Model model) {
        model.addAttribute("alumni", new Alumni());
        return "alumni/reset-password";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@ModelAttribute Alumni alumni, Model model) {
        boolean isReset = alumniService.resetPassword(alumni.getEmail());
        if (isReset) {
            model.addAttribute("message", "Password reset link has been sent to your email");
        } else {
            model.addAttribute("error", "Email not found");
        }
        return "alumni/reset-password";
    }
}
