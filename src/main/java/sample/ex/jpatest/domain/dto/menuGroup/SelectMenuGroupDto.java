package sample.ex.jpatest.domain.dto.menuGroup;

import lombok.Getter;
import lombok.ToString;
import sample.ex.jpatest.domain.dto.BaseInfo;
import sample.ex.jpatest.domain.entity.MenuGroup;

import java.time.LocalDateTime;

@Getter
@ToString
public class SelectMenuGroupDto extends BaseInfo {
    private Long id;
    private String menuGroupName;

    protected SelectMenuGroupDto(LocalDateTime createDate, String createBy, LocalDateTime updateDate, String modifier,
                              Long id, String menuGroupName) {
        super(createDate, createBy, updateDate, modifier);
        this.id = id;
        this.menuGroupName = menuGroupName;
    }

    public static SelectMenuGroupDto createDto(MenuGroup entity) {
        return new SelectMenuGroupDto(entity.getCreateDate(), entity.getCreateBy(), entity.getUpdateDate(), entity.getModifier(),
                entity.getId(), entity.getMenuGroupName());
    }
}
