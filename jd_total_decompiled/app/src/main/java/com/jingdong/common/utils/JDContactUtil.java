package com.jingdong.common.utils;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import com.jingdong.app.mall.bundle.marketing_sdk.contacts.ContactUtils;
import com.jingdong.app.mall.bundle.marketing_sdk.contacts.listener.IContactAuthListener;
import com.jingdong.app.mall.bundle.marketing_sdk.contacts.listener.IContactAuthStatusListener;
import com.jingdong.app.mall.bundle.marketing_sdk.contacts.listener.IContactUploadListener;
import com.jingdong.common.web.IRouterParams;
import com.jingdong.corelib.utils.Log;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDContactUtil {
    public static final String CLASS_NAME = "com.jingdong.app.mall.bundle.marketing_sdk.contacts.ContactUtils";
    public static final String NAME = "ContactUtils";
    public static final String PERMISSION_METHOD_NAME = "onClick";
    public static final int REQUEST_CODE_READ_CONTACTS = 1110;
    private static IRouterParams routerParams;

    public static void checkAuth(final IRouterParams iRouterParams) {
        if (iRouterParams == null) {
            return;
        }
        try {
            String str = "";
            if (iRouterParams.getRouterParam() != null && !TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                str = new JSONObject(iRouterParams.getRouterParam()).getString("actId");
            }
            ContactUtils.checkAuth(str, new IContactAuthStatusListener() { // from class: com.jingdong.common.utils.JDContactUtil.1
                @Override // com.jingdong.app.mall.bundle.marketing_sdk.contacts.listener.IContactAuthStatusListener
                public void updateAuthStatusResult(int i2, int i3) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("code", i2);
                        jSONObject.put("status", i3);
                        IRouterParams.this.onCallBack(jSONObject);
                    } catch (Exception unused) {
                    }
                }
            });
        } catch (Exception unused) {
        }
    }

    public static void gotoSetting(IRouterParams iRouterParams) {
        if (iRouterParams != null && iRouterParams.getContext() != null) {
            try {
                ContactUtils.gotoSetting((Activity) iRouterParams.getContext());
            } catch (Exception unused) {
            }
        }
    }

    public static void gotoSmsPage(IRouterParams iRouterParams) {
        if (iRouterParams != null && iRouterParams.getContext() != null) {
            try {
                if (iRouterParams.getRouterParam() == null || TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                    return;
                }
                JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
                ContactUtils.gotoSmsPage((Activity) iRouterParams.getContext(), jSONObject.getString("phoneNumber"), jSONObject.getString("content"));
            } catch (Exception unused) {
            }
        }
    }

    public static void handlePickData(Activity activity, Intent intent, int i2) {
        String str;
        final IRouterParams iRouterParams;
        try {
            str = "";
            iRouterParams = routerParams;
        } catch (Exception unused) {
            if (routerParams == null) {
                return;
            }
        } catch (Throwable th) {
            if (routerParams != null) {
                routerParams = null;
            }
            throw th;
        }
        if (-1 != i2) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("code", 1);
            jSONObject.put("msg", "result fail");
            if (iRouterParams != null) {
                iRouterParams.onCallBack(jSONObject);
            }
            if (routerParams != null) {
                routerParams = null;
                return;
            }
            return;
        }
        if (iRouterParams != null && iRouterParams.getRouterParam() != null && !TextUtils.isEmpty(iRouterParams.getRouterParam())) {
            str = new JSONObject(iRouterParams.getRouterParam()).getString("actId");
        }
        Log.e("JDContactUtil", "actId" + str);
        ContactUtils.handlePickData(activity, str, intent, new IContactUploadListener() { // from class: com.jingdong.common.utils.JDContactUtil.4
            @Override // com.jingdong.app.mall.bundle.marketing_sdk.contacts.listener.IContactUploadListener
            public void updateUploadResult(JSONObject jSONObject2) {
                try {
                    JSONObject jSONObject3 = new JSONObject();
                    if (jSONObject2 != null) {
                        jSONObject3.put("code", jSONObject2.optInt("code"));
                        jSONObject3.put("msg", jSONObject2.optString("msg"));
                    }
                    Log.e("JDContactUtil", jSONObject3.toString());
                    IRouterParams iRouterParams2 = IRouterParams.this;
                    if (iRouterParams2 != null) {
                        iRouterParams2.onCallBack(jSONObject3);
                    }
                } catch (Exception unused2) {
                    IRouterParams iRouterParams3 = IRouterParams.this;
                    if (iRouterParams3 != null) {
                        iRouterParams3.onCallBack(null);
                    }
                }
            }
        });
        if (routerParams == null) {
            return;
        }
        routerParams = null;
    }

    public static boolean hasContactPermission(IRouterParams iRouterParams) {
        return false;
    }

    public static void phoneBookAuth(final IRouterParams iRouterParams) {
        if (iRouterParams == null) {
            return;
        }
        try {
            String str = "";
            if (iRouterParams.getRouterParam() != null && !TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                str = new JSONObject(iRouterParams.getRouterParam()).getString("actId");
            }
            ContactUtils.phoneBookAuth(str, new IContactAuthListener() { // from class: com.jingdong.common.utils.JDContactUtil.2
                @Override // com.jingdong.app.mall.bundle.marketing_sdk.contacts.listener.IContactAuthListener
                public void updateAuthResult(int i2, String str2) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("code", i2);
                        jSONObject.put("msg", str2);
                        IRouterParams.this.onCallBack(jSONObject);
                    } catch (Exception unused) {
                    }
                }
            });
        } catch (Exception unused) {
        }
    }

    public static void phoneBookAuthCancel(final IRouterParams iRouterParams) {
        if (iRouterParams == null) {
            return;
        }
        try {
            String str = "";
            if (iRouterParams.getRouterParam() != null && !TextUtils.isEmpty(iRouterParams.getRouterParam())) {
                str = new JSONObject(iRouterParams.getRouterParam()).getString("actId");
            }
            ContactUtils.phoneBookAuthCancel(str, new IContactAuthListener() { // from class: com.jingdong.common.utils.JDContactUtil.3
                @Override // com.jingdong.app.mall.bundle.marketing_sdk.contacts.listener.IContactAuthListener
                public void updateAuthResult(int i2, String str2) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("code", i2);
                        jSONObject.put("msg", str2);
                        IRouterParams.this.onCallBack(jSONObject);
                    } catch (Exception unused) {
                    }
                }
            });
        } catch (Exception unused) {
        }
    }

    public static void pickAndGetResult(IRouterParams iRouterParams) {
        if (iRouterParams != null && iRouterParams.getContext() != null) {
            try {
                routerParams = iRouterParams;
                ContactUtils.gotoContact((Activity) iRouterParams.getContext());
                Log.e("JDContactUtil", "pickAndGetResult");
            } catch (Exception unused) {
            }
        }
    }

    public static void requestContactPermission(IRouterParams iRouterParams) {
        requestContactPermission(iRouterParams, false);
    }

    public static void requestContactPermissionDefault(IRouterParams iRouterParams) {
        requestContactPermission(iRouterParams, true);
    }

    public static void upload(IRouterParams iRouterParams) {
        if (iRouterParams != null && iRouterParams.getContext() != null) {
            try {
                iRouterParams.onCallBack(new JSONObject());
            } catch (Exception unused) {
            }
        }
    }

    private static void requestContactPermission(IRouterParams iRouterParams, boolean z) {
        if (iRouterParams != null && iRouterParams.getContext() != null) {
            try {
                iRouterParams.onCallBack(new JSONObject());
            } catch (Exception unused) {
            }
        }
    }
}
