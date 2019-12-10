package com.emedinaa.chefapp.data.socket;

/**
 * @author : Eduardo Medina
 * @see : https://developer.android.com/index.html
 * @since : 8/18/18
 */
public class SocketResponse {
    private boolean success;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SocketResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
