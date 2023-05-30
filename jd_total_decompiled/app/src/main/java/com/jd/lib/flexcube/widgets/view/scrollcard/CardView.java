package com.jd.lib.flexcube.widgets.view.scrollcard;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import com.jd.lib.babel.servicekit.imagekit.ImageLoad;
import com.jd.lib.flexcube.R;
import com.jd.lib.flexcube.iwidget.b.a;
import com.jd.lib.flexcube.iwidget.b.b;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;
import com.jd.lib.flexcube.iwidget.entity.material.ClickEvent;
import com.jd.lib.flexcube.iwidget.ui.IWidget;
import com.jd.lib.flexcube.iwidget.ui.IWidgetCommunication;
import com.jd.lib.flexcube.pool.WidgetPool;
import com.jd.lib.flexcube.widgets.b.e;
import com.jd.lib.flexcube.widgets.b.f;
import com.jd.lib.flexcube.widgets.c.a;
import com.jd.lib.flexcube.widgets.entity.ScrollCardEntity;
import com.jd.lib.flexcube.widgets.entity.text.CardConfig;
import com.jd.lib.flexcube.widgets.entity.text.ScrollCardDataPath;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class CardView extends FrameLayout implements IWidget<ScrollCardEntity> {

    /* renamed from: g */
    private e f4512g;

    /* renamed from: h */
    private CardConfig f4513h;

    /* renamed from: i */
    private Context f4514i;

    /* renamed from: j */
    private float f4515j;

    /* renamed from: k */
    private List<IWidget> f4516k;

    /* renamed from: l */
    private ImageView f4517l;

    /* renamed from: m */
    private ScrollCardEntity f4518m;

    /* renamed from: n */
    private int f4519n;
    public JSONObject o;

    public CardView(Context context, float f2, int i2) {
        super(context);
        this.f4516k = new ArrayList();
        this.f4514i = context;
        this.f4515j = f2;
        this.f4519n = i2;
        this.f4512g = new e(this);
    }

    public JSONObject a() {
        return this.o;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    /* renamed from: b */
    public void updateStyle(@NonNull ScrollCardEntity scrollCardEntity, float f2) {
        List<BaseWidgetEntity> list;
        this.f4515j = f2;
        removeAllViews();
        if (scrollCardEntity != null && scrollCardEntity.config != null && (list = scrollCardEntity.subElementList) != null && !list.isEmpty()) {
            this.f4518m = scrollCardEntity;
            CardConfig cardConfig = scrollCardEntity.config.cardConfig;
            this.f4513h = cardConfig;
            this.f4512g.i(cardConfig.cfInfo, f2);
            setBackgroundColor(a.a(this.f4513h.bgColor, 0));
            ImageView newImageView = ImageLoad.newImageView(getContext());
            this.f4517l = newImageView;
            newImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            addView(this.f4517l, new FrameLayout.LayoutParams(-1, -1));
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(0);
            addView(linearLayout, new FrameLayout.LayoutParams(-1, -1));
            linearLayout.setGravity(16);
            f.d(linearLayout, this.f4513h.paddingInfo, this.f4515j);
            int i2 = 0;
            while (i2 < scrollCardEntity.subElementList.size()) {
                BaseWidgetEntity baseWidgetEntity = scrollCardEntity.subElementList.get(i2);
                if (baseWidgetEntity != null) {
                    View a = WidgetPool.a(baseWidgetEntity.type, this.f4514i);
                    if (a instanceof IWidget) {
                        a.setTag(R.id.type, baseWidgetEntity.type);
                        IWidget iWidget = (IWidget) a;
                        iWidget.updateStyle(baseWidgetEntity, this.f4515j);
                        this.f4516k.add(iWidget);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(iWidget.getLayoutParamsWidth(), iWidget.getLayoutParamsHeight());
                        layoutParams.rightMargin = i2 < scrollCardEntity.subElementList.size() + (-1) ? b.d(this.f4513h.elementDistance, f2) : 0;
                        linearLayout.addView(a, layoutParams);
                    }
                }
                i2++;
            }
            return;
        }
        getLayoutParams().height = 0;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void clear() {
        removeAllViews();
        for (IWidget iWidget : this.f4516k) {
            if (iWidget instanceof View) {
                iWidget.clear();
                WidgetPool.e((View) iWidget);
            }
        }
        this.f4516k.clear();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        this.f4512g.b(canvas);
        super.draw(canvas);
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public int getLayoutParamsHeight() {
        return 0;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public int getLayoutParamsWidth() {
        return 0;
    }

    @Override // android.view.View
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
        this.f4512g.h(canvas);
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void updateContent(@NonNull Map<String, String> map, IWidgetCommunication iWidgetCommunication) {
        ScrollCardDataPath scrollCardDataPath;
        if (iWidgetCommunication != null) {
            iWidgetCommunication.getBabelScope();
        }
        if (map == null || (scrollCardDataPath = this.f4518m.dataPath) == null) {
            return;
        }
        if (!TextUtils.isEmpty(scrollCardDataPath.bgImage)) {
            String d = com.jd.lib.flexcube.widgets.b.b.d(map, this.f4518m.dataPath.bgImage);
            if (TextUtils.isEmpty(d)) {
                this.f4517l.setVisibility(8);
            } else {
                this.f4517l.setVisibility(0);
                ImageLoad.with(this.f4517l).load(d);
            }
        } else {
            this.f4517l.setVisibility(8);
        }
        Iterator<IWidget> it = this.f4516k.iterator();
        while (it.hasNext()) {
            it.next().updateContent(map, iWidgetCommunication);
        }
        int size = this.f4516k.size() - 1;
        while (true) {
            if (size < 0) {
                break;
            }
            if (this.f4516k.get(size) instanceof View) {
                View view = (View) this.f4516k.get(size);
                if (view.getVisibility() == 0) {
                    ((LinearLayout.LayoutParams) view.getLayoutParams()).rightMargin = 0;
                    break;
                }
            }
            size--;
        }
        if (!TextUtils.isEmpty(this.f4518m.dataPath.cardEvent)) {
            ClickEvent a = com.jd.lib.flexcube.widgets.b.b.a(map, this.f4518m.dataPath.cardEvent);
            if (a != null) {
                a.b bVar = new a.b(getContext(), a);
                bVar.a(iWidgetCommunication.getBabelScope());
                setOnClickListener(bVar.b());
            } else {
                setOnClickListener(null);
            }
        }
        if (TextUtils.isEmpty(this.f4518m.dataPath.cardExpo)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(com.jd.lib.flexcube.widgets.b.b.d(map, this.f4518m.dataPath.cardExpo));
            this.o = jSONObject;
            jSONObject.put("f_index", this.f4519n);
        } catch (JSONException unused) {
        }
    }
}
