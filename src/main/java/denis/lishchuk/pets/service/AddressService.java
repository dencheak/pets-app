package denis.lishchuk.pets.service;

import denis.lishchuk.pets.dto.request.AddressRequest;
import denis.lishchuk.pets.dto.response.AddressResponse;
import denis.lishchuk.pets.entity.Address;
import denis.lishchuk.pets.exception.InputDataException;
import denis.lishchuk.pets.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;


    public Address addressRequestToAddress(AddressRequest addressRequest, Address address){
        if(address == null)
            address = new Address();
        address.setCountry(addressRequest.getCountry());
        address.setCity(addressRequest.getCity());
        address.setStreet(addressRequest.getStreet());
        address.setNumberOfHouse(addressRequest.getNumberOfHouse());
        return address;
    }
    public AddressResponse save(AddressRequest addressRequest) {

        return new AddressResponse (addressRepository.save(addressRequestToAddress(addressRequest, null)));

    }

    public AddressResponse update(AddressRequest addressRequest, Long id)throws InputDataException {
        Address address = findOne(id);
        return new AddressResponse(addressRepository.save(addressRequestToAddress(addressRequest, address)));
    }

    public Address findOne(Long id)throws InputDataException {
        return addressRepository.findById(id).orElseThrow(() -> new InputDataException("User with id" + id + "not exists"));
    }

    public List<AddressResponse> findAll(){
        return addressRepository.findAll().stream().map(AddressResponse::new).collect(Collectors.toList());
    }

    public void delete(Long id) throws InputDataException{
        addressRepository.delete(findOne(id));
    }


}
