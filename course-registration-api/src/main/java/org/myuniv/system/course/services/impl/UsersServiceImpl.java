package org.myuniv.system.course.services.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.myuniv.system.course.beans.dao.UserEntity;
import org.myuniv.system.course.beans.dto.UserDto;
import org.myuniv.system.course.repositories.UsersRepository;
import org.myuniv.system.course.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service public class UsersServiceImpl implements UsersService {

    UsersRepository       usersRepository;
    BCryptPasswordEncoder encoder;

    @Autowired public UsersServiceImpl(UsersRepository usersRepository, BCryptPasswordEncoder encoder) {
        this.usersRepository = usersRepository;
        this.encoder = encoder;
    }

    public UserDto createUser(UserDto userDetails) {

        userDetails.setUserId(UUID.randomUUID().toString());
        userDetails.setEncryptedPassword(encoder.encode(userDetails.getPassword()));
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = mapper.map(userDetails, UserEntity.class);

        usersRepository.save(userEntity);

        UserDto returnValue = mapper.map(userEntity, UserDto.class);

        return returnValue;
    }

    public UserDto getUserDetailsByEmail(String email) {
        UserEntity userEntity = usersRepository.findByEmail(email);

        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }

        return new ModelMapper().map(userEntity, UserDto.class);
    }

    @Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = usersRepository.findByEmail(username);

        if (userEntity == null) {
            throw new UsernameNotFoundException(username);
        }

        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true, true, true,
            new ArrayList<>());
    }
}
