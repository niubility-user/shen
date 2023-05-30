package com.facebook.react.uimanager.layoutanimation;

import java.util.Locale;

/* loaded from: classes12.dex */
enum InterpolatorType {
    LINEAR,
    EASE_IN,
    EASE_OUT,
    EASE_IN_EASE_OUT,
    SPRING;

    public static InterpolatorType fromString(String str) {
        String lowerCase = str.toLowerCase(Locale.US);
        lowerCase.hashCode();
        char c2 = '\uffff';
        switch (lowerCase.hashCode()) {
            case -1965056864:
                if (lowerCase.equals("easeout")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1310315117:
                if (lowerCase.equals("easein")) {
                    c2 = 1;
                    break;
                }
                break;
            case -1102672091:
                if (lowerCase.equals("linear")) {
                    c2 = 2;
                    break;
                }
                break;
            case -895679987:
                if (lowerCase.equals("spring")) {
                    c2 = 3;
                    break;
                }
                break;
            case 1164546989:
                if (lowerCase.equals("easeineaseout")) {
                    c2 = 4;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return EASE_OUT;
            case 1:
                return EASE_IN;
            case 2:
                return LINEAR;
            case 3:
                return SPRING;
            case 4:
                return EASE_IN_EASE_OUT;
            default:
                throw new IllegalArgumentException("Unsupported interpolation type : " + str);
        }
    }
}
