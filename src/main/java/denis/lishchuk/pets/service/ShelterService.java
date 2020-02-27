package denis.lishchuk.pets.service;

import denis.lishchuk.pets.dto.request.ShelterRequest;
import denis.lishchuk.pets.dto.response.ShelterResponse;
import denis.lishchuk.pets.entity.Shelter;
import denis.lishchuk.pets.exception.InputDataException;
import denis.lishchuk.pets.repository.AddressRepository;
import denis.lishchuk.pets.repository.ShelterRepository;
import denis.lishchuk.pets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShelterService {
    @Autowired
    private ShelterRepository shelterRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserService userService;



    public Shelter shelterRequestToShelter(ShelterRequest shelterRequest, Shelter shelter) throws InputDataException {
        if(shelter == null && userService.findOne(shelterRequest.getUserId()).getShelter() != null)
            throw new InputDataException("you already have a shelter");
        if(shelter==null)
            shelter = new Shelter();
        shelter.setName(shelterRequest.getName());
        shelter.setAdmin(userService.findOne(shelterRequest.getUserId()));
        shelter.setAddress(addressService.findOne(shelterRequest.getAddressId()));
        return shelter;
    }
    public ShelterResponse save(ShelterRequest shelterRequest) throws InputDataException {

        return new ShelterResponse (shelterRepository.save(shelterRequestToShelter(shelterRequest, null)));

    }

    public Shelter findOne(Long id)throws InputDataException {
        return shelterRepository.findById(id).orElseThrow(() -> new InputDataException("Shelter with id" + id + "not exists"));
    }

    public ShelterResponse update(ShelterRequest shelterRequest, Long id)throws InputDataException {
        Shelter shelter = findOne(id);
        return new ShelterResponse(shelterRepository.save(shelterRequestToShelter(shelterRequest, shelter)));
    }

    public void delete(Long id) throws InputDataException{
        shelterRepository.delete(findOne(id));
    }

    public List<ShelterResponse> findAll(){
        return shelterRepository.findAll().stream().map(ShelterResponse::new).collect(Collectors.toList());
    }
}

