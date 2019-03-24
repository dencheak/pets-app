package denis.lishchuk.pets.controller;

import denis.lishchuk.pets.dto.request.KindRequest;
import denis.lishchuk.pets.dto.request.ShelterRequest;
import denis.lishchuk.pets.dto.request.UserRequest;
import denis.lishchuk.pets.dto.response.KindResponse;
import denis.lishchuk.pets.dto.response.ShelterResponse;
import denis.lishchuk.pets.dto.response.UserResponse;
import denis.lishchuk.pets.exception.InputDataException;
import denis.lishchuk.pets.service.ShelterService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/shelter")
public class ShelterController {
    @Autowired
    private ShelterService shelterService;

    @PostMapping
    public ShelterResponse save(@RequestBody @Valid ShelterRequest shelterRequest) throws InputDataException {
        return shelterService.save(shelterRequest);
    }

    @GetMapping
    public List<ShelterResponse> findAll() {
        return shelterService.findAll();
    }

    @PutMapping
    public ShelterResponse update(@RequestParam Long id, @RequestBody ShelterRequest shelterRequest) throws InputDataException {
        return shelterService.update(shelterRequest, id);
    }

    @SneakyThrows
    @DeleteMapping
    public void delete(@RequestParam Long id) throws InputDataException {
        shelterService.delete(id);
    }
}
