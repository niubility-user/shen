package com.jd.framework.network.toolbox;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.CacheMissError;
import com.android.volley.error.HttpsError;
import com.android.volley.error.IpError;
import com.android.volley.error.JsonExceptionError;
import com.android.volley.error.VolleyError;
import com.android.volley.toolbox.ByteArrayRequest;
import com.android.volley.toolbox.FastJsonArrayRequest;
import com.android.volley.toolbox.FastJsonObjectRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.VerifyCodeRequest;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.framework.network.JDResponse;
import com.jd.framework.network.JDResponseListener;
import com.jd.framework.network.error.JDCacheMissError;
import com.jd.framework.network.error.JDError;
import com.jd.framework.network.error.JDHttpsError;
import com.jd.framework.network.error.JDIpError;
import com.jd.framework.network.error.JDJsonExceptionError;
import com.jd.framework.network.error.JDSSLError;
import com.jd.framework.network.request.JDByteArrayRequest;
import com.jd.framework.network.request.JDFastJsonArrayRequest;
import com.jd.framework.network.request.JDFastJsonObjectRequest;
import com.jd.framework.network.request.JDJsonArrayRequest;
import com.jd.framework.network.request.JDJsonObjectRequest;
import com.jd.framework.network.request.JDRequest;
import com.jd.framework.network.request.JDStringRequest;
import com.jd.framework.network.request.JDVerifyCodeRequest;
import javax.net.ssl.SSLHandshakeException;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class JDNetworkConvertor {
    public static Response.ErrorListener createResponseErrorListener(final JDResponseListener jDResponseListener) {
        return new Response.ErrorListener() { // from class: com.jd.framework.network.toolbox.JDNetworkConvertor.8
            @Override // com.android.volley.Response.ErrorListener
            public void onCancel() {
                JDResponseListener jDResponseListener2 = jDResponseListener;
                if (jDResponseListener2 != null) {
                    jDResponseListener2.onCancel();
                }
            }

            @Override // com.android.volley.Response.ErrorListener
            public void onErrorResponse(VolleyError volleyError) {
                JDResponseListener jDResponseListener2 = jDResponseListener;
                if (jDResponseListener2 != null) {
                    jDResponseListener2.onError(JDNetworkConvertor.toJDError(volleyError));
                }
            }

            @Override // com.android.volley.Response.ErrorListener
            public void onStart() {
                JDResponseListener jDResponseListener2 = jDResponseListener;
                if (jDResponseListener2 != null) {
                    jDResponseListener2.onStart();
                }
            }
        };
    }

    public static void setupParameter(Request<?> request, JDRequest<?> jDRequest) {
        if (jDRequest.getPriority() != null) {
            request.setPriority(jDRequest.getPriority());
        }
        if (jDRequest.getTag() != null) {
            request.setTag(jDRequest.getTag());
        }
        if (jDRequest.getHeader() != null) {
            request.setHeaders(jDRequest.getHeader());
        }
        if (jDRequest.getParams() != null) {
            request.setParams(jDRequest.getParams());
        }
        request.setCacheTime(jDRequest.getCacheTime());
        request.setCacheModel(jDRequest.getCacheModel());
        request.setCacheKey(jDRequest.getCacheKey());
        request.setUseCookies(jDRequest.isUseCookies());
        request.setSequence(jDRequest.getSequence());
        request.setUseDomainName(jDRequest.isUseDomainName());
        request.setCallbackInMainThread(jDRequest.isCallbackInMainThread());
        request.setUseOkhttpFlag(jDRequest.getUseOkhttpFlag());
        request.setForce2HttpFlag(jDRequest.isForce2HttpFlag());
        request.setServiceKey(jDRequest.getServiceKey());
        request.setCacheResponseChecker(jDRequest.getCacheResponseChecker());
        request.setConnectTimeoutMs(jDRequest.getConnectTimeoutMs());
        request.setReadTimeoutMs(jDRequest.getReadTimeoutMs());
        request.setCallTimeoutMs(jDRequest.getCallTimeoutMs());
        request.setNeedRetryOnNetworkLayer(jDRequest.needRetryOnNetworkLayer());
        jDRequest.setMaxNumRetries(0);
        request.setMaxRetryNum(jDRequest.getMaxNumRetries());
        request.setRetryPolicy(new DefaultRetryPolicy(2500, jDRequest.getMaxNumRetries(), 1.0f));
    }

    public static JDError toJDError(VolleyError volleyError) {
        if (volleyError instanceof HttpsError.HttpsIPError) {
            return new JDHttpsError.JDHttpsIPError((HttpsError.HttpsIPError) volleyError);
        }
        if (volleyError instanceof HttpsError.HttpsDomainError) {
            return new JDHttpsError.JDHttpsDomainError((HttpsError.HttpsDomainError) volleyError);
        }
        if (volleyError instanceof JsonExceptionError) {
            return new JDJsonExceptionError(volleyError, ((JsonExceptionError) volleyError).isParseError());
        }
        if (volleyError instanceof CacheMissError) {
            return new JDCacheMissError(volleyError);
        }
        if (volleyError instanceof IpError) {
            return new JDIpError(volleyError);
        }
        if (volleyError.getCause() instanceof SSLHandshakeException) {
            return new JDSSLError(volleyError);
        }
        return new JDError(volleyError);
    }

    public static <T> JDResponse<T> toJDResponse(Response<T> response) {
        return new JDResponse<>(response.statusCode, response.isCache(), response.result, response.getHeaders());
    }

    public static <T> Request<T> toRequest(JDRequest<T> jDRequest) {
        Request<T> request;
        if (jDRequest instanceof JDStringRequest) {
            JDStringRequest jDStringRequest = (JDStringRequest) jDRequest;
            final JDResponseListener<String> responseListener = jDStringRequest.getResponseListener();
            request = new StringRequest(jDStringRequest.getMethod(), jDStringRequest.getUrl(), new Response.Listener<String>() { // from class: com.jd.framework.network.toolbox.JDNetworkConvertor.1
                @Override // com.android.volley.Response.Listener
                public void onResponse(Response<String> response) {
                    JDResponseListener jDResponseListener = responseListener;
                    if (jDResponseListener != null) {
                        jDResponseListener.onEnd(JDNetworkConvertor.toJDResponse(response));
                    }
                }
            }, createResponseErrorListener(responseListener));
        } else if (jDRequest instanceof JDJsonArrayRequest) {
            JDJsonArrayRequest jDJsonArrayRequest = (JDJsonArrayRequest) jDRequest;
            final JDResponseListener<JSONArray> responseListener2 = jDJsonArrayRequest.getResponseListener();
            request = new JsonArrayRequest(jDJsonArrayRequest.getMethod(), jDJsonArrayRequest.getUrl(), new Response.Listener<JSONArray>() { // from class: com.jd.framework.network.toolbox.JDNetworkConvertor.2
                @Override // com.android.volley.Response.Listener
                public void onResponse(Response<JSONArray> response) {
                    JDResponseListener jDResponseListener = responseListener2;
                    if (jDResponseListener != null) {
                        jDResponseListener.onEnd(JDNetworkConvertor.toJDResponse(response));
                    }
                }
            }, createResponseErrorListener(responseListener2));
        } else if (jDRequest instanceof JDJsonObjectRequest) {
            JDJsonObjectRequest jDJsonObjectRequest = (JDJsonObjectRequest) jDRequest;
            final JDResponseListener<JSONObject> responseListener3 = jDJsonObjectRequest.getResponseListener();
            request = new JsonObjectRequest(jDJsonObjectRequest.getMethod(), jDJsonObjectRequest.getUrl(), new Response.Listener<JSONObject>() { // from class: com.jd.framework.network.toolbox.JDNetworkConvertor.3
                @Override // com.android.volley.Response.Listener
                public void onResponse(Response<JSONObject> response) {
                    JDResponseListener jDResponseListener = responseListener3;
                    if (jDResponseListener != null) {
                        jDResponseListener.onEnd(JDNetworkConvertor.toJDResponse(response));
                    }
                }
            }, createResponseErrorListener(responseListener3), jDJsonObjectRequest.getPostBody());
        } else if (jDRequest instanceof JDFastJsonArrayRequest) {
            JDFastJsonArrayRequest jDFastJsonArrayRequest = (JDFastJsonArrayRequest) jDRequest;
            final JDResponseListener<JDJSONArray> responseListener4 = jDFastJsonArrayRequest.getResponseListener();
            request = new FastJsonArrayRequest(jDFastJsonArrayRequest.getMethod(), jDFastJsonArrayRequest.getUrl(), new Response.Listener<JDJSONArray>() { // from class: com.jd.framework.network.toolbox.JDNetworkConvertor.4
                @Override // com.android.volley.Response.Listener
                public void onResponse(Response<JDJSONArray> response) {
                    JDResponseListener jDResponseListener = responseListener4;
                    if (jDResponseListener != null) {
                        jDResponseListener.onEnd(JDNetworkConvertor.toJDResponse(response));
                    }
                }
            }, createResponseErrorListener(responseListener4));
        } else if (jDRequest instanceof JDFastJsonObjectRequest) {
            JDFastJsonObjectRequest jDFastJsonObjectRequest = (JDFastJsonObjectRequest) jDRequest;
            final JDResponseListener<JDJSONObject> responseListener5 = jDFastJsonObjectRequest.getResponseListener();
            request = new FastJsonObjectRequest(jDFastJsonObjectRequest.getMethod(), jDFastJsonObjectRequest.getUrl(), new Response.Listener<JDJSONObject>() { // from class: com.jd.framework.network.toolbox.JDNetworkConvertor.5
                @Override // com.android.volley.Response.Listener
                public void onResponse(Response<JDJSONObject> response) {
                    JDResponseListener jDResponseListener = responseListener5;
                    if (jDResponseListener != null) {
                        jDResponseListener.onEnd(JDNetworkConvertor.toJDResponse(response));
                    }
                }
            }, createResponseErrorListener(responseListener5), jDFastJsonObjectRequest.getPostBody());
        } else if (jDRequest instanceof JDVerifyCodeRequest) {
            JDVerifyCodeRequest jDVerifyCodeRequest = (JDVerifyCodeRequest) jDRequest;
            final JDResponseListener<byte[]> responseListener6 = jDVerifyCodeRequest.getResponseListener();
            request = new VerifyCodeRequest(jDVerifyCodeRequest.getMethod(), jDVerifyCodeRequest.getUrl(), new Response.Listener<byte[]>() { // from class: com.jd.framework.network.toolbox.JDNetworkConvertor.6
                @Override // com.android.volley.Response.Listener
                public void onResponse(Response<byte[]> response) {
                    JDResponseListener jDResponseListener = responseListener6;
                    if (jDResponseListener != null) {
                        jDResponseListener.onEnd(JDNetworkConvertor.toJDResponse(response));
                    }
                }
            }, createResponseErrorListener(responseListener6));
        } else if (jDRequest instanceof JDByteArrayRequest) {
            JDByteArrayRequest jDByteArrayRequest = (JDByteArrayRequest) jDRequest;
            final JDResponseListener<byte[]> responseListener7 = jDByteArrayRequest.getResponseListener();
            request = new ByteArrayRequest(jDByteArrayRequest.getMethod(), jDByteArrayRequest.getUrl(), new Response.Listener<byte[]>() { // from class: com.jd.framework.network.toolbox.JDNetworkConvertor.7
                @Override // com.android.volley.Response.Listener
                public void onResponse(Response<byte[]> response) {
                    JDResponseListener jDResponseListener = responseListener7;
                    if (jDResponseListener != null) {
                        jDResponseListener.onEnd(JDNetworkConvertor.toJDResponse(response));
                    }
                }
            }, createResponseErrorListener(responseListener7));
        } else {
            request = null;
        }
        setupParameter(request, jDRequest);
        return request;
    }
}
