package com.jingdong.sdk.jdshare.cell;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.appshare.R;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.ShareValues;
import com.jingdong.sdk.jdshare.utils.g;
import com.jingdong.sdk.jdshare.utils.h;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class ChannelAdapter extends RecyclerView.Adapter<b> {
    private Context a;
    private List<com.jingdong.c.a.c.b> b;

    /* renamed from: c */
    private c f14976c;
    private boolean d;

    /* renamed from: e */
    private boolean f14977e;

    /* loaded from: classes7.dex */
    public class a implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ b f14978g;

        a(b bVar) {
            ChannelAdapter.this = r1;
            this.f14978g = bVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (ChannelAdapter.this.f14976c != null) {
                ChannelAdapter.this.f14976c.a((com.jingdong.c.a.c.b) ChannelAdapter.this.b.get(this.f14978g.getAdapterPosition()));
            }
        }
    }

    /* loaded from: classes7.dex */
    public static class b extends RecyclerView.ViewHolder {
        View a;
        ImageView b;

        /* renamed from: c */
        TextView f14980c;
        TextView d;

        /* synthetic */ b(View view, a aVar) {
            this(view);
        }

        public void c(com.jingdong.c.a.c.b bVar, Context context, boolean z) {
            if (bVar == null) {
                return;
            }
            int i2 = bVar.d;
            if (i2 != 0) {
                this.b.setImageResource(i2);
            } else {
                JDImageUtils.displayImage(bVar.f12267e, this.b);
            }
            this.f14980c.setText(bVar.b);
            if (ShareValues.isNewWeiXinShareUI) {
                int i3 = 54;
                if (bVar.f12266c) {
                    this.d.setText("\u53e3\u4ee4");
                } else {
                    int i4 = bVar.f12270h;
                    if (i4 == 2) {
                        this.d.setText("\u5c0f\u7a0b\u5e8f");
                        i3 = 81;
                    } else {
                        if (i4 == 0 && z) {
                            this.d.setText("\u94fe\u63a5");
                        }
                        ((RelativeLayout.LayoutParams) this.d.getLayoutParams()).width = g.d(context, i3);
                        this.d.setVisibility(r1);
                        return;
                    }
                }
                r1 = 0;
                ((RelativeLayout.LayoutParams) this.d.getLayoutParams()).width = g.d(context, i3);
                this.d.setVisibility(r1);
                return;
            }
            this.d.setVisibility(bVar.f12266c ? 0 : 4);
        }

        private b(View view) {
            super(view);
            this.a = view;
            this.b = (ImageView) view.findViewById(R.id.share_layout_item_img);
            this.f14980c = (TextView) view.findViewById(R.id.share_layout_item_text);
            this.d = (TextView) view.findViewById(R.id.share_key_superScript);
        }
    }

    /* loaded from: classes7.dex */
    public interface c {
        void a(com.jingdong.c.a.c.b bVar);
    }

    public ChannelAdapter(Context context, List<com.jingdong.c.a.c.b> list, String str) {
        this.a = context;
        this.b = list;
        if (list == null || !ShareValues.newAddUrlQuerySwitch) {
            return;
        }
        Iterator<com.jingdong.c.a.c.b> it = list.iterator();
        while (it.hasNext()) {
            com.jingdong.c.a.c.b next = it.next();
            if (next != null && !next.f12266c && "Sinaweibo".equals(next.a) && str.length() + com.jingdong.sdk.jdshare.utils.a.h() > 512) {
                OKLog.d("ChannelAdapter", "weibourl\u957f\u5ea6\u8d85\u8fc7512 url:" + str);
                it.remove();
                return;
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<com.jingdong.c.a.c.b> list = this.b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: l */
    public void onBindViewHolder(@NonNull b bVar, int i2) {
        if (this.b == null) {
            return;
        }
        bVar.a.setOnClickListener(new a(bVar));
        bVar.c(this.b.get(i2), this.a, this.f14977e);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    /* renamed from: m */
    public b onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        View inflate = ImageUtil.inflate(R.layout.share_layout_item, null);
        b bVar = new b(inflate, null);
        inflate.setPadding(0, 0, 0, 0);
        if (this.d) {
            bVar.b.setScaleType(ImageView.ScaleType.FIT_XY);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) bVar.b.getLayoutParams();
            layoutParams.width = g.d(this.a, 100);
            layoutParams.height = g.d(this.a, 100);
            layoutParams.topMargin = 0;
            layoutParams.rightMargin = g.d(this.a, 10);
            layoutParams.leftMargin = g.d(this.a, 10);
            h.a(bVar.b, g.d(this.a, 50));
            ((RelativeLayout.LayoutParams) bVar.f14980c.getLayoutParams()).topMargin = g.d(this.a, 116);
            bVar.f14980c.setMaxWidth(g.d(this.a, 120));
            bVar.f14980c.setMaxLines(1);
            bVar.f14980c.setEllipsize(TextUtils.TruncateAt.END);
            bVar.f14980c.setTextSize(0, g.d(this.a, 28));
            bVar.f14980c.setTextColor(Color.parseColor(JDDarkUtil.COLOR_262626));
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) bVar.d.getLayoutParams();
            layoutParams2.rightMargin = 0;
            layoutParams2.topMargin = 0;
            bVar.d.setVisibility(8);
        } else {
            bVar.b.setScaleType(ImageView.ScaleType.FIT_XY);
            RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) bVar.b.getLayoutParams();
            RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) bVar.f14980c.getLayoutParams();
            if (ShareValues.isLandscapeMode()) {
                layoutParams3.width = DPIUtil.dip2px(this.a, 56.0f);
                layoutParams3.height = DPIUtil.dip2px(this.a, 56.0f);
                layoutParams4.topMargin = DPIUtil.dip2px(this.a, 74.0f);
                layoutParams4.bottomMargin = DPIUtil.dip2px(this.a, 40.0f);
                bVar.f14980c.setTextColor(Color.parseColor("#ececec"));
            } else {
                layoutParams3.width = g.d(this.a, 120);
                layoutParams3.height = g.d(this.a, 120);
                layoutParams4.topMargin = g.d(this.a, 124);
                layoutParams4.bottomMargin = g.d(this.a, 0);
                bVar.f14980c.setTextSize(0, g.d(this.a, 28));
                bVar.f14980c.setTextColor(Color.parseColor(JDDarkUtil.COLOR_262626));
            }
            layoutParams3.topMargin = g.d(this.a, 8);
            layoutParams3.rightMargin = g.d(this.a, 7);
            layoutParams3.leftMargin = g.d(this.a, 7);
            RelativeLayout.LayoutParams layoutParams5 = (RelativeLayout.LayoutParams) bVar.d.getLayoutParams();
            layoutParams5.width = g.d(this.a, 54);
            layoutParams5.height = g.d(this.a, 28);
            layoutParams5.rightMargin = g.d(this.a, -6);
            layoutParams5.topMargin = g.d(this.a, -8);
            bVar.d.setTextSize(0, g.d(this.a, 20));
        }
        return bVar;
    }

    public void n(boolean z) {
        this.d = z;
    }

    public void o(c cVar) {
        this.f14976c = cVar;
    }

    public void p(boolean z) {
        this.f14977e = z;
    }
}
