package retrofit2;

import com.google.common.net.HttpHeaders;
import com.jd.jdcache.util.UrlHelper;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.ParameterHandler;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.OPTIONS;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class ServiceMethod<T> {
    private final HttpUrl baseUrl;
    final CallAdapter<?> callAdapter;
    final Call.Factory callFactory;
    private final MediaType contentType;
    private final boolean hasBody;
    private final Headers headers;
    private final String httpMethod;
    private final boolean isFormEncoded;
    private final boolean isMultipart;
    private final ParameterHandler<?>[] parameterHandlers;
    private final String relativeUrl;
    private final Converter<ResponseBody, T> responseConverter;
    static final Pattern PARAM_URL_REGEX = Pattern.compile("\\{([a-zA-Z][a-zA-Z0-9_-]*)\\}");
    static final String PARAM = "[a-zA-Z][a-zA-Z0-9_-]*";
    static final Pattern PARAM_NAME_REGEX = Pattern.compile(PARAM);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class Builder<T> {
        CallAdapter<?> callAdapter;
        MediaType contentType;
        boolean gotBody;
        boolean gotField;
        boolean gotPart;
        boolean gotPath;
        boolean gotQuery;
        boolean gotUrl;
        boolean hasBody;
        Headers headers;
        String httpMethod;
        boolean isFormEncoded;
        boolean isMultipart;
        final Method method;
        final Annotation[] methodAnnotations;
        final Annotation[][] parameterAnnotationsArray;
        ParameterHandler<?>[] parameterHandlers;
        final Type[] parameterTypes;
        String relativeUrl;
        Set<String> relativeUrlParamNames;
        Converter<ResponseBody, T> responseConverter;
        Type responseType;
        final Retrofit retrofit;

        public Builder(Retrofit retrofit, Method method) {
            this.retrofit = retrofit;
            this.method = method;
            this.methodAnnotations = method.getAnnotations();
            this.parameterTypes = method.getGenericParameterTypes();
            this.parameterAnnotationsArray = method.getParameterAnnotations();
        }

        private CallAdapter<?> createCallAdapter() {
            Type genericReturnType = this.method.getGenericReturnType();
            if (!Utils.hasUnresolvableType(genericReturnType)) {
                if (genericReturnType != Void.TYPE) {
                    try {
                        return this.retrofit.callAdapter(genericReturnType, this.method.getAnnotations());
                    } catch (RuntimeException e2) {
                        throw methodError(e2, "Unable to create call adapter for %s", genericReturnType);
                    }
                }
                throw methodError("Service methods cannot return void.", new Object[0]);
            }
            throw methodError("Method return type must not include a type variable or wildcard: %s", genericReturnType);
        }

        private Converter<ResponseBody, T> createResponseConverter() {
            try {
                return this.retrofit.responseBodyConverter(this.responseType, this.method.getAnnotations());
            } catch (RuntimeException e2) {
                throw methodError(e2, "Unable to create converter for %s", this.responseType);
            }
        }

        private RuntimeException methodError(String str, Object... objArr) {
            return methodError(null, str, objArr);
        }

        private RuntimeException parameterError(Throwable th, int i2, String str, Object... objArr) {
            return methodError(th, str + " (parameter #" + (i2 + 1) + ")", objArr);
        }

        private Headers parseHeaders(String[] strArr) {
            Headers.Builder builder = new Headers.Builder();
            for (String str : strArr) {
                int indexOf = str.indexOf(58);
                if (indexOf != -1 && indexOf != 0 && indexOf != str.length() - 1) {
                    String substring = str.substring(0, indexOf);
                    String trim = str.substring(indexOf + 1).trim();
                    if (HttpHeaders.CONTENT_TYPE.equalsIgnoreCase(substring)) {
                        MediaType parse = MediaType.parse(trim);
                        if (parse != null) {
                            this.contentType = parse;
                        } else {
                            throw methodError("Malformed content type: %s", trim);
                        }
                    } else {
                        builder.add(substring, trim);
                    }
                } else {
                    throw methodError("@Headers value must be in the form \"Name: Value\". Found: \"%s\"", str);
                }
            }
            return builder.build();
        }

        private void parseHttpMethodAndPath(String str, String str2, boolean z) {
            String str3 = this.httpMethod;
            if (str3 == null) {
                this.httpMethod = str;
                this.hasBody = z;
                if (str2.isEmpty()) {
                    return;
                }
                int indexOf = str2.indexOf(63);
                if (indexOf != -1 && indexOf < str2.length() - 1) {
                    String substring = str2.substring(indexOf + 1);
                    if (ServiceMethod.PARAM_URL_REGEX.matcher(substring).find()) {
                        throw methodError("URL query string \"%s\" must not have replace block. For dynamic query parameters use @Query.", substring);
                    }
                }
                this.relativeUrl = str2;
                this.relativeUrlParamNames = ServiceMethod.parsePathParameters(str2);
                return;
            }
            throw methodError("Only one HTTP method is allowed. Found: %s and %s.", str3, str);
        }

        private void parseMethodAnnotation(Annotation annotation) {
            if (annotation instanceof DELETE) {
                parseHttpMethodAndPath(UrlHelper.METHOD_DELETE, ((DELETE) annotation).value(), false);
            } else if (annotation instanceof GET) {
                parseHttpMethodAndPath("GET", ((GET) annotation).value(), false);
            } else if (annotation instanceof HEAD) {
                parseHttpMethodAndPath(UrlHelper.METHOD_HEAD, ((HEAD) annotation).value(), false);
                if (!Void.class.equals(this.responseType)) {
                    throw methodError("HEAD method must use Void as response type.", new Object[0]);
                }
            } else if (annotation instanceof PATCH) {
                parseHttpMethodAndPath(UrlHelper.METHOD_PATCH, ((PATCH) annotation).value(), true);
            } else if (annotation instanceof POST) {
                parseHttpMethodAndPath("POST", ((POST) annotation).value(), true);
            } else if (annotation instanceof PUT) {
                parseHttpMethodAndPath(UrlHelper.METHOD_PUT, ((PUT) annotation).value(), true);
            } else if (annotation instanceof OPTIONS) {
                parseHttpMethodAndPath(UrlHelper.METHOD_OPTIONS, ((OPTIONS) annotation).value(), false);
            } else if (annotation instanceof HTTP) {
                HTTP http = (HTTP) annotation;
                parseHttpMethodAndPath(http.method(), http.path(), http.hasBody());
            } else if (annotation instanceof retrofit2.http.Headers) {
                String[] value = ((retrofit2.http.Headers) annotation).value();
                if (value.length != 0) {
                    this.headers = parseHeaders(value);
                    return;
                }
                throw methodError("@Headers annotation is empty.", new Object[0]);
            } else if (annotation instanceof Multipart) {
                if (!this.isFormEncoded) {
                    this.isMultipart = true;
                    return;
                }
                throw methodError("Only one encoding annotation is allowed.", new Object[0]);
            } else if (annotation instanceof FormUrlEncoded) {
                if (!this.isMultipart) {
                    this.isFormEncoded = true;
                    return;
                }
                throw methodError("Only one encoding annotation is allowed.", new Object[0]);
            }
        }

        private ParameterHandler<?> parseParameter(int i2, Type type, Annotation[] annotationArr) {
            ParameterHandler<?> parameterHandler = null;
            for (Annotation annotation : annotationArr) {
                ParameterHandler<?> parseParameterAnnotation = parseParameterAnnotation(i2, type, annotationArr, annotation);
                if (parseParameterAnnotation != null) {
                    if (parameterHandler != null) {
                        throw parameterError(i2, "Multiple Retrofit annotations found, only one allowed.", new Object[0]);
                    }
                    parameterHandler = parseParameterAnnotation;
                }
            }
            if (parameterHandler != null) {
                return parameterHandler;
            }
            throw parameterError(i2, "No Retrofit annotation found.", new Object[0]);
        }

        private ParameterHandler<?> parseParameterAnnotation(int i2, Type type, Annotation[] annotationArr, Annotation annotation) {
            if (annotation instanceof Url) {
                if (!this.gotUrl) {
                    if (!this.gotPath) {
                        if (!this.gotQuery) {
                            if (this.relativeUrl == null) {
                                this.gotUrl = true;
                                if (type != HttpUrl.class && type != String.class && type != URI.class && (!(type instanceof Class) || !"android.net.Uri".equals(((Class) type).getName()))) {
                                    throw parameterError(i2, "@Url must be okhttp3.HttpUrl, String, java.net.URI, or android.net.Uri type.", new Object[0]);
                                }
                                return new ParameterHandler.RelativeUrl();
                            }
                            throw parameterError(i2, "@Url cannot be used with @%s URL", this.httpMethod);
                        }
                        throw parameterError(i2, "A @Url parameter must not come after a @Query", new Object[0]);
                    }
                    throw parameterError(i2, "@Path parameters may not be used with @Url.", new Object[0]);
                }
                throw parameterError(i2, "Multiple @Url method annotations found.", new Object[0]);
            } else if (annotation instanceof Path) {
                if (!this.gotQuery) {
                    if (!this.gotUrl) {
                        if (this.relativeUrl != null) {
                            this.gotPath = true;
                            Path path = (Path) annotation;
                            String value = path.value();
                            validatePathName(i2, value);
                            return new ParameterHandler.Path(value, this.retrofit.stringConverter(type, annotationArr), path.encoded());
                        }
                        throw parameterError(i2, "@Path can only be used with relative url on @%s", this.httpMethod);
                    }
                    throw parameterError(i2, "@Path parameters may not be used with @Url.", new Object[0]);
                }
                throw parameterError(i2, "A @Path parameter must not come after a @Query.", new Object[0]);
            } else if (annotation instanceof Query) {
                Query query = (Query) annotation;
                String value2 = query.value();
                boolean encoded = query.encoded();
                Class<?> rawType = Utils.getRawType(type);
                this.gotQuery = true;
                if (Iterable.class.isAssignableFrom(rawType)) {
                    if (type instanceof ParameterizedType) {
                        return new ParameterHandler.Query(value2, this.retrofit.stringConverter(Utils.getParameterUpperBound(0, (ParameterizedType) type), annotationArr), encoded).iterable();
                    }
                    throw parameterError(i2, rawType.getSimpleName() + " must include generic type (e.g., " + rawType.getSimpleName() + "<String>)", new Object[0]);
                } else if (rawType.isArray()) {
                    return new ParameterHandler.Query(value2, this.retrofit.stringConverter(ServiceMethod.boxIfPrimitive(rawType.getComponentType()), annotationArr), encoded).array();
                } else {
                    return new ParameterHandler.Query(value2, this.retrofit.stringConverter(type, annotationArr), encoded);
                }
            } else if (annotation instanceof QueryMap) {
                Class<?> rawType2 = Utils.getRawType(type);
                if (Map.class.isAssignableFrom(rawType2)) {
                    Type supertype = Utils.getSupertype(type, rawType2, Map.class);
                    if (supertype instanceof ParameterizedType) {
                        ParameterizedType parameterizedType = (ParameterizedType) supertype;
                        Type parameterUpperBound = Utils.getParameterUpperBound(0, parameterizedType);
                        if (String.class == parameterUpperBound) {
                            return new ParameterHandler.QueryMap(this.retrofit.stringConverter(Utils.getParameterUpperBound(1, parameterizedType), annotationArr), ((QueryMap) annotation).encoded());
                        }
                        throw parameterError(i2, "@QueryMap keys must be of type String: " + parameterUpperBound, new Object[0]);
                    }
                    throw parameterError(i2, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                throw parameterError(i2, "@QueryMap parameter type must be Map.", new Object[0]);
            } else if (annotation instanceof Header) {
                String value3 = ((Header) annotation).value();
                Class<?> rawType3 = Utils.getRawType(type);
                if (Iterable.class.isAssignableFrom(rawType3)) {
                    if (type instanceof ParameterizedType) {
                        return new ParameterHandler.Header(value3, this.retrofit.stringConverter(Utils.getParameterUpperBound(0, (ParameterizedType) type), annotationArr)).iterable();
                    }
                    throw parameterError(i2, rawType3.getSimpleName() + " must include generic type (e.g., " + rawType3.getSimpleName() + "<String>)", new Object[0]);
                } else if (rawType3.isArray()) {
                    return new ParameterHandler.Header(value3, this.retrofit.stringConverter(ServiceMethod.boxIfPrimitive(rawType3.getComponentType()), annotationArr)).array();
                } else {
                    return new ParameterHandler.Header(value3, this.retrofit.stringConverter(type, annotationArr));
                }
            } else if (annotation instanceof HeaderMap) {
                Class<?> rawType4 = Utils.getRawType(type);
                if (Map.class.isAssignableFrom(rawType4)) {
                    Type supertype2 = Utils.getSupertype(type, rawType4, Map.class);
                    if (supertype2 instanceof ParameterizedType) {
                        ParameterizedType parameterizedType2 = (ParameterizedType) supertype2;
                        Type parameterUpperBound2 = Utils.getParameterUpperBound(0, parameterizedType2);
                        if (String.class == parameterUpperBound2) {
                            return new ParameterHandler.HeaderMap(this.retrofit.stringConverter(Utils.getParameterUpperBound(1, parameterizedType2), annotationArr));
                        }
                        throw parameterError(i2, "@HeaderMap keys must be of type String: " + parameterUpperBound2, new Object[0]);
                    }
                    throw parameterError(i2, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                throw parameterError(i2, "@HeaderMap parameter type must be Map.", new Object[0]);
            } else if (annotation instanceof Field) {
                if (this.isFormEncoded) {
                    Field field = (Field) annotation;
                    String value4 = field.value();
                    boolean encoded2 = field.encoded();
                    this.gotField = true;
                    Class<?> rawType5 = Utils.getRawType(type);
                    if (Iterable.class.isAssignableFrom(rawType5)) {
                        if (type instanceof ParameterizedType) {
                            return new ParameterHandler.Field(value4, this.retrofit.stringConverter(Utils.getParameterUpperBound(0, (ParameterizedType) type), annotationArr), encoded2).iterable();
                        }
                        throw parameterError(i2, rawType5.getSimpleName() + " must include generic type (e.g., " + rawType5.getSimpleName() + "<String>)", new Object[0]);
                    } else if (rawType5.isArray()) {
                        return new ParameterHandler.Field(value4, this.retrofit.stringConverter(ServiceMethod.boxIfPrimitive(rawType5.getComponentType()), annotationArr), encoded2).array();
                    } else {
                        return new ParameterHandler.Field(value4, this.retrofit.stringConverter(type, annotationArr), encoded2);
                    }
                }
                throw parameterError(i2, "@Field parameters can only be used with form encoding.", new Object[0]);
            } else if (annotation instanceof FieldMap) {
                if (this.isFormEncoded) {
                    Class<?> rawType6 = Utils.getRawType(type);
                    if (Map.class.isAssignableFrom(rawType6)) {
                        Type supertype3 = Utils.getSupertype(type, rawType6, Map.class);
                        if (supertype3 instanceof ParameterizedType) {
                            ParameterizedType parameterizedType3 = (ParameterizedType) supertype3;
                            Type parameterUpperBound3 = Utils.getParameterUpperBound(0, parameterizedType3);
                            if (String.class == parameterUpperBound3) {
                                Converter<T, String> stringConverter = this.retrofit.stringConverter(Utils.getParameterUpperBound(1, parameterizedType3), annotationArr);
                                this.gotField = true;
                                return new ParameterHandler.FieldMap(stringConverter, ((FieldMap) annotation).encoded());
                            }
                            throw parameterError(i2, "@FieldMap keys must be of type String: " + parameterUpperBound3, new Object[0]);
                        }
                        throw parameterError(i2, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                    }
                    throw parameterError(i2, "@FieldMap parameter type must be Map.", new Object[0]);
                }
                throw parameterError(i2, "@FieldMap parameters can only be used with form encoding.", new Object[0]);
            } else if (annotation instanceof Part) {
                if (this.isMultipart) {
                    Part part = (Part) annotation;
                    this.gotPart = true;
                    String value5 = part.value();
                    Class<?> rawType7 = Utils.getRawType(type);
                    if (value5.isEmpty()) {
                        if (Iterable.class.isAssignableFrom(rawType7)) {
                            if (type instanceof ParameterizedType) {
                                if (MultipartBody.Part.class.isAssignableFrom(Utils.getRawType(Utils.getParameterUpperBound(0, (ParameterizedType) type)))) {
                                    return ParameterHandler.RawPart.INSTANCE.iterable();
                                }
                                throw parameterError(i2, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                            }
                            throw parameterError(i2, rawType7.getSimpleName() + " must include generic type (e.g., " + rawType7.getSimpleName() + "<String>)", new Object[0]);
                        } else if (rawType7.isArray()) {
                            if (MultipartBody.Part.class.isAssignableFrom(rawType7.getComponentType())) {
                                return ParameterHandler.RawPart.INSTANCE.array();
                            }
                            throw parameterError(i2, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                        } else if (MultipartBody.Part.class.isAssignableFrom(rawType7)) {
                            return ParameterHandler.RawPart.INSTANCE;
                        } else {
                            throw parameterError(i2, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                        }
                    }
                    Headers of = Headers.of(HttpHeaders.CONTENT_DISPOSITION, "form-data; name=\"" + value5 + "\"", "Content-Transfer-Encoding", part.encoding());
                    if (Iterable.class.isAssignableFrom(rawType7)) {
                        if (type instanceof ParameterizedType) {
                            Type parameterUpperBound4 = Utils.getParameterUpperBound(0, (ParameterizedType) type);
                            if (!MultipartBody.Part.class.isAssignableFrom(Utils.getRawType(parameterUpperBound4))) {
                                return new ParameterHandler.Part(of, this.retrofit.requestBodyConverter(parameterUpperBound4, annotationArr, this.methodAnnotations)).iterable();
                            }
                            throw parameterError(i2, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                        }
                        throw parameterError(i2, rawType7.getSimpleName() + " must include generic type (e.g., " + rawType7.getSimpleName() + "<String>)", new Object[0]);
                    } else if (rawType7.isArray()) {
                        Class<?> boxIfPrimitive = ServiceMethod.boxIfPrimitive(rawType7.getComponentType());
                        if (!MultipartBody.Part.class.isAssignableFrom(boxIfPrimitive)) {
                            return new ParameterHandler.Part(of, this.retrofit.requestBodyConverter(boxIfPrimitive, annotationArr, this.methodAnnotations)).array();
                        }
                        throw parameterError(i2, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                    } else if (!MultipartBody.Part.class.isAssignableFrom(rawType7)) {
                        return new ParameterHandler.Part(of, this.retrofit.requestBodyConverter(type, annotationArr, this.methodAnnotations));
                    } else {
                        throw parameterError(i2, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                    }
                }
                throw parameterError(i2, "@Part parameters can only be used with multipart encoding.", new Object[0]);
            } else if (annotation instanceof PartMap) {
                if (this.isMultipart) {
                    this.gotPart = true;
                    Class<?> rawType8 = Utils.getRawType(type);
                    if (Map.class.isAssignableFrom(rawType8)) {
                        Type supertype4 = Utils.getSupertype(type, rawType8, Map.class);
                        if (supertype4 instanceof ParameterizedType) {
                            ParameterizedType parameterizedType4 = (ParameterizedType) supertype4;
                            Type parameterUpperBound5 = Utils.getParameterUpperBound(0, parameterizedType4);
                            if (String.class == parameterUpperBound5) {
                                Type parameterUpperBound6 = Utils.getParameterUpperBound(1, parameterizedType4);
                                if (!MultipartBody.Part.class.isAssignableFrom(Utils.getRawType(parameterUpperBound6))) {
                                    return new ParameterHandler.PartMap(this.retrofit.requestBodyConverter(parameterUpperBound6, annotationArr, this.methodAnnotations), ((PartMap) annotation).encoding());
                                }
                                throw parameterError(i2, "@PartMap values cannot be MultipartBody.Part. Use @Part List<Part> or a different value type instead.", new Object[0]);
                            }
                            throw parameterError(i2, "@PartMap keys must be of type String: " + parameterUpperBound5, new Object[0]);
                        }
                        throw parameterError(i2, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                    }
                    throw parameterError(i2, "@PartMap parameter type must be Map.", new Object[0]);
                }
                throw parameterError(i2, "@PartMap parameters can only be used with multipart encoding.", new Object[0]);
            } else if (annotation instanceof Body) {
                if (!this.isFormEncoded && !this.isMultipart) {
                    if (!this.gotBody) {
                        try {
                            Converter<T, RequestBody> requestBodyConverter = this.retrofit.requestBodyConverter(type, annotationArr, this.methodAnnotations);
                            this.gotBody = true;
                            return new ParameterHandler.Body(requestBodyConverter);
                        } catch (RuntimeException e2) {
                            throw parameterError(e2, i2, "Unable to create @Body converter for %s", type);
                        }
                    }
                    throw parameterError(i2, "Multiple @Body method annotations found.", new Object[0]);
                }
                throw parameterError(i2, "@Body parameters cannot be used with form or multi-part encoding.", new Object[0]);
            } else {
                return null;
            }
        }

        private void validatePathName(int i2, String str) {
            if (ServiceMethod.PARAM_NAME_REGEX.matcher(str).matches()) {
                if (!this.relativeUrlParamNames.contains(str)) {
                    throw parameterError(i2, "URL \"%s\" does not contain \"{%s}\".", this.relativeUrl, str);
                }
                return;
            }
            throw parameterError(i2, "@Path parameter name must match %s. Found: %s", ServiceMethod.PARAM_URL_REGEX.pattern(), str);
        }

        public ServiceMethod build() {
            CallAdapter<?> createCallAdapter = createCallAdapter();
            this.callAdapter = createCallAdapter;
            Type responseType = createCallAdapter.responseType();
            this.responseType = responseType;
            if (responseType != Response.class && responseType != okhttp3.Response.class) {
                this.responseConverter = createResponseConverter();
                for (Annotation annotation : this.methodAnnotations) {
                    parseMethodAnnotation(annotation);
                }
                if (this.httpMethod != null) {
                    if (!this.hasBody) {
                        if (!this.isMultipart) {
                            if (this.isFormEncoded) {
                                throw methodError("FormUrlEncoded can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
                            }
                        } else {
                            throw methodError("Multipart can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
                        }
                    }
                    int length = this.parameterAnnotationsArray.length;
                    this.parameterHandlers = new ParameterHandler[length];
                    for (int i2 = 0; i2 < length; i2++) {
                        Type type = this.parameterTypes[i2];
                        if (!Utils.hasUnresolvableType(type)) {
                            Annotation[] annotationArr = this.parameterAnnotationsArray[i2];
                            if (annotationArr != null) {
                                this.parameterHandlers[i2] = parseParameter(i2, type, annotationArr);
                            } else {
                                throw parameterError(i2, "No Retrofit annotation found.", new Object[0]);
                            }
                        } else {
                            throw parameterError(i2, "Parameter type must not include a type variable or wildcard: %s", type);
                        }
                    }
                    if (this.relativeUrl != null || this.gotUrl) {
                        boolean z = this.isFormEncoded;
                        if (!z && !this.isMultipart && !this.hasBody && this.gotBody) {
                            throw methodError("Non-body HTTP method cannot contain @Body.", new Object[0]);
                        }
                        if (z && !this.gotField) {
                            throw methodError("Form-encoded method must contain at least one @Field.", new Object[0]);
                        }
                        if (this.isMultipart && !this.gotPart) {
                            throw methodError("Multipart method must contain at least one @Part.", new Object[0]);
                        }
                        return new ServiceMethod(this);
                    }
                    throw methodError("Missing either @%s URL or @Url parameter.", this.httpMethod);
                }
                throw methodError("HTTP method annotation is required (e.g., @GET, @POST, etc.).", new Object[0]);
            }
            throw methodError("'" + Utils.getRawType(this.responseType).getName() + "' is not a valid response body type. Did you mean ResponseBody?", new Object[0]);
        }

        private RuntimeException methodError(Throwable th, String str, Object... objArr) {
            return new IllegalArgumentException(String.format(str, objArr) + "\n    for method " + this.method.getDeclaringClass().getSimpleName() + OrderISVUtil.MONEY_DECIMAL + this.method.getName(), th);
        }

        private RuntimeException parameterError(int i2, String str, Object... objArr) {
            return methodError(str + " (parameter #" + (i2 + 1) + ")", objArr);
        }
    }

    ServiceMethod(Builder<T> builder) {
        this.callFactory = builder.retrofit.callFactory();
        this.callAdapter = builder.callAdapter;
        this.baseUrl = builder.retrofit.baseUrl();
        this.responseConverter = builder.responseConverter;
        this.httpMethod = builder.httpMethod;
        this.relativeUrl = builder.relativeUrl;
        this.headers = builder.headers;
        this.contentType = builder.contentType;
        this.hasBody = builder.hasBody;
        this.isFormEncoded = builder.isFormEncoded;
        this.isMultipart = builder.isMultipart;
        this.parameterHandlers = builder.parameterHandlers;
    }

    static Class<?> boxIfPrimitive(Class<?> cls) {
        return Boolean.TYPE == cls ? Boolean.class : Byte.TYPE == cls ? Byte.class : Character.TYPE == cls ? Character.class : Double.TYPE == cls ? Double.class : Float.TYPE == cls ? Float.class : Integer.TYPE == cls ? Integer.class : Long.TYPE == cls ? Long.class : Short.TYPE == cls ? Short.class : cls;
    }

    static Set<String> parsePathParameters(String str) {
        Matcher matcher = PARAM_URL_REGEX.matcher(str);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        while (matcher.find()) {
            linkedHashSet.add(matcher.group(1));
        }
        return linkedHashSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Request toRequest(Object... objArr) throws IOException {
        RequestBuilder requestBuilder = new RequestBuilder(this.httpMethod, this.baseUrl, this.relativeUrl, this.headers, this.contentType, this.hasBody, this.isFormEncoded, this.isMultipart);
        ParameterHandler<?>[] parameterHandlerArr = this.parameterHandlers;
        int length = objArr != null ? objArr.length : 0;
        if (length == parameterHandlerArr.length) {
            for (int i2 = 0; i2 < length; i2++) {
                parameterHandlerArr[i2].apply(requestBuilder, objArr[i2]);
            }
            return requestBuilder.build();
        }
        throw new IllegalArgumentException("Argument count (" + length + ") doesn't match expected count (" + parameterHandlerArr.length + ")");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public T toResponse(ResponseBody responseBody) throws IOException {
        return this.responseConverter.convert(responseBody);
    }
}
