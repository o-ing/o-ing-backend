package kr.ac.hs.oing.common.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.*;

@Getter
@Setter
public class ResponseDto {

    private int status;
    private Map<String, String> data = new HashMap<>();

    public ResponseDto(int status, ResponseMessage data) {
        this.status = status;
        this.data.put("message", data.value());
    }

    public static ResponseDto of(HttpStatus httpStatus, ResponseMessage data) {
        int status = Optional
                .ofNullable(httpStatus)
                .orElse(HttpStatus.OK)
                .value();
        return new ResponseDto(status, data);
    }
}
