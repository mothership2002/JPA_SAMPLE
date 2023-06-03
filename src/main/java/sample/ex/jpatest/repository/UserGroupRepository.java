package sample.ex.jpatest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.ex.jpatest.domain.entity.UserGroup;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
}
