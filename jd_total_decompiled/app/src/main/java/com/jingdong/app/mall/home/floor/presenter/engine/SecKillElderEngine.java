package com.jingdong.app.mall.home.floor.presenter.engine;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.model.entity.SecKillElderEntity;
import com.jingdong.app.mall.home.n.h.h;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.entity.Product;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.utils.JDJSONObjectWithDefaultUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: classes4.dex */
public class SecKillElderEngine extends FloorEngine<SecKillElderEntity> {

    /* renamed from: c  reason: collision with root package name */
    private SecKillElderEntity f9673c;
    private String d;

    /* renamed from: f  reason: collision with root package name */
    private int f9675f;

    /* renamed from: g  reason: collision with root package name */
    private int f9676g;
    private String b = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()));

    /* renamed from: e  reason: collision with root package name */
    private boolean f9674e = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements HttpGroup.CustomOnAllListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ boolean f9677g;

        a(boolean z) {
            this.f9677g = z;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            SecKillElderEngine.this.f9674e = false;
            JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
            if (fastJsonObject == null) {
                return;
            }
            if (this.f9677g) {
                SecKillElderEngine.this.f9673c.setNextRoundObject(fastJsonObject);
            } else {
                SecKillElderEngine.this.k(fastJsonObject, true);
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            SecKillElderEngine.this.f9674e = false;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
        public void onProgress(int i2, int i3) {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
        public void onStart() {
        }
    }

    private void m(JDJSONObject jDJSONObject) {
        if (k(jDJSONObject, false)) {
            return;
        }
        l(false);
    }

    public boolean k(JDJSONObject jDJSONObject, boolean z) {
        Object obj = "";
        boolean z2 = false;
        if (this.f9673c == null || jDJSONObject == null || jDJSONObject.size() == 0) {
            return false;
        }
        try {
            this.d = jDJSONObject.getString("functionId");
            JDJSONArray jSONArray = jDJSONObject.getJSONArray("indexMiaoSha");
            this.f9673c.setMiaoshaAdvance(f.t0(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "miaoshaAdvance", "0"), 0));
            this.f9673c.setNextRoundKey(jDJSONObject.getString("nextRoundKey"));
            this.f9673c.setTimeInfo(!z, Long.valueOf(jDJSONObject.getLongValue("timeRemain")), jDJSONObject.getLongValue("nextStartTime"));
            this.f9673c.setNameText(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "name", ""));
            this.f9673c.setPanicExpoSourceValue(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "algorithmFrom", ""));
            String jSONStringWithDefault = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "operateWord", "");
            this.f9673c.setShowOperate(false);
            this.f9673c.setOperateJump((JumpEntity) JDJSON.parseObject(jDJSONObject.optString("wordJump", ""), JumpEntity.class));
            if (!TextUtils.isEmpty(jSONStringWithDefault) && this.f9675f != 0) {
                String str = "1970-01-01";
                this.f9676g = 0;
                try {
                    Object[] split = f.u("OperateWordData", "|1970-01-01|0").split(DYConstants.DY_REGEX_VERTICAL_LINE);
                    if (split.length == 3) {
                        obj = split[0];
                        str = split[1];
                        this.f9676g = f.t0(split[2], 0);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                if (jSONStringWithDefault.equals(obj) && this.b.equals(str)) {
                    int i2 = this.f9676g;
                    int i3 = this.f9675f;
                    if (i2 < i3) {
                        this.f9675f = i3 - i2;
                        this.f9673c.setOperateWord(jSONStringWithDefault);
                    } else {
                        this.f9675f = 0;
                    }
                }
                this.f9673c.setOperateWord(jSONStringWithDefault);
                o(jSONStringWithDefault, 0);
            } else {
                this.f9675f = 0;
            }
            SecKillElderEntity secKillElderEntity = this.f9673c;
            secKillElderEntity.setPlayCount(secKillElderEntity.isCacheData() ? 0 : this.f9675f);
            ArrayList<Product> list = Product.toList(jSONArray, 17);
            Iterator<Product> it = list.iterator();
            while (it.hasNext()) {
                int i4 = it.next().msItemType;
                if (i4 != 1 && i4 != 8) {
                    it.remove();
                }
            }
            this.f9673c.setProducts(list);
            if (z) {
                try {
                    this.f9673c.onDataChange();
                } catch (Exception e3) {
                    e = e3;
                    z2 = true;
                    if (Log.E) {
                        e.printStackTrace();
                    }
                    return z2;
                }
            }
            this.f9673c.parseExpo();
            if (this.f9673c.isCacheData()) {
                return true;
            }
            com.jingdong.app.mall.home.r.c.a.w(JdSdk.getInstance().getApplicationContext(), "Home_SeckillFloorExpo", this.f9673c.getPanicExpoSourceValue(), l.f(), RecommendMtaUtils.Home_PageId);
            return true;
        } catch (Exception e4) {
            e = e4;
        }
    }

    public void l(boolean z) {
        if (this.f9674e) {
            return;
        }
        this.f9674e = true;
        h.a(this.d, new a(z), z);
    }

    @Override // com.jingdong.app.mall.home.floor.presenter.engine.FloorEngine
    /* renamed from: n  reason: merged with bridge method [inline-methods] */
    public void e(com.jingdong.app.mall.home.r.e.h hVar, d dVar, SecKillElderEntity secKillElderEntity) {
        com.jingdong.app.mall.home.r.e.f fVar;
        super.e(hVar, dVar, secKillElderEntity);
        if (hVar == null || dVar == null || secKillElderEntity == null) {
            return;
        }
        this.f9673c = secKillElderEntity;
        ArrayList<com.jingdong.app.mall.home.r.e.f> arrayList = dVar.f10682c;
        if (arrayList == null || arrayList.size() <= 0 || (fVar = arrayList.get(0)) == null) {
            return;
        }
        fVar.a = hVar;
        secKillElderEntity.initData(hVar, fVar);
        m(fVar.h());
    }

    public void o(String str, int i2) {
        f.x0("OperateWordData", str + "|" + this.b + "|" + (i2 + this.f9676g));
    }
}
