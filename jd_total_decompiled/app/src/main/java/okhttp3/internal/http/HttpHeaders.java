package okhttp3.internal.http;

import com.jd.dynamic.DYConstants;
import com.jd.jdcache.util.UrlHelper;
import com.tencent.smtt.sdk.ProxyConfig;
import java.io.EOFException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import okhttp3.Challenge;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.ByteString;

/* loaded from: classes11.dex */
public final class HttpHeaders {
    private static final ByteString QUOTED_STRING_DELIMITERS = ByteString.encodeUtf8("\"\\");
    private static final ByteString TOKEN_DELIMITERS = ByteString.encodeUtf8("\t ,=");

    private HttpHeaders() {
    }

    public static long contentLength(Response response) {
        return contentLength(response.headers());
    }

    public static boolean hasBody(Response response) {
        if (response.request().method().equals(UrlHelper.METHOD_HEAD)) {
            return false;
        }
        int code = response.code();
        return (((code >= 100 && code < 200) || code == 204 || code == 304) && contentLength(response) == -1 && !"chunked".equalsIgnoreCase(response.header(com.google.common.net.HttpHeaders.TRANSFER_ENCODING))) ? false : true;
    }

    public static boolean hasVaryAll(Response response) {
        return hasVaryAll(response.headers());
    }

