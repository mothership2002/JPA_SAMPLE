package sample.ex.jpatest.Common;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Common {
    public static LocalDateTime date() {
        return LocalDateTime.now();
    }

    @SuppressWarnings("unchecked")
    public  <Entity, Dto> List<Entity> convert(List<Dto> dtoList, Class<?> entityClass) {
        return dtoList.stream().map(dto -> {
            try {
                return (Entity) entityClass
                        .getMethod("createEntity", dto.getClass())
                        .invoke(entityClass, dto);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toCollection(ArrayList::new));
    }
}
