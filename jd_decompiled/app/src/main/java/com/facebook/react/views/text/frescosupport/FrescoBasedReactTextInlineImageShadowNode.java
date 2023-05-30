package com.facebook.react.views.text.frescosupport;

import android.content.Context;
import android.net.Uri;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.text.ReactTextInlineImageShadowNode;
import com.facebook.react.views.text.TextInlineImageSpan;
import com.jd.dynamic.DYConstants;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.Locale;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class FrescoBasedReactTextInlineImageShadowNode extends ReactTextInlineImageShadowNode {
    @Nullable
    private final Object mCallerContext;
    private final AbstractDraweeControllerBuilder mDraweeControllerBuilder;
    private ReadableMap mHeaders;
    @Nullable
    private Uri mUri;
    private float mWidth = Float.NaN;
    private float mHeight = Float.NaN;
    private int mTintColor = 0;

    public FrescoBasedReactTextInlineImageShadowNode(AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, @Nullable Object obj) {
        this.mDraweeControllerBuilder = abstractDraweeControllerBuilder;
        this.mCallerContext = obj;
    }

    @Nullable
    private static Uri getResourceDrawableUri(Context context, @Nullable String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        return new Uri.Builder().scheme(UriUtil.LOCAL_RESOURCE_SCHEME).path(String.valueOf(context.getResources().getIdentifier(str.toLowerCase(Locale.getDefault()).replace("-", CartConstant.KEY_YB_INFO_LINK), "drawable", context.getPackageName()))).build();
    }

    @Override // com.facebook.react.views.text.ReactTextInlineImageShadowNode
    public TextInlineImageSpan buildInlineImageSpan() {
        return new FrescoBasedReactTextInlineImageSpan(getThemedContext().getResources(), (int) Math.ceil(this.mHeight), (int) Math.ceil(this.mWidth), this.mTintColor, getUri(), getHeaders(), getDraweeControllerBuilder(), getCallerContext());
    }

    @Nullable
    public Object getCallerContext() {
        return this.mCallerContext;
    }

    public AbstractDraweeControllerBuilder getDraweeControllerBuilder() {
        return this.mDraweeControllerBuilder;
    }

    public ReadableMap getHeaders() {
        return this.mHeaders;
    }

    @Nullable
    public Uri getUri() {
        return this.mUri;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public boolean isVirtual() {
        return true;
    }

    @ReactProp(name = "headers")
    public void setHeaders(ReadableMap readableMap) {
        this.mHeaders = readableMap;
    }

    @Override // com.facebook.react.uimanager.LayoutShadowNode
    public void setHeight(Dynamic dynamic) {
        if (dynamic.getType() == ReadableType.Number) {
            this.mHeight = (float) dynamic.asDouble();
            return;
        }
        throw new JSApplicationIllegalArgumentException("Inline images must not have percentage based height");
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0022, code lost:
        if (r1.getScheme() == null) goto L16;
     */
    /* JADX WARN: Removed duplicated region for block: B:17:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0036  */
    @com.facebook.react.uimanager.annotations.ReactProp(name = "src")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setSource(@javax.annotation.Nullable com.facebook.react.bridge.ReadableArray r4) {
        /*
            r3 = this;
            r0 = 0
            if (r4 == 0) goto L17
            int r1 = r4.size()
            if (r1 != 0) goto La
            goto L17
        La:
            r1 = 0
            com.facebook.react.bridge.ReadableMap r4 = r4.getMap(r1)
            java.lang.String r1 = "uri"
            java.lang.String r4 = r4.getString(r1)
            goto L18
        L17:
            r4 = r0
        L18:
            if (r4 == 0) goto L32
            android.net.Uri r1 = android.net.Uri.parse(r4)     // Catch: java.lang.Exception -> L27
            java.lang.String r2 = r1.getScheme()     // Catch: java.lang.Exception -> L25
            if (r2 != 0) goto L25
            goto L28
        L25:
            r0 = r1
            goto L28
        L27:
        L28:
            if (r0 != 0) goto L32
            com.facebook.react.uimanager.ThemedReactContext r0 = r3.getThemedContext()
            android.net.Uri r0 = getResourceDrawableUri(r0, r4)
        L32:
            android.net.Uri r4 = r3.mUri
            if (r0 == r4) goto L39
            r3.markUpdated()
        L39:
            r3.mUri = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.frescosupport.FrescoBasedReactTextInlineImageShadowNode.setSource(com.facebook.react.bridge.ReadableArray):void");
    }

    @ReactProp(name = DYConstants.DY_IMAGE_TINT_COLOR)
    public void setTintColor(int i2) {
        this.mTintColor = i2;
    }

    @Override // com.facebook.react.uimanager.LayoutShadowNode
    public void setWidth(Dynamic dynamic) {
        if (dynamic.getType() == ReadableType.Number) {
            this.mWidth = (float) dynamic.asDouble();
            return;
        }
        throw new JSApplicationIllegalArgumentException("Inline images must not have percentage based width");
    }
}
