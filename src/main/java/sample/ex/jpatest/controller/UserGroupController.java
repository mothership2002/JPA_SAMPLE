package sample.ex.jpatest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.ex.jpatest.service.UserGroupService;

@RestController
@RequestMapping("/user-group")
@RequiredArgsConstructor
public class UserGroupController {

    private final UserGroupService userGroupService;


}
