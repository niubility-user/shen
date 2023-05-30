package com.jingdong.sdk.deeplink;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.net.IDN;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.text.Typography;
import okio.Buffer;

/* loaded from: classes7.dex */
public final class DeepLinkUri {
    static final String CONVERT_TO_URI_ENCODE_SET = "^`{}|\\";
    static final String FORM_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#&!$(),~";
    static final String FRAGMENT_ENCODE_SET = "";
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    static final String PASSWORD_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#";
    static final String PATH_SEGMENT_ENCODE_SET = " \"<>^`{}|/\\?#";
    static final String QUERY_COMPONENT_ENCODE_SET = " \"'<>#&=";
    static final String QUERY_ENCODE_SET = " \"'<>#";
    static final String USERNAME_ENCODE_SET = " \"':;<=>@[]^`{}|/\\?#";
    private final String fragment;
    private final String host;
    private final String password;
    private final List<String> pathSegments;
    private final int port;
    private final List<String> queryNamesAndValues;
    private final String scheme;
    private final String url;
    private final String username;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.sdk.deeplink.DeepLinkUri$1  reason: invalid class name */
    /* loaded from: classes7.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$jingdong$sdk$deeplink$DeepLinkUri$Builder$ParseResult;

        static {
            int[] iArr = new int[Builder.ParseResult.values().length];
            $SwitchMap$com$jingdong$sdk$deeplink$DeepLinkUri$Builder$ParseResult = iArr;
            try {
                iArr[Builder.ParseResult.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$jingdong$sdk$deeplink$DeepLinkUri$Builder$ParseResult[Builder.ParseResult.INVALID_HOST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$jingdong$sdk$deeplink$DeepLinkUri$Builder$ParseResult[Builder.ParseResult.UNSUPPORTED_SCHEME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$jingdong$sdk$deeplink$DeepLinkUri$Builder$ParseResult[Builder.ParseResult.MISSING_SCHEME.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$jingdong$sdk$deeplink$DeepLinkUri$Builder$ParseResult[Builder.ParseResult.INVALID_PORT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* loaded from: classes7.dex */
    public static final class Builder {
        String encodedFragment;
        final List<String> encodedPathSegments;
        List<String> encodedQueryNamesAndValues;
        String host;
        String scheme;
        String encodedUsername = "";
        String encodedPassword = "";
        int port = -1;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes7.dex */
        public enum ParseResult {
            SUCCESS,
            MISSING_SCHEME,
            UNSUPPORTED_SCHEME,
            INVALID_PORT,
            INVALID_HOST
        }

        public Builder() {
            ArrayList arrayList = new ArrayList();
            this.encodedPathSegments = arrayList;
            arrayList.add("");
        }

        private static String canonicalizeHost(String str, int i2, int i3) {
            String percentDecode = DeepLinkUri.percentDecode(str, i2, i3);
            if (percentDecode.startsWith("[") && percentDecode.endsWith("]")) {
                InetAddress decodeIpv6 = decodeIpv6(percentDecode, 1, percentDecode.length() - 1);
                if (decodeIpv6 == null) {
                    return null;
                }
                byte[] address = decodeIpv6.getAddress();
                if (address.length == 16) {
                    return inet6AddressToAscii(address);
                }
                throw new AssertionError();
            }
            return domainToAscii(percentDecode);
        }

        private static boolean containsInvalidHostnameAsciiCodes(String str) {
            for (int i2 = 0; i2 < str.length(); i2++) {
                char charAt = str.charAt(i2);
                if (charAt <= 31 || charAt >= '\u007f' || " #%/:?@[\\]".indexOf(charAt) != -1) {
                    return true;
                }
            }
            return false;
        }

        private static boolean decodeIpv4Suffix(String str, int i2, int i3, byte[] bArr, int i4) {
            int i5 = i4;
            while (i2 < i3) {
                if (i5 == bArr.length) {
                    return false;
                }
                if (i5 != i4) {
                    if (str.charAt(i2) != '.') {
                        return false;
                    }
                    i2++;
                }
                int i6 = i2;
                int i7 = 0;
                while (i6 < i3) {
                    char charAt = str.charAt(i6);
                    if (charAt < '0' || charAt > '9') {
                        break;
                    } else if ((i7 == 0 && i2 != i6) || (i7 = ((i7 * 10) + charAt) - 48) > 255) {
                        return false;
                    } else {
                        i6++;
                    }
                }
                if (i6 - i2 == 0) {
                    return false;
                }
                bArr[i5] = (byte) i7;
                i5++;
                i2 = i6;
            }
            return i5 == i4 + 4;
        }

