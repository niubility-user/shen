package com.jingdong.sdk.jdshare.cell;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.appshare.R;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.utils.JDSharedCommandUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.jdshare.cell.JDShareFeedbackAdapter;
import com.jingdong.sdk.jdshare.utils.g;
import com.jingdong.sdk.jdtoast.ToastUtils;
import java.util.Arrays;

/* loaded from: classes7.dex */
public class b extends Dialog {

    /* renamed from: g  reason: collision with root package name */
    private View f14997g;

    /* renamed from: h  reason: collision with root package name */
    private Context f14998h;

    /* renamed from: i  reason: collision with root package name */
    private TextView f14999i;

    /* renamed from: j  reason: collision with root package name */
    private ImageView f15000j;

    /* renamed from: k  reason: collision with root package name */
    private RecyclerView f15001k;

    /* renamed from: l  reason: collision with root package name */
    private JDSharedCommandUtils.JDCommandInfo f15002l;

    /* renamed from: m  reason: collision with root package name */
    private ShareInfo f15003m;

    /* renamed from: n  reason: collision with root package name */
    private d f15004n;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (b.this.f15002l != null) {
                g.l("ShareJingwords_FeedbackClose", b.this.f15002l.srv, "", g.h(b.this.f15003m, b.this.f15002l.eventParamJson));
            }
            b.this.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.sdk.jdshare.cell.b$b  reason: collision with other inner class name */
    /* loaded from: classes7.dex */
    public class ViewOnClickListenerC0721b implements View.OnClickListener {
        ViewOnClickListenerC0721b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            JDShareFeedbackAdapter jDShareFeedbackAdapter = (JDShareFeedbackAdapter) b.this.f15001k.getAdapter();
            if (jDShareFeedbackAdapter != null) {
                com.jingdong.c.a.c.d m2 = jDShareFeedbackAdapter.m();
                if (m2 != null) {
                    if (b.this.f15002l != null) {
                        g.l("ShareJingwords_FeedbackSubmit", b.this.f15002l.srv + CartConstant.KEY_YB_INFO_LINK + m2.a, "", g.h(b.this.f15003m, b.this.f15002l.eventParamJson));
                    }
                    ToastUtils.showToastInCenter(b.this.f14998h, "\u53cd\u9988\u6210\u529f\uff0c\u611f\u8c22\u652f\u6301");
                    b.this.dismiss();
                    if (b.this.f15004n != null) {
                        b.this.f15004n.a();
                        return;
                    }
                    return;
                }
                ToastUtils.showToastInCenter(b.this.f14998h, "\u8bf7\u9009\u62e9\u7406\u7531\u540e\u63d0\u4ea4\u5594~");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public class c implements JDShareFeedbackAdapter.c {
        c() {
        }

        @Override // com.jingdong.sdk.jdshare.cell.JDShareFeedbackAdapter.c
        public void a(com.jingdong.c.a.c.d dVar) {
            if (dVar == null || b.this.f15002l == null) {
                return;
            }
            g.l("ShareJingwords_FeedbackType", b.this.f15002l.srv + CartConstant.KEY_YB_INFO_LINK + dVar.a, "", g.h(b.this.f15003m, b.this.f15002l.eventParamJson));
        }
    }

    /* loaded from: classes7.dex */
    interface d {
        void a();
    }

    public b(@NonNull Context context) {
        super(context);
        this.f14998h = context;
    }

    private void f() {
        Window window = getWindow();
        if (window != null) {
            window.requestFeature(1);
            View inflate = LayoutInflater.from(this.f14998h).inflate(R.layout.share_layout_feedback, (ViewGroup) null);
            this.f14997g = inflate;
            setContentView(inflate);
            window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
            window.setLayout(-1, -2);
            window.setGravity(80);
            window.setWindowAnimations(R.style.jd_share_key_popup_style);
            this.f14999i = (TextView) this.f14997g.findViewById(R.id.submit_feedback);
            this.f15000j = (ImageView) this.f14997g.findViewById(R.id.feedback_close);
            RecyclerView recyclerView = (RecyclerView) this.f14997g.findViewById(R.id.recycler_view_feedback);
            this.f15001k = recyclerView;
            recyclerView.addItemDecoration(new FeedbackItemSpaceDecoration(g.d(this.f14998h, 16)));
            this.f15001k.setLayoutManager(new LinearLayoutManager(this.f14998h, 1, false));
            this.f15000j.setOnClickListener(new a());
            this.f14999i.setOnClickListener(new ViewOnClickListenerC0721b());
        }
    }

    private void g() {
        JDShareFeedbackAdapter jDShareFeedbackAdapter = new JDShareFeedbackAdapter(this.f14998h, Arrays.asList(new com.jingdong.c.a.c.d(0, "\u5f39\u51fa\u53e3\u4ee4\u4e0e\u590d\u5236\u7684\u53e3\u4ee4\u4e0d\u7b26"), new com.jingdong.c.a.c.d(1, "\u6211\u6ca1\u6709\u590d\u5236\u4eac\u53e3\u4ee4"), new com.jingdong.c.a.c.d(2, "\u603b\u662f\u5f39\u51fa\u4e00\u4e2a\u4eac\u53e3\u4ee4"), new com.jingdong.c.a.c.d(-1, "\u5176\u4ed6")));
        this.f15001k.setAdapter(jDShareFeedbackAdapter);
        jDShareFeedbackAdapter.p(new c());
    }

    public void h(ShareInfo shareInfo, JDSharedCommandUtils.JDCommandInfo jDCommandInfo) {
        this.f15002l = jDCommandInfo;
        this.f15003m = shareInfo;
        if (TextUtils.isEmpty(jDCommandInfo.userName)) {
            jDCommandInfo.userName = "\u795e\u79d8\u7528\u6237";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i(d dVar) {
        this.f15004n = dVar;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        f();
        g();
    }
}
