package kr.ac.hs.oing.common.dto;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ResponseDto<T> {

    @Getter
    public static class ResponseDtoV1 {

        private final String message;
        private final LocalDateTime serverDateTime;

        public ResponseDtoV1(ResponseMessage message) {
            this.message = message.name();
            this.serverDateTime = LocalDateTime.now();
        }
    }

    @Getter
    public static class ResponseDtoV2<T> {

        private final String message;
        private final LocalDateTime serverDateTime;
        private final T data;

        public ResponseDtoV2(ResponseMessage message, T data) {
            this.message = message.name();
            this.serverDateTime = LocalDateTime.now();
            this.data = data;
        }
    }
}