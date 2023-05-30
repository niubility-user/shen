package com.jingdong.app.mall.im.business;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.service.callback.UIModeChangeListener;
import com.jingdong.service.impl.IMUIMode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class y extends IMUIMode {
    private final String a = y.class.getSimpleName();
    private HashMap<UIModeChangeListener, a> b;

    /* renamed from: c  reason: collision with root package name */
    private HashMap<Integer, HashMap<String, String>> f11139c;

    /* loaded from: classes4.dex */
    private class a implements DeepDarkChangeManager.OnUIModeChangeListener {

        /* renamed from: g  reason: collision with root package name */
        private UIModeChangeListener f11140g;

        public a(y yVar, UIModeChangeListener uIModeChangeListener) {
            this.f11140g = uIModeChangeListener;
        }

        @Override // com.jingdong.common.utils.DeepDarkChangeManager.OnUIModeChangeListener
        public void onUIModeChanged(int i2) {
            UIModeChangeListener uIModeChangeListener = this.f11140g;
            if (uIModeChangeListener != null) {
                uIModeChangeListener.uiModeChange(i2);
            }
        }
    }

    private String a(String str, Context context) {
        if (TextUtils.isEmpty(str) || context == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(str)));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
        } catch (IOException e2) {
            if (OKLog.E) {
                OKLog.e("bundleicssdkservice", this.a + "getAssetsJson>>>" + e2.getMessage());
            }
        } catch (Error e3) {
            if (OKLog.E) {
                OKLog.e("bundleicssdkservice", this.a + "getAssetsJson>>>" + e3.getMessage());
            }
        } catch (Exception e4) {
            if (OKLog.E) {
                OKLog.e("bundleicssdkservice", this.a + "getAssetsJson>>>" + e4.getMessage());
            }
        }
        return sb.toString();
    }

    private HashMap<String, Object> b(String str) {
        HashMap<String, Object> hashMap = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            HashMap<String, Object> hashMap2 = new HashMap<>();
            try {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String lowerCase = keys.next().toString().toLowerCase();
                    Object obj = jSONObject.get(lowerCase);
                    if (obj == null) {
                        obj = "";
                    }
                    hashMap2.put(lowerCase, obj);
                }
                return hashMap2;
            } catch (Exception e2) {
                e = e2;
                hashMap = hashMap2;
                if (OKLog.E) {
                    OKLog.e("bundleicssdkservice", this.a + "jsonToMap>>>" + e.getMessage());
                }
                return hashMap;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.jingdong.service.impl.IMUIMode, com.jingdong.service.service.UIModeService
    public HashMap getResMapByMode(Context context) {
        OKLog.d("bundleicssdkservice", this.a + "--- getResMapByMode");
        if (this.f11139c == null) {
            this.f11139c = new HashMap<>();
        }
        int uIMode = DeepDarkChangeManager.getInstance().getUIMode();
        HashMap<String, String> hashMap = this.f11139c.get(Integer.valueOf(uIMode));
        if (hashMap != null) {
            return hashMap;
        }
        String str = uIMode != 1 ? null : "im/ics_res_dark.json";
        try {
            if (!TextUtils.isEmpty(str)) {
                String a2 = a(str, context);
                if (!TextUtils.isEmpty(a2)) {
                    HashMap<String, Object> b = b(a2);
                    if (b != null && b.size() > 0) {
                        this.f11139c.put(Integer.valueOf(uIMode), b);
                    }
                    return b;
                }
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("bundleicssdkservice", this.a + "getResMapByMode>>>" + e2.getMessage());
            }
        }
        return null;
    }

    @Override // com.jingdong.service.impl.IMUIMode, com.jingdong.service.service.UIModeService
    public boolean isDarkMode() {
        return DeepDarkChangeManager.getInstance().getUIMode() == 1;
    }

    @Override // com.jingdong.service.impl.IMUIMode, com.jingdong.service.service.UIModeService
    public boolean isLightMode() {
        return DeepDarkChangeManager.getInstance().getUIMode() == 0;
    }

    @Override // com.jingdong.service.impl.IMUIMode, com.jingdong.service.service.UIModeService
    public void removeUIModeChangeListener(UIModeChangeListener uIModeChangeListener) {
        HashMap<UIModeChangeListener, a> hashMap;
        OKLog.d("bundleicssdkservice", this.a + "--- removeUIModeChangeListener");
        if (uIModeChangeListener == null || (hashMap = this.b) == null) {
            return;
        }
        try {
            a aVar = hashMap.get(uIModeChangeListener);
            if (aVar != null) {
                DeepDarkChangeManager.getInstance().removeDeepDarkChangeListener(aVar);
            }
            this.b.remove(uIModeChangeListener);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("bundleicssdkservice", this.a + "removeUIModeChangeListener>>>" + e2.getMessage());
            }
        }
    }

    @Override // com.jingdong.service.impl.IMUIMode, com.jingdong.service.service.UIModeService
    public void setOnUIModeChangeListener(UIModeChangeListener uIModeChangeListener) {
        OKLog.d("bundleicssdkservice", this.a + "--- setOnUIModeChangeListener");
        if (this.b == null) {
            this.b = new HashMap<>();
        }
        if (uIModeChangeListener != null) {
            try {
                a aVar = new a(this, uIModeChangeListener);
                this.b.put(uIModeChangeListener, aVar);
                DeepDarkChangeManager.getInstance().addDeepDarkChangeListener(aVar);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e("bundleicssdkservice", this.a + "setOnUIModeChangeListener>>>" + e2.getMessage());
                }
            }
        }
    }
}
