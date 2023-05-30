package com.jd.dynamic.lib.utils;

import android.text.TextUtils;
import android.util.Pair;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.apis.DYContainerConfig;
import com.jd.dynamic.base.DynamicDriver;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.XMLParse;
import com.jd.dynamic.entity.ViewNode;
import java.io.ByteArrayInputStream;
import java.util.concurrent.Executors;

/* loaded from: classes13.dex */
public class n {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        private long f2275g;

        a(long j2) {
            this.f2275g = 0L;
            this.f2275g = j2;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                int G = com.jd.dynamic.b.a.b.o().G();
                String str = "cart";
                String str2 = "cart_discount";
                Pair<String, String> F = com.jd.dynamic.b.a.b.o().F();
                DYConstants.DYLog("------v2----- fast init binary.... + " + this.f2275g + " fast is : " + F + " default is : cart -> cart_discount");
                if (!TextUtils.isEmpty((CharSequence) F.first) && !TextUtils.isEmpty((CharSequence) F.second)) {
                    str = (String) F.first;
                    str2 = (String) F.second;
                }
                r createFakeContainer = ((DynamicDriver) DynamicSdk.getDriver()).createFakeContainer(new DYContainerConfig(DynamicSdk.getEngine().getContext(), str, str2, null));
                if (createFakeContainer != null) {
                    DYConstants.DYLog("start fast load !!!! times: " + G);
                    for (int i2 = 0; i2 < G; i2++) {
                        createFakeContainer.e();
                    }
                }
                DYConstants.DYLog("------  fast init binary !!!!!    " + (System.currentTimeMillis() - this.f2275g));
            } catch (Throwable th) {
                t.a(th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class b implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        private long f2276g;

        b(long j2) {
            this.f2276g = 0L;
            this.f2276g = j2;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                DYConstants.DYLog("-------++++++++ fast init xml.... + " + this.f2276g);
                XMLParse xMLParse = new XMLParse(new ByteArrayInputStream("<DynamicData xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n    xmlns=\"http://www.jddynamic.com\" xsi:schemaLocation=\"http://www.jddynamic.com jddynamic.xsd\">\n    <FlexboxLayout alignItems=\"center\" direction=\"rtl\" flexDirection=\"row\" height=\"16\" layoutId=\"1987220000\" width=\"match_parent\">\n        <ImageView height=\"6\" layoutId=\"1987220003\" src=\"${resourceConfig.moreIcon}\" width=\"6\" />\n        <CollectionView alignItems=\"flex_start\" data=\"${jxSkuList}\" flexDirection=\"row\" flexWrap=\"wrap\" justifyContent=\"space_between\" layoutId=\"1987220004\" limitColumnSize=\"1\" minimumLineSpacing=\"10\" scrollDirection=\"1\">\n            <Items>\n                <FlexboxLayout alignItems=\"center\" direction=\"rtl\" flexDirection=\"row\" height=\"16\" layoutId=\"1987220002\" width=\"match_parent\">\n                    <TextView gravity=\"right\" layoutId=\"1987220001\" maxWidth=\"10\" text=\"t\" visibility=\"0\" textColor=\"#808080\" textSize=\"12\" />\n                </FlexboxLayout>\n            </Items>\n        </CollectionView>\n    </FlexboxLayout>\n</DynamicData>".getBytes()));
                ViewNode parse = xMLParse.parse();
                parse.unBindMaps = xMLParse.unBindMaps;
                ViewNode childByName = parse.getChildByName(DYConstants.DY_FLEXBOX_LAYOUT);
                com.jd.dynamic.lib.viewparse.f.a(childByName.getViewName(), childByName.getAttributes(), null, false, false).a(childByName, DynamicSdk.getEngine().getContext());
                DYConstants.DYLog("-------++++++++ fast init xml !!!!!" + (System.currentTimeMillis() - this.f2276g));
            } catch (Throwable th) {
                t.a(th);
            }
        }
    }

    public static void a() {
        Runnable bVar;
        long currentTimeMillis = System.currentTimeMillis();
        if (com.jd.dynamic.b.a.b.o().E()) {
            bVar = new a(currentTimeMillis);
        } else if (!com.jd.dynamic.b.a.b.o().D()) {
            DYConstants.DYLog("++++ not have fast init and return");
            return;
        } else {
            bVar = new b(currentTimeMillis);
        }
        Executors.newSingleThreadExecutor().execute(bVar);
    }
}
