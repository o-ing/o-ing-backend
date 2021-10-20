package kr.ac.hs.oing.common.converter;

import kr.ac.hs.oing.common.dto.ResponseDto;
import kr.ac.hs.oing.common.dto.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseConverter {
    public <T> ResponseEntity<ResponseDto> toResponseEntity(ResponseMessage message, T data) {
        return ResponseEntity.ok(
                ResponseDto.of(
                        message,
                        data
                )
        );
    }

    public ResponseEntity<ResponseDto> toResponseEntity(ResponseMessage message) {
        return ResponseEntity.ok(
                ResponseDto.of(
                        message
                )
        );
    }
}
