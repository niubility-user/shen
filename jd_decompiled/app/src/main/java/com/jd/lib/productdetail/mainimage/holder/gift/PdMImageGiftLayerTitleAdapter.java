package com.jd.lib.productdetail.mainimage.holder.gift;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.productdetail.mainimage.R;
import java.util.List;

/* loaded from: classes15.dex */
public class PdMImageGiftLayerTitleAdapter extends RecyclerView.Adapter<GiftsTitleHolder> {
    public List<String> a;
    public int b = 0;

    /* renamed from: c  reason: collision with root package name */
    public boolean f4829c;
    public boolean d;

    /* renamed from: e  reason: collision with root package name */
    public Context f4830e;

    /* renamed from: f  reason: collision with root package name */
    public a f4831f;

    /* renamed from: g  reason: collision with root package name */
    public boolean f4832g;

    /* renamed from: h  reason: collision with root package name */
    public RecyclerView f4833h;

    /* loaded from: classes15.dex */
    public class GiftsTitleHolder extends RecyclerView.ViewHolder {
        public TextView a;
        public View b;

        public GiftsTitleHolder(@NonNull View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.pd_topimage_gift_select_title);
            this.b = view.findViewById(R.id.pd_topimage_gift_select_icon);
            if (PdMImageGiftLayerTitleAdapter.this.f4829c) {
                this.b.setVisibility(8);
                this.a.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(PdMImageGiftLayerTitleAdapter.this.f4830e, R.color.lib_pd_image_color_1A1A1A, PdMImageGiftLayerTitleAdapter.this.d));
                this.a.setTypeface(Typeface.defaultFromStyle(1));
                if (PdMImageGiftLayerTitleAdapter.this.f4832g) {
                    this.a.setTextSize(2, 18.0f);
                } else {
                    this.a.setTextSize(2, 16.0f);
                }
            }
        }
    }

    /* loaded from: classes15.dex */
    public interface a {
        void onClick(View view, int i2);
    }

    public PdMImageGiftLayerTitleAdapter(RecyclerView recyclerView, Context context, boolean z) {
        this.f4830e = context;
        this.f4832g = z;
        this.f4833h = recyclerView;
    }

    @NonNull
    public GiftsTitleHolder a(@NonNull ViewGroup viewGroup) {
        return new GiftsTitleHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lib_pd_mainimage_holder_topimage_item_gift_bottomlayer_title, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<String> list = this.a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void h(int i2) {
        int i3 = this.b;
        if (i3 == i2) {
            return;
        }
        notifyItemChanged(i3);
        this.b = i2;
        notifyItemChanged(i2);
        GiftsTitleHolder giftsTitleHolder = (GiftsTitleHolder) this.f4833h.findViewHolderForAdapterPosition(i2);
        if (giftsTitleHolder != null) {
            View view = giftsTitleHolder.itemView;
            if (this.f4833h == null || view == null) {
                return;
            }
            int width = view.getWidth();
            Rect rect = new Rect();
            view.getLocalVisibleRect(rect);
            if (rect.width() < width) {
                int i4 = rect.left;
                if (i4 > 0) {
                    this.f4833h.scrollBy(-i4, 0);
                    return;
                } else if (i4 == 0) {
                    this.f4833h.scrollBy(width - rect.width(), 0);
                    return;
                } else {
                    return;
                }
            }
            return;
        }
        this.f4833h.smoothScrollToPosition(0);
        this.f4833h.getLayoutManager().scrollToPosition(i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0041, code lost:
        if (r5.f4832g != false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0048, code lost:
        if (r1 != false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x004a, code lost:
        r5 = 18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void l(@androidx.annotation.NonNull com.jd.lib.productdetail.mainimage.holder.gift.PdMImageGiftLayerTitleAdapter.GiftsTitleHolder r8) {
        /*
            r7 = this;
            java.util.List<java.lang.String> r0 = r7.a
            if (r0 == 0) goto Ld1
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto Ld1
            android.content.Context r0 = r7.f4830e
            if (r0 != 0) goto L10
            goto Ld1
        L10:
            java.util.List<java.lang.String> r0 = r7.a
            int r1 = r8.getAdapterPosition()
            java.lang.Object r0 = r0.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            int r1 = r7.b
            int r2 = r8.getAdapterPosition()
            r3 = 1
            r4 = 0
            if (r1 != r2) goto L28
            r1 = 1
            goto L29
        L28:
            r1 = 0
        L29:
            android.widget.TextView r2 = r8.a
            if (r2 == 0) goto Lc7
            android.view.View r5 = r8.b
            if (r5 != 0) goto L33
            goto Lc7
        L33:
            r2.setText(r0)
            android.widget.TextView r0 = r8.a
            r2 = 2
            com.jd.lib.productdetail.mainimage.holder.gift.PdMImageGiftLayerTitleAdapter r5 = com.jd.lib.productdetail.mainimage.holder.gift.PdMImageGiftLayerTitleAdapter.this
            boolean r6 = r5.f4829c
            if (r6 == 0) goto L44
            boolean r5 = r5.f4832g
            if (r5 == 0) goto L4f
            goto L4a
        L44:
            boolean r5 = r5.f4832g
            if (r5 == 0) goto L4d
            if (r1 == 0) goto L4f
        L4a:
            r5 = 18
            goto L54
        L4d:
            if (r1 == 0) goto L52
        L4f:
            r5 = 16
            goto L54
        L52:
            r5 = 14
        L54:
            float r5 = (float) r5
            r0.setTextSize(r2, r5)
            com.jd.lib.productdetail.mainimage.holder.gift.PdMImageGiftLayerTitleAdapter r0 = com.jd.lib.productdetail.mainimage.holder.gift.PdMImageGiftLayerTitleAdapter.this
            boolean r2 = r0.f4829c
            if (r2 != 0) goto Lc7
            if (r1 == 0) goto La8
            boolean r0 = r0.d
            if (r0 == 0) goto L7f
            android.view.View r0 = r8.b
            int r1 = com.jd.lib.productdetail.mainimage.R.drawable.lib_pd_mainimage_nav_icon_dark
            r0.setBackgroundResource(r1)
            android.widget.TextView r0 = r8.a
            com.jd.lib.productdetail.mainimage.holder.gift.PdMImageGiftLayerTitleAdapter r1 = com.jd.lib.productdetail.mainimage.holder.gift.PdMImageGiftLayerTitleAdapter.this
            android.content.Context r1 = r1.f4830e
            android.content.res.Resources r1 = r1.getResources()
            int r2 = com.jd.lib.productdetail.mainimage.R.color.lib_pd_image_color_ff3826
            int r1 = r1.getColor(r2)
            r0.setTextColor(r1)
            goto L99
        L7f:
            android.view.View r0 = r8.b
            int r1 = com.jd.lib.productdetail.mainimage.R.drawable.lib_pd_mainimage_nav_icon
            r0.setBackgroundResource(r1)
            android.widget.TextView r0 = r8.a
            com.jd.lib.productdetail.mainimage.holder.gift.PdMImageGiftLayerTitleAdapter r1 = com.jd.lib.productdetail.mainimage.holder.gift.PdMImageGiftLayerTitleAdapter.this
            android.content.Context r1 = r1.f4830e
            android.content.res.Resources r1 = r1.getResources()
            int r2 = com.jd.lib.productdetail.mainimage.R.color.lib_pd_image_color_FA2C19
            int r1 = r1.getColor(r2)
            r0.setTextColor(r1)
        L99:
            android.view.View r0 = r8.b
            r0.setVisibility(r4)
            android.widget.TextView r0 = r8.a
            android.graphics.Typeface r1 = android.graphics.Typeface.defaultFromStyle(r3)
            r0.setTypeface(r1)
            goto Lc7
        La8:
            android.widget.TextView r1 = r8.a
            android.content.Context r2 = r0.f4830e
            int r3 = com.jd.lib.productdetail.mainimage.R.color.lib_pd_image_color_1A1A1A
            boolean r0 = r0.d
            int r0 = com.jd.lib.productdetail.mainimage.utils.a.a(r2, r3, r0)
            r1.setTextColor(r0)
            android.widget.TextView r0 = r8.a
            android.graphics.Typeface r1 = android.graphics.Typeface.defaultFromStyle(r4)
            r0.setTypeface(r1)
            android.view.View r0 = r8.b
            r1 = 8
            r0.setVisibility(r1)
        Lc7:
            android.widget.TextView r0 = r8.a
            com.jd.lib.productdetail.mainimage.holder.gift.d r1 = new com.jd.lib.productdetail.mainimage.holder.gift.d
            r1.<init>(r7, r8)
            r0.setOnClickListener(r1)
        Ld1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.productdetail.mainimage.holder.gift.PdMImageGiftLayerTitleAdapter.l(com.jd.lib.productdetail.mainimage.holder.gift.PdMImageGiftLayerTitleAdapter$GiftsTitleHolder):void");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ void onBindViewHolder(@NonNull GiftsTitleHolder giftsTitleHolder, int i2) {
        l(giftsTitleHolder);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public /* bridge */ /* synthetic */ GiftsTitleHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        return a(viewGroup);
    }
}
