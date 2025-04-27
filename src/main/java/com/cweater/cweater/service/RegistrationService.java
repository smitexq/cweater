package com.cweater.cweater.service;

import com.cweater.cweater.entities.Role;
import com.cweater.cweater.entities.User;
import com.cweater.cweater.repository.UserRepo;
import com.cweater.cweater.tools.Urls;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class RegistrationService {

    private final UserRepo userRepo;
    private final MailSender mailSender;
    public RegistrationService(UserRepo userRepo, MailSender mailSender) {
        this.userRepo = userRepo;
        this.mailSender = mailSender;
    }

    public String addUser(User user, Map<String, Object> model) {
        Optional<User> userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb.isPresent()) {
            model.put("message","User exist!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        //Код активации аккаунта
        user.setActivationCode(UUID.randomUUID().toString());

        userRepo.save(user);

        StringBuilder msg = new StringBuilder();
        msg.append("Hello, ")
                .append(user.getUsername())
                .append("! Visit the url for activation account: http://")
                .append(Urls.getIp())
                .append("/activate/")
                .append(user.getActivationCode());

        mailSender.send(user.getEmail(),
                "Activate your account",
                String.valueOf(msg));

        return "redirect:/login";
    }

    public String activate(Map<String, Object> model, String code) {
        User user = userRepo.findByActivationCode(code);

        if (user == null) {model.put("message", "Неверный код активации!");}
        else {
            user.setActivationCode(null);
            userRepo.save(user);
            model.put("message", String.format("Аккаунт пользователя %s активирован. Войдите в систему.", user.getUsername()));
        }
        return "login";
    }
}
