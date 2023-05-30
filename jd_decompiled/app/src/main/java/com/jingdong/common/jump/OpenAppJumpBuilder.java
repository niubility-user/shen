package com.jingdong.common.jump;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.oklog.OKLog;
import java.net.URLEncoder;

/* loaded from: classes5.dex */
public class OpenAppJumpBuilder {
    private static final String TAG = "ppp_OpenAppJumpBuilder";
    private String host;
    private String key_params = "params";
    private String params;
    private String scheme;

    /* loaded from: classes5.dex */
    public static class Builder {
        public String host;
        public JDJSONObject params;
        public String scheme;

        public Builder() {
            this(null);
        }

        private void ensureParams() {
            if (this.params == null) {
                this.params = new JDJSONObject();
            }
        }

        public OpenAppJumpBuilder build() {
            ensureParams();
            this.params.put(OpenAppConstant.FLAG_INNERAPP, (Object) Boolean.TRUE);
            return new OpenAppJumpBuilder(this);
        }

        public Builder category(String str) {
            ensureParams();
            this.params.put("category", (Object) str);
            return this;
        }

        public Builder des(String str) {
            ensureParams();
            this.params.put("des", (Object) str);
            return this;
        }

        public Builder host(String str) {
            this.host = str;
            return this;
        }

        public <T> Builder map(String str, T t) {
            ensureParams();
            this.params.put(str, (Object) t);
            return this;
        }

        public Builder params(String str) {
            JDJSONObject jDJSONObject;
            ensureParams();
            try {
                jDJSONObject = JDJSON.parseObject(str);
            } catch (Exception e2) {
                e2.printStackTrace();
                jDJSONObject = null;
            }
            if (jDJSONObject != null) {
                this.params.putAll(jDJSONObject);
            }
            return this;
        }

        public Builder scheme(String str) {
            this.scheme = str;
            return this;
        }

        public Builder sourceType(String str) {
            ensureParams();
            this.params.put("sourceType", (Object) str);
            return this;
        }

        public Builder sourceValue(String str) {
            ensureParams();
            this.params.put("sourceValue", (Object) str);
            return this;
        }

        public Builder(Uri uri) {
            ensureParams();
            this.scheme = OpenAppConstant.SCHEME_OPENAPP_1;
            this.host = OpenAppConstant.HOST_1;
            this.params.put("category", (Object) "jump");
            if (uri == null) {
                return;
            }
            this.scheme = uri.getScheme();
            this.host = uri.getHost();
            try {
                JDJSONObject string2JDJsonObject = JumpUtil.string2JDJsonObject(OpenAppUtil.getQueryParameter(uri, "params"), uri);
                if (string2JDJsonObject != null) {
                    this.params.putAll(string2JDJsonObject);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public OpenAppJumpBuilder(Builder builder) {
        this.scheme = builder.scheme;
        this.host = builder.host;
        this.params = builder.params.toJSONString();
    }

    public static void demo(Context context) {
        new Builder().des(JumpUtil.VALUE_DES_ABOUT).sourceType("test1").sourceValue("test2").map("testKeyInt", 123).map("testKeyString", "helloworld").map("testKeyBool", Boolean.FALSE).build().jump(context);
    }

    public void jump(Context context) {
        Uri uri;
        String str;
        try {
            StringBuilder sb = new StringBuilder(this.scheme);
            sb.append("://");
            sb.append(this.host);
            sb.append("?");
            sb.append(this.key_params);
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.OENPAPP_BUILDER_ENCODE, false) ? URLEncoder.encode(this.params, "UTF-8") : this.params);
            String sb2 = sb.toString();
            if (OKLog.D) {
                OKLog.d(TAG, "url  = " + sb2);
            }
            Uri parse = Uri.parse(sb2);
            try {
                Intent intent = new Intent();
                intent.setData(parse);
                intent.setPackage(context.getPackageName());
                if (context instanceof Activity) {
                    OpenAppJumpController.dispatchJumpRequest(context, intent);
                } else {
                    intent.addFlags(268435456);
                    context.startActivity(intent);
                }
            } catch (Exception e2) {
                uri = parse;
                e = e2;
                if (context != null) {
                    str = "context: " + context.getClass().getSimpleName() + ",  ";
                } else {
                    str = "";
                }
                ExceptionReporter.reportOpenAppJumpException("Openapp_BuilderJumpError", uri != null ? uri.toString() : null, str + ExceptionReporter.getStackStringFromException(e));
                e.printStackTrace();
                if (OKLog.E) {
                    OKLog.e(TAG, e);
                }
            }
        } catch (Exception e3) {
            e = e3;
            uri = null;
        }
    }
}
