package sample.ex.jpatest.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.ex.jpatest.domain.dto.UserGroupDto;

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

    protected UserGroup(LocalDateTime createDate, String createBy, LocalDateTime updateDate, String modifier,
                        Long id, String userGroupName, MenuGroup menuGroup) {
        super(createDate, createBy, updateDate, modifier);
        this.id = id;
        this.userGroupName = userGroupName;
        this.menuGroup = menuGroup;
    }

    public static UserGroup createEntity(UserGroupDto dto) {
        if (dto != null) {
            return new UserGroup(dto.getCreateDate(), dto.getCreateBy(), dto.getUpdateDate(),
                    dto.getModifier(), dto.getId(), dto.getUserGroupName(),
                    MenuGroup.createEntity(dto.getMenuGroup()));
        }
        return null;
    }
}
