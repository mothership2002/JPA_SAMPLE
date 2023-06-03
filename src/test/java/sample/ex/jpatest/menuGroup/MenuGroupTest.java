package sample.ex.jpatest.menuGroup;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sample.ex.jpatest.domain.dto.menuGroup.MenuGroupDto;
import sample.ex.jpatest.repository.MenuGroupRepository;
import sample.ex.jpatest.service.MenuGroupService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@Slf4j
public class MenuGroupTest {

    @Autowired
    MenuGroupService menuGroupService;
    @Autowired
    MenuGroupRepository menuGrouprepository;


    @Test
    void service() {
        List<MenuGroupDto> result = menuGrouprepository.findAll().stream()
                .map(MenuGroupDto::createDto).collect(Collectors.toCollection(ArrayList::new));

        result.forEach( e -> log.info(e.toString()));

    }
}
