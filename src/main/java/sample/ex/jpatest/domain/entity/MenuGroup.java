package sample.ex.jpatest.domain.entity;

import lombok.*;
import sample.ex.jpatest.domain.dto.menuGroup.MenuGroupDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class MenuGroup extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "menu_group_id")
    private Long id;

    private String menuGroupName;

    @OneToMany(mappedBy = "menuGroup")
    private List<Menu> menuList = new ArrayList<>();

    @OneToMany(mappedBy = "menuGroup")
    private List<UserGroup> userGroupList = new ArrayList<>();

    protected MenuGroup(LocalDateTime createDate, String createBy, LocalDateTime updateDate, String modifier,
                        Long id, String menuGroupName) {
        super(createDate, createBy, updateDate, modifier);
        this.id = id;
        this.menuGroupName = menuGroupName;
    }

    public static MenuGroup createEntity(MenuGroupDto dto) {
        if (dto != null) {
            return new MenuGroup(dto.getCreateDate(), dto.getCreateBy(), dto.getUpdateDate(),
                    dto.getModifier(), dto.getId(), dto.getMenuGroupName());
        }
        return null;
    }


}
