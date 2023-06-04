package sample.ex.jpatest.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.ex.jpatest.domain.dto.menu.MenuDto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "menu_id")
    private Long id;

    private String menuName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_group_id")
    private MenuGroup menuGroup;

    public void setMenuGroup(MenuGroup menuGroup) {
        this.menuGroup = menuGroup;
        menuGroup.getMenuList().add(this);
    }

    protected Menu(LocalDateTime createDate, String createBy, LocalDateTime updateDate, String modifier,
                   Long id, String menuName) {
        super(createDate, createBy, updateDate, modifier);
        this.id = id;
        this.menuName = menuName;
    }

    public static Menu createEntity(MenuDto dto) {
        if (dto != null) {
            return new Menu(dto.getCreateDate(), dto.getCreateBy(),
                    dto.getUpdateDate(), dto.getModifier(),
                    dto.getId(), dto.getMenuName());
        }
        return null;
    }

}
