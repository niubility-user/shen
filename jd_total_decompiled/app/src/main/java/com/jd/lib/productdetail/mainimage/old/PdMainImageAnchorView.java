package com.jd.lib.productdetail.mainimage.old;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.framework.json.JDJSON;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicAnchorEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes15.dex */
public class PdMainImageAnchorView extends RecyclerView {

    /* renamed from: g  reason: collision with root package name */
    public BigImageAnchorAdapter f5129g;

    /* renamed from: h  reason: collision with root package name */
    public WareBusinessUnitMainImageEntity f5130h;

    /* renamed from: i  reason: collision with root package name */
    public Context f5131i;

    /* renamed from: j  reason: collision with root package name */
    public PdMainImagePresenter f5132j;

    /* loaded from: classes15.dex */
    public static class BigImageAnchorAdapter extends RecyclerView.Adapter<BigImageAnchorHoder> {
        public boolean a;
        public List<WareBusinessMagicAnchorEntity> b;

        /* renamed from: c  reason: collision with root package name */
        public int f5133c = -1;
        public a d;

        /* renamed from: e  reason: collision with root package name */
        public WareBusinessUnitMainImageEntity f5134e;

        /* renamed from: f  reason: collision with root package name */
        public PdMainImagePresenter f5135f;

        /* renamed from: g  reason: collision with root package name */
        public boolean f5136g;

        /* loaded from: classes15.dex */
        public class BigImageAnchorHoder extends RecyclerView.ViewHolder {
            public final TextView a;
            public final ImageView b;

            /* renamed from: c  reason: collision with root package name */
            public final View f5137c;

            public BigImageAnchorHoder(@NonNull View view) {
                super(view);
                this.a = (TextView) view.findViewById(R.id.lib_pd_top_image_anchor_item_txt);
                this.b = (ImageView) view.findViewById(R.id.lib_pd_top_image_anchor_item_icon);
                this.f5137c = view.findViewById(R.id.lib_pd_top_image_anchor_item_bg);
            }

            public final int b(boolean z, int i2, int i3) {
                TextView textView = this.a;
                if (textView == null) {
                    return 0;
                }
                return PDUtils.getColorWithTheme(z, ContextCompat.getColor(textView.getContext(), i2), ContextCompat.getColor(this.a.getContext(), i3));
            }
        }

        public BigImageAnchorAdapter(Context context, List<WareBusinessMagicAnchorEntity> list, boolean z) {
            this.b = list;
            this.f5136g = z;
        }

        public final GradientDrawable a(Context context, int i2) {
            int colorWithTheme = PDUtils.getColorWithTheme(this.a, ContextCompat.getColor(context, R.color.lib_pd_image_color_FFF2F2F2), ContextCompat.getColor(context, R.color.lib_pd_image_color_0A0909));
            if (this.f5136g) {
                colorWithTheme = PDUtils.getColorWithTheme(this.a, ContextCompat.getColor(context, R.color.lib_pd_image_color_E7E7E7), ContextCompat.getColor(context, R.color.lib_pd_image_color_6B6B6B));
            }
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(0);
            gradientDrawable.setColor(colorWithTheme);
            float dip2px = PDUtils.dip2px(14.0f);
            if (this.f5136g) {
                dip2px = PDUtils.dip2px(16.0f);
            }
            if (i2 == 0) {
                gradientDrawable.setCornerRadii(new float[]{dip2px, dip2px, 0.0f, 0.0f, 0.0f, 0.0f, dip2px, dip2px});
            } else if (i2 != 1 && i2 == 2) {
                gradientDrawable.setCornerRadii(new float[]{0.0f, 0.0f, dip2px, dip2px, dip2px, dip2px, 0.0f, 0.0f});
            }
            return gradientDrawable;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            List<WareBusinessMagicAnchorEntity> list = this.b;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        @NonNull
        public BigImageAnchorHoder h(@NonNull ViewGroup viewGroup) {
            return new BigImageAnchorHoder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lib_pd_mainimage_top_image_anchor_item, viewGroup, false));
        }

