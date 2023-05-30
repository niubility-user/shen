package com.jingdong.manto.widget.input;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Editable;
import android.text.Layout;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.AlignmentSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatEditText;
import com.jingdong.manto.widget.input.l;
import com.jingdong.manto.widget.input.z.d;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes16.dex */
public abstract class y extends AppCompatEditText implements com.jingdong.manto.widget.input.z.d {
    boolean a;
    private final Map<d.a, Object> b;

    /* renamed from: c  reason: collision with root package name */
    private final Map<View.OnFocusChangeListener, Object> f14501c;
    private final Map<d.c, Object> d;

    /* renamed from: e  reason: collision with root package name */
    private final c f14502e;

    /* renamed from: f  reason: collision with root package name */
    final w f14503f;

    /* renamed from: g  reason: collision with root package name */
    private int f14504g;

    /* renamed from: h  reason: collision with root package name */
    d.b f14505h;

    /* renamed from: i  reason: collision with root package name */
    char f14506i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f14507j;

    /* renamed from: k  reason: collision with root package name */
    volatile int f14508k;

    /* renamed from: l  reason: collision with root package name */
    private com.jingdong.manto.widget.input.autofill.e f14509l;

    /* renamed from: m  reason: collision with root package name */
    InputConnection f14510m;

    /* loaded from: classes16.dex */
    class a extends Editable.Factory {
        a() {
        }

        @Override // android.text.Editable.Factory
        public Editable newEditable(CharSequence charSequence) {
            return y.this.b(super.newEditable(charSequence));
        }
    }

    /* loaded from: classes16.dex */
    class b extends InputConnectionWrapper {
        b(InputConnection inputConnection, boolean z) {
            super(inputConnection, z);
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public final boolean commitText(CharSequence charSequence, int i2) {
            if (!TextUtils.isEmpty(charSequence)) {
                y.this.f14506i = charSequence.charAt(charSequence.length() - 1);
            }
            return super.commitText(charSequence, i2);
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public final boolean deleteSurroundingText(int i2, int i3) {
            y.this.f14506i = '\b';
            return super.deleteSurroundingText(i2, i3);
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public final boolean setComposingText(CharSequence charSequence, int i2) {
            if (!TextUtils.isEmpty(charSequence)) {
                y.this.f14506i = charSequence.charAt(charSequence.length() - 1);
            }
            return super.setComposingText(charSequence, i2);
        }
    }

    /* loaded from: classes16.dex */
    private final class c implements TextWatcher {
        final y a;
        final Map<TextWatcher, Object> b;

        private c(y yVar, y yVar2) {
            this.a = yVar2;
            this.b = new HashMap();
        }

        /* synthetic */ c(y yVar, y yVar2, a aVar) {
            this(yVar, yVar2);
        }

        @Override // android.text.TextWatcher
        public final void afterTextChanged(Editable editable) {
            if (Build.VERSION.SDK_INT >= 19) {
                y.a(editable);
            }
            if (y.a(this.a)) {
                return;
            }
            this.a.f14504g = 0;
            if (this.b.isEmpty()) {
                return;
            }
            for (TextWatcher textWatcher : (TextWatcher[]) this.b.keySet().toArray(new TextWatcher[this.b.size()])) {
                textWatcher.afterTextChanged(editable);
            }
        }

        @Override // android.text.TextWatcher
        public final void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            if (y.a(this.a) || this.b.isEmpty()) {
                return;
            }
            for (TextWatcher textWatcher : (TextWatcher[]) this.b.keySet().toArray(new TextWatcher[this.b.size()])) {
                textWatcher.beforeTextChanged(charSequence, i2, i3, i4);
            }
        }

        @Override // android.text.TextWatcher
        public final void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            if (y.a(this.a) || this.b.isEmpty()) {
                return;
            }
            for (TextWatcher textWatcher : (TextWatcher[]) this.b.keySet().toArray(new TextWatcher[this.b.size()])) {
                textWatcher.onTextChanged(charSequence, i2, i3, i4);
            }
        }
    }

    public y(Context context) {
        super(context);
        this.a = false;
        this.b = new HashMap();
        this.f14501c = new HashMap();
        this.d = new HashMap();
        c cVar = new c(this, this, null);
        this.f14502e = cVar;
        this.f14503f = new w(this);
        this.f14504g = 0;
        this.f14506i = (char) 0;
        this.f14507j = false;
        this.f14508k = -1;
        setBackgroundDrawable(null);
        setIncludeFontPadding(false);
        setAlignment(3);
        setSingleLine(true);
        setTextIsSelectable(true);
        setFocusable(false);
        setFocusableInTouchMode(false);
        if (Build.VERSION.SDK_INT >= 16) {
            setLineSpacing(0.0f, 1.0f);
        }
        setTypeface(Typeface.SANS_SERIF);
        super.addTextChangedListener(cVar);
        super.setPadding(0, 0, 0, 0);
        super.setEditableFactory(new a());
        if (f()) {
            this.f14509l = new com.jingdong.manto.widget.input.autofill.e(this);
        } else {
            this.f14509l = null;
        }
    }

