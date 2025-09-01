package com.example.agency.controller;

import com.example.agency.model.ContactForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.mail.internet.MimeMessage;

@Controller
public class HomeController {

    @Autowired(required = false)
    private JavaMailSender mailSender;

    @Value("${app.gtmId:}")
    private String gtmId;

    @ModelAttribute("form")
    public ContactForm form() {
        return new ContactForm();
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("page", "home");
        return "index";
    }

    @GetMapping("/services")
    public String services(Model model) {
        model.addAttribute("page", "services");
        return "services";
    }

    @GetMapping("/portfolio")
    public String portfolio(Model model) {
        model.addAttribute("page", "portfolio");
        return "portfolio";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("page", "contact");
        return "contact";
    }

    @PostMapping("/contact")
    public String handleContact(@Valid @ModelAttribute("form") ContactForm form,
                                BindingResult binding,
                                RedirectAttributes ra,
                                Model model) {
        if (binding.hasErrors()) {
            model.addAttribute("page", "contact");
            return "contact";
        }

        try {
            if (mailSender != null) {
                MimeMessage msg = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(msg, "UTF-8");

                helper.setTo(System.getProperty("agency.contact.to", "you@example.com"));
                helper.setReplyTo(form.getEmail());
                helper.setSubject("Запитване от сайта - " + form.getName());

                String text = """
                        Име: %s
                        Имейл: %s
                        Телефон: %s

                        Съобщение:
                        %s
                        """.formatted(
                        form.getName(),
                        form.getEmail(),
                        form.getPhone() == null ? "" : form.getPhone(),
                        form.getMessage()
                );

                helper.setText(text, false);
                mailSender.send(msg);

                ra.addFlashAttribute("flashMessage", "Благодарим! Ще се свържем с вас съвсем скоро.");
                ra.addFlashAttribute("flashOk", true);
            } else {
                ra.addFlashAttribute("flashMessage", "Съобщението е записано локално (имейлът не е конфигуриран).");
                ra.addFlashAttribute("flashOk", true);
            }
        } catch (Exception e) {
            ra.addFlashAttribute("flashMessage", "Възникна проблем при изпращане. Опитайте отново или ни пишете директно.");
            ra.addFlashAttribute("flashOk", false);
        }

        return "redirect:/contact";
    }
}