        public final Drawable l(Context context, int i2) {
            int colorWithTheme = PDUtils.getColorWithTheme(this.a, ContextCompat.getColor(context, R.color.lib_pd_image_color_14000000), ContextCompat.getColor(context, R.color.lib_pd_image_color_14FFFFFF));
            if (this.f5136g) {
                colorWithTheme = PDUtils.getColorWithTheme(this.a, ContextCompat.getColor(context, R.color.lib_pd_image_color_E7E7E7), ContextCompat.getColor(context, R.color.lib_pd_image_color_6B6B6B));
            }
            int dip2px = PDUtils.dip2px(0.5f);
            GradientDrawable a = a(context, i2);
            a.setStroke(dip2px, colorWithTheme);
            LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{a, a(context, i2)});
            if (i2 == 0) {
                layerDrawable.setLayerInset(1, dip2px, dip2px, 0, dip2px);
            } else if (i2 == 1) {
                layerDrawable.setLayerInset(1, 0, dip2px, 0, dip2px);
            } else if (i2 == 2) {
                layerDrawable.setLayerInset(1, 0, dip2px, dip2px, dip2px);
            }
            return layerDrawable;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(@NonNull BigImageAnchorHoder bigImageAnchorHoder, int i2) {
            View view;
            int i3;
            int b;
            int i4;
            BigImageAnchorHoder bigImageAnchorHoder2 = bigImageAnchorHoder;
            List<WareBusinessMagicAnchorEntity> list = this.b;
            WareBusinessMagicAnchorEntity wareBusinessMagicAnchorEntity = (list == null || i2 >= list.size()) ? null : this.b.get(i2);
            if (wareBusinessMagicAnchorEntity == null || bigImageAnchorHoder2 == null || (view = bigImageAnchorHoder2.itemView) == null || view.getContext() == null) {
                return;
            }
            int i5 = 0;
            boolean z = i2 == this.f5133c;
            bigImageAnchorHoder2.a.setText(wareBusinessMagicAnchorEntity.anchorName);
            if (z) {
                i3 = BigImageAnchorAdapter.this.a ? R.drawable.lib_pd_mainimage_anchor_item_dark_bg : R.drawable.lib_pd_mainimage_anchor_item_bg;
            } else {
                i3 = 0;
            }
            BigImageAnchorAdapter bigImageAnchorAdapter = BigImageAnchorAdapter.this;
            if (bigImageAnchorAdapter.f5136g) {
                if (z) {
                    i3 = bigImageAnchorAdapter.a ? R.drawable.lib_pd_mainimage_anchor_item_dark_bg_v12 : R.drawable.lib_pd_mainimage_anchor_item_bg_v12;
                } else {
                    i3 = 0;
                }
            }
            bigImageAnchorHoder2.f5137c.setBackgroundResource(i3);
            if (z) {
                BigImageAnchorAdapter bigImageAnchorAdapter2 = BigImageAnchorAdapter.this;
                if (bigImageAnchorAdapter2.f5136g) {
                    b = bigImageAnchorHoder2.b(bigImageAnchorAdapter2.a, R.color.lib_pd_image_color_1A1A1A, R.color.lib_pd_image_color_1F1F1F);
                } else {
                    b = bigImageAnchorHoder2.b(bigImageAnchorAdapter2.a, R.color.lib_pd_image_color_1A1A1A, R.color.lib_pd_image_color_ececec);
                }
            } else {
                BigImageAnchorAdapter bigImageAnchorAdapter3 = BigImageAnchorAdapter.this;
                if (bigImageAnchorAdapter3.f5136g) {
                    b = bigImageAnchorHoder2.b(bigImageAnchorAdapter3.a, R.color.lib_pd_image_color_595959, R.color.lib_pd_image_color_cccccc);
                } else {
                    b = bigImageAnchorHoder2.b(bigImageAnchorAdapter3.a, R.color.lib_pd_image_848383, R.color.lib_pd_image_color_808080);
                }
            }
            if (BigImageAnchorAdapter.this.f5136g) {
                bigImageAnchorHoder2.a.setTypeface(z ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
                bigImageAnchorHoder2.a.setTextSize(2, 12.0f);
            } else {
                bigImageAnchorHoder2.a.setTypeface(z ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
            }
            bigImageAnchorHoder2.a.setTextColor(b);
            bigImageAnchorHoder2.a.getPaint().setFakeBoldText(z);
            bigImageAnchorHoder2.b.setVisibility(8);
            if (TextUtils.equals(wareBusinessMagicAnchorEntity.type, "details") && !TextUtils.isEmpty(wareBusinessMagicAnchorEntity.arrow)) {
                bigImageAnchorHoder2.b.setVisibility(0);
                JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
                createSimple.showImageOnLoading(17170445);
                createSimple.showImageOnFail(17170445);
                JDImageUtils.displayImage(wareBusinessMagicAnchorEntity.arrow, bigImageAnchorHoder2.b, createSimple);
            }
            bigImageAnchorHoder2.a.setTag(Integer.valueOf(i2));
            bigImageAnchorHoder2.a.setOnClickListener(new k0(this, wareBusinessMagicAnchorEntity));
            ViewGroup.LayoutParams layoutParams = bigImageAnchorHoder2.itemView.getLayoutParams();
            if (i2 == 0) {
                View view2 = bigImageAnchorHoder2.itemView;
                view2.setBackground(l(view2.getContext(), 0));
                i4 = PDUtils.dip2px(10.0f);
            } else {
                if (i2 == getItemCount() - 1) {
                    View view3 = bigImageAnchorHoder2.itemView;
                    view3.setBackground(l(view3.getContext(), 2));
                    i5 = PDUtils.dip2px(10.0f);
                } else {
                    View view4 = bigImageAnchorHoder2.itemView;
                    view4.setBackground(l(view4.getContext(), 1));
                }
                i4 = 0;
            }
            if (layoutParams instanceof RecyclerView.LayoutParams) {
                RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
                ((ViewGroup.MarginLayoutParams) layoutParams2).leftMargin = i4;
                ((ViewGroup.MarginLayoutParams) layoutParams2).rightMargin = i5;
                bigImageAnchorHoder2.itemView.setLayoutParams(layoutParams2);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        @NonNull
        public /* bridge */ /* synthetic */ BigImageAnchorHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
            return h(viewGroup);
        }
    }

    /* loaded from: classes15.dex */
    public interface a {
        void onSelect(int i2, WareBusinessMagicAnchorEntity wareBusinessMagicAnchorEntity);
    }

    public PdMainImageAnchorView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5131i = context;
    }

    @NonNull
    public static HashMap<String, String> a(String str) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            if (!TextUtils.isEmpty(str)) {
                for (Map.Entry<String, Object> entry : JDJSON.parseObject(str).entrySet()) {
                    hashMap.put(entry.getKey(), entry.getValue().toString());
                }
            }
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
        return hashMap;
    }

