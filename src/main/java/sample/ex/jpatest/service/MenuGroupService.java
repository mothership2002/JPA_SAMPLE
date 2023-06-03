package sample.ex.jpatest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.ex.jpatest.Common.Common;
import sample.ex.jpatest.domain.dto.menu.MenuDto;
import sample.ex.jpatest.domain.dto.menuGroup.MenuGroupDto;
import sample.ex.jpatest.domain.dto.menuGroup.SelectMenuGroupDto;
import sample.ex.jpatest.domain.dto.userGroup.UserGroupDto;
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
        MenuGroupDto insertMenuGroupDto = MenuGroupDto.createDto(menuGroup);

        List<UserGroupDto> userGroupList = dto.getUserGroupList();
        userGroupList.forEach(e -> {
            e.setMenuGroup(insertMenuGroupDto);
            e.setCreateDate(Common.date());
        });

        List<MenuDto> menuDtoList = dto.getMenuList();
        menuDtoList.forEach(e -> {
            e.setMenuGroup(insertMenuGroupDto);
            e.setCreateDate(Common.date());
        });

        if (!userGroupList.isEmpty()) {
            List<UserGroup> userGroups = common.convert(userGroupList, UserGroup.class);
            userGroupRepository.saveAll(userGroups);
        }

        if (!menuDtoList.isEmpty()) {
            List<Menu> menuList = common.convert(menuDtoList, Menu.class);
            menuRepository.saveAll(menuList);
        }

        return menuGroup.getId();
    }

    @Transactional
    public void updateMenuGroup(MenuGroupDto updateMenuGroupDto) {

        Long menuGroupId = updateMenuGroupDto.getId();
        MenuGroup menuGroup = menuGroupRepository.findById(menuGroupId)
                .orElseThrow(() -> new IllegalArgumentException("없음"));

        String menuGroupName =
                updateMenuGroupDto.getMenuGroupName() != null ?
                        updateMenuGroupDto.getMenuGroupName() : menuGroup.getMenuGroupName();

        MenuGroupDto menuGroupDto = new MenuGroupDto(
                menuGroup.getCreateDate(),
                menuGroup.getCreateBy(),
                updateMenuGroupDto.getUpdateDate(),
                updateMenuGroupDto.getModifier(),
                updateMenuGroupDto.getId(),
                menuGroupName, null, null);

        menuGroupRepository.save(MenuGroup.createEntity(menuGroupDto));
  
        updateMenuGroupDto.getMenuList().forEach(e -> {
            e.setUpdateDate(common.date());
            e.setMenuGroup(updateMenuGroupDto);
        });
        updateMenuGroupDto.getUserGroupList().forEach(e -> {
            e.setUpdateDate(common.date());
            e.setMenuGroup(updateMenuGroupDto);
        });

        List<Menu> originalMenuList = menuRepository.findAllByMenuGroupId(menuGroupId);
        List<Menu> updateMenuList = updateMenuGroupDto.getMenuList().stream()
                .map(Menu::createEntity)
                .collect(Collectors.toCollection(ArrayList::new));
        menuRepository.saveAll(updateMenuList);

        List<MenuDto> deletedMenuList = originalMenuList.stream()
                .filter(e -> !updateMenuList.contains(e))
                .map(MenuDto::createDto)
                .collect(Collectors.toCollection(ArrayList::new));

        if (!deletedMenuList.isEmpty()) {
            List<Menu> submit =
                    deletedMenuList.stream()
                            .map(Menu::createEntity)
                            .collect(Collectors.toCollection(ArrayList::new));
            menuRepository.saveAll(submit);
        }


        List<UserGroup> originalUserGroupList = userGroupRepository.findAllByMenuGroupId(menuGroupId);
        List<UserGroup> updateUserGroupList =
                updateMenuGroupDto.getUserGroupList().stream()
                        .map(UserGroup::createEntity)
                        .collect(Collectors.toCollection(ArrayList::new));
        userGroupRepository.saveAll(updateUserGroupList);

        List<UserGroupDto> deleteUserGroupList =
                originalUserGroupList.stream()
                        .filter(e -> !updateUserGroupList.contains(e))
                        .map(UserGroupDto::createDto)
                        .collect(Collectors.toCollection(ArrayList::new));

        if (!deleteUserGroupList.isEmpty()) {
            List<UserGroup> submit = deleteUserGroupList.stream()
                    .map(UserGroup::createEntity)
                    .collect(Collectors.toCollection(ArrayList::new));
            userGroupRepository.saveAll(submit);
        }
    }

}
