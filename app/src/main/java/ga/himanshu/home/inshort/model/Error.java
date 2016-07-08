package ga.himanshu.home.inshort.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class Error extends java.lang.Error {

    public static final int ERROR_CODE_UNIDENTIFIED = -10;
    public static final int ERROR_CODE_NETWORK_ERROR = -9;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("errors")
    @Expose
    private Map<String, List<String>> errors;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;

    public Error() {
        this("Some error occured", ERROR_CODE_UNIDENTIFIED);
    }

    public Error(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    /**
     * @return The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return The errors
     */
    public Map<String, List<String>> getErrors() {
        return errors;
    }


    /**
     * @param errors
     */
    public void setErrors(Map<String, List<String>> errors) {
        this.errors = errors;
    }


    /**
     * @return The code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * @param code The code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * @return The statusCode
     */
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode The status_code
     */
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

}