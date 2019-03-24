package denis.lishchuk.pets.controller;

import denis.lishchuk.pets.dto.request.KindRequest;
import denis.lishchuk.pets.dto.request.UserRequest;
import denis.lishchuk.pets.dto.response.KindResponse;
import denis.lishchuk.pets.dto.response.UserResponse;
import denis.lishchuk.pets.exception.InputDataException;
import denis.lishchuk.pets.service.KindService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/kind")
public class KindController {

    @Autowired
    private KindService kindService;

    @PostMapping
    public KindResponse save(@RequestBody @Valid KindRequest kindRequest){
        return kindService.save(kindRequest);
    }

    @GetMapping("/get")
    public List<KindResponse> findAll() {
        return kindService.findAll();
    }

    @PutMapping
    public KindResponse update(@RequestParam Long id, @RequestBody KindRequest kindRequest) throws InputDataException {
        return kindService.update(kindRequest, id);
    }

    @SneakyThrows
    @DeleteMapping
    public void delete(@RequestParam Long id) throws InputDataException {
        kindService.delete(id);
    }
}
