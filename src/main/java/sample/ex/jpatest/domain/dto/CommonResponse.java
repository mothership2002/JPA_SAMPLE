package sample.ex.jpatest.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@ToString
public class CommonResponse<T> {

    private String message;
    private HttpStatus status;
    private LocalDateTime responseDate;
    private T data;

}
