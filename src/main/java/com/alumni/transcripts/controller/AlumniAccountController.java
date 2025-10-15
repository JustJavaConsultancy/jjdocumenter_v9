package com.alumni.transcripts.controller;

import com.alumni.transcripts.model.AlumniAccount;
import com.alumni.transcripts.service.AlumniAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/alumni")
public class AlumniAccountController {

    @Autowired
    private AlumniAccountService alumniAccountService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("alumniAccount", new AlumniAccount());
        return "alumni/register";
    }

    @PostMapping("/register")
    public String registerAlumni(@ModelAttribute AlumniAccount alumniAccount) {
        alumniAccountService.registerAlumni(alumniAccount);
        return "redirect:/alumni/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "alumni/login";
    }

    @PostMapping("/login")
    public String loginAlumni(@RequestParam String email, @RequestParam String password, Model model) {
        AlumniAccount alumniAccount = alumniAccountService.loginAlumni(email, password);
        if (alumniAccount != null) {
            model.addAttribute("alumniAccount", alumniAccount);
            return "alumni/dashboard";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "alumni/login";
        }
    }

    @GetMapping("/reset-password")
    public String showResetPasswordForm() {
        return "alumni/reset-password";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String email, Model model) {
        boolean isReset = alumniAccountService.resetPassword(email);
        if (isReset) {
            model.addAttribute("message", "Password reset link has been sent to your email");
        } else {
            model.addAttribute("error", "Email not found");
        }
        return "alumni/reset-password";
    }
}
