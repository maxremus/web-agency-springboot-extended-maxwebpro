package com.example.agency.controller;

import com.example.agency.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContactController {

    @Autowired
    private MailService mailService;

    @PostMapping("/send-message")
    public String sendMessage(@RequestParam String name,
                              @RequestParam String email,
                              @RequestParam String message,
                              RedirectAttributes ra) {
        try {
            // 1) Имейл до теб
            String subject = "Ново запитване от " + name;
            String body = "От: " + email + "\n\n" + message;
            mailService.sendToMe(subject, body);

            // 2) Автоматичен отговор към клиента
            mailService.sendAutoReply(email, name);

            ra.addAttribute("sent", "true");
        } catch (Exception ex) {
            ra.addAttribute("error", "mail");
        }
        return "redirect:/contact";
    }
}
