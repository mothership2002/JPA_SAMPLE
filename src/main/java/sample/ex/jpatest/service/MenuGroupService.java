package sample.ex.jpatest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.ex.jpatest.Common.Common;
import sample.ex.jpatest.domain.dto.BaseInfo;
import sample.ex.jpatest.domain.dto.MenuDto;
import sample.ex.jpatest.domain.dto.MenuGroupDto;
import sample.ex.jpatest.domain.dto.UserGroupDto;
import sample.ex.jpatest.domain.entity.Menu;
import sample.ex.jpatest.domain.entity.MenuGroup;
import sample.ex.jpatest.domain.entity.UserGroup;
import sample.ex.jpatest.repository.MenuGroupRepository;
import sample.ex.jpatest.repository.MenuRepository;
import sample.ex.jpatest.repository.UserGroupRepository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MenuGroupService {

    private final MenuGroupRepository menuGroupRepository;
    private final UserGroupRepository userGroupRepository;
    private final MenuRepository menuRepository;

    @Transactional
    public Long insertMenuGroup(MenuGroupDto dto) {
        MenuGroup menuGroup = MenuGroup.createEntity(dto);
        menuGroupRepository.save(menuGroup);
        MenuGroupDto menuGroupDto = MenuGroupDto.createDto(menuGroup);

        List<UserGroupDto> userGroupList = dto.getUserGroupList();
        userGroupList.forEach(e -> {
            e.setMenuGroup(menuGroupDto);
            e.setCreateDate(Common.date());
        });

        List<MenuDto> menuDtoList = dto.getMenuList();
        menuDtoList.forEach(e -> {
            e.setMenuGroup(menuGroupDto);
            e.setCreateDate(Common.date());
        });

        if (!userGroupList.isEmpty() && userGroupList != null) {
            List<UserGroup> userGroups = convert(userGroupList, UserGroup.class);
            userGroupRepository.saveAll(userGroups);
        }

        if (!menuDtoList.isEmpty() && menuDtoList != null) {
            List<Menu> menuList = convert(menuDtoList, Menu.class);
            menuRepository.saveAll(menuList);
        }

        return menuGroup.getId();
    }

    private <Entity, Dto> List<Entity> convert(List<Dto> dtoList, Class<?> entityClass) {
        List<Entity> result = dtoList.stream().map(dto -> {
            try {
                return (Entity) entityClass
                        .getMethod("createEntity", dto.getClass())
                        .invoke(entityClass, dto);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toCollection(ArrayList::new));
        return result;
    }

//    private <T extends BaseInfo> void createDate(List<T> t) {
//        t.forEach(e ->
//                e.setCreateDate(Common.date())
//        );
//    }
}
