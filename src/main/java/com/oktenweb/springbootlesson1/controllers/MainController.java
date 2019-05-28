package com.oktenweb.springbootlesson1.controllers;

import com.oktenweb.springbootlesson1.dbservices.UserService;
import com.oktenweb.springbootlesson1.models.User;
import com.oktenweb.springbootlesson1.services.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class MainController {

//jsahfdhsagfdhagfdhgafsdhgafsd
    private UserService userService;

    //http://localhost:8080/   [get]
//    @RequestMapping(path = "/", method = RequestMethod.GET)
    @GetMapping("/")
    public String home() {
        System.out.println("you are at home");
        return "you are at home";
    }


    //http://localhost:8080/save?name=kokos&surname=kokosv [get]
    @GetMapping("/save")
    public User save(/*@RequestParam String name, @RequestParam String surname*/
            User user
    ) {
//        System.out.println(name); // kokos
//        System.out.println(surname); // kokosov
        System.out.println(user);
        return user;
    }

    @PostMapping("/save")
    public User saveUser(User user) {
        System.out.println(user);
        userService.save(user);

        System.out.println(user);
        return user;
    }

    @GetMapping("/users")
    public List<User> users() {
        List<User> all = userService.findAll();
        return all;
    }

    @GetMapping("/user/{xxx}") //user/1111/name/vasya   /user/{id}/name/{name}
    public User getUser(@PathVariable int xxx) {

//        Optional<User> optionalUser = userService.findById(xxx);
//        User user = optionalUser.get();

        User usr = userService.findById(xxx).get();
        return usr;
    }


    @PostMapping("/upload")
    public String upload(@RequestParam("filexxx") MultipartFile file) throws IOException {

        String path = System.getProperty("user.home") + File.separator + "images" + File.separator + file.getOriginalFilename();
        file.transferTo(new File(path));

        return "ok";
    }


    private EmailService emailService;

    @GetMapping("/email")
    public String sendEmail(@RequestParam String email, @RequestParam MultipartFile file) {
        System.out.println(email);
        emailService.sendEmail(email, file);
        return "";
    }


}
