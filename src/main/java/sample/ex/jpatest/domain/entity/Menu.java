package sample.ex.jpatest.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.ex.jpatest.domain.dto.MenuDto;

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

    protected Menu(LocalDateTime createDate, String createBy, LocalDateTime updateDate, String modifier,
                   Long id, String menuName, MenuGroup menuGroup) {
        super(createDate, createBy, updateDate, modifier);
        this.id = id;
        this.menuName = menuName;
        this.menuGroup = menuGroup;
    }

    public static Menu createEntity(MenuDto dto) {
        if (dto != null) {
            return new Menu(dto.getCreateDate(), dto.getCreateBy(),
                    dto.getUpdateDate(), dto.getModifier(),
                    dto.getMenuId(), dto.getMenuName(),
                    MenuGroup.createEntity(dto.getMenuGroup()));
        }
        return null;
    }
}
