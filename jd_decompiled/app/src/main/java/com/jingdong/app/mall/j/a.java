package com.jingdong.app.mall.j;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jdjr.mobilecert.MobileCertConstants;
import com.jingdong.app.mall.e;
import com.jingdong.app.mall.miaosha.MiaoShaPublicConstants;
import com.jingdong.common.deeplinkhelper.imhelper.DDParameterBuilder;
import com.jingdong.common.deeplinkhelper.imhelper.DeeplinkDongDongHelper;
import com.jingdong.jdsdk.mta.JDMtaUtils;

/* loaded from: classes4.dex */
public class a {

    /* renamed from: c  reason: collision with root package name */
    private static a f11141c;
    private b a;
    private InterfaceC0338a b;

    /* renamed from: com.jingdong.app.mall.j.a$a  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    public interface InterfaceC0338a {
        void a(c cVar);
    }

    /* loaded from: classes4.dex */
    public interface b {
        void a(int i2);
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (f11141c == null) {
                f11141c = new a();
            }
            aVar = f11141c;
        }
        return aVar;
    }

    public static void d(Context context, Bundle bundle) {
        String string = bundle.getString("entry");
        if (!TextUtils.isEmpty(string)) {
            JDMtaUtils.onClick(e.b().a(), string, e.b().a().getClass().getName());
        }
        if (TextUtils.isEmpty(bundle.getString("from"))) {
            bundle.putString("from", DDParameterBuilder.ACTION_BROADCAST_ENTRY_ASK);
        }
        DDParameterBuilder generateWithPin = DDParameterBuilder.generateWithPin();
        if (generateWithPin != null) {
            generateWithPin.addFrom(DDParameterBuilder.ACTION_BROADCAST_ENTRY_ASK).addSkuID(bundle.getString("sku")).addShopID(bundle.getString("shopId")).addVenderID(bundle.getString("venderId")).addStoreId(bundle.getString("storeId")).addAskType(bundle.getString("askType")).addDirectWaiter(bundle.getString("directWaiter")).addSearchKeyword(bundle.getString("searchKeyword")).addClass3(bundle.getString("class3")).addBuId(bundle.getInt("buId")).addChannelId(bundle.getInt("channelId")).addChannelTag(bundle.getInt("channelTag")).addBbtf(bundle.getString("bbtf")).addOrderID(bundle.getString("orderId")).addSkillID(bundle.getString("groupId")).addEntry(string).addServiceId(bundle.getString("serviceId")).addextRelativeId(bundle.getString("extRelativeId")).addContent(bundle.getString("content")).addTemplate(bundle.getString(MobileCertConstants.TEMPLATE)).addDisputeId(Integer.valueOf(bundle.getInt("disputeId"))).addTemplate(bundle.getString(MobileCertConstants.TEMPLATE)).addToPin(bundle.getString("toPin")).addToApp(bundle.getString("toApp")).addAvatar(bundle.getString("avatar")).addName(bundle.getString("name")).addGid(bundle.getString(MiaoShaPublicConstants.KEY_GID));
        }
        bundle.putAll(generateWithPin.getBundle());
        DeeplinkDongDongHelper.getInstance().startDongDong(context, bundle);
    }

    public static void e(Context context, Bundle bundle) {
        String string = bundle.getString("entry");
        if (TextUtils.isEmpty(bundle.getString("from"))) {
            if (!TextUtils.isEmpty(string)) {
                JDMtaUtils.onClick(e.b().a(), string, e.b().a().getClass().getName());
                bundle.putString("from", DDParameterBuilder.ACTION_BROADCAST_ENTRY_ASK);
            } else {
                bundle.putString("from", DDParameterBuilder.ACTION_BROADCAST_M_PRODUCT_ASK);
            }
        }
        DDParameterBuilder convertFromWebParameter = DDParameterBuilder.convertFromWebParameter(bundle);
        if (convertFromWebParameter != null) {
            DeeplinkDongDongHelper.getInstance().startDongDong(context, convertFromWebParameter.getBundle());
        }
    }

    public synchronized void b(c cVar) {
        InterfaceC0338a interfaceC0338a = this.b;
        if (interfaceC0338a != null) {
            interfaceC0338a.a(cVar);
            this.b = null;
        }
    }

    public synchronized void c(int i2) {
        b bVar = this.a;
        if (bVar != null) {
            bVar.a(i2);
            this.a = null;
        }
    }
}
