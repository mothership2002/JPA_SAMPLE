package sample.ex.jpatest.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sample.ex.jpatest.domain.entity.MenuGroup;
import sample.ex.jpatest.domain.entity.UserGroup;

import java.time.LocalDateTime;

@Getter
@ToString
@Setter
public class UserGroupDto extends BaseInfo {

    private Long id;
    private String userGroupName;
    private MenuGroupDto menuGroup;

    public UserGroupDto(LocalDateTime createDate, String createBy, LocalDateTime updateDate, String modifier,
                        Long id, String userGroupName, MenuGroupDto menuGroupDto) {
        super(createDate, createBy, updateDate, modifier);
        this.id = id;
        this.userGroupName = userGroupName;
        if(menuGroupDto != null) {
            this.menuGroup = menuGroupDto;
        }
    }

    public static UserGroupDto createDto(UserGroup entity) {
        if (entity != null) {
            return new UserGroupDto(entity.getCreateDate(), entity.getCreateBy(),
                    entity.getUpdateDate(), entity.getModifier(),
                    entity.getId(), entity.getUserGroupName(),
                    null);
        }
        return null;
    }
}
