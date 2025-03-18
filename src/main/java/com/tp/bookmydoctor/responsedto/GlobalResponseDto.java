package com.tp.bookmydoctor.responsedto;

import lombok.Data;

@Data
public class GlobalResponseDto {
    private boolean error;
    private String message;
    private Object data;

    public GlobalResponseDto(boolean error, String message, Object data) {
        this.error = error;
        this.message = message;
        this.data = data;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
