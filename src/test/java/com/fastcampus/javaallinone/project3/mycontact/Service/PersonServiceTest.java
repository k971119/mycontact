package com.fastcampus.javaallinone.project3.mycontact.Service;

import com.fastcampus.javaallinone.project3.mycontact.Domain.Block;
import com.fastcampus.javaallinone.project3.mycontact.Domain.Person;
import com.fastcampus.javaallinone.project3.mycontact.MycontactApplication;
import com.fastcampus.javaallinone.project3.mycontact.Repository.BlockRepository;
import com.fastcampus.javaallinone.project3.mycontact.Repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceTest {
    @Autowired
    private PersonService personService;

    @Test
    void getPerson(){
        Person person = personService.getPerson(3L);

        assertThat(person.getName()).isEqualTo("dennis");
    }

    @Test
    void getPeopleExcludeBlocks(){
        List<Person> result = personService.getPeopleExcludeBlocks();

        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0).getName()).isEqualTo("martin");
        assertThat(result.get(1).getName()).isEqualTo("david");
        assertThat(result.get(2).getName()).isEqualTo("benny");
    }
}