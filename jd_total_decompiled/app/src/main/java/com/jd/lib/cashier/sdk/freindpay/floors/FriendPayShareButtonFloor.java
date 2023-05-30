package com.jd.lib.cashier.sdk.freindpay.floors;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.share.IShare;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.b0;
import com.jd.lib.cashier.sdk.core.utils.f;
import com.jd.lib.cashier.sdk.core.utils.p;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.f.f.i;
import com.jd.lib.cashier.sdk.freindpay.bean.ActivityResult;
import com.jd.lib.cashier.sdk.freindpay.bean.FamilyOuterInfo;
import com.jd.lib.cashier.sdk.freindpay.bean.ShareInfo;
import java.util.Map;

/* loaded from: classes14.dex */
public class FriendPayShareButtonFloor extends AbstractFloor<com.jd.lib.cashier.sdk.f.c.a, i> implements f<ActivityResult> {

    /* renamed from: i */
    private TextView f3392i;

    /* renamed from: j */
    private TextView f3393j;

    /* renamed from: k */
    private FamilyOuterInfo f3394k;

    /* renamed from: l */
    private final String f3395l;

    /* loaded from: classes14.dex */
    public class a implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ ShareInfo f3396g;

        /* renamed from: h */
        final /* synthetic */ Context f3397h;

        a(FriendPayShareButtonFloor friendPayShareButtonFloor, ShareInfo shareInfo, Context context) {
            this.f3396g = shareInfo;
            this.f3397h = context;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            b0.a aVar = new b0.a();
            aVar.a("Wxfriends");
            aVar.b(this.f3396g.imageUrl);
            aVar.e(this.f3396g.shareUrl);
            aVar.d(this.f3396g.title);
            aVar.c(this.f3396g.description);
            Map<String, String> f2 = aVar.f();
            Context context = this.f3397h;
            if (context instanceof FragmentActivity) {
                b0.a((FragmentActivity) context, f2);
            }
            com.jd.lib.cashier.sdk.f.d.a.d(this.f3397h);
        }
    }

    /* loaded from: classes14.dex */
    public class b implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ String f3398g;

        b(String str) {
            FriendPayShareButtonFloor.this = r1;
            this.f3398g = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            p.a(FriendPayShareButtonFloor.this.getConvertView().getContext(), this.f3398g);
        }
    }

    public FriendPayShareButtonFloor(com.jd.lib.cashier.sdk.f.c.a aVar, View view) {
        super(view);
        this.f3395l = FriendPayShareButtonFloor.class.getSimpleName();
        if (aVar != null) {
            aVar.b(this);
        }
    }

    private void d() {
        this.f3393j.setClickable(false);
        this.f3393j.setVisibility(8);
    }

    private void e() {
        try {
            this.f3393j.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_8C8C8C));
            getConvertView().setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FFFFFFF));
        } catch (Exception e2) {
            r.d(this.f3395l, e2.getMessage());
        }
    }

    private void g(ShareInfo shareInfo) {
        if (this.f3392i == null) {
            return;
        }
        Context context = getConvertView().getContext();
        if (shareInfo != null && context != null) {
            this.f3392i.setClickable(true);
            this.f3392i.setOnClickListener(new a(this, shareInfo, context));
            return;
        }
        this.f3392i.setClickable(false);
    }

    private void h(String str, String str2) {
        this.f3393j.setClickable(true);
        this.f3393j.setVisibility(0);
        this.f3393j.setText(str);
        this.f3393j.setOnClickListener(new b(str2));
    }

    @Override // com.jd.lib.cashier.sdk.core.utils.f
    /* renamed from: c */
    public void callBack(ActivityResult activityResult) {
        if (activityResult == null || this.f3393j == null) {
            return;
        }
        if (this.f3394k == null) {
            d();
            return;
        }
        r.b(this.f3395l, "requestCode = " + activityResult.requestCode + "\t+resultCode = " + activityResult.resultCode);
        IShare share = DependInitializer.getShare();
        if (share != null && activityResult.requestCode == share.getShareRequestCode() && activityResult.resultCode == share.getShareResultSucCode()) {
            if (!TextUtils.isEmpty(this.f3394k.familyText) && !TextUtils.isEmpty(this.f3394k.jumpUrl) && this.f3394k.isShowFamilyOuter()) {
                FamilyOuterInfo familyOuterInfo = this.f3394k;
                h(familyOuterInfo.familyText, familyOuterInfo.jumpUrl);
                return;
            }
            d();
            return;
        }
        d();
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: f */
    public void b(com.jd.lib.cashier.sdk.f.c.a aVar, i iVar) {
        if (iVar != null) {
            g(iVar.a);
            this.f3394k = iVar.b;
            e();
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        this.f3392i = (TextView) getView(R.id.lib_cashier_friend_pay_share_button);
        this.f3393j = (TextView) getView(R.id.lib_cashier_friend_pay_family_outer_button);
    }
}
