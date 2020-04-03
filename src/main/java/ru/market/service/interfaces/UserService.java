/*
 * Copyright
 */

package ru.market.service.interfaces;

import ru.market.model.user.User;
import ru.market.model.user.UserRole;

import java.util.Collection;
import java.util.List;

public interface UserService extends MainService<User> {
    /**
     * Возвращает пользователя, у которого совпадает
     * имя с значением входящего параметра.
     */
    User getByName(String name);

    /**
     * Возвращает пользователя, у которого совпадает уникальный
     * логин с значением входящего параметра.
     */
    User getByUsername(String username);

    /**
     * Возвращает главного администратора сайта.
     */
    User getMainAdministrator();

    /**
     * Возвращает список всех администраторов сайта.
     */
    Collection<User> getAdministrators();


    /**
     * Возвращает список всех клиентов сайта.
     */
    Collection<User> getClients();

    /**
     * Возвращает список персонала сайта.
     */
    Collection<User> getPersonnel();

    /**
     * Возвращает авторизированого пользователя.
     */
    User getAuthenticatedUser();

    /**
     * Удаляет пользователя, у которого совпадает
     * имя с значением входящего параметра.
     */
    void removeByName(String name);

    /**
     * Удаляет пользователя из базы даных, у которого совпадает
     * роль с значением входящего параметра.
     */
    void removeByRole(UserRole role);

    /**
     * Удаляет список персонала сайта.
     */
    void removePersonnel();
}
