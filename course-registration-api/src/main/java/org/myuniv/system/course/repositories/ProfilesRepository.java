package org.myuniv.system.course.repositories;

import org.myuniv.system.course.beans.dao.ProfileEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProfilesRepository extends CrudRepository<ProfileEntity, Long> {
    ProfileEntity findByUserId(String userId);
}
