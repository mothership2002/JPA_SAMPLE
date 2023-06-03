package sample.ex.jpatest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.ex.jpatest.domain.dto.MenuDto;
import sample.ex.jpatest.repository.MenuGroupRepository;
import sample.ex.jpatest.repository.MenuRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MenuGroupService {

    private final MenuGroupRepository menuGroupRepository;
    public void insertMenu(MenuDto dto) {
    }
}
