package sample.ex.jpatest.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import sample.ex.jpatest.domain.entity.QUserGroup;

import java.util.List;

@RequiredArgsConstructor
public class UserGroupRepositoryCustomImpl implements UserGroupRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final QUserGroup userGroup = QUserGroup.userGroup;

    @Override
    public long updateDeleteStatus(Long menuGroupId, List<Long> deletedMenuId) {
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(userGroup.menuGroup.id.eq(menuGroupId));
        deletedMenuId.stream().forEach(e -> builder.or(userGroup.id.eq(e)));

        return queryFactory.update(userGroup)
                .setNull(userGroup.menuGroup.id)
                .where(builder)
                .execute();
    }
}
