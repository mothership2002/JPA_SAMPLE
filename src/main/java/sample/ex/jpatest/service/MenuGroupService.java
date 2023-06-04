package sample.ex.jpatest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.ex.jpatest.Common.Common;
import sample.ex.jpatest.domain.dto.BaseInfo;
import sample.ex.jpatest.domain.dto.menu.MenuDto;
import sample.ex.jpatest.domain.dto.menuGroup.MenuGroupDto;
import sample.ex.jpatest.domain.dto.menuGroup.SelectMenuGroupDto;
import sample.ex.jpatest.domain.dto.userGroup.UserGroupDto;
import sample.ex.jpatest.domain.entity.BaseEntity;
import sample.ex.jpatest.domain.entity.Menu;
import sample.ex.jpatest.domain.entity.MenuGroup;
import sample.ex.jpatest.domain.entity.UserGroup;
import sample.ex.jpatest.repository.MenuGroupRepository;
import sample.ex.jpatest.repository.MenuRepository;
import sample.ex.jpatest.repository.UserGroupRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MenuGroupService {

    private final Common common;

    private final MenuGroupRepository menuGroupRepository;
    private final UserGroupRepository userGroupRepository;
    private final MenuRepository menuRepository;

    public List<SelectMenuGroupDto> selectMenuGroup() {
        return menuGroupRepository.findAll().stream()
                .map(SelectMenuGroupDto::createDto).collect(Collectors.toCollection(ArrayList::new));
    }

    @Transactional
    public Long insertMenuGroup(MenuGroupDto dto) {
        MenuGroup menuGroup = MenuGroup.createEntity(dto);
        menuGroupRepository.save(menuGroup);

        List<UserGroupDto> userGroupList = dto.getUserGroupList();
        userGroupList.forEach(e -> e.setCreateDate(Common.date()));

        List<MenuDto> menuDtoList = dto.getMenuList();
        menuDtoList.forEach(e -> e.setCreateDate(Common.date()));

        if (!userGroupList.isEmpty()) {
            List<UserGroup> userGroups = common.convert(userGroupList, UserGroup.class);
            userGroups.forEach(e -> e.setMenuGroup(menuGroup));
            userGroupRepository.saveAll(userGroups);
        }

        if (!menuDtoList.isEmpty()) {
            List<Menu> menuList = common.convert(menuDtoList, Menu.class);
            menuList.forEach(e -> e.setMenuGroup(menuGroup));
            menuRepository.saveAll(menuList);
        }

        return menuGroup.getId();
    }


    @Transactional
    public void updateMenuGroup(MenuGroupDto dto) {
        dto.setUpdateDate(common.date());
        MenuGroup menuGroup = MenuGroup.createEntity(dto);
        List<MenuDto> menuDtoList = dto.getMenuList();
        List<UserGroupDto> userGroupDtoList = dto.getUserGroupList();

        setUpdateDate(menuDtoList);
        setUpdateDate(userGroupDtoList);

        List<Menu> menuList = menuDtoList.stream()
                .map(Menu::createEntity)
                .collect(Collectors.toCollection(ArrayList::new));

        List<UserGroup> userGroupList = userGroupDtoList.stream()
                .map(UserGroup::createEntity)
                .collect(Collectors.toCollection(ArrayList::new));

        menuList.forEach(e -> e.setMenuGroup(menuGroup));
        userGroupList.forEach(e -> e.setMenuGroup(menuGroup));

        menuRepository.saveAll(menuList);
        userGroupRepository.saveAll(userGroupList);
        menuGroupRepository.save(menuGroup);
    }

    private <T extends BaseInfo> void setUpdateDate(List<T> list) {
        list.forEach(e -> e.setUpdateDate(common.date()));
    }
}
