package kr.ac.hs.oing.common.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.*;

@Data
public class ResponseDto {

    private int status;
    private String message;
    private Object data;

    public ResponseDto(int status, ResponseMessage message) {
        this.status = status;
        this.message = message.toString();
    }

    public ResponseDto(int status, ResponseMessage message, Object data) {
        this.status = status;
        this.message = message.toString();
        this.data = data;
    }

    public static ResponseDto of(HttpStatus status, ResponseMessage message) {
        return new ResponseDto(status(status), message);
    }

    public static ResponseDto of(HttpStatus status, ResponseMessage message, Object data) {
        return new ResponseDto(status(status), message, data);
    }

    private static int status(HttpStatus status) {
        return Optional
                .ofNullable(status)
                .orElse(HttpStatus.OK)
                .value();
    }
}
