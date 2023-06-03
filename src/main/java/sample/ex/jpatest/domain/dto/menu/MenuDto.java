package sample.ex.jpatest.domain.dto.menu;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sample.ex.jpatest.domain.dto.BaseInfo;
import sample.ex.jpatest.domain.dto.menuGroup.MenuGroupDto;
import sample.ex.jpatest.domain.entity.Menu;

import java.time.LocalDateTime;

@Getter
@ToString
@Setter
public class MenuDto extends BaseInfo {

    private Long menuId;
    private String menuName;
    private MenuGroupDto menuGroup;

    public MenuDto(LocalDateTime createDate, String createBy, LocalDateTime updateDate, String modifier,
                   Long menuId, String menuName, MenuGroupDto menuGroup) {
        super(createDate, createBy, updateDate, modifier);
        this.menuId = menuId;
        this.menuName = menuName;
        if (menuGroup != null) {
            this.menuGroup = menuGroup;
        }
    }

    public static MenuDto createDto(Menu entity) {
        if (entity != null) {
            return new MenuDto(entity.getCreateDate(), entity.getCreateBy(),
                    entity.getUpdateDate(), entity.getModifier(), entity.getId(),
                    entity.getMenuName(), null);
        }
        return null;
    }
}
