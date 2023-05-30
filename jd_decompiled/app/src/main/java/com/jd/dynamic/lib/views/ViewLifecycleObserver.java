package com.jd.dynamic.lib.views;

import android.text.TextUtils;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import com.jd.dynamic.R;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.lib.lifecycle.EventExtend;
import com.jd.dynamic.lib.lifecycle.LifecycleEventObserverExtend;
import java.util.Map;
import rx.Observable;
import rx.functions.Action1;

/* loaded from: classes13.dex */
public class ViewLifecycleObserver implements LifecycleEventObserverExtend {

    /* renamed from: g */
    private View f2528g;

    /* renamed from: h */
    private Map<String, String> f2529h;

    /* renamed from: i */
    private DynamicTemplateEngine f2530i;

    /* renamed from: com.jd.dynamic.lib.views.ViewLifecycleObserver$1 */
    /* loaded from: classes13.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[EventExtend.values().length];
            a = iArr;
            try {
                iArr[EventExtend.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[EventExtend.ON_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[EventExtend.ON_RESUME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[EventExtend.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[EventExtend.ON_STOP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[EventExtend.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[EventExtend.ON_ACTIVITY_RESULT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[EventExtend.ON_ANY.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public ViewLifecycleObserver(View view, Map<String, String> map, DynamicTemplateEngine dynamicTemplateEngine) {
        this.f2528g = view;
        this.f2529h = map;
        view.setTag(R.id.dynamic_lifecycle_observer, this);
        this.f2530i = dynamicTemplateEngine;
    }

    public /* synthetic */ void a(String str) {
        View view = this.f2528g;
        com.jd.dynamic.lib.utils.s.b(str, view, this.f2530i, view);
    }

    public static /* synthetic */ void b(Throwable th) {
    }

    public /* synthetic */ void c(String str) {
        Observable.from(com.jd.dynamic.lib.utils.s.i(str)).forEach(new Action1() { // from class: com.jd.dynamic.lib.views.y
            {
                ViewLifecycleObserver.this = this;
            }

            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ViewLifecycleObserver.this.e((String) obj);
            }
        }, new Action1() { // from class: com.jd.dynamic.lib.views.v
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ViewLifecycleObserver.d((Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void d(Throwable th) {
    }

    public /* synthetic */ void e(String str) {
        View view = this.f2528g;
        com.jd.dynamic.lib.utils.s.b(str, view, this.f2530i, view);
    }

    public static /* synthetic */ void f(Throwable th) {
    }

    public /* synthetic */ void g(String str) {
        View view = this.f2528g;
        com.jd.dynamic.lib.utils.s.b(str, view, this.f2530i, view);
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull Lifecycle.Event event) {
        onStateChanged(lifecycleOwner, EventExtend.valueOf(event.name()));
    }

    @Override // com.jd.dynamic.lib.lifecycle.LifecycleEventObserverExtend
    public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull EventExtend eventExtend) {
        Map<String, String> map;
        String str;
        Map<String, String> map2;
        String str2;
        String str3;
        DynamicTemplateEngine dynamicTemplateEngine;
        DynamicTemplateEngine dynamicTemplateEngine2;
        com.jd.dynamic.lib.utils.t.e("ViewLifecycleObserver", this.f2528g.getClass().getSimpleName() + "" + eventExtend.toString());
        final String str4 = null;
        switch (AnonymousClass1.a[eventExtend.ordinal()]) {
            case 1:
                str4 = this.f2529h.get("onCreate");
                map = this.f2529h;
                str = "onPageCreate";
                str3 = map.get(str);
                break;
            case 2:
                map2 = this.f2529h;
                str2 = "onStart";
                str4 = map2.get(str2);
                str3 = null;
                break;
            case 3:
                str4 = this.f2529h.get("onResume");
                map = this.f2529h;
                str = "onPageEnter";
                str3 = map.get(str);
                break;
            case 4:
                str4 = this.f2529h.get("onPause");
                map = this.f2529h;
                str = "onPageLeave";
                str3 = map.get(str);
                break;
            case 5:
                map2 = this.f2529h;
                str2 = "onStop";
                str4 = map2.get(str2);
                str3 = null;
                break;
            case 6:
                lifecycleOwner.getLifecycle().removeObserver(this);
                this.f2528g.setTag(R.id.dynamic_lifecycle_observer, null);
                str4 = this.f2529h.get("onDestroy");
                str3 = this.f2529h.get("onPageDestroy");
                break;
            case 7:
                map2 = this.f2529h;
                str2 = "onActivityResult";
                str4 = map2.get(str2);
                str3 = null;
                break;
            default:
                str3 = null;
                break;
        }
        if (!TextUtils.isEmpty(str4) && (dynamicTemplateEngine2 = this.f2530i) != null && !dynamicTemplateEngine2.isRelease) {
            if (com.jd.dynamic.b.a.b.o().Q()) {
                this.f2528g.post(new Runnable() { // from class: com.jd.dynamic.lib.views.x
                    {
                        ViewLifecycleObserver.this = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        ViewLifecycleObserver.this.c(str4);
                    }
                });
            } else {
                Observable.from(com.jd.dynamic.lib.utils.s.i(str4)).forEach(new Action1() { // from class: com.jd.dynamic.lib.views.w
                    {
                        ViewLifecycleObserver.this = this;
                    }

                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        ViewLifecycleObserver.this.g((String) obj);
                    }
                }, new Action1() { // from class: com.jd.dynamic.lib.views.u
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        ViewLifecycleObserver.f((Throwable) obj);
                    }
                });
            }
        }
        if (TextUtils.isEmpty(str3) || (dynamicTemplateEngine = this.f2530i) == null || dynamicTemplateEngine.isRelease) {
            return;
        }
        Observable.from(com.jd.dynamic.lib.utils.s.i(str3)).forEach(new Action1() { // from class: com.jd.dynamic.lib.views.a0
            {
                ViewLifecycleObserver.this = this;
            }

            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ViewLifecycleObserver.this.a((String) obj);
            }
        }, new Action1() { // from class: com.jd.dynamic.lib.views.z
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ViewLifecycleObserver.b((Throwable) obj);
            }
        });
    }
}
