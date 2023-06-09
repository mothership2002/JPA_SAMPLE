package sample.ex.jpatest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sample.ex.jpatest.domain.entity.Menu;

import java.util.List;

public interface MenuRepository extends MenuRepositoryCustom, JpaRepository<Menu, Long> {

    @Query("select m from Menu m where m.menuGroup.id = :menuGroupId")
    List<Menu> findAllByMenuGroupId(Long menuGroupId);
}
