package kr.ac.hs.oing.common.dto;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ResponseDto<T> {

    private final String message;
    private final LocalDateTime serverDateTime;
    private final T data;

    public ResponseDto(ResponseMessage message, T data) {
        this.message = message.name();
        this.serverDateTime = LocalDateTime.now();
        this.data = data;
    }
}