package sample.ex.jpatest.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MenuGroup extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "menu_group_id")
    private Long id;

    private String menuGroupName;

    @OneToMany(mappedBy = "menuGroup")
    private List<Menu> menuList = new ArrayList<>();

    @OneToMany(mappedBy = "menuGroup")
    private List<UserGroup> userGroupList = new ArrayList<>();

}
