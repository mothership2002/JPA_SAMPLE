package sample.ex.jpatest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sample.ex.jpatest.Common.Common;
import sample.ex.jpatest.domain.dto.CommonResponse;
import sample.ex.jpatest.domain.dto.menuGroup.InsertMenuGroupDto;
import sample.ex.jpatest.domain.dto.menuGroup.SelectMenuGroupDto;
import sample.ex.jpatest.service.MenuGroupService;

import java.util.List;

import static sample.ex.jpatest.Common.Common.date;

@RestController
@RequestMapping("/menu-group")
@RequiredArgsConstructor
public class MenuGroupController {

    private final MenuGroupService menuGroupService;

    @PostMapping
    public CommonResponse insertMenuGroup(@RequestBody InsertMenuGroupDto insertMenuGroupDto) {
        insertMenuGroupDto.setCreateDate(Common.date());
        Long menuGroupId = menuGroupService.insertMenuGroup(insertMenuGroupDto);
        return new CommonResponse<>("message", HttpStatus.OK, date(), menuGroupId);
    }

    @GetMapping
    public CommonResponse<List<SelectMenuGroupDto>> selectMenuGroup() {
        List<SelectMenuGroupDto> result = menuGroupService.selectMenuGroup();
        return new CommonResponse<>("message",HttpStatus.OK,date(),result);
    }
}
