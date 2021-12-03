package com.the.hackers.recipeproject.data.transfer.object;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class MyEventPlans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String planName;
    private String venue;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date eventDate;
}
