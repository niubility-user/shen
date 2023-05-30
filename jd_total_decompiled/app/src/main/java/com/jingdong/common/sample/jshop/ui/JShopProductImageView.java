package com.jingdong.common.sample.jshop.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.R;
import com.jingdong.common.sample.jshop.Entity.JShopStock;
import com.jingdong.common.sample.jshop.utils.JShopStockUtils;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes6.dex */
public class JShopProductImageView extends SimpleDraweeView {
    private static final String TAG = "JShopProductImageView";
    private boolean isSquare;
    private Drawable offShelfDrawable;
    private Drawable soldOutDrawable;
    private JShopStock stock;

    public JShopProductImageView(Context context, GenericDraweeHierarchy genericDraweeHierarchy) {
        super(context, genericDraweeHierarchy);
        this.isSquare = false;
        initDrawable(context);
    }

    private void initDrawable(Context context) {
        this.soldOutDrawable = context.getResources().getDrawable(R.drawable.cart_product_no_stock);
        this.offShelfDrawable = context.getResources().getDrawable(R.drawable.cart_product_sold_off);
    }

    public boolean isSquare() {
        return this.isSquare;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        Drawable drawable;
        super.onDraw(canvas);
        JShopStock jShopStock = this.stock;
        if (jShopStock == JShopStock.SOLDOUT && (drawable = this.soldOutDrawable) != null) {
            if (Log.D) {
                Log.d(TAG, "draw soldOut drawable");
            }
        } else if (jShopStock == JShopStock.OFFSHELF && (drawable = this.offShelfDrawable) != null) {
            if (Log.D) {
                Log.d(TAG, "draw offShelf drawable");
            }
        } else {
            if (Log.D) {
                Log.d(TAG, "draw only super");
            }
            drawable = null;
        }
        if (drawable != null) {
            int measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int i2 = (measuredWidth - intrinsicWidth) >> 1;
            int i3 = (measuredHeight - intrinsicHeight) >> 1;
            if (Log.D) {
                Log.d(TAG, String.format("bound [%d, %d, %d, %d] - space[%d, %d ]", Integer.valueOf(intrinsicWidth), Integer.valueOf(intrinsicHeight), Integer.valueOf(measuredWidth), Integer.valueOf(measuredHeight), Integer.valueOf(i2), Integer.valueOf(i3)));
            }
            drawable.setBounds(i2, i3, intrinsicWidth + i2, intrinsicHeight + i3);
            drawable.draw(canvas);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.drawee.view.DraweeView, android.widget.ImageView, android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
    }

    public void setSquare(boolean z) {
        this.isSquare = z;
    }

    public void setStock(JShopStock jShopStock) {
        this.stock = jShopStock;
        JShopStockUtils.setDrawableAlphaWithStockState(getDrawable(), jShopStock, true);
    }

    public JShopProductImageView(Context context) {
        super(context);
        this.isSquare = false;
        initDrawable(context);
    }

    public JShopProductImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isSquare = false;
        initDrawable(context);
    }

    public JShopProductImageView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.isSquare = false;
        initDrawable(context);
    }

    public JShopProductImageView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.isSquare = false;
        initDrawable(context);
    }
}
