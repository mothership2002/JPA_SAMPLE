package sample.ex.jpatest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.ex.jpatest.domain.entity.MenuGroup;

public interface UserGroupRepository extends JpaRepository<MenuGroup, Long> {
}
