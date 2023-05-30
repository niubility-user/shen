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
    */
    public void l(@NonNull GiftsTitleHolder giftsTitleHolder) {
        int i2;
        List<String> list = this.a;
        if (list == null || list.isEmpty() || this.f4830e == null) {
            return;
        }
        String str = this.a.get(giftsTitleHolder.getAdapterPosition());
        boolean z = this.b == giftsTitleHolder.getAdapterPosition();
        TextView textView = giftsTitleHolder.a;
        if (textView != null && giftsTitleHolder.b != null) {
            textView.setText(str);
            TextView textView2 = giftsTitleHolder.a;
            PdMImageGiftLayerTitleAdapter pdMImageGiftLayerTitleAdapter = PdMImageGiftLayerTitleAdapter.this;
            if (!pdMImageGiftLayerTitleAdapter.f4829c) {
                if (!pdMImageGiftLayerTitleAdapter.f4832g) {
                    if (!z) {
                        i2 = 14;
                    }
                }
                i2 = 16;
            }
            textView2.setTextSize(2, i2);
            PdMImageGiftLayerTitleAdapter pdMImageGiftLayerTitleAdapter2 = PdMImageGiftLayerTitleAdapter.this;
            if (!pdMImageGiftLayerTitleAdapter2.f4829c) {
                if (z) {
                    if (pdMImageGiftLayerTitleAdapter2.d) {
                        giftsTitleHolder.b.setBackgroundResource(R.drawable.lib_pd_mainimage_nav_icon_dark);
                        giftsTitleHolder.a.setTextColor(PdMImageGiftLayerTitleAdapter.this.f4830e.getResources().getColor(R.color.lib_pd_image_color_ff3826));
                    } else {
                        giftsTitleHolder.b.setBackgroundResource(R.drawable.lib_pd_mainimage_nav_icon);
                        giftsTitleHolder.a.setTextColor(PdMImageGiftLayerTitleAdapter.this.f4830e.getResources().getColor(R.color.lib_pd_image_color_FA2C19));
                    }
                    giftsTitleHolder.b.setVisibility(0);
                    giftsTitleHolder.a.setTypeface(Typeface.defaultFromStyle(1));
                } else {
                    giftsTitleHolder.a.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(pdMImageGiftLayerTitleAdapter2.f4830e, R.color.lib_pd_image_color_1A1A1A, pdMImageGiftLayerTitleAdapter2.d));
                    giftsTitleHolder.a.setTypeface(Typeface.defaultFromStyle(0));
                    giftsTitleHolder.b.setVisibility(8);
                }
            }
        }
        giftsTitleHolder.a.setOnClickListener(new d(this, giftsTitleHolder));
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
