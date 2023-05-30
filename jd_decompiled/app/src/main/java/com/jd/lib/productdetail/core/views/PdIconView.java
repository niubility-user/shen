package com.jd.lib.productdetail.core.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.productdetail.core.R;
import com.jd.lib.productdetail.core.entitys.ProductDetailEntity;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.unification.uniconfig.UnIconConfigHelper;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.math.BigDecimal;
import java.math.RoundingMode;

/* loaded from: classes15.dex */
public class PdIconView extends SimpleDraweeView implements JDImageLoadingListener {
    private boolean isInited;
    String mDefaultIconId;
    int mIconHeight;
    int mIconWidth;
    private IListener mListener;

    /* loaded from: classes15.dex */
    public interface IListener {
        void onFailed(View view, String str);

        void onSuc(View view, String str);
    }

    public PdIconView(@NonNull Context context) {
        this(context, null);
    }

    private boolean adjustIcon(String str, boolean z, boolean z2, int i2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return adjustSize(UnIconConfigHelper.getBitmap(str, z2, i2), z);
    }

    private void adjustImage(String str, boolean z) {
        setAdjustViewBounds(false);
        JDImageUtils.displayImage(str, this, this);
    }

    private boolean adjustSize(Bitmap bitmap, boolean z) {
        int i2;
        int i3;
        if (bitmap != null) {
            BigDecimal valueOf = BigDecimal.valueOf(bitmap.getWidth());
            BigDecimal valueOf2 = BigDecimal.valueOf(bitmap.getHeight());
            if (!valueOf2.equals(BigDecimal.valueOf(0L))) {
                double doubleValue = valueOf.divide(valueOf2, 2, RoundingMode.HALF_UP).doubleValue();
                if (z) {
                    i3 = this.mIconWidth;
                    double d = i3;
                    Double.isNaN(d);
                    i2 = (int) (d / doubleValue);
                } else {
                    int i4 = this.mIconHeight;
                    double d2 = i4;
                    Double.isNaN(d2);
                    i2 = i4;
                    i3 = (int) (d2 * doubleValue);
                }
                try {
                    setImageBitmap(Bitmap.createScaledBitmap(bitmap, i3, i2, true));
                } catch (Exception e2) {
                    ExceptionReporter.reportExceptionToBugly(e2);
                }
            }
            return true;
        }
        return false;
    }

    private boolean isDefaultHeight() {
        return -2 == this.mIconHeight;
    }

    private boolean isDefaultWidth() {
        return this.mIconWidth == -2;
    }

    private void loadParamInfo() {
        if ((!this.isInited) != false) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            this.mIconHeight = layoutParams.height;
            this.mIconWidth = layoutParams.width;
        }
        this.isInited = true;
    }

    private void setImage(String str) {
        if (isDefaultHeight() && isDefaultWidth()) {
            JDImageUtils.displayImage(str, this, this);
        } else if (isDefaultHeight()) {
            adjustImage(str, true);
        } else if (isDefaultWidth()) {
            adjustImage(str, false);
        } else {
            JDImageUtils.displayImage(str, this, this);
        }
    }

    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
    public void onLoadingCancelled(String str, View view) {
    }

    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
        IListener iListener = this.mListener;
        if (iListener != null) {
            iListener.onSuc(view, str);
        }
    }

    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
    public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
        setVisibility(8);
        IListener iListener = this.mListener;
        if (iListener != null) {
            iListener.onFailed(view, str);
        }
    }

    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
    public void onLoadingStarted(String str, View view) {
    }

    public void setIcon(String str, String str2, ProductDetailEntity productDetailEntity) {
        if (productDetailEntity == null) {
            return;
        }
        setIcon(str, str2, productDetailEntity.isElderTheme(), PDUtils.stringToInt(productDetailEntity.getPdCurrentMode()));
    }

    public void setLoadListener(IListener iListener) {
        this.mListener = iListener;
    }

    public PdIconView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @SuppressLint({"ResourceType"})
    public PdIconView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PdIconView);
        if (obtainStyledAttributes != null) {
            this.mDefaultIconId = obtainStyledAttributes.getString(R.styleable.PdIconView_icon_id);
            obtainStyledAttributes.recycle();
        }
        if (TextUtils.isEmpty(this.mDefaultIconId)) {
            return;
        }
        setIcon(this.mDefaultIconId, false, 0);
    }

    public void setIcon(String str, String str2, boolean z, int i2) {
        loadParamInfo();
        boolean icon = !TextUtils.isEmpty(str) ? setIcon(str, z, i2) : false;
        if (icon) {
            IListener iListener = this.mListener;
            if (iListener != null) {
                iListener.onSuc(this, str);
            }
        } else if (!TextUtils.isEmpty(str2)) {
            setImage(str2);
            icon = true;
        } else {
            IListener iListener2 = this.mListener;
            if (iListener2 != null) {
                iListener2.onFailed(this, str);
            }
        }
        setVisibility(icon ? 0 : 8);
    }

    public boolean setIcon(String str, boolean z, int i2) {
        loadParamInfo();
        if (isDefaultHeight() && isDefaultWidth()) {
            Drawable drawable = UnIconConfigHelper.getDrawable(str, z, i2);
            if (drawable == null) {
                return false;
            }
            setImageDrawable(drawable);
        } else if (isDefaultHeight()) {
            return adjustIcon(str, true, z, i2);
        } else {
            if (isDefaultWidth()) {
                return adjustIcon(str, false, z, i2);
            }
            Drawable drawable2 = UnIconConfigHelper.getDrawable(str, z, i2);
            if (drawable2 == null) {
                return false;
            }
            setImageDrawable(drawable2);
        }
        return true;
    }
}
