package sample.ex.jpatest.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.ex.jpatest.domain.dto.userGroup.UserGroupDto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserGroup extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_group_id")
    private Long id;

    private String userGroupName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_group_id")
    private MenuGroup menuGroup;

    public void setMenuGroup(MenuGroup menuGroup) {
        this.menuGroup = menuGroup;
        menuGroup.getUserGroupList().add(this);
    }

    protected UserGroup(LocalDateTime createDate, String createBy, LocalDateTime updateDate, String modifier,
                        Long id, String userGroupName) {
        super(createDate, createBy, updateDate, modifier);
        this.id = id;
        this.userGroupName = userGroupName;
    }

    public static UserGroup createEntity(UserGroupDto dto) {
        if (dto != null) {
            return new UserGroup(dto.getCreateDate(), dto.getCreateBy(), dto.getUpdateDate(),
                    dto.getModifier(), dto.getId(), dto.getUserGroupName());
        }
        return null;
    }
}
