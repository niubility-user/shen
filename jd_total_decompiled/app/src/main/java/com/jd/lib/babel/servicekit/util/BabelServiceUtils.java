package com.jd.lib.babel.servicekit.util;

import android.content.Context;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.jd.lib.babel.servicekit.BabelServer;
import com.jd.lib.babel.servicekit.imagekit.ImageLoad;
import com.jd.lib.babel.servicekit.iservice.BabelLoginCallback;
import com.jd.lib.babel.servicekit.iservice.IClick;
import com.jd.lib.babel.servicekit.iservice.IEvent;
import com.jd.lib.babel.servicekit.iservice.ILanguage;
import com.jd.lib.babel.servicekit.iservice.ILogin;
import com.jd.lib.babel.servicekit.iservice.IParser;
import com.jd.lib.babel.servicekit.model.BabelJumpEntity;
import com.jd.lib.babel.servicekit.model.MtaData;
import java.util.List;

/* loaded from: classes13.dex */
public class BabelServiceUtils {
    public static void displayImage(String str, ImageView imageView) {
        ImageLoad.with(imageView).needImageOnFail(true).needImageOnLoading(true).load(str);
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

    public static String getLanguage() {
        ILanguage iLanguage = (ILanguage) getService(ILanguage.class);
        if (iLanguage != null) {
            return iLanguage.getLanguage();
        }
        return null;
    }

    public static <S> S getService(@NonNull Class<S> cls) {
        return (S) BabelServer.get().getService(cls);
    }

    public static boolean hasLogin() {
        ILogin iLogin = (ILogin) getService(ILogin.class);
        if (iLogin != null) {
            return iLogin.hasLogin();
        }
        return false;
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

    public static <T> T parseObject(String str, Class<T> cls) {
        IParser iParser = (IParser) getService(IParser.class);
        if (iParser != null) {
            return (T) iParser.parseObject(str, cls);
        }
        return null;
    }
}
