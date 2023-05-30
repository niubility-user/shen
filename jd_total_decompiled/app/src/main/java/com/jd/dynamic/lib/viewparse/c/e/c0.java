package com.jd.dynamic.lib.viewparse.c.e;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import rx.Observable;
import rx.functions.Action1;

/* loaded from: classes13.dex */
public class c0 extends p0<EditText> {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ EditText f2372g;

        a(EditText editText) {
            this.f2372g = editText;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f2372g.requestFocus();
                InputMethodManager inputMethodManager = (InputMethodManager) this.f2372g.getContext().getSystemService("input_method");
                if (inputMethodManager != null) {
                    inputMethodManager.showSoftInput(this.f2372g, 0);
                }
            } catch (Exception e2) {
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "AndroidEditTextParse parse showKeyBoard catch error", c0.this.b(), c0.this.d(), e2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class b implements TextWatcher {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ List f2374g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ EditText f2375h;

        b(List list, EditText editText) {
            this.f2374g = list;
            this.f2375h = editText;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            Iterator it = this.f2374g.iterator();
            while (it.hasNext()) {
                EditText editText = this.f2375h;
                com.jd.dynamic.lib.utils.s.b((String) it.next(), editText, c0.this.a, editText);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class c implements TextView.OnEditorActionListener {
        final /* synthetic */ List a;
        final /* synthetic */ EditText b;

        c(List list, EditText editText) {
            this.a = list;
            this.b = editText;
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
            if (keyEvent.getAction() == 1) {
                Iterator it = this.a.iterator();
                while (it.hasNext()) {
                    EditText editText = this.b;
                    com.jd.dynamic.lib.utils.s.b((String) it.next(), editText, c0.this.a, editText);
                }
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f(EditText editText, List list) {
        editText.setSelection(editText.getText().length());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            com.jd.dynamic.lib.utils.s.b((String) it.next(), editText, this.a, editText);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public /* synthetic */ void g(final EditText editText, Map.Entry entry) {
        char c2;
        String str = (String) entry.getValue();
        String str2 = (String) entry.getKey();
        str2.hashCode();
        char c3 = '\uffff';
        int i2 = 5;
        switch (str2.hashCode()) {
            case -775324713:
                if (str2.equals("editorActionListener")) {
                    c2 = 0;
                    break;
                }
                c2 = '\uffff';
                break;
            case -377784860:
                if (str2.equals(DYConstants.DY_SHOW_KEY_BOARD)) {
                    c2 = 1;
                    break;
                }
                c2 = '\uffff';
                break;
            case 3202695:
                if (str2.equals(DYConstants.DY_HINT)) {
                    c2 = 2;
                    break;
                }
                c2 = '\uffff';
                break;
            case 1542216988:
                if (str2.equals(DYConstants.DY_HINT_COLOR)) {
                    c2 = 3;
                    break;
                }
                c2 = '\uffff';
                break;
            case 1550420753:
                if (str2.equals("textChangeListener")) {
                    c2 = 4;
                    break;
                }
                c2 = '\uffff';
                break;
            case 1742240669:
                if (str2.equals("imeOptions")) {
                    c2 = 5;
                    break;
                }
                c2 = '\uffff';
                break;
            default:
                c2 = '\uffff';
                break;
        }
        switch (c2) {
            case 0:
                editText.setOnEditorActionListener(new c(com.jd.dynamic.lib.utils.s.i(str), editText));
                return;
            case 1:
                if (TextUtils.equals("1", str) || Boolean.parseBoolean(str)) {
                    editText.postDelayed(new a(editText), 500L);
                    return;
                }
                return;
            case 2:
                editText.setHint(str);
                return;
            case 3:
                if (!TextUtils.isEmpty(str) && str.startsWith("#")) {
                    try {
                        editText.setHintTextColor(Color.parseColor(str));
                        return;
                    } catch (Exception e2) {
                        DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "AndroidEditTextParse parse hintColor catch error", b(), d(), e2);
                        return;
                    }
                }
                return;
            case 4:
                final List<String> i3 = com.jd.dynamic.lib.utils.s.i(str);
                editText.addTextChangedListener(new b(i3, editText));
                if (TextUtils.isEmpty(editText.getText())) {
                    return;
                }
                editText.post(new Runnable() { // from class: com.jd.dynamic.lib.viewparse.c.e.b
                    @Override // java.lang.Runnable
                    public final void run() {
                        c0.this.f(editText, i3);
                    }
                });
                return;
            case 5:
                str.hashCode();
                switch (str.hashCode()) {
                    case -1656172098:
                        if (str.equals("actionGo")) {
                            c3 = 0;
                            break;
                        }
                        break;
                    case -1188100275:
                        if (str.equals("actionPrevious")) {
                            c3 = 1;
                            break;
                        }
                        break;
                    case 1493749630:
                        if (str.equals("actionSearch")) {
                            c3 = 2;
                            break;
                        }
                        break;
                    case 1851394776:
                        if (str.equals("actionDone")) {
                            c3 = 3;
                            break;
                        }
                        break;
                    case 1851683401:
                        if (str.equals("actionNext")) {
                            c3 = 4;
                            break;
                        }
                        break;
                    case 1851692686:
                        if (str.equals("actionNone")) {
                            c3 = 5;
                            break;
                        }
                        break;
                    case 1851832030:
                        if (str.equals("actionSend")) {
                            c3 = 6;
                            break;
                        }
                        break;
                }
                switch (c3) {
                    case 0:
                        i2 = 2;
                        break;
                    case 1:
                        i2 = 7;
                        break;
                    case 2:
                        i2 = 3;
                        break;
                    case 3:
                        i2 = 6;
                        break;
                    case 4:
                        break;
                    case 5:
                        i2 = 1;
                        break;
                    case 6:
                        i2 = 4;
                        break;
                    default:
                        i2 = 0;
                        break;
                }
                editText.setImeOptions(i2);
                editText.setInputType(1);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void h(Throwable th) {
    }

    @Override // com.jd.dynamic.lib.viewparse.c.e.q0
    /* renamed from: i  reason: merged with bridge method [inline-methods] */
    public void a(HashMap<String, String> hashMap, final EditText editText) {
        Observable.from(hashMap.entrySet()).forEach(new Action1() { // from class: com.jd.dynamic.lib.viewparse.c.e.c
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                c0.this.g(editText, (Map.Entry) obj);
            }
        }, new Action1() { // from class: com.jd.dynamic.lib.viewparse.c.e.a
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                c0.h((Throwable) obj);
            }
        });
    }
}
