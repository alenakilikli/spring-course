package com.example.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class OnlineCourse {
    Integer id;
    String name;
    List<Students> studentsList;
    boolean isClosed;
    LocalDateTime updateOn;
    LocalDateTime createOn;

    public OnlineCourse() {

    }
}
