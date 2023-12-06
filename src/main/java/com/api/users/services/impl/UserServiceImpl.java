package com.api.users.services.impl;

import com.api.users.domain.Role;
import com.api.users.domain.User;
import com.api.users.dto.RoleDto;
import com.api.users.dto.UserDto;
import com.api.users.dto.UserInsertDto;
import com.api.users.repositories.RoleRepository;
import com.api.users.repositories.UserRepository;
import com.api.users.services.UserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(final UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        Page<User> list = userRepository.findAll(pageable);
        return list.map(UserDto::new);
    }

    @Override
    public UserDto findById(Long id) {
        User entity = userRepository.findById(id).orElseThrow(() -> new RuntimeException("erro"));
        return new UserDto(entity);
    }

    @Transactional
    @Override
    public UserDto save(UserInsertDto userDto) {
        User entity = new User();
        copyDtoToEntity(userDto, entity);
        return new UserDto(userRepository.save(entity));
    }

    @Transactional
    @Override
    public UserDto update(Long id, UserDto userDto) {
        try {
            User entity = userRepository.getOne(id);
            copyDtoToEntity(userDto, entity);
            return new UserDto(userRepository.save(entity));
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("User not found with id: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Integrity violation");
        }
    }

    private void copyDtoToEntity(UserDto dto, User entity) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());

        entity.getRoles().clear();
        for (RoleDto roleDto : dto.getRoles()) {
            Role role = roleRepository.getOne(roleDto.getId());
            entity.getRoles().add(role);
        }
    }
}
