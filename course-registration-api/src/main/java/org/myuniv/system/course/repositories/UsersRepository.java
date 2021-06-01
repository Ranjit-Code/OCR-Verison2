package org.myuniv.system.course.repositories;

import org.myuniv.system.course.beans.dao.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
}
