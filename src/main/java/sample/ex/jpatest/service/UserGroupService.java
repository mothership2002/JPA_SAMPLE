package sample.ex.jpatest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.ex.jpatest.domain.dto.menu.MenuDto;
import sample.ex.jpatest.repository.UserGroupRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserGroupService {

    private final UserGroupRepository userGroupRepository;
    public void insertGroup(MenuDto dto) {

    }
}
