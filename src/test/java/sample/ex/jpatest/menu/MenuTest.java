package sample.ex.jpatest.menu;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sample.ex.jpatest.domain.dto.MenuDto;
import sample.ex.jpatest.domain.entity.Menu;
import sample.ex.jpatest.service.MenuService;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Slf4j
public class MenuTest {

    @Autowired
    MenuService menuService;

    @Test
    void menu_insert() {
        MenuDto menuDto = new MenuDto(LocalDateTime.now(),
                "insertTest",
                null, null,
                null, "상품주문");
        menuService.insertMenu(menuDto);

        List<MenuDto> list = menuService.selectMenuList();
        boolean haha = list.stream()
                .filter(e -> e.getMenuName().equals(menuDto.getMenuName())).findFirst().isPresent();

        assertThat(haha).isEqualTo(true);
    }
}
