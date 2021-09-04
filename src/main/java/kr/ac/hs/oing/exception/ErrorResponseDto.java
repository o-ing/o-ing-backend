package kr.ac.hs.oing.exception;

import kr.ac.hs.oing.common.dto.ResponseMessage;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@Data
public class ErrorResponseDto {
    private int status;
    private String message;

    public ErrorResponseDto(ErrorMessage errorMessage) {
        this.status = errorMessage.status();
        this.message = errorMessage.message();
    }


    public static ErrorResponseDto of(ErrorMessage errorMessage) {
        return new ErrorResponseDto(errorMessage);
    }

}
