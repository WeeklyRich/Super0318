package until;

public class JsonResponseResult {
    private int code;
    private String msg;
    private Object result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "JsonResponseResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }

    public JsonResponseResult (){}
    public JsonResponseResult ( int code, String msg, Object result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }
}
