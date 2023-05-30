package com.jd.cashier.app.jdlibcutter.impl.ui.title;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.jd.cashier.app.jdlibcutter.protocol.ui.title.IThemeTitle;
import com.jingdong.common.unification.title.theme.JdThemeTitle;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;

/* loaded from: classes13.dex */
public class JDThemeTitleImpl implements IThemeTitle {
    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.title.IThemeTitle
    public TextView getRightTv1TextView(View view) {
        if (view instanceof JdThemeTitle) {
            return ((JdThemeTitle) view).getRight1TextView();
        }
        return null;
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.title.IThemeTitle
    public TextView getTitleView(View view) {
        if (view instanceof JdThemeTitle) {
            return ((JdThemeTitle) view).getTitleTextView();
        }
        return null;
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.title.IThemeTitle
    public View instanceTitleView(Context context) {
        JdThemeTitle jdThemeTitle = new JdThemeTitle(context);
        jdThemeTitle.setModuleId(ThemeTitleConstant.CHECKOUT_MODULE_ID);
        jdThemeTitle.setUseGrayBg(true);
        jdThemeTitle.setAutoDarkMode(true);
        jdThemeTitle.setStatusBarHint(true);
        return jdThemeTitle;
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.title.IThemeTitle
    public boolean isThemeLightStyleColor(View view) {
        if (view instanceof JdThemeTitle) {
            return TextUtils.equals(((JdThemeTitle) view).getColorStyle(), "LIGHT");
        }
        return false;
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.title.IThemeTitle
    public void loadTheme(View view) {
        if (view instanceof JdThemeTitle) {
            ((JdThemeTitle) view).loadTheme();
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.title.IThemeTitle
    public void notifyChange(View view) {
        if (view instanceof JdThemeTitle) {
            ((JdThemeTitle) view).notifyChange();
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.title.IThemeTitle
    public void onHandDarkMode(View view) {
        if (view instanceof JdThemeTitle) {
            JdThemeTitle jdThemeTitle = (JdThemeTitle) view;
            jdThemeTitle.setDarkMode(false);
            jdThemeTitle.setAutoDarkMode(false);
            jdThemeTitle.loadTheme();
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.title.IThemeTitle
    public void setCustomOpen(View view, boolean z) {
        if (view instanceof JdThemeTitle) {
            ((JdThemeTitle) view).setCustomOpen(z);
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.title.IThemeTitle
    public void setLeft1BackDrawableId(View view) {
        if (view instanceof JdThemeTitle) {
            ((JdThemeTitle) view).setLeft1DrawableId(ThemeTitleConstant.TITLE_BACK_DRAWABLE_ID);
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.title.IThemeTitle
    public void setLeft1ImageViewVisibility(View view, int i2) {
        ImageView left1ImageView;
        if (!(view instanceof JdThemeTitle) || (left1ImageView = ((JdThemeTitle) view).getLeft1ImageView()) == null) {
            return;
        }
        left1ImageView.setVisibility(i2);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.title.IThemeTitle
    public void setLeftIv1ClickListener(View view, View.OnClickListener onClickListener) {
        if (view instanceof JdThemeTitle) {
            ((JdThemeTitle) view).setLeftIv1ClickListener(onClickListener);
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.title.IThemeTitle
    public void setRightTv1ClickListener(View view, View.OnClickListener onClickListener) {
        if (view instanceof JdThemeTitle) {
            ((JdThemeTitle) view).setRightTv1ClickListener(onClickListener);
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.title.IThemeTitle
    public void setRightTv1Text(View view, String str) {
        if (view instanceof JdThemeTitle) {
            ((JdThemeTitle) view).setRightTv1Text(str);
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.title.IThemeTitle
    public void setRightTv1Visibility(View view, int i2) {
        if (view instanceof JdThemeTitle) {
            ((JdThemeTitle) view).setRightTv1Visibility(i2);
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.title.IThemeTitle
    public void setStatusBarColorStyle(View view, Activity activity) {
        if (!(view instanceof JdThemeTitle) || activity == null) {
            return;
        }
        ((JdThemeTitle) view).setStatusBarColorStyle(activity);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.title.IThemeTitle
    public void setStatusBarHint(View view, boolean z) {
        if (view instanceof JdThemeTitle) {
            ((JdThemeTitle) view).setStatusBarHint(z);
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.title.IThemeTitle
    public void setTitleBgImageViewAlpha(View view, float f2) {
        ImageView titleBgImageView;
        if (!(view instanceof JdThemeTitle) || (titleBgImageView = ((JdThemeTitle) view).getTitleBgImageView()) == null) {
            return;
        }
        titleBgImageView.setAlpha(f2);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.title.IThemeTitle
    public void setTitleText(View view, String str) {
        if (view instanceof JdThemeTitle) {
            ((JdThemeTitle) view).setTitleText(str);
        }
    }
}
