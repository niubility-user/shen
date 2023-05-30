package com.jd.lib.cashier.sdk.pay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.y;
import com.jd.lib.cashier.sdk.pay.bean.PublicGoodContent;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes14.dex */
public class PublicGoodPlanContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context a;
    public List<PublicGoodContent> b;

    /* loaded from: classes14.dex */
    static class a extends RecyclerView.ViewHolder {
        private final TextView a;
        private final TextView b;

        /* renamed from: c  reason: collision with root package name */
        private final LinearLayout f3846c;

        public a(@NotNull View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.lib_cashier_dialog_public_good_plan_sub_title);
            this.b = (TextView) view.findViewById(R.id.lib_cashier_dialog_public_good_plan_message);
            this.f3846c = (LinearLayout) view.findViewById(R.id.lib_cashier_dialog_public_good_plan_root_layout);
        }

        public void b(String str) {
            this.b.setText(str);
            if (y.t(12.0f)) {
                this.b.setLineSpacing(DpiUtil.dip2px(8.0f), 1.0f);
            }
        }

        public void c(String str) {
            this.a.setText(str);
        }

        public void d() {
            TextView textView = this.b;
            if (textView == null || this.f3846c == null) {
                return;
            }
            textView.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A, JDDarkUtil.COLOR_ECECEC));
            this.f3846c.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FFFFFFF));
        }
    }

    public PublicGoodPlanContentAdapter(Context context, List<PublicGoodContent> list) {
        this.a = context;
        this.b = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<PublicGoodContent> list = this.b;
        if (list == null || list.size() == 0) {
            return 0;
        }
        return this.b.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2) {
        PublicGoodContent publicGoodContent;
        if (this.b == null || i2 > r0.size() - 1 || (publicGoodContent = this.b.get(i2)) == null) {
            return;
        }
        a aVar = (a) viewHolder;
        aVar.c(publicGoodContent.title);
        aVar.b(publicGoodContent.description);
        aVar.d();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new a(LayoutInflater.from(this.a).inflate(R.layout.lib_cashier_public_good_plan_content_item, viewGroup, false));
    }

    public void setList(List<PublicGoodContent> list) {
        List<PublicGoodContent> list2 = this.b;
        if (list2 != null) {
            list2.clear();
            this.b.addAll(list);
        }
    }
}
