package utils;

public class Response {
    private int code;
    private String content;
    private String url;

    public Response(int code, String content, String url) {
        this.code = code;
        this.content = content;
        this.url = url;
    }

    public int getCode() {
        return code;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
