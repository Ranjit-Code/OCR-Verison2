package org.myuniv.system.course.repositories;

import org.myuniv.system.course.beans.dao.CourseEntity;
import org.springframework.data.repository.CrudRepository;

public interface CoursesRepository extends CrudRepository<CourseEntity, Long> {
}
