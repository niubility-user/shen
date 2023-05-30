package com.jingdong.app.mall.home.floor.presenter.engine;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.common.MallFloorEvent;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.model.entity.PanicFloorEntity;
import com.jingdong.app.mall.home.floor.view.adapter.MallPanicFloorRecyclerAdapter;
import com.jingdong.app.mall.home.n.h.h;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.common.entity.Product;
import com.jingdong.common.utils.JDJSONObjectWithDefaultUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes4.dex */
public class PanicFloorEngine extends ListItemFloorEngine<PanicFloorEntity> {
    private boolean b = false;

    /* renamed from: c */
    private String f9669c;

    /* loaded from: classes4.dex */
    public class a implements HttpGroup.CustomOnAllListener {

        /* renamed from: g */
        final /* synthetic */ boolean f9670g;

        /* renamed from: h */
        final /* synthetic */ PanicFloorEntity f9671h;

        a(boolean z, PanicFloorEntity panicFloorEntity) {
            PanicFloorEngine.this = r1;
            this.f9670g = z;
            this.f9671h = panicFloorEntity;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            if (Log.D) {
                Log.d("PanicFloorEngine", " -->> onEnd getPanicBuyingList()");
            }
            PanicFloorEngine.this.b = false;
            JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
            if (this.f9670g) {
                this.f9671h.setNextRoundObject(fastJsonObject);
            } else {
                PanicFloorEngine.this.l(fastJsonObject, this.f9671h, true);
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            if (Log.D) {
                Log.d("PanicFloorEngine", " -->> onError getPanicBuyingList()");
            }
            PanicFloorEngine.this.b = false;
            MallFloorEvent.k(PanicFloorEngine.this.b(), this.f9670g);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
        public void onProgress(int i2, int i3) {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
        public void onStart() {
        }
    }

    public boolean l(JDJSONObject jDJSONObject, PanicFloorEntity panicFloorEntity, boolean z) {
        if (jDJSONObject == null || jDJSONObject.size() == 0) {
            return false;
        }
        try {
            this.f9669c = jDJSONObject.getString("functionId");
            JDJSONArray jSONArray = jDJSONObject.getJSONArray("indexMiaoSha");
            panicFloorEntity.setMiaoshaAdvance(f.t0(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "miaoshaAdvance", "0"), 0));
            panicFloorEntity.setNextRoundKey(jDJSONObject.getString("nextRoundKey"));
            String string = jDJSONObject.getString("scheme");
            panicFloorEntity.setTimeInfo(!z, jDJSONObject.getLong("timeRemain"), jDJSONObject.getLong("nextStartTime").longValue());
            panicFloorEntity.setNameText(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "name", ""));
            if (!TextUtils.isEmpty(string)) {
                panicFloorEntity.setIsTestA("A".equals(string));
            }
            String jSONStringWithDefault = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "algorithmFrom", "");
            panicFloorEntity.setPanicExpoSourceValue(jSONStringWithDefault);
            if (!panicFloorEntity.isDataFromCache()) {
                com.jingdong.app.mall.home.r.c.a.v(JdSdk.getInstance().getApplicationContext(), "Home_SeckillFloorExpo", jSONStringWithDefault);
            }
            ArrayList<Product> list = Product.toList(jSONArray, 17);
            if (list != null) {
                Iterator<Product> it = list.iterator();
                while (it.hasNext()) {
                    int i2 = it.next().msItemType;
                    if (i2 != 1 && i2 != 8) {
                        it.remove();
                    }
                }
            }
            if (list != null && list.size() >= 4) {
                if (Log.D) {
                    Log.d("PanicFloorEngine", " -->>scheme = " + string + " isTestA = " + panicFloorEntity.isTestA() + " productList size = " + list.size());
                }
                panicFloorEntity.resetItemTmpList(list);
                MallFloorEvent.j(b(), panicFloorEntity.getFloorId());
                return true;
            }
            MallFloorEvent.k(b(), false);
            return false;
        } catch (Exception e2) {
            if (Log.E) {
                e2.printStackTrace();
                return false;
            }
            return false;
        }
    }

    public void m(boolean z, PanicFloorEntity panicFloorEntity) {
        if (this.b) {
            return;
        }
        this.b = true;
        h.a(this.f9669c, new a(z, panicFloorEntity), z);
    }

    public boolean n(boolean z, JDJSONObject jDJSONObject, PanicFloorEntity panicFloorEntity) {
        if (panicFloorEntity == null) {
            return false;
        }
        if (z) {
            m(false, panicFloorEntity);
            return false;
        }
        boolean l2 = l(jDJSONObject, panicFloorEntity, false);
        if (!l2) {
            m(false, panicFloorEntity);
        }
        return l2;
    }

    @Override // com.jingdong.app.mall.home.floor.presenter.engine.ListItemFloorEngine
    /* renamed from: o */
    public void i(com.jingdong.app.mall.home.r.e.f fVar, PanicFloorEntity panicFloorEntity) {
        super.i(fVar, panicFloorEntity);
        panicFloorEntity.setExpo(fVar.j());
        panicFloorEntity.setExpoJson(fVar.l());
        panicFloorEntity.setTitleColor(com.jingdong.app.mall.home.floor.view.b.h.a.d(fVar.C(), -15066598));
        int[] p = m.p(fVar.L(), 0, false);
        if (p == null) {
            p = MallPanicFloorRecyclerAdapter.f9721g;
        } else if (p.length == 1) {
            p = new int[]{p[0], p[0]};
        }
        panicFloorEntity.setPromotionTagColor(p);
        int[] p2 = m.p(fVar.getJsonString("rightCornerColor"), 0, false);
        if (p2 == null) {
            p2 = PanicFloorEntity.DEFAULT_RIGHT_ARROW_COLORS;
        } else if (p2.length == 1) {
            p2 = new int[]{p2[0], p2[0]};
        }
        panicFloorEntity.setRightArrowColor(p2);
        boolean B = m.B(panicFloorEntity.mModel);
        panicFloorEntity.setVersion100(B);
        panicFloorEntity.setPriceStyle(fVar.getJsonInt("priceStyle", 0));
        panicFloorEntity.setSkuTagImg(fVar.getJsonString("skuTagImg"));
        panicFloorEntity.setInterestPointColor(m.j(fVar.getJsonString("interestPointColor"), -381927));
        PanicFloorEntity.BuyTimeViewData buyTimeViewData = panicFloorEntity.getBuyTimeViewData();
        buyTimeViewData.setBackgroundColor(0);
        buyTimeViewData.setTimePointColor(B ? -381927 : -441010);
        buyTimeViewData.setTimeTextColor(B ? -381927 : -441010);
        buyTimeViewData.setTimeTextSizePx(22);
    }
}