    @NonNull
    public static HashMap<String, String> b(String str, HashMap<String, String> hashMap) {
        try {
            if (!TextUtils.isEmpty(str)) {
                for (Map.Entry<String, Object> entry : JDJSON.parseObject(str).entrySet()) {
                    hashMap.put(entry.getKey(), entry.getValue().toString());
                }
            }
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
        return hashMap;
    }

    public void c(int i2) {
        BigImageAnchorAdapter bigImageAnchorAdapter = this.f5129g;
        if (bigImageAnchorAdapter != null) {
            bigImageAnchorAdapter.getClass();
            int i3 = 0;
            loop0: while (i3 < bigImageAnchorAdapter.getItemCount()) {
                try {
                    WareBusinessMagicAnchorEntity wareBusinessMagicAnchorEntity = bigImageAnchorAdapter.b.get(i3);
                    if (wareBusinessMagicAnchorEntity != null && wareBusinessMagicAnchorEntity.index == i2) {
                        break;
                    }
                    if (wareBusinessMagicAnchorEntity != null && wareBusinessMagicAnchorEntity.pageIndex != null) {
                        for (int i4 = 0; i4 < wareBusinessMagicAnchorEntity.pageIndex.size(); i4++) {
                            if (wareBusinessMagicAnchorEntity.pageIndex.get(i4).intValue() == i2) {
                                break loop0;
                            }
                        }
                    }
                    i3++;
                } catch (Exception e2) {
                    ExceptionReporter.reportExceptionToBugly(e2);
                }
            }
            i3 = -1;
            int i5 = bigImageAnchorAdapter.f5133c;
            if (i5 == i3) {
                return;
            }
            if (i5 != -1) {
                bigImageAnchorAdapter.f5133c = -1;
                bigImageAnchorAdapter.notifyItemChanged(i5);
            }
            if (i3 != -1) {
                bigImageAnchorAdapter.f5133c = i3;
                bigImageAnchorAdapter.notifyItemChanged(i3);
            }
        }
    }

    public void d(a aVar) {
        this.f5129g.d = aVar;
    }

    public void e(PdMainImagePresenter pdMainImagePresenter) {
        this.f5132j = pdMainImagePresenter;
        BigImageAnchorAdapter bigImageAnchorAdapter = this.f5129g;
        if (bigImageAnchorAdapter != null) {
            bigImageAnchorAdapter.f5135f = pdMainImagePresenter;
        }
    }

    public PdMainImageAnchorView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f5131i = context;
    }
}
