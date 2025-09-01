package com.example.agency.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    // Изпращане на имейл към теб
    public void sendToMe(String subject, String body) {
        try {
            MimeMessage mm = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mm, false, "UTF-8");

            helper.setFrom("marian_87_vd@abv.bg"); // твоят акаунт
            helper.setTo("marian_87_vd@abv.bg");   // получател = ти
            helper.setSubject(subject);
            helper.setText(body, false);

            mailSender.send(mm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Автоматичен отговор към клиента
    public void sendAutoReply(String clientEmail, String clientName) {
        try {
            MimeMessage mm = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mm, false, "UTF-8");

            helper.setFrom("marian_87_vd@abv.bg"); // трябва да е твоят акаунт
            helper.setTo(clientEmail);             // имейл на клиента
            helper.setSubject("Благодарим за запитването!");
            helper.setText("Здравей, " + clientName + ",\n\n" +
                    "Благодарим за съобщението! Ще се свържем с теб възможно най-скоро.\n\n" +
                    "Поздрави,\nЕкипът на MaxWebPro", false);

            mailSender.send(mm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
