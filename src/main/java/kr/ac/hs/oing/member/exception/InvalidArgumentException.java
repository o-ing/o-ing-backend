package kr.ac.hs.oing.member.exception;

public class InvalidArgumentException extends IllegalArgumentException {
    public InvalidArgumentException(MemberExceptionMessage message) {
        super(message.message());
    }
}
