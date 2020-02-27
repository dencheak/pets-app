package denis.lishchuk.pets.controller;

import denis.lishchuk.pets.dto.request.UserRequest;
import denis.lishchuk.pets.dto.response.UserResponse;
import denis.lishchuk.pets.exception.InputDataException;
import denis.lishchuk.pets.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public List<UserResponse> findAll(){
        return userService.findAll();
    }

    @PostMapping("/{login}")
    public UserResponse findByLogin(@PathVariable String login) throws InputDataException {return  userService.findByLogin(login);}

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
