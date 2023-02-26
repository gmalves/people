package com.masson.people.business.repository;

import com.masson.people.business.domain.Address;

public interface AddressFinder {

    Address findByZipCode(String zipCode);
}
