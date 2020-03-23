package ru.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.market.model.model.Model;

@NoRepositoryBean
public interface MainRepository<T extends Model> extends JpaRepository<T, Long> {
}
