package denis.lishchuk.pets.controller;

import denis.lishchuk.pets.dto.request.PaginationRequest;
import denis.lishchuk.pets.dto.request.PetFilterRequest;
import denis.lishchuk.pets.dto.request.PetRequest;
import denis.lishchuk.pets.dto.request.UserRequest;
import denis.lishchuk.pets.dto.response.DataResponse;
import denis.lishchuk.pets.dto.response.PetResponse;
import denis.lishchuk.pets.dto.response.UserResponse;
import denis.lishchuk.pets.entity.Pet;
import denis.lishchuk.pets.exception.InputDataException;
import denis.lishchuk.pets.service.PetService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping("/filter")
    public DataResponse<PetResponse> findAllByFilter(@RequestBody PetFilterRequest filterRequest){
        return petService.findByFilter(filterRequest);
    }

    @PostMapping
    public PetResponse save(@RequestBody @Valid PetRequest petRequest) throws InputDataException {
        return petService.save(petRequest);
    }

    @PostMapping("/page")
    public DataResponse<PetResponse> getPage(@RequestBody  PaginationRequest paginationRequest){
        return petService.findAll(paginationRequest);
    }

    @GetMapping("/get")
    public List <PetResponse> findAll(){
        return petService.findAll();
    }

    @PutMapping
    public PetResponse update(@RequestParam Long id, @RequestBody PetRequest petRequest) throws InputDataException {
        return petService.update(petRequest, id);
    }

    @SneakyThrows
    @DeleteMapping
    public void delete(@RequestParam Long id) throws InputDataException {
        petService.delete(id);
    }
}
