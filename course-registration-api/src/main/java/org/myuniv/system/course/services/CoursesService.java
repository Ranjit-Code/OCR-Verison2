package org.myuniv.system.course.services;

import org.myuniv.system.course.beans.dto.CourseDto;
import org.springframework.stereotype.Service;

@Service public interface CoursesService {
    CourseDto createCourse(CourseDto courseDto);

    CourseDto updateCourse(CourseDto courseDto);

    CourseDto retrieveCourse(Long courseId);

    void deleteCourse(Long courseId);
}
