package okhttp3.internal.http;

import com.jd.jdcache.util.UrlHelper;

/* loaded from: classes11.dex */
public final class HttpMethod {
    private HttpMethod() {
    }

    public static boolean invalidatesCache(String str) {
        return str.equals("POST") || str.equals(UrlHelper.METHOD_PATCH) || str.equals(UrlHelper.METHOD_PUT) || str.equals(UrlHelper.METHOD_DELETE) || str.equals("MOVE");
    }

    public static boolean permitsRequestBody(String str) {
        return (str.equals("GET") || str.equals(UrlHelper.METHOD_HEAD)) ? false : true;
    }

    public static boolean redirectsToGet(String str) {
        return !str.equals("PROPFIND");
    }

    public static boolean redirectsWithBody(String str) {
        return str.equals("PROPFIND");
    }

    public static boolean requiresRequestBody(String str) {
        return str.equals("POST") || str.equals(UrlHelper.METHOD_PUT) || str.equals(UrlHelper.METHOD_PATCH) || str.equals("PROPPATCH") || str.equals("REPORT");
    }
}
