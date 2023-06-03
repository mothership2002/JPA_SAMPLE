package sample.ex.jpatest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sample.ex.jpatest.Common.Common;
import sample.ex.jpatest.domain.dto.CommonResponse;
import sample.ex.jpatest.domain.dto.MenuDto;
import sample.ex.jpatest.domain.entity.Menu;
import sample.ex.jpatest.service.MenuService;

import java.time.LocalDateTime;
import java.util.List;

import static sample.ex.jpatest.Common.Common.date;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping
    public CommonResponse<List<MenuDto>> selectMenuList() {
        List<MenuDto> menus = menuService.selectMenuList();
        return new CommonResponse<>("message", HttpStatus.OK, date(), menus);
    }

    @PostMapping
    public CommonResponse insertMenu(@RequestBody MenuDto dto) {
        dto.setCreateDate(date());
        Long menuId = menuService.insertMenu(dto);
        return new CommonResponse("message", HttpStatus.OK, date(), menuId);
    }

}
