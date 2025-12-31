package com.example.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleGetResponse {
    private final Long id;
    private final String title;
    private final String text;
    private final String name;

    public ScheduleGetResponse(Long id, String title, String text, String name) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.name = name;
    }
}
