package kr.ac.hs.oing.common.converter;

import kr.ac.hs.oing.common.dto.ResponseDto.ResponseDtoV1;
import kr.ac.hs.oing.common.dto.ResponseDto.ResponseDtoV2;
import kr.ac.hs.oing.common.dto.ResponseMessage;
import kr.ac.hs.oing.error.ErrorMessage;
import kr.ac.hs.oing.error.ErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseConverter {

    public ResponseEntity<ResponseDtoV1> toResponseEntity(ResponseMessage message) {
        return ResponseEntity
            .status(message.getStatus())
            .body(
                new ResponseDtoV1(message)
            );
    }

    public <T> ResponseEntity<ResponseDtoV2<T>> toResponseEntity(ResponseMessage message, T data) {
        return ResponseEntity
            .status(message.getStatus())
            .body(
                new ResponseDtoV2<>(message, data)
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