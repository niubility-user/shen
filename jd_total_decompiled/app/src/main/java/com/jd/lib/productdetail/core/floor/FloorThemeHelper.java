package com.jd.lib.productdetail.core.floor;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.jd.lib.productdetail.core.R;
import com.jingdong.common.unification.uniconfig.UnIconConfigHelper;

/* loaded from: classes15.dex */
public class FloorThemeHelper {
    private static int JXFrameTextBackground;
    private static int JXIndicatorViewBackground;
    private static int JXThemeBtnColor;
    private static int JXThemeBtnHightLightColor;
    private static int JXThemeBtnTitleColor;
    private static int JXThemeColor = R.color.lib_pd_core_color_c09947;
    private static int JXThemePriceColor = R.color.lib_pd_core_black;
    private static int normalFrameTextBackground;
    private static int normalIndicatorViewBackground;
    private static int normalThemeAdColor;
    private static int normalThemeBtnColor;
    private static int normalThemeBtnHighLightColor;
    private static int normalThemeBtnTitleColor;
    private static int normalThemeColor;

    /* renamed from: com.jd.lib.productdetail.core.floor.FloorThemeHelper$1  reason: invalid class name */
    /* loaded from: classes15.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$jd$lib$productdetail$core$floor$FloorThemeEnum;

        static {
            int[] iArr = new int[FloorThemeEnum.values().length];
            $SwitchMap$com$jd$lib$productdetail$core$floor$FloorThemeEnum = iArr;
            try {
                iArr[FloorThemeEnum.FEATURE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$jd$lib$productdetail$core$floor$FloorThemeEnum[FloorThemeEnum.NORMAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    static {
        int i2 = R.color.lib_pd_core_color_efc532;
        JXThemeBtnColor = i2;
        JXThemeBtnHightLightColor = i2;
        JXThemeBtnTitleColor = R.color.lib_pd_core_white;
        JXFrameTextBackground = R.drawable.lib_pd_core_c09947_corners;
        JXIndicatorViewBackground = R.drawable.lib_pd_core_dot_yellow;
        int i3 = R.color.lib_pd_core_red;
        normalThemeColor = i3;
        normalThemeAdColor = R.color.lib_pd_core_common_black;
        normalThemeBtnColor = i3;
        normalThemeBtnHighLightColor = R.color.lib_pd_core_red_d;
        normalThemeBtnTitleColor = R.color.lib_pd_core_color_333333;
        normalFrameTextBackground = com.jingdong.common.R.drawable.label_15;
        normalIndicatorViewBackground = R.drawable.lib_pd_core_dog_red;
    }

    public int getCurrentThemeColor(Context context, FloorThemeEnum floorThemeEnum) {
        int i2 = AnonymousClass1.$SwitchMap$com$jd$lib$productdetail$core$floor$FloorThemeEnum[floorThemeEnum.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                return ContextCompat.getColor(context, normalThemeColor);
            }
            return ContextCompat.getColor(context, normalThemeColor);
        }
        return ContextCompat.getColor(context, JXThemeColor);
    }

    public void setAdTextViewColor(Context context, FloorThemeEnum floorThemeEnum, TextView... textViewArr) {
        if (textViewArr == null) {
            return;
        }
        for (TextView textView : textViewArr) {
            if (textView != null) {
                int i2 = AnonymousClass1.$SwitchMap$com$jd$lib$productdetail$core$floor$FloorThemeEnum[floorThemeEnum.ordinal()];
                if (i2 == 1) {
                    textView.setTextColor(ContextCompat.getColor(context, JXThemeColor));
                } else if (i2 == 2) {
                    textView.setTextColor(ContextCompat.getColor(context, normalThemeAdColor));
                }
            }
        }
    }

    public void setFramePriceTextColor(Context context, FloorThemeEnum floorThemeEnum, TextView... textViewArr) {
        if (textViewArr == null) {
            return;
        }
        for (TextView textView : textViewArr) {
            if (textView != null) {
                int i2 = AnonymousClass1.$SwitchMap$com$jd$lib$productdetail$core$floor$FloorThemeEnum[floorThemeEnum.ordinal()];
                if (i2 == 1) {
                    textView.setTextColor(ContextCompat.getColor(context, JXThemePriceColor));
                    textView.setBackgroundResource(JXFrameTextBackground);
                } else if (i2 == 2) {
                    textView.setTextColor(ContextCompat.getColor(context, normalThemeColor));
                    textView.setBackgroundResource(normalFrameTextBackground);
                }
            }
        }
    }

    public void setFrameTextViewTheme(Context context, FloorThemeEnum floorThemeEnum, TextView... textViewArr) {
        if (textViewArr == null) {
            return;
        }
        for (TextView textView : textViewArr) {
            if (textView != null) {
                int i2 = AnonymousClass1.$SwitchMap$com$jd$lib$productdetail$core$floor$FloorThemeEnum[floorThemeEnum.ordinal()];
                if (i2 == 1) {
                    textView.setTextColor(ContextCompat.getColor(context, JXThemeColor));
                    textView.setBackgroundResource(JXFrameTextBackground);
                } else if (i2 == 2) {
                    textView.setTextColor(ContextCompat.getColor(context, normalThemeColor));
                    textView.setBackgroundResource(normalFrameTextBackground);
                }
            }
        }
    }

    public void setIndicatorViewBackground(FloorThemeEnum floorThemeEnum, View... viewArr) {
        if (viewArr == null) {
            return;
        }
        for (View view : viewArr) {
            if (view != null) {
                int i2 = AnonymousClass1.$SwitchMap$com$jd$lib$productdetail$core$floor$FloorThemeEnum[floorThemeEnum.ordinal()];
                if (i2 == 1) {
                    view.setBackgroundResource(JXIndicatorViewBackground);
                } else if (i2 == 2) {
                    view.setBackgroundResource(normalIndicatorViewBackground);
                }
            }
        }
    }

    public void setPriceTextColor(Context context, FloorThemeEnum floorThemeEnum, TextView... textViewArr) {
        if (textViewArr == null) {
            return;
        }
        for (TextView textView : textViewArr) {
            if (textView != null) {
                int i2 = AnonymousClass1.$SwitchMap$com$jd$lib$productdetail$core$floor$FloorThemeEnum[floorThemeEnum.ordinal()];
                if (i2 == 1) {
                    textView.setTextColor(ContextCompat.getColor(context, JXThemePriceColor));
                } else if (i2 == 2) {
                    textView.setTextColor(ContextCompat.getColor(context, normalThemeColor));
                }
            }
        }
    }

    public void setPromotionFrameTextViewTheme(Context context, boolean z, FloorThemeEnum floorThemeEnum, TextView... textViewArr) {
        if (textViewArr == null) {
            return;
        }
        for (TextView textView : textViewArr) {
            if (textView != null) {
                int i2 = AnonymousClass1.$SwitchMap$com$jd$lib$productdetail$core$floor$FloorThemeEnum[floorThemeEnum.ordinal()];
                if (i2 != 1) {
                    if (i2 == 2) {
                        if (z) {
                            textView.setTextColor(ContextCompat.getColor(context, JXThemeColor));
                            ViewCompat.setBackground(textView, UnIconConfigHelper.getDrawable("tab_var_124"));
                        } else {
                            textView.setTextColor(ContextCompat.getColor(context, normalThemeColor));
                            ViewCompat.setBackground(textView, UnIconConfigHelper.getDrawable("tab_var_071"));
                        }
                    }
                } else if (z) {
                    textView.setTextColor(ContextCompat.getColor(context, JXThemeColor));
                    ViewCompat.setBackground(textView, UnIconConfigHelper.getDrawable("tab_var_126"));
                } else {
                    textView.setTextColor(ContextCompat.getColor(context, JXThemeColor));
                    ViewCompat.setBackground(textView, UnIconConfigHelper.getDrawable("tab_var_126"));
                }
            }
        }
    }

    public void setRectIndicatorViewBackground(FloorThemeEnum floorThemeEnum, View... viewArr) {
        if (viewArr == null) {
            return;
        }
        for (View view : viewArr) {
            if (view != null) {
                int i2 = AnonymousClass1.$SwitchMap$com$jd$lib$productdetail$core$floor$FloorThemeEnum[floorThemeEnum.ordinal()];
                if (i2 == 1) {
                    view.setBackgroundResource(R.color.lib_pd_core_color_c09947);
                } else if (i2 == 2) {
                    view.setBackgroundResource(R.color.lib_pd_core_red);
                }
            }
        }
    }

    public void setSuitFrameTextViewTheme(Context context, FloorThemeEnum floorThemeEnum, TextView... textViewArr) {
        if (textViewArr == null) {
            return;
        }
        for (TextView textView : textViewArr) {
            if (textView != null) {
                int i2 = AnonymousClass1.$SwitchMap$com$jd$lib$productdetail$core$floor$FloorThemeEnum[floorThemeEnum.ordinal()];
                if (i2 == 1) {
                    textView.setTextColor(ContextCompat.getColor(context, JXThemeColor));
                    ViewCompat.setBackground(textView, UnIconConfigHelper.getDrawable("tab_var_126"));
                } else if (i2 == 2) {
                    textView.setTextColor(ContextCompat.getColor(context, normalThemeColor));
                    ViewCompat.setBackground(textView, UnIconConfigHelper.getDrawable("tab_var_071"));
                }
            }
        }
    }

    public void setTextViewColor(Context context, FloorThemeEnum floorThemeEnum, TextView... textViewArr) {
        if (textViewArr == null) {
            return;
        }
        for (TextView textView : textViewArr) {
            if (textView != null) {
                int i2 = AnonymousClass1.$SwitchMap$com$jd$lib$productdetail$core$floor$FloorThemeEnum[floorThemeEnum.ordinal()];
                if (i2 == 1) {
                    textView.setTextColor(ContextCompat.getColor(context, JXThemeColor));
                } else if (i2 == 2) {
                    textView.setTextColor(ContextCompat.getColor(context, normalThemeColor));
                }
            }
        }
    }
}
