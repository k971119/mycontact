package com.fastcampus.javaallinone.project3.mycontact.Repository;

import com.fastcampus.javaallinone.project3.mycontact.Domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;
    @Test
    void crud(){
        Person person = new Person();
        person.setName("martin");
        person.setAge(10);
        person.setBloodType("A");

        personRepository.save(person);

        System.out.println(personRepository.findAll());

        List<Person> people = personRepository.findAll();

        assertThat(people.size()).isEqualTo(1);
        assertThat(people.get(0).getName()).isEqualTo("martin");
        assertThat(people.get(0).getAge()).isEqualTo(10);
    }

    @Test
    void hashCodeAndEqauls(){
        Person person1 = new Person("martin", 10, "A");
        Person person2 = new Person("martin", 10, "A");

        System.out.println(person1.equals(person2));
        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());

        Map<Person, Integer> map =new HashMap<>();
        map.put(person1, person1.getAge());

        System.out.println(map);
        System.out.println(map.get(person2));
    }

    @Test
    void findByBloodType(){
        givenPerson("martin",10,"A");
        givenPerson("david",9,"B");
        givenPerson("dennis",8,"O");
        givenPerson("sophia",7,"AB");
        givenPerson("benny",6,"A");
        givenPerson("john",5,"A");

        List<Person> result = personRepository.findByBloodType("A");

        result.forEach(System.out::println);
    }

    @Test
    void findByBirthdayBetween(){
        givenPerson("martin",10,"A",LocalDate.of(1991,8,15));
        givenPerson("david",9,"B",LocalDate.of(1992,5,5));
        givenPerson("dennis",8,"O",LocalDate.of(1997,11,19));
        givenPerson("sophia",7,"AB",LocalDate.of(1993,8,22));
        givenPerson("benny",6,"A",LocalDate.of(1998,5,1));
        givenPerson("john",5,"A",LocalDate.of(1999,1,10));

        List<Person> result = personRepository.findByBirthdayBetween(LocalDate.of(1991,8,1),LocalDate.of(1991,8,31));

        result.forEach(System.out::println);
    }

    private void givenPerson(String name, int age, String bloodType){
        givenPerson(name,age,bloodType,null);
    }

    private void givenPerson(String name, int age, String bloodType,LocalDate birthday){
        Person person = new Person(name, age, bloodType);
        person.setBirthday(birthday);
        personRepository.save(person);
    }
}