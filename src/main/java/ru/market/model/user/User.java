/*
 * Copyright
 */

package ru.market.model.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.market.model.model.Model;
import ru.market.model.order.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public final class User extends Model implements UserDetails {
    /**
     * Номер версии класса необходимый для десериализации и сериализации.
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "name", nullable = false)
    private String name = "";

    @Column(name = "username")
    private String username = "";

    @Column(name = "password")
    private String password = "";

    @Column(
            name = "email",
            nullable = false
    )
    private String email = "";

    @Column(
            name = "phone",
            nullable = false
    )
    private String phone = "";

    @Column(name = "vkontakte")
    private String vkontakte = "";

    @Column(name = "facebook")
    private String facebook = "";

    @Column(name = "skype")
    private String skype = "";

    @Column(name = "description")
    private String description = "";

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.CLIENT;

    /**
     * Список заказов, которые сделал текущий клиент.
     */
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "client",
            cascade = CascadeType.REMOVE
    )
    private Collection<Order> clientOrders = new ArrayList<>();

    /**
     * Список заказов, которые обработал текущий менеджер.
     */
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "manager",
            cascade = CascadeType.REMOVE
    )
    private Collection<Order> managerOrders = new ArrayList<>();

    protected User() {
    }

    /**
     * Возвращает описание пользователя.
     */
    @Override
    public String toString() {
        return "Name: " + this.name
                + "\nRole: " + this.role.name()
                + "\nEmail: " + this.email
                + "\nPhone: " + this.phone;
    }

    /**
     * Сравнивает текущий объект с объектом переданым как параметр.
     */
    @Override
    public boolean equals(Object object) {
        boolean result = super.equals(object);
        if (result) {
            final User user = (User) object;
            result = this.name.equals(user.name) &&
                    this.username.equals(user.username) &&
                    this.email.equals(user.email) &&
                    this.phone.equals(user.phone) &&
                    this.role.equals(user.role);
        }
        return result;
    }

    @Override
    public int hashCode() {
        int result = this.name.hashCode();
        result = 31 * result + this.username.hashCode();
        result = 31 * result + this.email.hashCode();
        result = 31 * result + this.phone.hashCode();
        result = 31 * result + this.role.hashCode();
        return result;
    }

    /**
     * Возвращает значение типа boolean в зависемости от срока действия аккаунта.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Возвращает значение типа boolean от того,
     * заблокирован текущий аккаунт (пользователь) или нет.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Возвращает значение типа boolean от того, активны ли права (полномичия)
     * данного аккаунта или нет.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Возвращает значение типа boolean от того,
     * активный ли текущий аккаунт или нет.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Возвращает список всех ролей пользователя через объект-обертку
     * класса SimpleGrantedAuthority.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> roles = new ArrayList<>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + this.role.name());
        roles.add(authority);
        return roles;
    }


    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public String getVkontakte() {
        return this.vkontakte;
    }

    public void setVkontakte(final String vkontakte) {
        this.vkontakte = vkontakte;
    }

    public String getFacebook() {
        return this.facebook;
    }

    public void setFacebook(final String facebook) {
        this.facebook = facebook;
    }

    public String getSkype() {
        return this.skype;
    }

    public void setSkype(final String skype) {
        this.skype = skype;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public UserRole getRole() {
        return this.role;
    }

    public void setRole(final UserRole role) {
        this.role = role;
    }

    public void setClientOrders(final Collection<Order> orders) {
        this.clientOrders = new ArrayList<>(orders);
    }

    public void setManagerOrders(final Collection<Order> orders) {
        this.managerOrders = new ArrayList<>(orders);
    }

    public static UserBuilder getBuilder() {
        return new UserBuilder();
    }
}
