package sample.ex.jpatest.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import sample.ex.jpatest.domain.entity.QMenu;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class MenuRepositoryCustomImpl implements MenuRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final QMenu menu = QMenu.menu;

    @Override
    public long updateDeleteStatus(Long menuGroupId, Long[] deletedMenuId) {
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(menu.menuGroup.id.eq(menuGroupId));
        Arrays.stream(deletedMenuId).forEach(e -> builder.or(menu.id.eq(e)));

        return queryFactory.update(menu)
                .setNull(menu.menuGroup.id)
                .where(builder)
                .execute();
    }
}
