/*
 * Copyright
 */

package ru.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.market.model.model.Model;

@NoRepositoryBean
/**
 * JpaRepository – это интерфейс фреймворка Spring Data предоставляющий набор
 * стандартных методов JPA для работы с БД.
 */
public interface MainRepository<T extends Model> extends JpaRepository<T, Long> {
}
