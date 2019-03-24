package denis.lishchuk.pets.controller;


import denis.lishchuk.pets.dto.request.AddressRequest;
import denis.lishchuk.pets.dto.request.UserRequest;
import denis.lishchuk.pets.dto.response.AddressResponse;
import denis.lishchuk.pets.dto.response.UserResponse;
import denis.lishchuk.pets.exception.InputDataException;
import denis.lishchuk.pets.service.AddressService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PostMapping
    public AddressResponse save(@RequestBody @Valid AddressRequest addressRequest){
        return addressService.save(addressRequest);
    }

    @GetMapping
    public List<AddressResponse> findAll() {
        return addressService.findAll();
    }

    @PutMapping
    public AddressResponse update(@RequestParam Long id, @RequestBody AddressRequest addressRequest) throws InputDataException {
        return addressService.update(addressRequest, id);
    }

    @SneakyThrows
    @DeleteMapping
    public void delete(@RequestParam Long id) throws InputDataException {
        addressService.delete(id);
    }
}