    static String a(Editable editable) {
        if (editable == null || editable.length() <= 0) {
            return null;
        }
        return editable.toString();
    }

    static boolean a(y yVar) {
        return yVar.f14504g > 0;
    }

    private void setAlignment(int i2) {
        setGravity(i2 | (getGravity() & (-8388612) & (-8388614)));
        int gravity = getGravity();
        CharSequence hint = getHint();
        if (TextUtils.isEmpty(hint)) {
            return;
        }
        Spannable s = InputUtil.s(hint);
        int i3 = gravity & 7;
        int i4 = 5;
        Layout.Alignment alignment = i3 != 1 ? i3 != 5 ? Layout.Alignment.ALIGN_NORMAL : Layout.Alignment.ALIGN_OPPOSITE : Layout.Alignment.ALIGN_CENTER;
        s.setSpan(new AlignmentSpan.Standard(alignment), 0, getHint().length(), 18);
        super.setHint(s);
        if (Build.VERSION.SDK_INT >= 17) {
            if (alignment == Layout.Alignment.ALIGN_CENTER) {
                i4 = 4;
            } else if (alignment == Layout.Alignment.ALIGN_OPPOSITE) {
                i4 = 6;
            }
            super.setTextAlignment(i4);
        }
    }

    @Override // com.jingdong.manto.widget.input.z.d
    public final int a(int i2) {
        int paddingTop;
        float f2;
        float lineHeight;
        if (Build.VERSION.SDK_INT >= 16) {
            paddingTop = getPaddingTop();
            f2 = i2;
            lineHeight = getLineHeight() + getLineSpacingExtra();
        } else {
            paddingTop = getPaddingTop();
            f2 = i2;
            lineHeight = getLineHeight();
        }
        return paddingTop + ((int) (f2 * lineHeight));
    }

    @Override // com.jingdong.manto.widget.input.z.d
    public final void a(View.OnFocusChangeListener onFocusChangeListener) {
        if (onFocusChangeListener != null) {
            this.f14501c.put(onFocusChangeListener, this);
        }
    }

    @Override // com.jingdong.manto.widget.input.z.d
    public final void a(com.jingdong.manto.q.n nVar) {
        com.jingdong.manto.widget.input.autofill.e eVar = this.f14509l;
        if (eVar != null) {
            com.jingdong.manto.widget.input.autofill.b bVar = eVar.d;
            bVar.d = nVar;
            l a2 = l.a(nVar);
            l.c cVar = bVar.f14436c;
            if (cVar == null || a2.f14475c.containsKey(cVar)) {
                return;
            }
            a2.f14475c.put(cVar, a2);
        }
    }

    @Override // com.jingdong.manto.widget.input.z.d
    public final void a(d.a aVar) {
        this.b.put(aVar, this);
    }

    @Override // com.jingdong.manto.widget.input.z.d
    public final void a(d.c cVar) {
        this.d.put(cVar, this);
    }

    public final void a(CharSequence charSequence) {
        j();
        Editable editableText = getEditableText();
        if (editableText == null) {
            setText(charSequence, TextView.BufferType.EDITABLE);
        } else {
            clearComposingText();
            if (TextUtils.isEmpty(charSequence)) {
                editableText.clear();
            } else {
                editableText.replace(0, editableText.length(), charSequence);
            }
        }
        i();
    }

