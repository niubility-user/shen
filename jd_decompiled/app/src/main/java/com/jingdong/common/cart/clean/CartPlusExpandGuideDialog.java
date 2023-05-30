package com.jingdong.common.cart.clean;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.R;
import com.jingdong.common.utils.JDImageUtils;

/* loaded from: classes5.dex */
public class CartPlusExpandGuideDialog extends Dialog {
    SimpleDraweeView closeImg;
    SimpleDraweeView guideImg;

    public CartPlusExpandGuideDialog(Context context, String str) {
        super(context, R.style.cartPlusExpandGuideDialog);
        setContentView(R.layout.lib_cart_plus_expand_guide_dialog);
        this.guideImg = (SimpleDraweeView) findViewById(R.id.cart_plus_expand_guide_img);
        this.closeImg = (SimpleDraweeView) findViewById(R.id.cart_plus_expand_close_img);
        if (!TextUtils.isEmpty(str)) {
            JDImageUtils.displayImage(str, this.guideImg, new JDDisplayImageOptions());
        }
        initFullWindow();
        setListener();
    }

    private void setListener() {
        this.closeImg.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.cart.clean.CartPlusExpandGuideDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CartPlusExpandGuideDialog.this.dismiss();
            }
        });
    }

    protected void initFullWindow() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.height = -1;
        attributes.width = -1;
    }
}
