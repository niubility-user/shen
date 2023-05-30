package com.jd.stat.common;

import android.app.Activity;
import android.text.TextUtils;
import android.view.MotionEvent;
import com.jd.dynamic.DYConstants;
import com.jd.stat.bot.BlogUtil;
import com.jd.stat.bot.BotDetector;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class TriTouchUtil {
    private static TriTouchUtil a;
    private int b = 0;

    /* renamed from: c */
    private CopyOnWriteArrayList<String> f6978c = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<String> d = new CopyOnWriteArrayList<>();

    /* renamed from: e */
    private CopyOnWriteArrayList<String> f6979e = new CopyOnWriteArrayList<>();

    private static int a(MotionEvent motionEvent) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        if (motionEvent == null) {
            return 0;
        }
        int deviceId = motionEvent.getDeviceId();
        int toolType = motionEvent.getToolType(0);
        float size = motionEvent.getSize();
        float touchMinor = motionEvent.getTouchMinor();
        float touchMajor = motionEvent.getTouchMajor();
        if (deviceId <= 0) {
            str = "0";
        } else {
            str = "1";
        }
        if (toolType == 0) {
            str2 = str + "0";
        } else {
            str2 = str + "1";
        }
        if (size == 1.0d) {
            str3 = str2 + "0";
        } else {
            str3 = str2 + "1";
        }
        if (touchMinor == 0.0d) {
            str4 = str3 + "0";
        } else {
            str4 = str3 + "1";
        }
        if (touchMajor == 0.0d) {
            str5 = str4 + "0";
        } else {
            str5 = str4 + "1";
        }
        return Integer.parseInt(str5, 2);
    }

    public static TriTouchUtil getInstance() {
        if (a == null) {
            synchronized (TriTouchUtil.class) {
                if (a == null) {
                    a = new TriTouchUtil();
                }
            }
        }
        return a;
    }

    public void collectTouchEvent(JSONObject jSONObject, Activity activity) {
        if (jSONObject == null) {
            return;
        }
        try {
            Object opt = jSONObject.opt("motionparam");
            if (opt != null && (opt instanceof MotionEvent)) {
                MotionEvent motionEvent = (MotionEvent) opt;
                if (BotDetector.needTriTouch(activity)) {
                    while (this.f6979e.size() > 0 && this.f6979e.size() + 1 > 3) {
                        this.f6979e.remove(0);
                    }
                    this.f6979e.add(String.valueOf(a(motionEvent)));
                }
                if (motionEvent.getAction() == 1) {
                    while (this.d.size() > 0 && this.d.size() + 1 > 3) {
                        this.d.remove(0);
                    }
                    this.d.add(String.valueOf(a(motionEvent)));
                }
                if (getTouchEventSize() < 3 && BlogUtil.allowTriTouch) {
                    int action = motionEvent.getAction();
                    if (action == 0) {
                        this.b = 0;
                    } else if (action != 1) {
                        if (action != 2) {
                            return;
                        }
                        this.b = 1;
                    } else {
                        this.f6978c.add(a(motionEvent, this.b));
                        this.b = 0;
                        if (getTouchEventSize() >= 3) {
                            BlogUtil.allowTriTouch = false;
                        }
                    }
                }
            }
        } catch (Throwable th) {
            com.jd.stat.common.b.b.b("TriTouchUtil", th);
        }
    }

    public String getBlogActionUpTouchEvent() {
        ArrayList arrayList = new ArrayList(this.d);
        if (arrayList.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            sb.append((String) it.next());
            sb.append(DYConstants.DY_REGEX_COMMA);
            i2++;
            if (i2 == 3) {
                break;
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public String getClogTriTouch() {
        ArrayList arrayList = new ArrayList(this.f6979e);
        if (arrayList.size() == 0) {
            return "FFFFFF";
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = arrayList.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            sb.append(String.format("%02x", Integer.valueOf(Integer.parseInt((String) it.next()))));
            i2++;
            if (i2 == 3) {
                break;
            }
        }
        String sb2 = sb.toString();
        if (sb2.length() == 2) {
            return "0000" + sb2;
        } else if (sb2.length() == 4) {
            return "00" + sb2;
        } else {
            return sb2;
        }
    }

    public int getTouchEventSize() {
        CopyOnWriteArrayList<String> copyOnWriteArrayList = this.f6978c;
        if (copyOnWriteArrayList != null) {
            return copyOnWriteArrayList.size();
        }
        return 0;
    }

    public String getTriTouch() {
        try {
            if (com.jd.stat.security.d.a().q()) {
                String str = "";
                int min = Math.min(this.f6978c.size(), 3);
                for (int i2 = 0; i2 < min; i2++) {
                    str = TextUtils.isEmpty(str) ? str + this.f6978c.get(i2) : str + DYConstants.DY_REGEX_COMMA + this.f6978c.get(i2);
                }
                this.f6978c.clear();
                return str;
            }
            return com.jingdong.jdsdk.a.a.a;
        } catch (Throwable unused) {
            return "c";
        }
    }

    private String a(MotionEvent motionEvent, int i2) {
        try {
            StringBuilder sb = new StringBuilder();
            int deviceId = motionEvent.getDeviceId();
            int toolType = motionEvent.getToolType(0);
            float size = motionEvent.getSize();
            float touchMinor = motionEvent.getTouchMinor();
            float touchMajor = motionEvent.getTouchMajor();
            sb.append("up");
            sb.append("~");
            sb.append(deviceId);
            sb.append("~");
            sb.append(toolType);
            sb.append("~");
            sb.append(Math.round(size * 10.0f) / 10.0f);
            sb.append("~");
            sb.append(Math.round(touchMinor * 10.0f) / 10.0f);
            sb.append("~");
            sb.append(Math.round(touchMajor * 10.0f) / 10.0f);
            sb.append("~");
            sb.append(i2);
            return sb.toString();
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }
}