    @Override // android.widget.TextView, com.jingdong.manto.widget.input.z.d
    public void addTextChangedListener(TextWatcher textWatcher) {
        if (textWatcher != null) {
            c cVar = this.f14502e;
            cVar.b.put(textWatcher, cVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Editable b(Editable editable) {
        return this.f14503f.a(editable);
    }

    @Override // com.jingdong.manto.widget.input.z.d
    public final void b() {
        setAlignment(5);
    }

    @Override // com.jingdong.manto.widget.input.z.d
    public final void b(View.OnFocusChangeListener onFocusChangeListener) {
        if (onFocusChangeListener != null) {
            this.f14501c.remove(onFocusChangeListener);
        }
    }

    @Override // com.jingdong.manto.widget.input.z.d
    public final void b(com.jingdong.manto.q.n nVar) {
        if (this.f14509l != null) {
            l.a(nVar).a(this.f14509l.d.f14436c);
        }
    }

    @Override // com.jingdong.manto.widget.input.z.d
    public final void c() {
        setAlignment(3);
    }

    @Override // android.view.View
    public void clearFocus() {
        if (getParent() instanceof ViewGroup) {
            ((ViewGroup) getParent()).setFocusableInTouchMode(true);
            ((ViewGroup) getParent()).setDescendantFocusability(131072);
        }
        super.clearFocus();
    }

    @Override // com.jingdong.manto.widget.input.z.d
    public final void d() {
        setAlignment(1);
    }

    @Override // com.jingdong.manto.widget.input.z.d
    public final void destroy() {
        this.b.clear();
        this.d.clear();
        this.f14501c.clear();
        this.f14502e.b.clear();
        com.jingdong.manto.widget.input.autofill.e eVar = this.f14509l;
        if (eVar != null) {
            com.jingdong.manto.widget.input.autofill.b bVar = eVar.d;
            l.a(bVar.d).a(bVar.f14436c);
            com.jingdong.manto.widget.input.autofill.e eVar2 = this.f14509l;
            eVar2.f14442f = null;
            eVar2.b.c();
        }
        setOnFocusChangeListener(null);
    }

    public boolean e() {
        return this.f14507j;
    }

    public boolean f() {
        return true;
    }

    public final int g() {
        return a(getLineCount()) + getPaddingBottom();
    }

    public com.jingdong.manto.widget.input.autofill.e getAutoFillController() {
        return this.f14509l;
    }

    @Override // com.jingdong.manto.widget.input.z.d
    public final int getInputId() {
        return this.f14508k;
    }

    @Override // com.jingdong.manto.widget.input.z.d
    public char getLastKeyPressed() {
        return this.f14506i;
    }

    @Override // com.jingdong.manto.widget.input.z.d
    public final View getView() {
        return this;
    }

    protected abstract void h();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void i() {
        this.f14504g = Math.max(0, this.f14504g - 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void j() {
        this.f14504g++;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.b.isEmpty()) {
            return;
        }
        for (d.a aVar : (d.a[]) this.b.keySet().toArray(new d.a[this.b.size()])) {
            aVar.a();
        }
    }

    @Override // androidx.appcompat.widget.AppCompatEditText, android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        b bVar = new b(super.onCreateInputConnection(editorInfo), true);
        this.f14510m = bVar;
        return bVar;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onFocusChanged(boolean z, int i2, Rect rect) {
        super.onFocusChanged(z, i2, rect);
        if (!z) {
            clearComposingText();
        }
        if (z) {
            h();
        }
        if (this.f14501c.isEmpty()) {
            return;
        }
        for (View.OnFocusChangeListener onFocusChangeListener : (View.OnFocusChangeListener[]) this.f14501c.keySet().toArray(new View.OnFocusChangeListener[this.f14501c.size()])) {
            onFocusChangeListener.onFocusChange(this, z);
        }
    }

    @Override // android.widget.TextView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        boolean onKeyDown = super.onKeyDown(i2, keyEvent);
        if (onKeyDown && i2 == 66) {
            this.f14506i = '\n';
        }
        return onKeyDown;
    }

    @Override // android.widget.TextView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        d.b bVar = this.f14505h;
        if (bVar == null || !bVar.a(i2)) {
            return super.onKeyUp(i2, keyEvent);
        }
        return true;
    }

    @Override // android.widget.TextView, android.view.View
    protected final void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (this.d.isEmpty()) {
            return;
        }
        for (Object obj : this.d.keySet().toArray()) {
            getMeasuredWidth();
            getMeasuredHeight();
            ((d.c) obj).a();
        }
    }

    @Override // android.widget.TextView
    public void removeTextChangedListener(TextWatcher textWatcher) {
        if (textWatcher != null) {
            this.f14502e.b.remove(textWatcher);
        }
    }

    @Override // android.view.View
    public boolean requestFocus(int i2, Rect rect) {
        if (130 == i2 && rect == null && (getParent() instanceof ViewGroup)) {
            ((ViewGroup) getParent()).setDescendantFocusability(262144);
            ((ViewGroup) getParent()).setFocusableInTouchMode(false);
        }
        return super.requestFocus(i2, rect);
    }

    @Override // com.jingdong.manto.widget.input.z.d
    public final void setFixed(boolean z) {
        this.f14507j = z;
    }

    @Override // com.jingdong.manto.widget.input.z.d
    public final void setInputId(int i2) {
        this.f14508k = i2;
    }

    @Override // android.widget.TextView
    public void setInputType(int i2) {
        if (getInputType() != i2) {
            super.setInputType(i2);
        }
    }

    @Override // android.widget.TextView
    public void setMaxHeight(int i2) {
        if (Build.VERSION.SDK_INT < 16 || getMaxHeight() == i2) {
            return;
        }
        super.setMaxHeight(i2);
    }

    @Override // android.widget.TextView
    public void setMinHeight(int i2) {
        if (Build.VERSION.SDK_INT < 16 || getMinHeight() == i2) {
            return;
        }
        super.setMinHeight(i2);
    }

    public void setOnKeyUpPostImeListener(d.b bVar) {
        this.f14505h = bVar;
    }

    public void setPasswordMode(boolean z) {
        j();
        if (this.a != z) {
            this.a = z;
            setTransformationMethod(z ? new q() : null);
        }
        i();
    }

    @Override // android.widget.EditText
    public void setSelection(int i2) {
        if (getEditableText() != null) {
            super.setSelection(Math.min(i2, getEditableText().length()));
        }
    }

    @Override // android.widget.TextView
    public void setSingleLine(boolean z) {
    }

    @Override // android.widget.TextView
    public final void setTextSize(float f2) {
        setTextSize(0, f2);
    }
}
