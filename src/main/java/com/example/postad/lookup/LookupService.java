package com.example.postad.lookup;


import com.example.postad.exceptionhandle.BaseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LookupService {

    private final LookupRepo lookupRepo;

    public LookupService(LookupRepo lookupRepo) {
        this.lookupRepo = lookupRepo;
    }

    public GenericLookup findOne(String uuid) {
        return this.lookupRepo.findById(uuid)
                .orElseThrow(() -> new BaseException("Lookup not found with given id: " + uuid));

    }

    public List<GenericLookup> findByIntegrationCodeAndNameContains(String code, String name) {
        return this.lookupRepo.findByIntegrationCodeAndNameContains(code, name);
    }

    public List<GenericLookup> findByNameIsContaining(String name) {
        return this.lookupRepo.findByNameIsContaining(name);
    }

    public List<GenericLookup> findByIntegrationCode(String code) {
        return this.lookupRepo.findByIntegrationCode(code);
    }

    public List<GenericLookup> findByClassType(String type) {
        return this.lookupRepo.findByClassType(type);
    }

}