        /* JADX WARN: Code restructure failed: missing block: B:41:0x0079, code lost:
            return null;
         */
        /* JADX WARN: Removed duplicated region for block: B:31:0x004f  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private static java.net.InetAddress decodeIpv6(java.lang.String r11, int r12, int r13) {
            /*
                r0 = 16
                byte[] r1 = new byte[r0]
                r2 = -1
                r3 = 0
                r4 = 0
                r5 = -1
                r6 = -1
            L9:
                r7 = 0
                if (r12 >= r13) goto L7a
                if (r4 != r0) goto Lf
                return r7
            Lf:
                int r8 = r12 + 2
                if (r8 > r13) goto L27
                java.lang.String r9 = "::"
                r10 = 2
                boolean r9 = r11.regionMatches(r12, r9, r3, r10)
                if (r9 == 0) goto L27
                if (r5 == r2) goto L1f
                return r7
            L1f:
                int r4 = r4 + 2
                r5 = r4
                if (r8 != r13) goto L25
                goto L7a
            L25:
                r6 = r8
                goto L4b
            L27:
                if (r4 == 0) goto L4a
                java.lang.String r8 = ":"
                r9 = 1
                boolean r8 = r11.regionMatches(r12, r8, r3, r9)
                if (r8 == 0) goto L35
                int r12 = r12 + 1
                goto L4a
            L35:
                java.lang.String r8 = "."
                boolean r12 = r11.regionMatches(r12, r8, r3, r9)
                if (r12 == 0) goto L49
                int r12 = r4 + (-2)
                boolean r11 = decodeIpv4Suffix(r11, r6, r13, r1, r12)
                if (r11 != 0) goto L46
                return r7
            L46:
                int r4 = r4 + 2
                goto L7a
            L49:
                return r7
            L4a:
                r6 = r12
            L4b:
                r12 = r6
                r8 = 0
            L4d:
                if (r12 >= r13) goto L60
                char r9 = r11.charAt(r12)
                int r9 = com.jingdong.sdk.deeplink.DeepLinkUri.decodeHexDigit(r9)
                if (r9 != r2) goto L5a
                goto L60
            L5a:
                int r8 = r8 << 4
                int r8 = r8 + r9
                int r12 = r12 + 1
                goto L4d
            L60:
                int r9 = r12 - r6
                if (r9 == 0) goto L79
                r10 = 4
                if (r9 <= r10) goto L68
                goto L79
            L68:
                int r7 = r4 + 1
                int r9 = r8 >>> 8
                r9 = r9 & 255(0xff, float:3.57E-43)
                byte r9 = (byte) r9
                r1[r4] = r9
                int r4 = r7 + 1
                r8 = r8 & 255(0xff, float:3.57E-43)
                byte r8 = (byte) r8
                r1[r7] = r8
                goto L9
            L79:
                return r7
            L7a:
                if (r4 == r0) goto L8b
                if (r5 != r2) goto L7f
                return r7
            L7f:
                int r11 = r4 - r5
                int r12 = 16 - r11
                java.lang.System.arraycopy(r1, r5, r1, r12, r11)
                int r0 = r0 - r4
                int r0 = r0 + r5
                java.util.Arrays.fill(r1, r5, r0, r3)
            L8b:
                java.net.InetAddress r11 = java.net.InetAddress.getByAddress(r1)     // Catch: java.net.UnknownHostException -> L90
                return r11
            L90:
                java.lang.AssertionError r11 = new java.lang.AssertionError
                r11.<init>()
                goto L97
            L96:
                throw r11
            L97:
                goto L96
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.deeplink.DeepLinkUri.Builder.decodeIpv6(java.lang.String, int, int):java.net.InetAddress");
        }

        private static String domainToAscii(String str) {
            try {
                String lowerCase = IDN.toASCII(str).toLowerCase(Locale.US);
                if (lowerCase.isEmpty()) {
                    return null;
                }
                if (containsInvalidHostnameAsciiCodes(lowerCase)) {
                    return null;
                }
                return lowerCase;
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }

        private static String inet6AddressToAscii(byte[] bArr) {
            int i2 = 0;
            int i3 = -1;
            int i4 = 0;
            int i5 = 0;
            while (i4 < bArr.length) {
                int i6 = i4;
                while (i6 < 16 && bArr[i6] == 0 && bArr[i6 + 1] == 0) {
                    i6 += 2;
                }
                int i7 = i6 - i4;
                if (i7 > i5) {
                    i3 = i4;
                    i5 = i7;
                }
                i4 = i6 + 2;
            }
            Buffer buffer = new Buffer();
            while (i2 < bArr.length) {
                try {
                    if (i2 == i3) {
                        buffer.writeByte(58);
                        i2 += i5;
                        if (i2 == 16) {
                            buffer.writeByte(58);
                        }
                    } else {
                        if (i2 > 0) {
                            buffer.writeByte(58);
                        }
                        buffer.writeHexadecimalUnsignedLong(((bArr[i2] & 255) << 8) | (bArr[i2 + 1] & 255));
                        i2 += 2;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return null;
                } finally {
                    buffer.close();
                }
            }
            return buffer.readUtf8();
        }

        private boolean isDot(String str) {
            return str.equals(OrderISVUtil.MONEY_DECIMAL) || str.equalsIgnoreCase("%2e");
        }

        private boolean isDotDot(String str) {
            return str.equals("..") || str.equalsIgnoreCase("%2e.") || str.equalsIgnoreCase(".%2e") || str.equalsIgnoreCase("%2e%2e");
        }

        private static int parsePort(String str, int i2, int i3) {
            int parseInt;
            try {
                parseInt = Integer.parseInt(DeepLinkUri.canonicalize(str, i2, i3, "", false, false));
            } catch (NumberFormatException unused) {
            }
            if (parseInt <= 0 || parseInt > 65535) {
                return -1;
            }
            return parseInt;
        }

        private void pop() {
            if (this.encodedPathSegments.remove(r0.size() - 1).isEmpty() && !this.encodedPathSegments.isEmpty()) {
                this.encodedPathSegments.set(r0.size() - 1, "");
                return;
            }
            this.encodedPathSegments.add("");
        }

        private static int portColonOffset(String str, int i2, int i3) {
            while (i2 < i3) {
                char charAt = str.charAt(i2);
                if (charAt == ':') {
                    return i2;
                }
                if (charAt != '[') {
                    i2++;
                }
                do {
                    i2++;
                    if (i2 < i3) {
                    }
                    i2++;
                } while (str.charAt(i2) != ']');
                i2++;
            }
            return i3;
        }

        private void push(String str, int i2, int i3, boolean z, boolean z2) {
            String canonicalize = DeepLinkUri.canonicalize(str, i2, i3, DeepLinkUri.PATH_SEGMENT_ENCODE_SET, z2, false);
            if (isDot(canonicalize)) {
                return;
            }
            if (isDotDot(canonicalize)) {
                pop();
                return;
            }
            if (this.encodedPathSegments.get(r8.size() - 1).isEmpty()) {
                this.encodedPathSegments.set(r8.size() - 1, canonicalize);
            } else {
                this.encodedPathSegments.add(canonicalize);
            }
            if (z) {
                this.encodedPathSegments.add("");
            }
        }

        private void removeAllCanonicalQueryParameters(String str) {
            for (int size = this.encodedQueryNamesAndValues.size() - 2; size >= 0; size -= 2) {
                if (str.equals(this.encodedQueryNamesAndValues.get(size))) {
                    this.encodedQueryNamesAndValues.remove(size + 1);
                    this.encodedQueryNamesAndValues.remove(size);
                    if (this.encodedQueryNamesAndValues.isEmpty()) {
                        this.encodedQueryNamesAndValues = null;
                        return;
                    }
                }
            }
        }

        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:56)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:30)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:18)
            */
        /* JADX WARN: Removed duplicated region for block: B:13:0x002c  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0044 A[SYNTHETIC] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0041 -> B:11:0x0029). Please submit an issue!!! */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void resolvePath(java.lang.String r11, int r12, int r13) {
            /*
                r10 = this;
                if (r12 != r13) goto L3
                return
            L3:
                char r0 = r11.charAt(r12)
                r1 = 47
                java.lang.String r2 = ""
                r3 = 1
                if (r0 == r1) goto L1e
                r1 = 92
                if (r0 != r1) goto L13
                goto L1e
            L13:
                java.util.List<java.lang.String> r0 = r10.encodedPathSegments
                int r1 = r0.size()
                int r1 = r1 - r3
                r0.set(r1, r2)
                goto L29
            L1e:
                java.util.List<java.lang.String> r0 = r10.encodedPathSegments
                r0.clear()
                java.util.List<java.lang.String> r0 = r10.encodedPathSegments
                r0.add(r2)
                goto L41
            L29:
                r6 = r12
                if (r6 >= r13) goto L44
                java.lang.String r12 = "/\\"
                int r12 = com.jingdong.sdk.deeplink.DeepLinkUri.access$200(r11, r6, r13, r12)
                if (r12 >= r13) goto L36
                r0 = 1
                goto L37
            L36:
                r0 = 0
            L37:
                r9 = 1
                r4 = r10
                r5 = r11
                r7 = r12
                r8 = r0
                r4.push(r5, r6, r7, r8, r9)
                if (r0 == 0) goto L29
            L41:
                int r12 = r12 + 1
                goto L29
            L44:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.deeplink.DeepLinkUri.Builder.resolvePath(java.lang.String, int, int):void");
        }

        private static int schemeDelimiterOffset(String str, int i2, int i3) {
            if (i3 - i2 < 2) {
                return -1;
            }
            char charAt = str.charAt(i2);
            if ((charAt >= 'a' && charAt <= 'z') || (charAt >= 'A' && charAt <= 'Z')) {
                while (true) {
                    i2++;
                    if (i2 >= i3) {
                        break;
                    }
                    char charAt2 = str.charAt(i2);
                    if (charAt2 < 'a' || charAt2 > 'z') {
                        if (charAt2 < 'A' || charAt2 > 'Z') {
                            if (charAt2 < '0' || charAt2 > '9') {
                                if (charAt2 != '+' && charAt2 != '-' && charAt2 != '.') {
                                    if (charAt2 == ':') {
                                        return i2;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return -1;
        }

        private int skipLeadingAsciiWhitespace(String str, int i2, int i3) {
            while (i2 < i3) {
                char charAt = str.charAt(i2);
                if (charAt != '\t' && charAt != '\n' && charAt != '\f' && charAt != '\r' && charAt != ' ') {
                    return i2;
                }
                i2++;
            }
            return i3;
        }

        private int skipTrailingAsciiWhitespace(String str, int i2, int i3) {
            for (int i4 = i3 - 1; i4 >= i2; i4--) {
                char charAt = str.charAt(i4);
                if (charAt != '\t' && charAt != '\n' && charAt != '\f' && charAt != '\r' && charAt != ' ') {
                    return i4 + 1;
                }
            }
            return i2;
        }

        private static int slashCount(String str, int i2, int i3) {
            int i4 = 0;
            while (i2 < i3) {
                char charAt = str.charAt(i2);
                if (charAt != '\\' && charAt != '/') {
                    break;
                }
                i4++;
                i2++;
            }
            return i4;
        }

        public Builder addEncodedPathSegment(String str) {
            if (str != null) {
                push(str, 0, str.length(), false, true);
                return this;
            }
            throw new IllegalArgumentException("encodedPathSegment == null");
        }

        public Builder addEncodedQueryParameter(String str, String str2) {
            if (str != null) {
                if (this.encodedQueryNamesAndValues == null) {
                    this.encodedQueryNamesAndValues = new ArrayList();
                }
                this.encodedQueryNamesAndValues.add(DeepLinkUri.canonicalize(str, DeepLinkUri.QUERY_COMPONENT_ENCODE_SET, true, true));
                this.encodedQueryNamesAndValues.add(str2 != null ? DeepLinkUri.canonicalize(str2, DeepLinkUri.QUERY_COMPONENT_ENCODE_SET, true, true) : null);
                return this;
            }
            throw new IllegalArgumentException("encodedName == null");
        }

        public Builder addPathSegment(String str) {
            if (str != null) {
                push(str, 0, str.length(), false, false);
                return this;
            }
            throw new IllegalArgumentException("pathSegment == null");
        }

        public Builder addQueryParameter(String str, String str2) {
            if (str != null) {
                if (this.encodedQueryNamesAndValues == null) {
                    this.encodedQueryNamesAndValues = new ArrayList();
                }
                this.encodedQueryNamesAndValues.add(DeepLinkUri.canonicalize(str, DeepLinkUri.QUERY_COMPONENT_ENCODE_SET, false, true));
                this.encodedQueryNamesAndValues.add(str2 != null ? DeepLinkUri.canonicalize(str2, DeepLinkUri.QUERY_COMPONENT_ENCODE_SET, false, true) : null);
                return this;
            }
            throw new IllegalArgumentException("name == null");
        }

        DeepLinkUri build() {
            if (this.scheme != null) {
                if (this.host != null) {
                    return new DeepLinkUri(this, null);
                }
                throw new IllegalStateException("host == null");
            }
            throw new IllegalStateException("scheme == null");
        }

        int effectivePort() {
            int i2 = this.port;
            return i2 != -1 ? i2 : DeepLinkUri.defaultPort(this.scheme);
        }

        Builder encodedFragment(String str) {
            if (str != null) {
                this.encodedFragment = DeepLinkUri.canonicalize(str, "", true, false);
                return this;
            }
            throw new IllegalArgumentException("encodedFragment == null");
        }

        public Builder encodedPassword(String str) {
            if (str != null) {
                this.encodedPassword = DeepLinkUri.canonicalize(str, " \"':;<=>@[]^`{}|/\\?#", true, false);
                return this;
            }
            throw new IllegalArgumentException("encodedPassword == null");
        }

        public Builder encodedPath(String str) {
            if (str != null) {
                if (str.startsWith("/")) {
                    resolvePath(str, 0, str.length());
                    return this;
                }
                throw new IllegalArgumentException("unexpected encodedPath: " + str);
            }
            throw new IllegalArgumentException("encodedPath == null");
        }

        public Builder encodedQuery(String str) {
            this.encodedQueryNamesAndValues = str != null ? DeepLinkUri.queryStringToNamesAndValues(DeepLinkUri.canonicalize(str, DeepLinkUri.QUERY_ENCODE_SET, true, true)) : null;
            return this;
        }

        public Builder encodedUsername(String str) {
            if (str != null) {
                this.encodedUsername = DeepLinkUri.canonicalize(str, " \"':;<=>@[]^`{}|/\\?#", true, false);
                return this;
            }
            throw new IllegalArgumentException("encodedUsername == null");
        }

        Builder fragment(String str) {
            if (str != null) {
                this.encodedFragment = DeepLinkUri.canonicalize(str, "", false, false);
                return this;
            }
            throw new IllegalArgumentException("fragment == null");
        }

        public Builder host(String str) {
            if (str != null) {
                String canonicalizeHost = canonicalizeHost(str, 0, str.length());
                if (canonicalizeHost != null) {
                    this.host = canonicalizeHost;
                    return this;
                }
                throw new IllegalArgumentException("unexpected host: " + str);
            }
            throw new IllegalArgumentException("host == null");
        }

        ParseResult parse(DeepLinkUri deepLinkUri, String str) {
            int delimiterOffset;
            boolean z = false;
            int skipLeadingAsciiWhitespace = skipLeadingAsciiWhitespace(str, 0, str.length());
            int skipTrailingAsciiWhitespace = skipTrailingAsciiWhitespace(str, skipLeadingAsciiWhitespace, str.length());
            int schemeDelimiterOffset = schemeDelimiterOffset(str, skipLeadingAsciiWhitespace, skipTrailingAsciiWhitespace);
            if (schemeDelimiterOffset != -1) {
                if (str.regionMatches(true, skipLeadingAsciiWhitespace, "https:", 0, 6)) {
                    this.scheme = "https";
                    skipLeadingAsciiWhitespace += 6;
                } else if (str.regionMatches(true, skipLeadingAsciiWhitespace, "http:", 0, 5)) {
                    this.scheme = "http";
                    skipLeadingAsciiWhitespace += 5;
                } else {
                    String substring = str.substring(skipLeadingAsciiWhitespace, schemeDelimiterOffset);
                    this.scheme = substring;
                    skipLeadingAsciiWhitespace += substring.length() + 1;
                }
            } else if (deepLinkUri != null) {
                this.scheme = deepLinkUri.scheme;
            } else {
                return ParseResult.MISSING_SCHEME;
            }
            int slashCount = slashCount(str, skipLeadingAsciiWhitespace, skipTrailingAsciiWhitespace);
            char c2 = '#';
            if (slashCount < 2 && deepLinkUri != null && deepLinkUri.scheme.equals(this.scheme)) {
                this.encodedUsername = deepLinkUri.encodedUsername();
                this.encodedPassword = deepLinkUri.encodedPassword();
                this.host = deepLinkUri.host;
                this.port = deepLinkUri.port;
                this.encodedPathSegments.clear();
                this.encodedPathSegments.addAll(deepLinkUri.encodedPathSegments());
                if (skipLeadingAsciiWhitespace == skipTrailingAsciiWhitespace || str.charAt(skipLeadingAsciiWhitespace) == '#') {
                    encodedQuery(deepLinkUri.encodedQuery());
                }
            } else {
                int i2 = skipLeadingAsciiWhitespace + slashCount;
                boolean z2 = false;
                while (true) {
                    delimiterOffset = DeepLinkUri.delimiterOffset(str, i2, skipTrailingAsciiWhitespace, "@/\\?#");
                    char charAt = delimiterOffset != skipTrailingAsciiWhitespace ? str.charAt(delimiterOffset) : '\uffff';
                    if (charAt == '\uffff' || charAt == c2 || charAt == '/' || charAt == '\\' || charAt == '?') {
                        break;
                    }
                    if (charAt == '@') {
                        if (!z) {
                            int delimiterOffset2 = DeepLinkUri.delimiterOffset(str, i2, delimiterOffset, ":");
                            String canonicalize = DeepLinkUri.canonicalize(str, i2, delimiterOffset2, " \"':;<=>@[]^`{}|/\\?#", true, false);
                            if (z2) {
                                canonicalize = this.encodedUsername + "%40" + canonicalize;
                            }
                            this.encodedUsername = canonicalize;
                            if (delimiterOffset2 != delimiterOffset) {
                                this.encodedPassword = DeepLinkUri.canonicalize(str, delimiterOffset2 + 1, delimiterOffset, " \"':;<=>@[]^`{}|/\\?#", true, false);
                                z = true;
                            }
                            z2 = true;
                        } else {
                            this.encodedPassword += "%40" + DeepLinkUri.canonicalize(str, i2, delimiterOffset, " \"':;<=>@[]^`{}|/\\?#", true, false);
                        }
                        i2 = delimiterOffset + 1;
                    }
                    c2 = '#';
                }
                int portColonOffset = portColonOffset(str, i2, delimiterOffset);
                int i3 = portColonOffset + 1;
                if (i3 < delimiterOffset) {
                    this.host = canonicalizeHost(str, i2, portColonOffset);
                    int parsePort = parsePort(str, i3, delimiterOffset);
                    this.port = parsePort;
                    if (parsePort == -1) {
                        return ParseResult.INVALID_PORT;
                    }
                } else {
                    this.host = canonicalizeHost(str, i2, portColonOffset);
                    this.port = DeepLinkUri.defaultPort(this.scheme);
                }
                if (this.host == null) {
                    return ParseResult.INVALID_HOST;
                }
                skipLeadingAsciiWhitespace = delimiterOffset;
            }
            int delimiterOffset3 = DeepLinkUri.delimiterOffset(str, skipLeadingAsciiWhitespace, skipTrailingAsciiWhitespace, "?#");
            resolvePath(str, skipLeadingAsciiWhitespace, delimiterOffset3);
            if (delimiterOffset3 < skipTrailingAsciiWhitespace && str.charAt(delimiterOffset3) == '?') {
                int delimiterOffset4 = DeepLinkUri.delimiterOffset(str, delimiterOffset3, skipTrailingAsciiWhitespace, "#");
                this.encodedQueryNamesAndValues = DeepLinkUri.queryStringToNamesAndValues(DeepLinkUri.canonicalize(str, delimiterOffset3 + 1, delimiterOffset4, DeepLinkUri.QUERY_ENCODE_SET, true, true));
                delimiterOffset3 = delimiterOffset4;
            }
            if (delimiterOffset3 < skipTrailingAsciiWhitespace && str.charAt(delimiterOffset3) == '#') {
                this.encodedFragment = DeepLinkUri.canonicalize(str, 1 + delimiterOffset3, skipTrailingAsciiWhitespace, "", true, false);
            }
            return ParseResult.SUCCESS;
        }

        public Builder password(String str) {
            if (str != null) {
                this.encodedPassword = DeepLinkUri.canonicalize(str, " \"':;<=>@[]^`{}|/\\?#", false, false);
                return this;
            }
            throw new IllegalArgumentException("password == null");
        }

        public Builder port(int i2) {
            if (i2 > 0 && i2 <= 65535) {
                this.port = i2;
                return this;
            }
            throw new IllegalArgumentException("unexpected port: " + i2);
        }

        public Builder query(String str) {
            this.encodedQueryNamesAndValues = str != null ? DeepLinkUri.queryStringToNamesAndValues(DeepLinkUri.canonicalize(str, DeepLinkUri.QUERY_ENCODE_SET, false, true)) : null;
            return this;
        }

        public Builder removeAllEncodedQueryParameters(String str) {
            if (str != null) {
                if (this.encodedQueryNamesAndValues == null) {
                    return this;
                }
                removeAllCanonicalQueryParameters(DeepLinkUri.canonicalize(str, DeepLinkUri.QUERY_COMPONENT_ENCODE_SET, true, true));
                return this;
            }
            throw new IllegalArgumentException("encodedName == null");
        }

        public Builder removeAllQueryParameters(String str) {
            if (str != null) {
                if (this.encodedQueryNamesAndValues == null) {
                    return this;
                }
                removeAllCanonicalQueryParameters(DeepLinkUri.canonicalize(str, DeepLinkUri.QUERY_COMPONENT_ENCODE_SET, false, true));
                return this;
            }
            throw new IllegalArgumentException("name == null");
        }

        public Builder removePathSegment(int i2) {
            this.encodedPathSegments.remove(i2);
            if (this.encodedPathSegments.isEmpty()) {
                this.encodedPathSegments.add("");
            }
            return this;
        }

        public Builder scheme(String str) {
            if (str != null) {
                this.scheme = str;
                return this;
            }
            throw new IllegalArgumentException("scheme == null");
        }

        public Builder setEncodedPathSegment(int i2, String str) {
            if (str != null) {
                String canonicalize = DeepLinkUri.canonicalize(str, 0, str.length(), DeepLinkUri.PATH_SEGMENT_ENCODE_SET, true, false);
                this.encodedPathSegments.set(i2, canonicalize);
                if (isDot(canonicalize) || isDotDot(canonicalize)) {
                    throw new IllegalArgumentException("unexpected path segment: " + str);
                }
                return this;
            }
            throw new IllegalArgumentException("encodedPathSegment == null");
        }

        public Builder setEncodedQueryParameter(String str, String str2) {
            removeAllEncodedQueryParameters(str);
            addEncodedQueryParameter(str, str2);
            return this;
        }

        public Builder setPathSegment(int i2, String str) {
            if (str != null) {
                String canonicalize = DeepLinkUri.canonicalize(str, 0, str.length(), DeepLinkUri.PATH_SEGMENT_ENCODE_SET, false, false);
                if (!isDot(canonicalize) && !isDotDot(canonicalize)) {
                    this.encodedPathSegments.set(i2, canonicalize);
                    return this;
                }
                throw new IllegalArgumentException("unexpected path segment: " + str);
            }
            throw new IllegalArgumentException("pathSegment == null");
        }

        public Builder setQueryParameter(String str, String str2) {
            removeAllQueryParameters(str);
            addQueryParameter(str, str2);
            return this;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.scheme);
            sb.append("://");
            if (!this.encodedUsername.isEmpty() || !this.encodedPassword.isEmpty()) {
                sb.append(this.encodedUsername);
                if (!this.encodedPassword.isEmpty()) {
                    sb.append(':');
                    sb.append(this.encodedPassword);
                }
                sb.append('@');
            }
            if (this.host.indexOf(58) != -1) {
                sb.append('[');
                sb.append(this.host);
                sb.append(']');
            } else {
                sb.append(this.host);
            }
            int effectivePort = effectivePort();
            if (effectivePort != DeepLinkUri.defaultPort(this.scheme)) {
                sb.append(':');
                sb.append(effectivePort);
            }
            DeepLinkUri.pathSegmentsToString(sb, this.encodedPathSegments);
            if (this.encodedQueryNamesAndValues != null) {
                sb.append('?');
                DeepLinkUri.namesAndValuesToQueryString(sb, this.encodedQueryNamesAndValues);
            }
            if (this.encodedFragment != null) {
                sb.append('#');
                sb.append(this.encodedFragment);
            }
            return sb.toString();
        }

        public Builder username(String str) {
            if (str != null) {
                this.encodedUsername = DeepLinkUri.canonicalize(str, " \"':;<=>@[]^`{}|/\\?#", false, false);
                return this;
            }
            throw new IllegalArgumentException("username == null");
        }
    }

    /* synthetic */ DeepLinkUri(Builder builder, AnonymousClass1 anonymousClass1) {
        this(builder);
    }

    static String canonicalize(String str, int i2, int i3, String str2, boolean z, boolean z2) {
        int i4 = i2;
        while (i4 < i3) {
            int codePointAt = str.codePointAt(i4);
            if (codePointAt >= 32 && codePointAt < 127 && str2.indexOf(codePointAt) == -1 && ((codePointAt != 37 || z) && (!z2 || codePointAt != 43))) {
                i4 += Character.charCount(codePointAt);
            } else {
                Buffer buffer = new Buffer();
                buffer.writeUtf8(str, i2, i4);
                canonicalize(buffer, str, i4, i3, str2, z, z2);
                return buffer.readUtf8();
            }
        }
        return str.substring(i2, i3);
    }

    static int decodeHexDigit(char c2) {
        if (c2 < '0' || c2 > '9') {
            char c3 = 'a';
            if (c2 < 'a' || c2 > 'f') {
                c3 = 'A';
                if (c2 < 'A' || c2 > 'F') {
                    return -1;
                }
            }
            return (c2 - c3) + 10;
        }
        return c2 - '0';
    }

    static int defaultPort(String str) {
        if (str.equals("http")) {
            return 80;
        }
        return str.equals("https") ? 443 : -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int delimiterOffset(String str, int i2, int i3, String str2) {
        while (i2 < i3) {
            if (str2.indexOf(str.charAt(i2)) != -1) {
                return i2;
            }
            i2++;
        }
        return i3;
    }

    static DeepLinkUri get(URL url) {
        return parse(url.toString());
    }

    static DeepLinkUri getChecked(String str) throws MalformedURLException, UnknownHostException {
        Builder builder = new Builder();
        Builder.ParseResult parse = builder.parse(null, str);
        int i2 = AnonymousClass1.$SwitchMap$com$jingdong$sdk$deeplink$DeepLinkUri$Builder$ParseResult[parse.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                throw new MalformedURLException("Invalid URL: " + parse + " for " + str);
            }
            throw new UnknownHostException("Invalid host: " + str);
        }
        return builder.build();
    }

    static void namesAndValuesToQueryString(StringBuilder sb, List<String> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2 += 2) {
            String str = list.get(i2);
            String str2 = list.get(i2 + 1);
            if (i2 > 0) {
                sb.append(Typography.amp);
            }
            sb.append(str);
            if (str2 != null) {
                sb.append('=');
                sb.append(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DeepLinkUri parse(String str) {
        Builder builder = new Builder();
        if (builder.parse(null, str) == Builder.ParseResult.SUCCESS) {
            return builder.build();
        }
        return null;
    }

    static void pathSegmentsToString(StringBuilder sb, List<String> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            sb.append('/');
            sb.append(list.get(i2));
        }
    }

    static String percentDecode(String str) {
        return percentDecode(str, 0, str.length());
    }

    static List<String> queryStringToNamesAndValues(String str) {
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 <= str.length()) {
            int indexOf = str.indexOf(38, i2);
            if (indexOf == -1) {
                indexOf = str.length();
            }
            int indexOf2 = str.indexOf(61, i2);
            if (indexOf2 != -1 && indexOf2 <= indexOf) {
                arrayList.add(str.substring(i2, indexOf2));
                arrayList.add(str.substring(indexOf2 + 1, indexOf));
            } else {
                arrayList.add(str.substring(i2, indexOf));
                arrayList.add(null);
            }
            i2 = indexOf + 1;
        }
        return arrayList;
    }

    String encodedFragment() {
        if (this.fragment == null) {
            return null;
        }
        return this.url.substring(this.url.indexOf(35) + 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String encodedHost() {
        return canonicalize(this.host, CONVERT_TO_URI_ENCODE_SET, true, true);
    }

    String encodedPassword() {
        if (this.password.isEmpty()) {
            return "";
        }
        int indexOf = this.url.indexOf(64);
        return this.url.substring(this.url.indexOf(58, this.scheme.length() + 3) + 1, indexOf);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String encodedPath() {
        int indexOf = this.url.indexOf(47, this.scheme.length() + 3);
        String str = this.url;
        return this.url.substring(indexOf, delimiterOffset(str, indexOf, str.length(), "?#"));
    }

    List<String> encodedPathSegments() {
        int indexOf = this.url.indexOf(47, this.scheme.length() + 3);
        String str = this.url;
        int delimiterOffset = delimiterOffset(str, indexOf, str.length(), "?#");
        ArrayList arrayList = new ArrayList();
        while (indexOf < delimiterOffset) {
            int i2 = indexOf + 1;
            int delimiterOffset2 = delimiterOffset(this.url, i2, delimiterOffset, "/");
            arrayList.add(this.url.substring(i2, delimiterOffset2));
            indexOf = delimiterOffset2;
        }
        return arrayList;
    }

    String encodedQuery() {
        if (this.queryNamesAndValues == null) {
            return null;
        }
        int indexOf = this.url.indexOf(63) + 1;
        String str = this.url;
        return this.url.substring(indexOf, delimiterOffset(str, indexOf + 1, str.length(), "#"));
    }

    String encodedUsername() {
        if (this.username.isEmpty()) {
            return "";
        }
        int length = this.scheme.length() + 3;
        String str = this.url;
        return this.url.substring(length, delimiterOffset(str, length, str.length(), ":@"));
    }

    public boolean equals(Object obj) {
        return (obj instanceof DeepLinkUri) && ((DeepLinkUri) obj).url.equals(this.url);
    }

    String fragment() {
        return this.fragment;
    }

    public int hashCode() {
        return this.url.hashCode();
    }

    String host() {
        return this.host;
    }

    boolean isHttps() {
        return this.scheme.equals("https");
    }

    Builder newBuilder() {
        Builder builder = new Builder();
        builder.scheme = this.scheme;
        builder.encodedUsername = encodedUsername();
        builder.encodedPassword = encodedPassword();
        builder.host = this.host;
        builder.port = this.port;
        builder.encodedPathSegments.clear();
        builder.encodedPathSegments.addAll(encodedPathSegments());
        builder.encodedQuery(encodedQuery());
        builder.encodedFragment = encodedFragment();
        return builder;
    }

    String password() {
        return this.password;
    }

    List<String> pathSegments() {
        return this.pathSegments;
    }

    int pathSize() {
        return this.pathSegments.size();
    }

    int port() {
        return this.port;
    }

    String query() {
        if (this.queryNamesAndValues == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        namesAndValuesToQueryString(sb, this.queryNamesAndValues);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String queryParameter(String str) {
        List<String> list = this.queryNamesAndValues;
        if (list == null) {
            return null;
        }
        int size = list.size();
        for (int i2 = 0; i2 < size; i2 += 2) {
            if (str.equals(this.queryNamesAndValues.get(i2))) {
                return this.queryNamesAndValues.get(i2 + 1);
            }
        }
        return null;
    }

    String queryParameterName(int i2) {
        return this.queryNamesAndValues.get(i2 * 2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Set<String> queryParameterNames() {
        if (this.queryNamesAndValues == null) {
            return Collections.emptySet();
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int size = this.queryNamesAndValues.size();
        for (int i2 = 0; i2 < size; i2 += 2) {
            linkedHashSet.add(this.queryNamesAndValues.get(i2));
        }
        return Collections.unmodifiableSet(linkedHashSet);
    }

    String queryParameterValue(int i2) {
        return this.queryNamesAndValues.get((i2 * 2) + 1);
    }

    List<String> queryParameterValues(String str) {
        if (this.queryNamesAndValues == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        int size = this.queryNamesAndValues.size();
        for (int i2 = 0; i2 < size; i2 += 2) {
            if (str.equals(this.queryNamesAndValues.get(i2))) {
                arrayList.add(this.queryNamesAndValues.get(i2 + 1));
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    int querySize() {
        List<String> list = this.queryNamesAndValues;
        if (list != null) {
            return list.size() / 2;
        }
        return 0;
    }

    DeepLinkUri resolve(String str) {
        Builder builder = new Builder();
        if (builder.parse(this, str) == Builder.ParseResult.SUCCESS) {
            return builder.build();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String scheme() {
        return this.scheme;
    }

    public String toString() {
        return this.url;
    }

    URI uri() {
        try {
            return new URI(canonicalize(this.url, CONVERT_TO_URI_ENCODE_SET, true, false));
        } catch (URISyntaxException unused) {
            throw new IllegalStateException("not valid as a java.net.URI: " + this.url);
        }
    }

    URL url() {
        try {
            return new URL(this.url);
        } catch (MalformedURLException e2) {
            throw new RuntimeException(e2);
        }
    }

    String username() {
        return this.username;
    }

    private DeepLinkUri(Builder builder) {
        this.scheme = builder.scheme;
        this.username = percentDecode(builder.encodedUsername);
        this.password = percentDecode(builder.encodedPassword);
        this.host = builder.host;
        this.port = builder.effectivePort();
        this.pathSegments = percentDecode(builder.encodedPathSegments);
        List<String> list = builder.encodedQueryNamesAndValues;
        this.queryNamesAndValues = list != null ? percentDecode(list) : null;
        String str = builder.encodedFragment;
        this.fragment = str != null ? percentDecode(str) : null;
        this.url = builder.toString();
    }

    static DeepLinkUri get(URI uri) {
        return parse(uri.toString());
    }

    private List<String> percentDecode(List<String> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String next = it.next();
            arrayList.add(next != null ? percentDecode(next) : null);
        }
        return Collections.unmodifiableList(arrayList);
    }

    static String percentDecode(String str, int i2, int i3) {
        for (int i4 = i2; i4 < i3; i4++) {
            if (str.charAt(i4) == '%') {
                Buffer buffer = new Buffer();
                buffer.writeUtf8(str, i2, i4);
                percentDecode(buffer, str, i4, i3);
                return buffer.readUtf8();
            }
        }
        return str.substring(i2, i3);
    }

    static void canonicalize(Buffer buffer, String str, int i2, int i3, String str2, boolean z, boolean z2) {
        Buffer buffer2 = null;
        while (i2 < i3) {
            int codePointAt = str.codePointAt(i2);
            if (!z || (codePointAt != 9 && codePointAt != 10 && codePointAt != 12 && codePointAt != 13)) {
                if (z2 && codePointAt == 43) {
                    buffer.writeUtf8(z ? "%20" : "%2B");
                } else if (codePointAt >= 32 && codePointAt < 127 && str2.indexOf(codePointAt) == -1 && (codePointAt != 37 || z)) {
                    buffer.writeUtf8CodePoint(codePointAt);
                } else {
                    if (buffer2 == null) {
                        buffer2 = new Buffer();
                    }
                    buffer2.writeUtf8CodePoint(codePointAt);
                    while (!buffer2.exhausted()) {
                        int readByte = buffer2.readByte() & 255;
                        buffer.writeByte(37);
                        char[] cArr = HEX_DIGITS;
                        buffer.writeByte((int) cArr[(readByte >> 4) & 15]);
                        buffer.writeByte((int) cArr[readByte & 15]);
                    }
                }
            }
            i2 += Character.charCount(codePointAt);
        }
    }

    static void percentDecode(Buffer buffer, String str, int i2, int i3) {
        int i4;
        while (i2 < i3) {
            int codePointAt = str.codePointAt(i2);
            if (codePointAt == 37 && (i4 = i2 + 2) < i3) {
                int decodeHexDigit = decodeHexDigit(str.charAt(i2 + 1));
                int decodeHexDigit2 = decodeHexDigit(str.charAt(i4));
                if (decodeHexDigit != -1 && decodeHexDigit2 != -1) {
                    buffer.writeByte((decodeHexDigit << 4) + decodeHexDigit2);
                    i2 = i4;
                    i2 += Character.charCount(codePointAt);
                }
            }
            buffer.writeUtf8CodePoint(codePointAt);
            i2 += Character.charCount(codePointAt);
        }
    }

    static String canonicalize(String str, String str2, boolean z, boolean z2) {
        return canonicalize(str, 0, str.length(), str2, z, z2);
    }
}
