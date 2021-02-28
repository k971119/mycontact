package com.fastcampus.javaallinone.project3.mycontact.Repository;

import com.fastcampus.javaallinone.project3.mycontact.Domain.Person;
import com.fastcampus.javaallinone.project3.mycontact.dto.Birthday;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    void crud(){
        Person person = new Person();
        person.setName("john");
        person.setBloodType("A");

        personRepository.deleteAll();

        personRepository.save(person);

        //List<Person> result = personRepository.findByName("john");
        List<Person> result = personRepository.findAll();

        personRepository.findAll().forEach(System.out::println);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("john");
        //assertThat(result.get(0).getAge()).isEqualTo(10);
        assertThat(result.get(0).getBloodType()).isEqualTo("A");
    }

    @Test
    void findByBloodType(){


        List<Person> result = personRepository.findByBloodType("A");

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getName()).isEqualTo("martin");
        assertThat(result.get(1).getName()).isEqualTo("benny");

        result.forEach(System.out::println);
    }

    @Test
    void findByBirthdayBetween(){

        List<Person> result = personRepository.findByMonthOfBirthday(8);

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getName()).isEqualTo("martin");
        assertThat(result.get(1).getName()).isEqualTo("sophia");
    }
}