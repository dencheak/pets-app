package denis.lishchuk.pets.service;

import denis.lishchuk.pets.dto.request.PaginationRequest;
import denis.lishchuk.pets.dto.request.PetFilterRequest;
import denis.lishchuk.pets.dto.request.PetRequest;
import denis.lishchuk.pets.dto.response.DataResponse;
import denis.lishchuk.pets.dto.response.PetResponse;
import denis.lishchuk.pets.dto.response.UserResponse;
import denis.lishchuk.pets.entity.Pet;
import denis.lishchuk.pets.exception.InputDataException;
import denis.lishchuk.pets.repository.PetRepository;
import denis.lishchuk.pets.specification.PetSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private KindService kindService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private FileService fileService;


    public Pet petRequestToPet(PetRequest petRequest, Pet pet) throws InputDataException {
        if(pet == null)
            pet = new Pet();
        pet.setName(petRequest.getName());
        pet.setKind(kindService.findOne(petRequest.getKindId()));
        pet.setAddress(addressService.findOne(petRequest.getAddressId()));
       // pet.setImagePath(fileService.IMG_DIR);
        return pet;
    }

    public PetResponse save(PetRequest petRequest) throws InputDataException {

        return new PetResponse (petRepository.save(petRequestToPet(petRequest, null)));

    }

    public PetResponse update(PetRequest petRequest, Long id)throws InputDataException {
        Pet pet = findOne(id);
        return new PetResponse(petRepository.save(petRequestToPet(petRequest, pet)));
    }

    public Pet findOne(Long id)throws InputDataException {
        return petRepository.findById(id).orElseThrow(() -> new InputDataException("User with id" + id + "not exists"));
    }

    public void delete(Long id) throws InputDataException{
        petRepository.delete(findOne(id));
    }

    public List<PetResponse> findAll(){
        return petRepository.findAll().stream().map(PetResponse::new).collect(Collectors.toList());
    }

    public DataResponse<PetResponse> findAll(PaginationRequest paginationRequest){
        Page<Pet> page = petRepository.findAll(paginationRequest.toPageRequest());

        return new DataResponse<>(page.get().map(PetResponse::new).collect(Collectors.toList()), page.getTotalPages(), page.getTotalElements());

    }

    public DataResponse<PetResponse> findByFilter(PetFilterRequest filterRequest){
        Page <Pet> page = petRepository.findAll(new PetSpecification(filterRequest), filterRequest.getPagination().toPageRequest());
        return new DataResponse<>(page.get().map(PetResponse::new).collect(Collectors.toList()), page.getTotalPages(), page.getTotalElements());
    }
}
