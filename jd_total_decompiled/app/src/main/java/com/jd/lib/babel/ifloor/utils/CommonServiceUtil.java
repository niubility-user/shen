package com.jd.lib.babel.ifloor.utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.entity.BaseFloorModel;
import com.jd.lib.babel.ifloor.net.RequestBuilder;
import com.jd.lib.babel.servicekit.BabelServer;
import com.jd.lib.babel.servicekit.imagekit.ImageArr;
import com.jd.lib.babel.servicekit.imagekit.ImageLoad;
import com.jd.lib.babel.servicekit.imagekit.ImageWraper;
import com.jd.lib.babel.servicekit.iservice.BabelCartCallback;
import com.jd.lib.babel.servicekit.iservice.BabelLoginCallback;
import com.jd.lib.babel.servicekit.iservice.ICart;
import com.jd.lib.babel.servicekit.iservice.IClick;
import com.jd.lib.babel.servicekit.iservice.IEvent;
import com.jd.lib.babel.servicekit.iservice.IFrame;
import com.jd.lib.babel.servicekit.iservice.ILogin;
import com.jd.lib.babel.servicekit.iservice.IParser;
import com.jd.lib.babel.servicekit.model.BabelJumpEntity;
import com.jd.lib.babel.servicekit.model.CartSummary;
import com.jd.lib.babel.servicekit.model.MtaData;
import com.jd.lib.babel.servicekit.util.BabelServiceUtils;
import java.util.List;

/* loaded from: classes13.dex */
public class CommonServiceUtil {
    public static void addRequest(Context context, RequestBuilder requestBuilder) {
        BabelServer.get().getNetWorkKitServer().addRequest(context, requestBuilder.build());
    }

    public static void addSingleProductToCartWithToast(Context context, String str, BabelCartCallback babelCartCallback) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Activity activity = null;
        if (context instanceof Activity) {
            activity = (Activity) context;
        } else {
            IFrame iFrame = (IFrame) getService(IFrame.class);
            if (iFrame != null) {
                activity = iFrame.getActivity(context);
            }
        }
        if (activity == null) {
            return;
        }
        CartSummary cartSummary = new CartSummary(str);
        ICart iCart = (ICart) getService(ICart.class);
        if (iCart != null) {
            iCart.addSingleProductToCartWithToast(activity, cartSummary, babelCartCallback);
        }
    }

    public static void displayImage(String str, ImageWraper imageWraper) {
        ImageLoad.with(imageWraper).needImageOnFail(true).needImageOnLoading(true).load(str);
    }

    public static void displayImageWithScale(String str, ImageView imageView, boolean z) {
        ImageLoad.with(imageView).needImageOnFail(true).needImageOnLoading(true).useOption(true).isScale(z).load(str);
    }

    public static void execJump(Context context, BabelJumpEntity babelJumpEntity) {
        IClick iClick = (IClick) getService(IClick.class);
        if (iClick != null) {
            iClick.execJump(context, babelJumpEntity);
        }
    }

    public static void executeLogin(Context context, BabelLoginCallback babelLoginCallback) {
        ILogin iLogin = (ILogin) getService(ILogin.class);
        if (iLogin != null) {
            iLogin.executeLogin(context, babelLoginCallback);
        }
    }

    public static ImageView getImageView(Context context) {
        return ImageLoad.newImageView(context);
    }

    public static <S> S getService(@NonNull Class<S> cls) {
        return (S) BabelServer.get().getService(cls);
    }

    public static boolean hasLogin() {
        return BabelServiceUtils.hasLogin();
    }

    public static void onClickMta(Context context, MtaData mtaData) {
        IEvent iEvent = (IEvent) getService(IEvent.class);
        if (iEvent != null) {
            iEvent.onClickMta(context, mtaData);
        }
    }

    public static <T> List<T> parseArray(String str, Class<T> cls) {
        IParser iParser = (IParser) getService(IParser.class);
        if (iParser != null) {
            return iParser.parseArray(str, cls);
        }
        return null;
    }

    public static <T extends BaseFloorModel> T parseFloorModel(String str, Class<T> cls) {
        IParser iParser = (IParser) getService(IParser.class);
        if (iParser != null) {
            return (T) iParser.parseObject(str, cls);
        }
        return null;
    }

    public static <T> T parseObject(String str, Class<T> cls) {
        IParser iParser = (IParser) getService(IParser.class);
        if (iParser != null) {
            return (T) iParser.parseObject(str, cls);
        }
        return null;
    }

    public static void sendExposureData(BabelScope babelScope, String str, String str2) {
        if (babelScope == null || babelScope.iFloorUI == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        babelScope.iFloorUI.sendExposureData(MtaData.Builder.from(str, str2).build());
    }

    public static void displayImage(String str, ImageView imageView) {
        ImageLoad.with(imageView).needImageOnFail(true).needImageOnLoading(true).load(str);
    }

    public static void displayImage(ImageArr imageArr, ImageView imageView) {
        ImageLoad.with(imageView).load(imageArr);
    }
}
