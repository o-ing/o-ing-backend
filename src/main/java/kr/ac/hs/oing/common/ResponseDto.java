package kr.ac.hs.oing.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@Getter
@Setter
public class ResponseDto {

    private int status;
    private String message;
    private Object data;

    public ResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseDto(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static ResponseDto of(HttpStatus httpStatus, ResponseMessage responseMessage) {
        int status = Optional
                .ofNullable(httpStatus)
                .orElse(HttpStatus.OK)
                .value();

        String message = responseMessage.value();

        return new ResponseDto(status, message);
    }

    public static ResponseDto of(HttpStatus httpStatus, ResponseMessage responseMessage, Object data) {
        int status = Optional
                .ofNullable(httpStatus)
                .orElse(HttpStatus.OK)
                .value();

        String message = responseMessage.value();

        return new ResponseDto(status, message, data);
    }
}
