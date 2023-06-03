package sample.ex.jpatest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sample.ex.jpatest.domain.entity.UserGroup;

import java.util.List;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
    List<UserGroup> findAllByMenuGroupId(Long MenuGroupId);
}
