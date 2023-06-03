package sample.ex.jpatest.domain.dto;

import lombok.Getter;
import lombok.ToString;
import sample.ex.jpatest.domain.entity.Menu;
import sample.ex.jpatest.domain.entity.MenuGroup;
import sample.ex.jpatest.domain.entity.UserGroup;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
public class MenuGroupDto extends BaseInfo {

    private Long id;
    private String menuGroupName;
    private List<MenuDto> menuList;
    private List<UserGroupDto> userGroupList;

    public MenuGroupDto(LocalDateTime createDate, String createBy, LocalDateTime updateDate, String modifier,
                        Long id, String menuGroupName, List<MenuDto> menuList, List<UserGroupDto> userGroupList) {
        super(createDate, createBy, updateDate, modifier);
        this.id = id;
        this.menuGroupName = menuGroupName;
        this.menuList = menuList;
        this.userGroupList = userGroupList;
    }

    public static MenuGroupDto createDto(MenuGroup entity) {
        if (entity != null) {
            return new MenuGroupDto(entity.getCreateDate(), entity.getCreateBy(),
                    entity.getUpdateDate(), entity.getModifier(),
                    entity.getId(), entity.getMenuGroupName(),
                    null,null);
        }
        return null;
    }


    private static List<MenuDto> checkNullMenu(List<Menu> menuList) {
        if (!menuList.isEmpty()) {
            return menuList.stream()
                    .map(MenuDto::createDto)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        return new ArrayList<>();
    }

    private static List<UserGroupDto> checkNullUserGroup(List<UserGroup> userGroupList) {
        if (!userGroupList.isEmpty()) {
            return userGroupList.stream()
                    .map(UserGroupDto::createDto)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        return new ArrayList<>();
    }

}
