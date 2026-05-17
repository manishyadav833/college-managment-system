package com.college.website.controller;

import com.college.website.entity.ContactMessage;
import com.college.website.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private ContactMessageRepository contactMessageRepository;
    @PostMapping("/send-message")
    public String sendMessage(

            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String message

    ){

        // SAVE DATABASE
        ContactMessage contactMessage = new ContactMessage();

        contactMessage.setName(name);
        contactMessage.setEmail(email);
        contactMessage.setPhone(phone);
        contactMessage.setMessage(message);

        contactMessageRepository.save(contactMessage);

        // EMAIL SEND
        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo("kamlashsatish@gmail.com");
        mail.setSubject("For Admission");

        mail.setText(
                "Name: " + name +
                        "\nEmail: " + email +
                        "\nPhone: " + phone +
                        "\n\nMessage:\n" + message
        );

        mailSender.send(mail);

        return "redirect:/contact1";
    }

}