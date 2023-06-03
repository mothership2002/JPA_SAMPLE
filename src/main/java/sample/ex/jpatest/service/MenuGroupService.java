package sample.ex.jpatest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.ex.jpatest.Common.Common;
import sample.ex.jpatest.domain.dto.menu.MenuDto;
import sample.ex.jpatest.domain.dto.menuGroup.InsertMenuGroupDto;
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

    private final MenuGroupRepository menuGroupRepository;
    private final UserGroupRepository userGroupRepository;
    private final MenuRepository menuRepository;

    public List<SelectMenuGroupDto> selectMenuGroup() {
        return menuGroupRepository.findAll().stream()
                .map(SelectMenuGroupDto::createDto).collect(Collectors.toCollection(ArrayList::new));
    }


    @Transactional
    public Long insertMenuGroup(InsertMenuGroupDto dto) {
        MenuGroup menuGroup = MenuGroup.createEntity(dto);
        menuGroupRepository.save(menuGroup);
        InsertMenuGroupDto insertMenuGroupDto = InsertMenuGroupDto.createDto(menuGroup);

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
            List<UserGroup> userGroups = convert(userGroupList, UserGroup.class);
            userGroupRepository.saveAll(userGroups);
        }

        if (!menuDtoList.isEmpty()) {
            List<Menu> menuList = convert(menuDtoList, Menu.class);
            menuRepository.saveAll(menuList);
        }

        return menuGroup.getId();
    }

    @SuppressWarnings("unchecked")
    private <Entity, Dto> List<Entity> convert(List<Dto> dtoList, Class<?> entityClass) {
        return dtoList.stream().map(dto -> {
            try {
                return (Entity) entityClass
                        .getMethod("createEntity", dto.getClass())
                        .invoke(entityClass, dto);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toCollection(ArrayList::new));
    }


//    private <T extends BaseInfo> void createDate(List<T> t) {
//        t.forEach(e ->
//                e.setCreateDate(Common.date())
//        );
//    }
}
