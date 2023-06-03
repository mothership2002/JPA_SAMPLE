package sample.ex.jpatest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sample.ex.jpatest.domain.dto.CommonResponse;
import sample.ex.jpatest.domain.dto.menu.MenuDto;
import sample.ex.jpatest.domain.dto.menu.SelectMenuDto;
import sample.ex.jpatest.service.MenuService;

import java.util.List;

import static sample.ex.jpatest.Common.Common.date;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;
    @GetMapping("/{menuGroupId}")
    public CommonResponse<List<SelectMenuDto>> selectMenuList(@PathVariable Long menuGroupId) {
        List<SelectMenuDto> menus = menuService.selectMenuList(menuGroupId);
        return new CommonResponse<>("message", HttpStatus.OK, date(), menus);
    }

    @PostMapping
    public CommonResponse insertMenu(@RequestBody MenuDto dto) {
        dto.setCreateDate(date());
        Long menuId = menuService.insertMenu(dto);
        return new CommonResponse("message", HttpStatus.OK, date(), menuId);
    }

}
