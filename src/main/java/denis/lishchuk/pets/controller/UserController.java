package denis.lishchuk.pets.controller;

import denis.lishchuk.pets.dto.request.PetRequest;
import denis.lishchuk.pets.dto.request.UserRequest;
import denis.lishchuk.pets.dto.response.PetResponse;
import denis.lishchuk.pets.dto.response.UserResponse;
import denis.lishchuk.pets.exception.InputDataException;
import denis.lishchuk.pets.service.PetService;
import denis.lishchuk.pets.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserResponse save(@RequestBody @Valid UserRequest userRequest) throws InputDataException {
        return userService.save(userRequest);
    }

    @GetMapping
    public List<UserResponse> findAll(){
        return userService.findAll();
    }

    @PutMapping
    public UserResponse update(@RequestParam Long id, @RequestBody UserRequest userRequest) throws InputDataException {
        return userService.update(userRequest, id);
    }

    @SneakyThrows
    @DeleteMapping
    public void delete(@RequestParam Long id) throws InputDataException {
        userService.delete(id);
    }
}
