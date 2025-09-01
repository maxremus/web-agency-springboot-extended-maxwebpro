package com.example.agency.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ContactForm {

    @NotBlank(message = "Името е задължително")
    @Size(max = 80, message = "Твърде дълго име")
    private String name;

    @NotBlank(message = "Имейлът е задължителен")
    @Email(message = "Невалиден имейл")
    private String email;

    @Size(max = 30, message = "Твърде дълъг телефон")
    private String phone;

    @NotBlank(message = "Моля, напишете съобщение")
    @Size(max = 2000, message = "Съобщението е твърде дълго")
    private String message;

    // getters/setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
