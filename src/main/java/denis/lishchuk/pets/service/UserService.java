package denis.lishchuk.pets.service;

import denis.lishchuk.pets.dto.request.UserRequest;
import denis.lishchuk.pets.dto.response.UserResponse;
import denis.lishchuk.pets.entity.User;
import denis.lishchuk.pets.exception.InputDataException;
import denis.lishchuk.pets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShelterService shelterService;

    public UserResponse save(UserRequest userRequest) throws InputDataException {

       return new UserResponse (userRepository.save(userRequestToUser(userRequest, null)));

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
