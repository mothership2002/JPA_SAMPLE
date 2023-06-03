package sample.ex.jpatest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.ex.jpatest.service.MenuGroupService;

@RestController
@RequestMapping("/menu-group")
@RequiredArgsConstructor
public class MenuGroupController {

    private final MenuGroupService menuGroupService;

}
