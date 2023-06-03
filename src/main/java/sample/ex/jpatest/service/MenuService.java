package sample.ex.jpatest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.ex.jpatest.domain.dto.menu.MenuDto;
import sample.ex.jpatest.domain.dto.menu.SelectMenuDto;
import sample.ex.jpatest.domain.entity.Menu;
import sample.ex.jpatest.domain.entity.MenuGroup;
import sample.ex.jpatest.repository.MenuGroupRepository;
import sample.ex.jpatest.repository.MenuRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MenuService {

    private final MenuRepository menuRepository;
    private final MenuGroupRepository menuGroupRepository;

    @Transactional
    public Long insertMenu(MenuDto dto) {
        Menu menu = Menu.createEntity(dto);
        MenuGroup menuGroup = menu.getMenuGroup();
        if (menuGroup != null) {
            menuGroupRepository.save(menuGroup);
        }
        menuRepository.save(menu);
        return menu.getId();
    }

    public List<SelectMenuDto> selectMenuList(Long memberGroupId) {
        return menuRepository.findAllWithMenuGroup(memberGroupId)
                .stream().map(SelectMenuDto::createDto).collect(Collectors.toCollection(ArrayList::new));
    }
}
