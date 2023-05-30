package com.jd.dynamic.lib.viewparse.c;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.dynamic.R;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicUtils;
import com.jd.dynamic.lib.viewparse.BaseFrameLayout;
import com.jd.dynamic.lib.views.CollectionView;
import com.jd.dynamic.lib.views.CornerSimpleDraweeView;
import com.jd.dynamic.lib.views.MarqueeTextView;
import com.jd.dynamic.lib.views.RichTextViewContainer;
import com.jd.dynamic.lib.views.SpanView;
import com.jd.dynamic.lib.views.TagView;
import com.jd.dynamic.lib.views.UnIconView;
import com.jd.dynamic.yoga.android.YogaLayout;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/* loaded from: classes13.dex */
public class v extends f<View> {

    /* renamed from: c  reason: collision with root package name */
    private JSONObject f2446c;

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean d(Map.Entry entry) {
        return Boolean.valueOf(DynamicUtils.isEL((String) entry.getValue()) || DynamicUtils.isKnownSymbol((String) entry.getValue()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void e(HashMap hashMap, Map.Entry entry) {
        hashMap.remove(entry.getKey());
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01da  */
    @Override // com.jd.dynamic.lib.viewparse.c.j
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public View a(HashMap<String, String> hashMap, View view) {
        w wVar;
        f qVar;
        KeyEvent.Callback callback;
        final HashMap<String, String> hashMap2 = new HashMap<>(hashMap);
        Observable.from(hashMap.entrySet()).filter(new Func1() { // from class: com.jd.dynamic.lib.viewparse.c.c
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                Boolean d;
                d = v.d((Map.Entry) obj);
                return d;
            }
        }).forEach(new Action1() { // from class: com.jd.dynamic.lib.viewparse.c.d
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                v.e(hashMap2, (Map.Entry) obj);
            }
        });
        if (com.jd.dynamic.lib.utils.m.J(hashMap2)) {
            if (com.jd.dynamic.b.a.b.o().y() && this.a.isAttached && TextUtils.equals(hashMap2.get("visibility"), "2")) {
                com.jd.dynamic.lib.utils.t.e("ViewRefreshHandler", view.getClass().getSimpleName(), "only refresh visibility", "params size = " + hashMap2.size());
                view.setTag(R.id.dynamic_view_need_rebind, com.jd.dynamic.b.c.a.b);
                com.jd.dynamic.lib.utils.m.r(view, 8);
                if (view.getParent() instanceof YogaLayout) {
                    ((YogaLayout) view.getParent()).invalidate(view);
                }
                return view;
            }
            t tVar = new t();
            tVar.c(this.f2446c);
            tVar.b(this.a);
            tVar.a(hashMap2, view);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                if ((view instanceof YogaLayout) || (view.getParent() instanceof YogaLayout)) {
                    wVar = new w();
                    wVar.a(hashMap2, view);
                }
                if (view instanceof YogaLayout) {
                }
                new w().b(hashMap2, view);
                if (view instanceof SpanView) {
                }
                if (view.getParent() instanceof YogaLayout) {
                }
                return view;
            } else if (!(layoutParams instanceof YogaLayout.LayoutParams)) {
                new com.jd.dynamic.lib.viewparse.g.a.b().a(view.getContext(), hashMap2, layoutParams);
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    new com.jd.dynamic.lib.viewparse.g.a.a().b(view.getContext(), hashMap2, (ViewGroup.MarginLayoutParams) layoutParams);
                }
                if (view instanceof YogaLayout) {
                    new w().b(hashMap2, view);
                    if (view instanceof SpanView) {
                    }
                    if (view.getParent() instanceof YogaLayout) {
                    }
                    return view;
                }
                new w().b(hashMap2, view);
                if (view instanceof SpanView) {
                }
                if (view.getParent() instanceof YogaLayout) {
                }
                return view;
            } else {
                wVar = new w();
                wVar.a(hashMap2, view);
                if (((view instanceof YogaLayout) && (view.getParent() instanceof BaseFrameLayout)) || (view.getLayoutParams() instanceof RecyclerView.LayoutParams)) {
                    new w().b(hashMap2, view);
                }
                if (view instanceof SpanView) {
                    if (view instanceof MarqueeTextView) {
                        qVar = new l();
                        qVar.b(this.a);
                        callback = (MarqueeTextView) view;
                    } else if (view instanceof AppCompatTextView) {
                        qVar = new r();
                        qVar.b(this.a);
                        callback = (AppCompatTextView) view;
                    } else if (view instanceof CornerSimpleDraweeView) {
                        qVar = new k();
                        qVar.b(this.a);
                        callback = (CornerSimpleDraweeView) view;
                    } else if (view instanceof EditText) {
                        qVar = new h();
                        qVar.b(this.a);
                        callback = (EditText) view;
                    } else if (view instanceof CollectionView) {
                        qVar = new g();
                        qVar.b(this.a);
                        callback = (CollectionView) view;
                    } else if (view instanceof com.jd.dynamic.lib.viewparse.b.carouselView.f) {
                        qVar = new com.jd.dynamic.lib.viewparse.b.carouselView.n();
                        qVar.b(this.a);
                        callback = (com.jd.dynamic.lib.viewparse.b.carouselView.f) view;
                    } else if (view instanceof TagView) {
                        qVar = new q();
                        qVar.b(this.a);
                        callback = (TagView) view;
                    } else if (view instanceof UnIconView) {
                        new s().b(hashMap2, (UnIconView) view);
                    } else if (view instanceof RichTextViewContainer) {
                        new m().d(hashMap2, (RichTextViewContainer) view);
                    } else if (DynamicSdk.getEngine() != null && DynamicSdk.getEngine().getCustomViewFactory() != null) {
                        DynamicSdk.getEngine().getCustomViewFactory().parse(hashMap2, view);
                    }
                    qVar.a(hashMap2, callback);
                } else {
                    new p().d(hashMap2, (SpanView) view);
                }
                if (view.getParent() instanceof YogaLayout) {
                    ((YogaLayout) view.getParent()).invalidate(view);
                }
                return view;
            }
        }
        return view;
    }

    @Override // com.jd.dynamic.lib.viewparse.c.f
    public void c(JSONObject jSONObject) {
        this.f2446c = jSONObject;
    }
}
