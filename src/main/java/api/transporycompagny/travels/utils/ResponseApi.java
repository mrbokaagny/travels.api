package api.transporycompagny.travels.utils;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseApi {

    private String error;
    private String status;
    private String message;
    private Object data;

    public ResponseApi() {
    }

    public ResponseApi(String error, String status, String message, Object data) {
        this.error = error;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getError() {
        return this.error;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public Object getData() {
        return this.data;
    }

    public void hydrateData(String error, String message, String status, Object data) {
        this.setData(data);
        this.setError(error);
        this.setStatus(status);
        this.setMessage(message);
    }

    public ResponseEntity<?> builErrorJson(Exception e) {
        this.hydrateData("INTERNAL_ERROR", "Please, contact administrator ane error persist "+e.getMessage(), "error",
                null);
        return new ResponseEntity<>(this.buildJson(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public JSONObject buildJson() {
        JSONObject json = new JSONObject();
        json.put("status", this.getStatus());
        json.put("error", this.getError());
        json.put("message", this.getMessage());
        json.put("data", this.getData());
        return json;
    }
}
