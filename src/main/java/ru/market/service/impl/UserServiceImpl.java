/*
 * Copyright
 */

package ru.market.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.market.model.user.User;
import ru.market.model.user.UserRole;
import ru.market.repository.UserRepository;
import ru.market.service.interfaces.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static ru.market.util.validator.ObjectValidator.*;

@Service
@ComponentScan(basePackages = "ru.market.repository")
public  class UserServiceImpl extends MainServiceImpl<User> implements UserService, UserDetailsService {

    /**
     * ID роли клиента в базе данных.
     */
    private final static Long CLIENT_ROLE_ID = 1L;

    /**
     * ID роли адмиистратора в базе данных.
     */
    private final static Long ADMIN_ROLE_ID = 2L;


    /**
     * Реализация интерфейса {@link UserRepository}
     * для работы пользователей с базой данных.
     */
    private final UserRepository repository;

    /**
     * Конструктор для инициализации основных переменных сервиса.
     * Помечаный аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public UserServiceImpl(final UserRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Возвращает пользователя, у которого совпадает имя с
     * значением входящего параметра. Режим только для чтения.
     */
    @Override
    @Transactional(readOnly = true)
    public User getByName(final String name) throws IllegalArgumentException, NullPointerException {
        if (isEmpty(name)) {
            throw new IllegalArgumentException("No user name!");
        }
        final User user = this.repository.findByName(name);
        if (isNull(user)) {
            throw new NullPointerException("Can't find user by name " + name + "!");
        }
        return user;
    }

    /**
     * Возвращает пользователя, у которого совпадает уникальный
     * логин с значением входящего параметра. Режим только для чтения.
     */
    @Override
    @Transactional(readOnly = true)
    public User getByUsername(final String username)
            throws IllegalArgumentException, NullPointerException {
        if (isEmpty(username)) {
            throw new IllegalArgumentException("No username!");
        }
        final User user = this.repository.findByUsername(username);
        if (isNull(user)) {
            throw new NullPointerException("Can't find user by username " + username + "!");
        }
        return user;
    }

    /**
     * Возвращает главного администратора сайта.
     * Режим только для чтения.
     */
    @Override
    @Transactional(readOnly = true)
    public User getMainAdministrator() throws NullPointerException {
        final User user = new ArrayList<>(getAdministrators()).get(0);
        if (isNull(user)) {
            throw new NullPointerException("Can't find administrator!");
        }
        return user;
    }

    /**
     * Возвращает список всех администраторов сайта.
     * Режим только для чтения.
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<User> getAdministrators() {
        return this.repository.findAllByRole(UserRole.ADMIN);
    }

    /**
     * Возвращает список всех клиентов сайта.
     * Режим только для чтения.
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<User> getClients() {
        return this.repository.findAllByRole(UserRole.CLIENT);
    }

    /**
     * Возвращает список персонала сайта.
     * Режим только для чтения.
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<User> getPersonnel() {
        final List<User> users = new ArrayList<>();
        users.addAll(getAdministrators());
        return users;
    }

    /**
     * Возвращает авторизированого пользователя.
     * Режим только для чтения.
     */
    @Override
    @Transactional(readOnly = true)
    public User getAuthenticatedUser() {
        User user;
        try {
            final SecurityContext context = SecurityContextHolder.getContext();
            final Authentication authentication = context.getAuthentication();
            user = (User) authentication.getPrincipal();
        } catch (Exception ex) {
            ex.printStackTrace();
            user = null;
        }
        return user;
    }

    /**
     * Удаляет пользователя, у которого совпадает имя с значением входящего параметра.
     */
    @Override
    @Transactional
    public void removeByName(final String name) {
        if (isNotEmpty(name)) {
            this.repository.deleteByName(name);
        }
    }

    /**
     * Удаляет пользователя из базы даных, у которого совпадает
     * роль с значением входящего параметра.
     */
    @Override
    @Transactional
    public void removeByRole(final UserRole role) {
        if (isNotNull(role)) {
            this.repository.deleteAllByRole(role);
        }
    }

    /**
     * Удаляет список персонала сайта.
     */
    @Override
    @Transactional
    public void removePersonnel() {
        final Collection<User> personnel = getPersonnel();
        if (isNotEmpty(personnel)) {
            final User mainAdmin = getMainAdministrator();
            personnel.remove(mainAdmin);
            this.repository.delete(personnel);
        }
    }

    /**
     * Возвращает пользователя, у которого совпадает уникальный
     * логин с значением входящего параметра. Режим только для чтения.
     * Реализованый метод интерфейса {@link UserDetailsService}.
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        try {
            return getByUsername(username);
        } catch (IllegalArgumentException | NullPointerException ex) {
            throw new UsernameNotFoundException(ex.getMessage(), ex);
        }
    }
}
