package sample.ex.jpatest.domain.entity;

import lombok.*;
import sample.ex.jpatest.domain.dto.menuGroup.InsertMenuGroupDto;

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
                        Long id, String menuGroupName, List<Menu> menuList, List<UserGroup> userGroupList) {
        super(createDate, createBy, updateDate, modifier);
        this.id = id;
        this.menuGroupName = menuGroupName;
        this.menuList = menuList;
        this.userGroupList = userGroupList;
    }

    public static MenuGroup createEntity(InsertMenuGroupDto dto) {
        if (dto != null) {
            return new MenuGroup(dto.getCreateDate(), dto.getCreateBy(), dto.getUpdateDate(),
                    dto.getModifier(), dto.getId(), dto.getMenuGroupName(),
                    null, null);
        }
        return null;
    }

}
