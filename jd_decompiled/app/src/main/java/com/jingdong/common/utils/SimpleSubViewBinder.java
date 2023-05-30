package com.jingdong.common.utils;

import android.net.Uri;
import android.view.View;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.utils.SimpleBeanAdapter;
import com.jingdong.common.utils.adapter.SimpleImageProcessor;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes6.dex */
public class SimpleSubViewBinder implements SubViewBinder {
    private JDDisplayImageOptions imageOptions;
    private SimpleImageProcessor imageProcessor;

    public SimpleSubViewBinder(SimpleImageProcessor simpleImageProcessor) {
        this.imageProcessor = simpleImageProcessor;
    }

    @Override // com.jingdong.common.utils.SubViewBinder
    public boolean bind(SimpleBeanAdapter.SubViewHolder subViewHolder) {
        if (OKLog.D) {
            OKLog.d(SimpleSubViewBinder.class.getName(), "bind() -->> ");
        }
        if (subBind(subViewHolder)) {
            return true;
        }
        View subView = subViewHolder.getSubView();
        Object subData = subViewHolder.getSubData();
        if ((subView instanceof Checkable) && (subData instanceof Boolean)) {
            if (OKLog.D) {
                OKLog.d(SimpleSubViewBinder.class.getName(), "bind() checkable -->> ");
            }
            ((Checkable) subView).setChecked(((Boolean) subData).booleanValue());
            return true;
        } else if ((subView instanceof TextView) && (subData instanceof CharSequence)) {
            if (OKLog.D) {
                OKLog.d(SimpleSubViewBinder.class.getName(), "bind() text -->> ");
            }
            ((TextView) subView).setText((CharSequence) subData);
            return true;
        } else {
            if (subView instanceof ImageView) {
                if (subData instanceof Integer) {
                    if (OKLog.D) {
                        OKLog.d(SimpleSubViewBinder.class.getName(), "bind() image id 1 -->> ");
                    }
                    ((ImageView) subView).setImageResource(((Integer) subData).intValue());
                    return true;
                } else if (subData instanceof String) {
                    try {
                        if (OKLog.D) {
                            OKLog.d(SimpleSubViewBinder.class.getName(), "bind() image id 2 -->> ");
                        }
                        ((ImageView) subView).setImageResource(Integer.parseInt((String) subData));
                        return true;
                    } catch (NumberFormatException unused) {
                        String str = (String) subData;
                        if (!str.startsWith("http")) {
                            if (OKLog.D) {
                                OKLog.d(SimpleSubViewBinder.class.getName(), "bind() image uri -->> ");
                            }
                            ((ImageView) subView).setImageURI(Uri.parse(str));
                            return true;
                        }
                        if (OKLog.D) {
                            OKLog.d(SimpleSubViewBinder.class.getName(), "bind() image url -->> ");
                        }
                        JDDisplayImageOptions jDDisplayImageOptions = this.imageOptions;
                        if (jDDisplayImageOptions == null) {
                            JDImageUtils.displayImage(str, (ImageView) subView);
                        } else {
                            JDImageUtils.displayImage(str, (ImageView) subView, jDDisplayImageOptions);
                        }
                        return true;
                    }
                }
            }
            if (OKLog.D) {
                OKLog.d(SimpleSubViewBinder.class.getName(), "bind() return false -->> ");
                return false;
            }
            return false;
        }
    }

    public SimpleImageProcessor getSimpleImageProcessor() {
        return this.imageProcessor;
    }

    protected boolean subBind(SimpleBeanAdapter.SubViewHolder subViewHolder) {
        return false;
    }

    public SimpleSubViewBinder(SimpleImageProcessor simpleImageProcessor, JDDisplayImageOptions jDDisplayImageOptions) {
        this.imageProcessor = simpleImageProcessor;
        this.imageOptions = jDDisplayImageOptions;
    }
}
