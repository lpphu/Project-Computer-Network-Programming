package com.project.Controllers;

public class Response {
    private boolean status ;
    private String message;
    private Object data;

    
    public Response() {
        this.status = false;
        this.message = "";
        this.data = null;
    }
    public Response(boolean status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }

    public String toString() {
        String dataString ="";
        if (this.data != null) {
            dataString = this.data.toString();
        }
        return "Response [status=" + status + ", message=" + message + ", data=" + dataString + "]";
    }
    
}
