package denis.lishchuk.pets.service;

import denis.lishchuk.pets.dto.request.UserRequest;
import denis.lishchuk.pets.dto.response.UserResponse;
import denis.lishchuk.pets.entity.Role;
import denis.lishchuk.pets.entity.User;
import denis.lishchuk.pets.exception.InputDataException;
import denis.lishchuk.pets.repository.UserRepository;
import denis.lishchuk.pets.security.tokenUtils.TokenTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {


    @Autowired
    private ShelterService shelterService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenTool tokenTool;


    public String save(UserRequest request) throws Exception {
        if (userRepository.findByLoginEquals(request.getLogin()).isPresent()) {
            throw new Exception("Credentials are busy. Please, try one more time " +
                    "with other logib");
        }
        User user = new User();
        user.setLogin(request.getLogin());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        if(request.isCheck()) {
            user.setRole(Role.SHELTER_ADMIN);
        }
        else{
            user.setRole(Role.USER);
        }
        user = userRepository.saveAndFlush(user);

        return tokenTool.createToken(user.getLogin(), user.getRole().name());
    }


    public String findOneByRequest(UserRequest userRequest) throws InputDataException {
        User user = userRepository.findByLoginEquals(userRequest.getLogin()).orElseThrow(() -> new InputDataException("User with login " + userRequest.getLogin() + " not exists"));

        if (passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
            return tokenTool.createToken(user.getLogin(), user.getRole().name());
        }

        throw new IllegalArgumentException("Wrong login or password");
    }

    public UserResponse findByLogin(String login) throws InputDataException {
       return new UserResponse(userRepository.findByLoginEquals(login).orElseThrow(() -> new InputDataException("User with login not exists")));

    }

    public UserResponse update(UserRequest userRequest, Long id)throws InputDataException{
        User user = findOne(id);
        return new UserResponse(userRepository.save(userRequestToUser(userRequest, user)));
    }

    public User findOne(Long id)throws InputDataException {
        return userRepository.findById(id).orElseThrow(() -> new InputDataException("User with id" + id + "not exists"));
    }

    public User userRequestToUser(UserRequest userRequest, User user) throws InputDataException {
        if(user == null)
            user = new User();
        user.setLogin(userRequest.getLogin());
        user.setPassword(userRequest.getPassword());
        if(shelterService.findOne(userRequest.getShelterId())!=null)
            user.setShelter(shelterService.findOne(userRequest.getShelterId()));
        return user;
    }

    public void delete(Long id) throws InputDataException{
        userRepository.delete(findOne(id));
    }

    public List<UserResponse> findAll(){
        return userRepository.findAll().stream().map(UserResponse::new).collect(Collectors.toList());
    }
}
