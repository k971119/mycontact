package com.fastcampus.javaallinone.project3.mycontact.Domain;

import com.fastcampus.javaallinone.project3.mycontact.dto.Birthday;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Person {
    @Id
    @GeneratedValue
    private long id;

    @NonNull
    private String name;

    @NonNull
    private int age;

    private String hobby;

    @NonNull
    private String bloodType;

    private String address;

    @Valid
    @Embedded
    private Birthday birthday;

    private String job;

    @ToString.Exclude
    private String phoneNumber;

    @OneToOne
    private Block block;
}
