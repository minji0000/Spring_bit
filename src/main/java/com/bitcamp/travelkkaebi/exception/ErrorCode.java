package com.bitcamp.travelkkaebi.exception;

public enum ErrorCode {
    PROFILE_SAVE_FAIL("프로파일 이미지 저장 실패");

    public final String message;

    ErrorCode(String message) { this.message = message; }
}