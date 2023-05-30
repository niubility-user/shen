package retrofit2.adapter.rxjava;

import com.jingdong.common.utils.LangUtils;
import retrofit2.Response;

/* loaded from: classes11.dex */
public final class HttpException extends Exception {
    private final int code;
    private final String message;
    private final transient Response<?> response;

    public HttpException(Response<?> response) {
        super("HTTP " + response.code() + LangUtils.SINGLE_SPACE + response.message());
        this.code = response.code();
        this.message = response.message();
        this.response = response;
    }

    public int code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public Response<?> response() {
        return this.response;
    }
}
