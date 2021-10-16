package kr.ac.hs.oing.common.dto;

import lombok.Data;

@Data
public class ResponseDto<T> {
    private int status;
    private String message;
    private T data;

    private ResponseDto(ResponseMessage message) {
        this.status = message.status().value();
        this.message = message.name();
    }

    private ResponseDto(ResponseMessage message, T data) {
        this(message);
        this.data = data;
    }

    public static ResponseDto of(ResponseMessage message) {
        return new ResponseDto(message);
    }

    public static <T> ResponseDto<T> of(ResponseMessage message, T data) {
        return new ResponseDto(message, data);
    }
}
