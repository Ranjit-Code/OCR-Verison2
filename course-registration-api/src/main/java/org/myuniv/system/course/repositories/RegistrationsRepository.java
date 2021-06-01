package org.myuniv.system.course.repositories;

import org.myuniv.system.course.beans.dao.RegistrationEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RegistrationsRepository extends CrudRepository<RegistrationEntity, Long> {
    Optional<RegistrationEntity> findByCourseIdAndStudentId(long courseId, String studentId);
}
