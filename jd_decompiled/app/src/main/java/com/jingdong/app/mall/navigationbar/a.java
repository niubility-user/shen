package com.jingdong.app.mall.navigationbar;

import android.content.Context;
import android.os.Handler;
import com.jingdong.common.unification.customtheme.entity.NavigationInfo;
import com.jingdong.common.unification.navigationbar.IButtonAction;
import com.jingdong.jdsdk.JdSdk;

/* loaded from: classes4.dex */
public class a implements IButtonAction {
    private Runnable a;
    private int b;

    /* renamed from: c */
    private Context f11356c;
    private NavigationInfo d;

    public a(int i2) {
        this(c.G().Q(i2, null), i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:298:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x01ea A[Catch: Exception -> 0x00e7, TryCatch #1 {Exception -> 0x00e7, blocks: (B:211:0x0086, B:215:0x009c, B:229:0x00c7, B:236:0x00d6, B:242:0x00fe, B:244:0x0103, B:246:0x0109, B:249:0x0113, B:254:0x0123, B:256:0x0129, B:258:0x0131, B:259:0x0138, B:261:0x013e, B:263:0x0146, B:264:0x014d, B:266:0x0153, B:268:0x015b, B:269:0x0162, B:273:0x0170, B:277:0x017f, B:278:0x0182, B:287:0x01bc, B:296:0x01da, B:300:0x01e5, B:302:0x01ea, B:305:0x01f3, B:294:0x01c9, B:276:0x017d, B:272:0x016e, B:252:0x011b, B:232:0x00ce, B:241:0x00ee), top: B:381:0x0082 }] */
    /* JADX WARN: Removed duplicated region for block: B:307:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x020a A[Catch: Exception -> 0x0247, TryCatch #2 {Exception -> 0x0247, blocks: (B:207:0x007e, B:311:0x0205, B:313:0x020a, B:317:0x0213), top: B:383:0x007e }] */
    /* JADX WARN: Removed duplicated region for block: B:320:0x0229 A[Catch: Exception -> 0x0245, TryCatch #3 {Exception -> 0x0245, blocks: (B:319:0x0225, B:320:0x0229, B:321:0x022f), top: B:386:0x0101 }] */
    /* JADX WARN: Removed duplicated region for block: B:331:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:332:0x025b  */
    /* JADX WARN: Removed duplicated region for block: B:336:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x0292  */
    /* JADX WARN: Removed duplicated region for block: B:340:0x02bd  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:348:0x02f3  */
    /* JADX WARN: Removed duplicated region for block: B:358:0x034a  */
    /* JADX WARN: Removed duplicated region for block: B:359:0x0369  */
    /* JADX WARN: Removed duplicated region for block: B:360:0x0388  */
    /* JADX WARN: Removed duplicated region for block: B:361:0x03a7  */
    /* JADX WARN: Removed duplicated region for block: B:366:0x03d1  */
    /* JADX WARN: Removed duplicated region for block: B:367:0x03ef  */
    /* JADX WARN: Removed duplicated region for block: B:368:0x040d  */
    /* JADX WARN: Removed duplicated region for block: B:369:0x042b  */
    /* JADX WARN: Removed duplicated region for block: B:375:0x045e  */
    /* JADX WARN: Removed duplicated region for block: B:390:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v34 */
    /* JADX WARN: Type inference failed for: r1v73 */
    /* JADX WARN: Type inference failed for: r1v74 */
    /* JADX WARN: Type inference failed for: r1v75 */
    @Override // com.jingdong.common.unification.navigationbar.IButtonAction
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            Method dump skipped, instructions count: 1162
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.navigationbar.a.run():void");
    }

    public a(int i2, NavigationInfo navigationInfo) {
        this(c.G().Q(i2, navigationInfo), i2);
        this.d = navigationInfo;
    }

    public a(Runnable runnable, int i2) {
        this(runnable, true, i2);
    }

    public a(Runnable runnable, boolean z, int i2) {
        this.f11356c = JdSdk.getInstance().getApplicationContext();
        new Handler();
        this.b = i2;
        if (runnable == null) {
            return;
        }
        this.a = runnable;
    }
}
