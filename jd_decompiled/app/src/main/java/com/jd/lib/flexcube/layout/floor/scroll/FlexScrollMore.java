package com.jd.lib.flexcube.layout.floor.scroll;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jd.lib.babel.servicekit.BabelServer;
import com.jd.lib.babel.servicekit.iservice.IFonts;
import com.jd.lib.flexcube.iwidget.b.a;

/* loaded from: classes14.dex */
public class FlexScrollMore extends LinearLayout {
    private Context mContext;

    public FlexScrollMore(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    private void initView() {
        IFonts iFonts;
        setOrientation(0);
        setGravity(17);
        ImageView imageView = new ImageView(this.mContext);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(17, 21));
        addView(imageView);
        if (BabelServer.get() != null && (iFonts = (IFonts) BabelServer.get().getService(IFonts.class)) != null) {
            iFonts.setImageIcon(imageView, "Babel.ttf", "&#xe00b;", a.a("#686868", -12303292));
        }
        TextView textView = new TextView(this.mContext);
        textView.setTextSize(0, 36.0f);
        textView.setText("\u67e5\n\u770b\n\u66f4\n\u591a");
        textView.setGravity(17);
        textView.setTextColor(Color.argb(102, 0, 0, 0));
        addView(textView, new LinearLayout.LayoutParams(50, -1));
    }
}
