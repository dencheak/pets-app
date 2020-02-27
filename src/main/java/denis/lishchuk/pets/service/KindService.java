package denis.lishchuk.pets.service;

import denis.lishchuk.pets.dto.request.KindRequest;
import denis.lishchuk.pets.dto.response.KindResponse;
import denis.lishchuk.pets.entity.Kind;
import denis.lishchuk.pets.exception.InputDataException;
import denis.lishchuk.pets.repository.KindRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KindService {

    @Autowired
    private KindRepository kindRepository;


    public Kind kindRequestToKind(KindRequest kindRequest, Kind kind){
        if(kind == null)
            kind = new Kind();
        kind.setName(kindRequest.getName());
        kind.setBreed(kindRequest.getBreed());
        return kind;
    }

    public KindResponse save(KindRequest kindRequest) {

        return new KindResponse (kindRepository.save(kindRequestToKind(kindRequest, null)));

    }

    public KindResponse update(KindRequest kindRequest, Long id)throws InputDataException {
        Kind kind= findOne(id);
        return new KindResponse(kindRepository.save(kindRequestToKind(kindRequest, kind)));
    }

    public Kind findOne(Long id)throws InputDataException {
        return kindRepository.findById(id).orElseThrow(() -> new InputDataException("Kind with id" + id + "not exists"));
    }

    public List<KindResponse> findAll(){
        return kindRepository.findAll().stream().map(KindResponse::new).collect(Collectors.toList());
    }

    public void delete(Long id) throws InputDataException{
        kindRepository.delete(findOne(id));
    }

    public Kind findByName(String name){
        return kindRepository.findByName(name);
    }
}
