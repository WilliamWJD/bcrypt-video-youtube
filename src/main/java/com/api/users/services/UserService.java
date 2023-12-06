package com.api.users.services;

import com.api.users.dto.UserDto;
import com.api.users.dto.UserInsertDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * The interface user service.
 */
public interface UserService {
    /**
     * Find all list.
     *
     * @return the list
     */
    Page<UserDto> findAll(Pageable pageable);

    /**
     * Find by id user dto.
     *
     * @param id the id
     * @return the user dto
     */
    UserDto findById(final Long id);

    /**
     * Save user dto.
     *
     * @param userDto the user dto
     * @return the user dto
     */
    UserDto save(final UserInsertDto userDto);

    /**
     * Update user dto.
     *
     * @param id          the id
     * @param userDto the user dto
     * @return the user dto
     */
    UserDto update(final Long id, final UserDto userDto);

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(final Long id);
}
