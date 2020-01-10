package com.clbee.pbcms.service;

import java.util.List;

import com.clbee.pbcms.mapper.PersonMapper;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private final PersonMapper personMapper;

    public PersonService(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    public List selectPersonList(){
        return personMapper.selectPersonList();
    }
}
