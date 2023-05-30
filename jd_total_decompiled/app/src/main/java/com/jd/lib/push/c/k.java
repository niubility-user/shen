package com.jd.lib.push.c;

import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.entity.HWModel;
import com.jingdong.common.entity.JDModel;
import com.jingdong.common.entity.MIModel;
import com.jingdong.common.entity.MZModel;
import com.jingdong.common.entity.OPPOModel;

/* loaded from: classes16.dex */
public class k {
    private JDJSONObject a;

    /* loaded from: classes16.dex */
    public static class a {
        private boolean a;
        private boolean b;

        public a(String str, String str2, JDJSONObject jDJSONObject) {
            JDJSONObject optJSONObject;
            this.a = true;
            this.b = true;
            if (jDJSONObject == null || (optJSONObject = jDJSONObject.optJSONObject(str)) == null) {
                return;
            }
            this.a = optJSONObject.optBoolean("JdPush", true);
            this.b = optJSONObject.optBoolean(str2, true);
        }

        public boolean a() {
            return this.a;
        }

        public boolean b() {
            return this.b;
        }
    }

    private JDJSONObject g() {
        if (this.a == null) {
            try {
                this.a = JDJSON.parseObject(com.jd.lib.push.utils.d.e());
            } catch (Throwable th) {
                com.jingdong.jdpush_new.j.g.e("PushChannel", "\u83b7\u53d6\u7ebf\u4e0a\u5f00\u5173\u5f02\u5e38 ", th);
            }
        }
        return this.a;
    }

    public a a() {
        return new a(HWModel.HW_MODEL, "HWPush", g());
    }

    public a b() {
        return new a("honorModel", "HonorPush", g());
    }

    public a c() {
        return new a(JDModel.JD_MODEL, "JdPush", g());
    }

    public a d() {
        return new a(MIModel.MI_MODEL, "MiPush", g());
    }

    public a e() {
        return new a(MZModel.MZ_MODEL, MZModel.MZ_PUSH, g());
    }

    public a f() {
        return new a(OPPOModel.OPPO_MODEL, OPPOModel.OPPO_PUSH, g());
    }

    public a h() {
        return new a("vivoModel", JDModel.VIVO_PUSH, g());
    }
}
