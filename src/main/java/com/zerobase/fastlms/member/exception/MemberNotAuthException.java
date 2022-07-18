package com.zerobase.fastlms.member.exception;

public class MemberNotAuthException extends RuntimeException {
    public MemberNotAuthException(String error) {
        super(error);
    }
}