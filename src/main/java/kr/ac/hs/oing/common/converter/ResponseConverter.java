package kr.ac.hs.oing.common.converter;

import kr.ac.hs.oing.common.dto.ResponseDto;
import kr.ac.hs.oing.common.dto.ResponseMessage;
import kr.ac.hs.oing.error.ErrorMessage;
import kr.ac.hs.oing.error.ErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseConverter {

    public <T> ResponseEntity<ResponseDto<T>> toResponseEntity(ResponseMessage message, T data) {
        return ResponseEntity
            .status(message.getStatus())
            .body(
                new ResponseDto<>(message, data)
            );
    }

    public ResponseEntity<ErrorResponseDto> toResponseEntity(ErrorMessage message) {
        return ResponseEntity
            .status(message.status())
            .body(
                new ErrorResponseDto(message)
            );
    }
}