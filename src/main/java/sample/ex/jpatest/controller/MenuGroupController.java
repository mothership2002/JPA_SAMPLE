package sample.ex.jpatest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.ex.jpatest.Common.Common;
import sample.ex.jpatest.domain.dto.CommonResponse;
import sample.ex.jpatest.domain.dto.MenuGroupDto;
import sample.ex.jpatest.service.MenuGroupService;

import static sample.ex.jpatest.Common.Common.date;

@RestController
@RequestMapping("/menu-group")
@RequiredArgsConstructor
public class MenuGroupController {

    private final MenuGroupService menuGroupService;

    @PostMapping
    public CommonResponse insertMenuGroup(@RequestBody MenuGroupDto menuGroupDto) {
        menuGroupDto.setCreateDate(Common.date());
        Long menuGroupId = menuGroupService.insertMenuGroup(menuGroupDto);
        return new CommonResponse<>("message", HttpStatus.OK, date(), menuGroupId);
    }
}
