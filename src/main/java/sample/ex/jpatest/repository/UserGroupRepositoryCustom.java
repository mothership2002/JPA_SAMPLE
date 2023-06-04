package sample.ex.jpatest.repository;

import java.util.List;

public interface UserGroupRepositoryCustom {

    long updateDeleteStatus(Long menuGroupId, List<Long> userGroupIdList);
}
