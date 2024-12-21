package pl.beganov.myuni.service.core.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import pl.beganov.myuni.entity.Schedule;
import pl.beganov.myuni.exception.ResourceNotFoundException;
import pl.beganov.myuni.repository.ScheduleRepository;
import pl.beganov.myuni.service.core.ScheduleService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ScheduleServiceImpl implements ScheduleService {

    ScheduleRepository scheduleRepository;

    @Override
    public Schedule findScheduleByUserId(Long userId) {
        return scheduleRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found for user: "+ userId));
    }

    @Override
    public void saveSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
    }
}
