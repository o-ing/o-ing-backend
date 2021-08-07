package kr.ac.hs.oing.member.exception;

public class DuplicationArgumentException extends IllegalArgumentException {
    public DuplicationArgumentException(MemberExceptionMessage message) {
        super(message.message());
    }
}
