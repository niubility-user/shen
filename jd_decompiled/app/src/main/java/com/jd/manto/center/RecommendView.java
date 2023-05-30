package com.jd.manto.center;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.manto.launch.LaunchParam;
import com.jingdong.manto.pkg.db.entity.PkgRecommend;
import com.jingdong.manto.sdk.api.IImageLoader;
import com.jingdong.manto.utils.MantoTrack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes17.dex */
public final class RecommendView extends RelativeLayout {

    /* renamed from: g  reason: collision with root package name */
    private View f6485g;

    /* renamed from: h  reason: collision with root package name */
    private ImageView f6486h;

    /* renamed from: i  reason: collision with root package name */
    private TextView f6487i;

    /* renamed from: j  reason: collision with root package name */
    private TextView f6488j;

    /* renamed from: k  reason: collision with root package name */
    private IImageLoader f6489k;

    /* renamed from: l  reason: collision with root package name */
    private List<PkgRecommend> f6490l;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ PkgRecommend f6491g;

        a(PkgRecommend pkgRecommend) {
            this.f6491g = pkgRecommend;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LaunchParam launchParam = new LaunchParam();
            launchParam.appId = this.f6491g.appId;
            launchParam.debugType = "1";
            com.jingdong.a.p(launchParam, RecommendView.this.getContext());
            MantoTrack.sendCommonDataWithExt(RecommendView.this.getContext(), "\u67e5\u770b\u63a8\u8350", "Applets_Center_Recommend", "", "", "", "", "", null);
            HashMap hashMap = new HashMap();
            hashMap.put("vapp", "1");
            hashMap.put("vapp_appid", this.f6491g.appId);
            hashMap.put("source", "myApplets");
            Context context = RecommendView.this.getContext();
            PkgRecommend pkgRecommend = this.f6491g;
            MantoTrack.sendCommonDataWithExt(context, pkgRecommend.name, "Applets_Center_Enter", pkgRecommend.appId, "", "", "", "", hashMap);
        }
    }

    public RecommendView(Context context) {
        this(context, null);
    }

    private void a() {
        List<PkgRecommend> list = this.f6490l;
        if (list == null || list.size() <= 0) {
            return;
        }
        PkgRecommend pkgRecommend = this.f6490l.get(0);
        this.f6487i.setText(pkgRecommend.name);
        this.f6488j.setText(pkgRecommend.description);
        this.f6485g.setOnClickListener(new a(pkgRecommend));
        IImageLoader iImageLoader = this.f6489k;
        if (iImageLoader != null) {
            try {
                iImageLoader.loadImage(this.f6486h, pkgRecommend.logo);
            } catch (Exception unused) {
            }
        }
    }

    public void b(List<PkgRecommend> list) {
        this.f6490l = list;
        a();
    }

    public RecommendView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RecommendView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f6490l = new ArrayList(5);
        View inflate = RelativeLayout.inflate(context, R.layout.manto_center_recommend_item, this);
        this.f6485g = inflate.findViewById(R.id.container_top);
        this.f6486h = (ImageView) inflate.findViewById(R.id.iv_logo);
        this.f6487i = (TextView) inflate.findViewById(R.id.tv_app_name);
        this.f6488j = (TextView) inflate.findViewById(R.id.manto_app_desc);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_tag);
        this.f6489k = (IImageLoader) com.jingdong.a.n(IImageLoader.class);
    }
}
