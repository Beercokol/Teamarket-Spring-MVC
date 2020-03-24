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
     *
     * @param username Логин пользователя для возврата.
     * @return Объект класса {@link User} - пользователь с логином username.
     */
    User getByUsername(String username);

    /**
     * Возвращает главного администратора сайта.
     *
     * @return Объект класса {@link User} - главный администратор.
     */
    User getMainAdministrator();

    /**
     * Возвращает список всех администраторов сайта.
     *
     * @return Объект типа {@link List} - список администраторов.
     */
    Collection<User> getAdministrators();

    /**
     * Возвращает список всех менеджеров сайта.
     *
     * @return Объект типа {@link List} - список менеджеров.
     */
    Collection<User> getManagers();

    /**
     * Возвращает список всех клиентов сайта.
     *
     * @return Объект типа {@link List} - список клиентов.
     */
    Collection<User> getClients();

    /**
     * Возвращает список персонала сайта.
     *
     * @return Объект типа {@link List} - список персонала.
     */
    Collection<User> getPersonnel();

    /**
     * Возвращает авторизированого пользователя.
     *
     * @return Объект класса {@link User} - авторизированый пользователь.
     */
    User getAuthenticatedUser();

    /**
     * Удаляет пользователя, у которого совпадает
     * имя с значением входящего параметра.
     *
     * @param name Имя пользователя для удаления.
     */
    void removeByName(String name);

    /**
     * Удаляет пользователя из базы даных, у которого совпадает
     * роль с значением входящего параметра.
     *
     * @param role Роль пользователя для удаления.
     */
    void removeByRole(UserRole role);

    /**
     * Удаляет список персонала сайта.
     */
    void removePersonnel();
}
