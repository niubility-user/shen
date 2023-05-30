package jd.wjlogin_sdk.util;

import android.text.TextUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.language.LanguageController;

/* loaded from: classes.dex */
public enum LanguageToast {
    THLanguage100(-100, LanguageController.LANGUAGE_CODE_TH_TH, "\u0e04\u0e33\u0e02\u0e2d\u0e40\u0e04\u0e23\u0e37\u0e2d\u0e02\u0e48\u0e32\u0e22\u0e25\u0e49\u0e21\u0e40\u0e2b\u0e25\u0e27 \u0e42\u0e1b\u0e23\u0e14\u0e15\u0e23\u0e27\u0e08\u0e2a\u0e2d\u0e1a\u0e01\u0e32\u0e23\u0e15\u0e31\u0e49\u0e07\u0e04\u0e48\u0e32\u0e40\u0e04\u0e23\u0e37\u0e2d\u0e02\u0e48\u0e32\u0e22\u0e02\u0e2d\u0e07\u0e04\u0e38\u0e13!"),
    THLanguage101(-101, LanguageController.LANGUAGE_CODE_TH_TH, "\u0e40\u0e04\u0e23\u0e37\u0e2d\u0e02\u0e48\u0e32\u0e22\u0e44\u0e21\u0e48\u0e15\u0e2d\u0e1a\u0e2a\u0e19\u0e2d\u0e07 \u0e01\u0e23\u0e38\u0e13\u0e32\u0e15\u0e23\u0e27\u0e08\u0e2a\u0e2d\u0e1a\u0e41\u0e25\u0e30\u0e25\u0e2d\u0e07\u0e2d\u0e35\u0e01\u0e04\u0e23\u0e31\u0e49\u0e07!"),
    THLanguage102(-102, LanguageController.LANGUAGE_CODE_TH_TH, "\u0e42\u0e2d\u0e4a\u0e30\u0e42\u0e2d \u0e01\u0e32\u0e23\u0e14\u0e33\u0e40\u0e19\u0e34\u0e19\u0e01\u0e32\u0e23\u0e21\u0e35\u0e1b\u0e31\u0e0d\u0e2b\u0e32!"),
    THLanguage103(jd.wjweblogin.d.c.f20052g, LanguageController.LANGUAGE_CODE_TH_TH, "\u0e42\u0e2d\u0e4a\u0e30\u0e42\u0e2d \u0e01\u0e32\u0e23\u0e14\u0e33\u0e40\u0e19\u0e34\u0e19\u0e01\u0e32\u0e23\u0e21\u0e35\u0e1b\u0e31\u0e0d\u0e2b\u0e32!"),
    IDLanguage100(-100, "id_ID", "Ups! Jaringan bermasalah. Silakan periksa jaringan Anda."),
    IDLanguage101(-101, "id_ID", "Ups! Jaringan bermasalah. Silakan periksa jaringan Anda dan coba lagi."),
    IDLanguage102(-102, "id_ID", "Ups! Terjadi kesalahan."),
    IDLanguage103(jd.wjweblogin.d.c.f20052g, "id_ID", "Ups! Terjadi kesalahan."),
    ENLanguage100(-100, LanguageController.LANGUAGE_CODE_EN_US, "Oops! Network request failed. Please check your network settings."),
    ENLanguage101(-101, LanguageController.LANGUAGE_CODE_EN_US, "Oops! Network error. Please check your network and try again."),
    ENLanguage102(-102, LanguageController.LANGUAGE_CODE_EN_US, "Oops, an program error occurs."),
    ENLanguage103(jd.wjweblogin.d.c.f20052g, LanguageController.LANGUAGE_CODE_EN_US, "Oops, an program error occurs."),
    CNLanguage100(-100, LanguageController.LANGUAGE_CODE_ZH_CN, "\u7f51\u7edc\u8bf7\u6c42\u5931\u8d25\uff0c\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc\u8bbe\u7f6e"),
    CNLanguage101(-101, LanguageController.LANGUAGE_CODE_ZH_CN, "\u7f51\u7edc\u5728\u5f00\u5c0f\u5dee\uff0c\u68c0\u67e5\u540e\u518d\u8bd5\u5427"),
    CNLanguage102(-102, LanguageController.LANGUAGE_CODE_ZH_CN, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86"),
    CNLanguage103(jd.wjweblogin.d.c.f20052g, LanguageController.LANGUAGE_CODE_ZH_CN, "\u767b\u5f55\u6392\u961f\u4e2d\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5");
    
    private int code;
    private String language;
    private String toast;

    LanguageToast(int i2, String str, String str2) {
        this.code = i2;
        this.language = str;
        this.toast = str2;
    }

    public static String getToastMsg(int i2) {
        for (LanguageToast languageToast : values()) {
            if (i2 == languageToast.getCode() && isOnlySameLanguageCode(languageToast)) {
                return languageToast.getToast();
            }
        }
        return "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86";
    }

    private static boolean isExactlySameLanguage(LanguageToast languageToast) {
        return languageToast.getLanguage().equalsIgnoreCase(jd.wjlogin_sdk.common.h.a.j());
    }

    private static boolean isOnlySameLanguageCode(LanguageToast languageToast) {
        try {
            String j2 = jd.wjlogin_sdk.common.h.a.j();
            p.b("WJLogin.LanguageToast", "currentLanguage = " + j2);
            if (!TextUtils.isEmpty(j2)) {
                if (languageToast.getLanguage().split(CartConstant.KEY_YB_INFO_LINK)[0].equalsIgnoreCase(j2.split(CartConstant.KEY_YB_INFO_LINK)[0])) {
                    return true;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return false;
    }

    public int getCode() {
        return this.code;
    }

    public String getLanguage() {
        return this.language;
    }

    public String getToast() {
        return this.toast;
    }
}
