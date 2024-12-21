package pl.beganov.myuni.service.core;

import pl.beganov.myuni.entity.Schedule;

public interface ScheduleService {
    Schedule findScheduleByUserId(Long userId);

    void saveSchedule(Schedule schedule);
}
