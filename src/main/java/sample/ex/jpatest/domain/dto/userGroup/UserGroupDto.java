package sample.ex.jpatest.domain.dto.userGroup;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sample.ex.jpatest.domain.dto.BaseInfo;
import sample.ex.jpatest.domain.dto.menuGroup.InsertMenuGroupDto;
import sample.ex.jpatest.domain.entity.UserGroup;

import java.time.LocalDateTime;

@Getter
@ToString
@Setter
public class UserGroupDto extends BaseInfo {

    private Long id;
    private String userGroupName;
    private InsertMenuGroupDto menuGroup;

    public UserGroupDto(LocalDateTime createDate, String createBy, LocalDateTime updateDate, String modifier,
                        Long id, String userGroupName, InsertMenuGroupDto insertMenuGroupDto) {
        super(createDate, createBy, updateDate, modifier);
        this.id = id;
        this.userGroupName = userGroupName;
        if(insertMenuGroupDto != null) {
            this.menuGroup = insertMenuGroupDto;
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