    /* JADX WARN: Code restructure failed: missing block: B:61:0x007d, code lost:
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x007d, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void parseChallengeHeader(List<Challenge> list, Buffer buffer) {
        String readToken;
        int skipAll;
        String readToken2;
        while (true) {
            String str = null;
            while (true) {
                if (str == null) {
                    skipWhitespaceAndCommas(buffer);
                    str = readToken(buffer);
                    if (str == null) {
                        return;
                    }
                }
                boolean skipWhitespaceAndCommas = skipWhitespaceAndCommas(buffer);
                readToken = readToken(buffer);
                if (readToken == null) {
                    if (buffer.exhausted()) {
                        list.add(new Challenge(str, Collections.emptyMap()));
                        return;
                    }
                    return;
                }
                skipAll = skipAll(buffer, (byte) 61);
                boolean skipWhitespaceAndCommas2 = skipWhitespaceAndCommas(buffer);
                if (skipWhitespaceAndCommas || (!skipWhitespaceAndCommas2 && !buffer.exhausted())) {
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    int skipAll2 = skipAll + skipAll(buffer, (byte) 61);
                    while (true) {
                        if (readToken == null) {
                            readToken = readToken(buffer);
                            if (skipWhitespaceAndCommas(buffer)) {
                                break;
                            }
                            skipAll2 = skipAll(buffer, (byte) 61);
                        }
                        if (skipAll2 == 0) {
                            break;
                        } else if (skipAll2 > 1 || skipWhitespaceAndCommas(buffer)) {
                            return;
                        } else {
                            if (!buffer.exhausted() && buffer.getByte(0L) == 34) {
                                readToken2 = readQuotedString(buffer);
                            } else {
                                readToken2 = readToken(buffer);
                            }
                            if (readToken2 == null || ((String) linkedHashMap.put(readToken, readToken2)) != null) {
                                return;
                            }
                            if (!skipWhitespaceAndCommas(buffer) && !buffer.exhausted()) {
                                return;
                            }
                            readToken = null;
                        }
                    }
                    list.add(new Challenge(str, linkedHashMap));
                    str = readToken;
                }
            }
            list.add(new Challenge(str, Collections.singletonMap(null, readToken + repeat('=', skipAll))));
        }
    }

    public static List<Challenge> parseChallenges(Headers headers, String str) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < headers.size(); i2++) {
            if (str.equalsIgnoreCase(headers.name(i2))) {
                parseChallengeHeader(arrayList, new Buffer().writeUtf8(headers.value(i2)));
            }
        }
        return arrayList;
    }

    public static int parseSeconds(String str, int i2) {
        try {
            long parseLong = Long.parseLong(str);
            if (parseLong > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            if (parseLong < 0) {
                return 0;
            }
            return (int) parseLong;
        } catch (NumberFormatException unused) {
            return i2;
        }
    }

    private static String readQuotedString(Buffer buffer) {
        if (buffer.readByte() == 34) {
            Buffer buffer2 = new Buffer();
            while (true) {
                long indexOfElement = buffer.indexOfElement(QUOTED_STRING_DELIMITERS);
                if (indexOfElement == -1) {
                    return null;
                }
                if (buffer.getByte(indexOfElement) == 34) {
                    buffer2.write(buffer, indexOfElement);
                    buffer.readByte();
                    return buffer2.readUtf8();
                } else if (buffer.size() == indexOfElement + 1) {
                    return null;
                } else {
                    buffer2.write(buffer, indexOfElement);
                    buffer.readByte();
                    buffer2.write(buffer, 1L);
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    private static String readToken(Buffer buffer) {
        try {
            long indexOfElement = buffer.indexOfElement(TOKEN_DELIMITERS);
            if (indexOfElement == -1) {
                indexOfElement = buffer.size();
            }
            if (indexOfElement != 0) {
                return buffer.readUtf8(indexOfElement);
            }
            return null;
        } catch (EOFException unused) {
            throw new AssertionError();
        }
    }

    public static void receiveHeaders(CookieJar cookieJar, HttpUrl httpUrl, Headers headers) {
        if (cookieJar == CookieJar.NO_COOKIES) {
            return;
        }
        List<Cookie> parseAll = Cookie.parseAll(httpUrl, headers);
        if (parseAll.isEmpty()) {
            return;
        }
        cookieJar.saveFromResponse(httpUrl, parseAll);
    }

    private static String repeat(char c2, int i2) {
        char[] cArr = new char[i2];
        Arrays.fill(cArr, c2);
        return new String(cArr);
    }

    private static int skipAll(Buffer buffer, byte b) {
        int i2 = 0;
        while (!buffer.exhausted() && buffer.getByte(0L) == b) {
            i2++;
            buffer.readByte();
        }
        return i2;
    }

    public static int skipUntil(String str, int i2, String str2) {
        while (i2 < str.length() && str2.indexOf(str.charAt(i2)) == -1) {
            i2++;
        }
        return i2;
    }

    public static int skipWhitespace(String str, int i2) {
        char charAt;
        while (i2 < str.length() && ((charAt = str.charAt(i2)) == ' ' || charAt == '\t')) {
            i2++;
        }
        return i2;
    }

    private static boolean skipWhitespaceAndCommas(Buffer buffer) {
        boolean z = false;
        while (!buffer.exhausted()) {
            byte b = buffer.getByte(0L);
            if (b != 44) {
                if (b != 32 && b != 9) {
                    break;
                }
                buffer.readByte();
            } else {
                buffer.readByte();
                z = true;
            }
        }
        return z;
    }

    private static long stringToLong(String str) {
        if (str == null) {
            return -1L;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    private static Set<String> varyFields(Response response) {
        return varyFields(response.headers());
    }

    public static Headers varyHeaders(Response response) {
        return varyHeaders(response.networkResponse().request().headers(), response.headers());
    }

    public static boolean varyMatches(Response response, Headers headers, Request request) {
        for (String str : varyFields(response)) {
            if (!Util.equal(headers.values(str), request.headers(str))) {
                return false;
            }
        }
        return true;
    }

    public static long contentLength(Headers headers) {
        return stringToLong(headers.get(com.google.common.net.HttpHeaders.CONTENT_LENGTH));
    }

    public static boolean hasVaryAll(Headers headers) {
        return varyFields(headers).contains(ProxyConfig.MATCH_ALL_SCHEMES);
    }

    public static Set<String> varyFields(Headers headers) {
        Set<String> emptySet = Collections.emptySet();
        int size = headers.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (com.google.common.net.HttpHeaders.VARY.equalsIgnoreCase(headers.name(i2))) {
                String value = headers.value(i2);
                if (emptySet.isEmpty()) {
                    emptySet = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
                }
                for (String str : value.split(DYConstants.DY_REGEX_COMMA)) {
                    emptySet.add(str.trim());
                }
            }
        }
        return emptySet;
    }

    public static Headers varyHeaders(Headers headers, Headers headers2) {
        Set<String> varyFields = varyFields(headers2);
        if (varyFields.isEmpty()) {
            return new Headers.Builder().build();
        }
        Headers.Builder builder = new Headers.Builder();
        int size = headers.size();
        for (int i2 = 0; i2 < size; i2++) {
            String name = headers.name(i2);
            if (varyFields.contains(name)) {
                builder.add(name, headers.value(i2));
            }
        }
        return builder.build();
    }
}
