package sample.ex.jpatest.domain.dto.menu;

import lombok.Getter;
import lombok.ToString;
import sample.ex.jpatest.domain.dto.BaseInfo;
import sample.ex.jpatest.domain.entity.Menu;

import java.time.LocalDateTime;

@Getter
@ToString
public class SelectMenuDto extends BaseInfo {

    private Long id;
    private String menuName;

    protected SelectMenuDto(LocalDateTime createDate, String createBy, LocalDateTime updateDate, String modifier,
                            Long id, String menuName) {
        super(createDate, createBy, updateDate, modifier);
        this.id = id;
        this.menuName = menuName;
    }

    public static SelectMenuDto createDto(Menu menu) {
        return new SelectMenuDto(menu.getCreateDate(), menu.getCreateBy(),
                menu.getUpdateDate(), menu.getModifier(),
                menu.getId(), menu.getMenuName());
    }
}
