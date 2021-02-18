package com.fastcampus.javaallinone.project3.mycontact.Domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Block {
    @Id
    @GeneratedValue
    private long id;

    @NonNull
    private String name;

    private String reason;

    private LocalDate startTime;

    private LocalDate endTime;
}
