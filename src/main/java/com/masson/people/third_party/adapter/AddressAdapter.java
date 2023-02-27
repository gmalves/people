package com.masson.people.third_party.adapter;

import com.masson.people.business.domain.Address;
import com.masson.people.business.repository.AddressFinder;
import com.masson.people.business.service.PeopleService;
import com.masson.people.third_party.viacep.ViaCepClient;
import com.masson.people.third_party.viacep.response.AddressResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressAdapter implements AddressFinder {
    @Autowired
    private ViaCepClient viaCepClient;
    @Override
    public Address findByZipCode(String zipCode) {
        return mapFromResponse(viaCepClient.findByZipCode(zipCode));
    }

    private Address mapFromResponse(AddressResponse response){
        var address = new Address();
        address.setCity(response.getLocalidade());
        address.setZipCode(response.getCep());
        address.setComplement(response.getComplemento());
        address.setDistrict(response.getBairro());
        address.setStreet(response.getLogradouro());
        address.setState(response.getUf());
        return address;
    }
}
