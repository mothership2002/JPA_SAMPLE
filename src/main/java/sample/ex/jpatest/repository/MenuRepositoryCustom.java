package sample.ex.jpatest.repository;

import java.util.List;

public interface MenuRepositoryCustom {

    long updateDeleteStatus(Long menuGroupId, Long[] deletedMenuId);
}
