package com.example.schedule.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name ="schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String text;
    private String name;

    public Schedule(String title, String text, String name) {
        this.title = title;
        this.text = text;
        this.name = name;
    }

    public void update(String title, String text, String name) {
        this.title = title;
        this.text = text;
        this.name = name;
    }

}
