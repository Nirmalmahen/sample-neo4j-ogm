package graphDB.utils;

import lombok.Data;

@Data
public class BaseResponse {
    private final boolean success;
    private final String message;
    private Object payload;

    private BaseResponse(boolean success, String message, Object payload) {
        this.success = success;
        this.message = message;
        this.payload = payload;
    }

    private BaseResponse(Exception violations) {
        this.success = false;
        this.message = violations.getMessage();
    }

    public static BaseResponse create(boolean success, String message, Object payload) {
        return new BaseResponse(success, message, payload);
    }

    public static BaseResponse create(Exception violations) {
        return new BaseResponse(violations);
    }

    public static BaseResponse create(boolean success, String message) {
        return new BaseResponse(success, message, null);
    }
}
