package com.fastcampus.javaallinone.project3.mycontact.Repository;

import com.fastcampus.javaallinone.project3.mycontact.Domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
