package denis.lishchuk.pets.controller;

import denis.lishchuk.pets.dto.request.UserRequest;
import denis.lishchuk.pets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("/public/register")
    public String saveUser(@RequestBody @Valid UserRequest userRequest) throws Exception {
        return userService.save(userRequest);
    }

    @PostMapping("/public/login")
    public String login(@RequestBody @Valid UserRequest userRequest) throws Exception {
        return userService.findOneByRequest(userRequest);
    }
}
