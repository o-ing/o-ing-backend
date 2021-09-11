package kr.ac.hs.oing.exception;

import lombok.Data;

@Data
public class ErrorResponseDto {
    private int status;
    private String message;

    public ErrorResponseDto(ErrorMessage errorMessage) {
        this.status = errorMessage.status().value();
        this.message = errorMessage.name();
    }


    public static ErrorResponseDto of(ErrorMessage errorMessage) {
        return new ErrorResponseDto(errorMessage);
    }

}
