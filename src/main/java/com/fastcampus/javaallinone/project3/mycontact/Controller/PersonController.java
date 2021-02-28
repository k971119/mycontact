package com.fastcampus.javaallinone.project3.mycontact.Controller;

import com.fastcampus.javaallinone.project3.mycontact.Controller.dto.PersonDto;
import com.fastcampus.javaallinone.project3.mycontact.Domain.Person;
import com.fastcampus.javaallinone.project3.mycontact.Repository.PersonRepository;
import com.fastcampus.javaallinone.project3.mycontact.Service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/person")
@RestController
@Slf4j
public class PersonController {
    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable Long id){
        return personService.getPerson(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)     //해당정보가 생성된걸 명확하게 리턴하는 상태값
    public void postPerson(@RequestBody Person person){          //default Requestparam
        personService.put(person);
        
        log.info("person -> {}",personRepository.findAll());
    }

    @PutMapping(value = "/{id}")
    public void modifyPerson(@PathVariable Long id,@RequestBody PersonDto personDto){
        personService.modify(id,personDto);

        log.info("{}",personRepository.findAll());
    }

    @PatchMapping(value = "/{id}")
    public void modifyPerson(@PathVariable Long id, String name){
        personService.modify(id, name);

        log.info("{}",personRepository.findAll());
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id){
        personService.delete(id);

        log.info("{}",personRepository.findAll());
        log.info("person deleted : {}",personRepository.findPeopleDeleted());
    }
}
