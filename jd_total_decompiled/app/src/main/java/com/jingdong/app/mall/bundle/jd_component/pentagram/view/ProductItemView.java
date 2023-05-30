package com.jingdong.app.mall.bundle.jd_component.pentagram.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jingdong.app.mall.bundle.jd_component.R;
import com.jingdong.app.mall.bundle.jd_component.pentagram.entity.ProductForRadar;
import com.jingdong.app.mall.bundle.jd_component.pentagram.utils.PentagramViewUtils;
import com.jingdong.app.mall.bundle.jd_component.pentagram.view.PentagramView;
import com.jingdong.common.jump.OpenAppJumpBuilder;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.List;

/* loaded from: classes2.dex */
public class ProductItemView extends BaseView {
    private ProductForRadar data;
    private PentagramView.Listener listener;
    private TextView name;
    private int position;
    private View view;

    public ProductItemView(@NonNull Context context) {
        super(context);
        this.position = 0;
    }

    public static void jumpOpenApp(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        new OpenAppJumpBuilder.Builder(Uri.parse(str)).build().jump(context);
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.pentagram.view.BaseView
    public void bind(Object obj) {
    }

    public void bindData(ProductForRadar productForRadar, int i2) {
        this.position = i2;
        this.data = productForRadar;
        if (productForRadar != null) {
            this.view.setVisibility(0);
            this.name.setVisibility(0);
            List<String> list = PentagramViewUtils.isDarkConfig() ? productForRadar.darkColors : productForRadar.colors;
            List<Integer> list2 = productForRadar.corners;
            float[] fArr = {DPIUtil.dip2px(2.0f), DPIUtil.dip2px(2.0f), DPIUtil.dip2px(2.0f), DPIUtil.dip2px(2.0f), DPIUtil.dip2px(2.0f), DPIUtil.dip2px(2.0f), DPIUtil.dip2px(2.0f), DPIUtil.dip2px(2.0f)};
            if (list2 != null && list2.size() == 4) {
                fArr = new float[]{DPIUtil.dip2px(list2.get(0).intValue()), DPIUtil.dip2px(list2.get(0).intValue()), DPIUtil.dip2px(list2.get(1).intValue()), DPIUtil.dip2px(list2.get(1).intValue()), DPIUtil.dip2px(list2.get(2).intValue()), DPIUtil.dip2px(list2.get(2).intValue()), DPIUtil.dip2px(list2.get(3).intValue()), DPIUtil.dip2px(list2.get(3).intValue())};
            }
            if (list != null) {
                if (list.size() >= 2) {
                    int[] iArr = new int[list.size()];
                    for (int i3 = 0; i3 < list.size(); i3++) {
                        iArr[i3] = Color.parseColor(list.get(i3));
                    }
                    GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, iArr);
                    gradientDrawable.setCornerRadii(fArr);
                    this.view.setBackgroundDrawable(gradientDrawable);
                } else if (list.size() == 1) {
                    GradientDrawable gradientDrawable2 = new GradientDrawable();
                    gradientDrawable2.setColor(Color.parseColor(list.get(0)));
                    gradientDrawable2.setCornerRadii(fArr);
                    this.view.setBackgroundDrawable(gradientDrawable2);
                }
            }
            String str = PentagramViewUtils.isDarkConfig() ? productForRadar.nameDarkColor : productForRadar.nameColor;
            if (!TextUtils.isEmpty(str)) {
                this.name.setTextColor(Color.parseColor(str));
            }
            this.name.setText((TextUtils.isEmpty(productForRadar.name) || TextUtils.isEmpty(productForRadar.wareJumpUrl)) ? productForRadar.name : productForRadar.name + ">");
            return;
        }
        this.view.setVisibility(8);
        this.name.setVisibility(8);
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.pentagram.view.BaseView
    protected void initView() {
        this.view = findViewById(R.id.lib_pentagram_product_color);
        TextView textView = (TextView) findViewById(R.id.lib_pentagram_product_name);
        this.name = textView;
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.jd_component.pentagram.view.ProductItemView.1
            {
                ProductItemView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ProductItemView.this.listener == null || ProductItemView.this.data == null) {
                    return;
                }
                ProductItemView.this.listener.onProductNameClick(ProductItemView.this.data, ProductItemView.this.position);
            }
        });
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.pentagram.view.BaseView
    protected int layoutId() {
        return R.layout.lib_so_product_item_view;
    }

    @Override // com.jingdong.app.mall.bundle.jd_component.pentagram.view.BaseView
    public void reset(Object obj) {
    }

    public void setListener(PentagramView.Listener listener) {
        this.listener = listener;
    }

    public ProductItemView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.position = 0;
    }

    public ProductItemView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.position = 0;
    }
}
