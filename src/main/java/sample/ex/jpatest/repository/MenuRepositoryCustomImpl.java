package sample.ex.jpatest.repository;

import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import sample.ex.jpatest.domain.entity.QMenu;

@RequiredArgsConstructor
public class MenuRepositoryCustomImpl implements MenuRepositoryCustom {

    private JPAQueryFactory queryFactory;
    private final QMenu menu = QMenu.menu;
    @Override
    public Long updateMenuListByMenuGroupId(Long menuGroupId) {
        return queryFactory.update(menu)
                .where(menu.menuGroup.id.eq(menuGroupId))
                .set(menu.menuName, );
    }
}
