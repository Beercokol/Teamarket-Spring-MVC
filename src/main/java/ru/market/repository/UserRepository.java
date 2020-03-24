/*
 * Copyright
 */

package ru.market.repository;

import ru.market.model.user.User;
import ru.market.model.user.UserRole;

import java.util.Collection;

public interface UserRepository extends MainRepository<User> {

    User findByName(String name);

    User findByUsername(String username);

    User findByRole(UserRole role);

    Collection<User> findAllByRole(UserRole role);

    void deleteAllByRole(UserRole role);

    void deleteByName(String name);
}
