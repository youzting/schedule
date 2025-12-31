package com.example.schedule.service;

import com.example.schedule.dto.*;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleCreateResponse save(ScheduleCreateRequest request){
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getText(),
                request.getName()
        );
        Schedule s = scheduleRepository.save(schedule);
        return new ScheduleCreateResponse(
                s.getId(),
                s.getTitle(),
                s.getText(),
                s.getName()
        );
    }

    @Transactional(readOnly = true)
    public List<ScheduleGetResponse> getAll(){
        List<Schedule> schedules = scheduleRepository.findAll();
        List<ScheduleGetResponse> dtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            ScheduleGetResponse dto = new ScheduleGetResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getText(),
                    schedule.getName()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public ScheduleGetResponse getOne(Long scheduleId){
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("일정이 없습니다")
        );
        return new ScheduleGetResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getText(),
                schedule.getName()
        );
    }

    @Transactional
    public ScheduleUpdateResponse update(Long scheduleId, ScheduleUpdateRequest request){
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                ()  -> new IllegalStateException("일정이 없습니다")
        );
        schedule.update(request.getTitle(), request.getText(), request.getName());
        return new ScheduleUpdateResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getText(),
                schedule.getName()
        );
    }

    @Transactional
    public void delete(Long scheduleId){
        boolean existence =  scheduleRepository.existsById(scheduleId);
        if(!existence){
            throw new IllegalStateException("일정이 없습니다.");
        }
        scheduleRepository.deleteById(scheduleId);
    }



}
