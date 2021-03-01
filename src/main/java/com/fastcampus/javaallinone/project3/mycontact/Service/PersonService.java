package com.fastcampus.javaallinone.project3.mycontact.Service;

import com.fastcampus.javaallinone.project3.mycontact.Controller.dto.PersonDto;
import com.fastcampus.javaallinone.project3.mycontact.Domain.Person;
import com.fastcampus.javaallinone.project3.mycontact.Repository.PersonRepository;
import com.fastcampus.javaallinone.project3.mycontact.dto.Birthday;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> getPeopleByName(String name){
        return personRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public Person getPerson(Long id){
        return personRepository.findById(id).orElse(null);          //id검색후 값이없다면 null 리턴
    }

    @Transactional
    public void put(PersonDto personDto) {
        Person person = new Person();
        person.set(personDto);
        person.setName(personDto.getName());

        personRepository.save(person);
    }

    @Transactional
    public void modify(Long id, PersonDto personDto) {
        Person person = personRepository.findById(id).orElseThrow(()->new RuntimeException("아이디가 존재하지않습니다."));

        if(!person.getName().equals(personDto.getName())){
            throw new RuntimeException("이름이 다릅니다.");
        }
        if(personDto.getBirthday() != null) {
            person.setBirthday(Birthday.of(personDto.getBirthday()));
        }
        person.set(personDto);

        personRepository.save(person);
    }

    @Transactional
    public void modify(Long id, String name){
        Person person = personRepository.findById(id).orElseThrow(()-> new RuntimeException("아이디가 존재하지 않습니다."));

        person.setName(name);

        personRepository.save(person);
    }

    @Transactional
    public void delete(Long id) {

        Person person = personRepository.findById(id).orElseThrow(()-> new RuntimeException("아이디가 존재하지 않습니다."));

        person.setDeleted(true);

        personRepository.save(person);
    }
}
