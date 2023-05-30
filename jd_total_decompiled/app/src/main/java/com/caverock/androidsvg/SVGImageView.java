package com.caverock.androidsvg;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class SVGImageView extends ImageView {

    /* renamed from: i  reason: collision with root package name */
    private static Method f825i;

    /* renamed from: g  reason: collision with root package name */
    private h f826g;

    /* renamed from: h  reason: collision with root package name */
    private g f827h;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class b extends AsyncTask<Integer, Integer, h> {
        private Context a;
        private int b;

        b(Context context, int i2) {
            this.a = context;
            this.b = i2;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public h doInBackground(Integer... numArr) {
            try {
                return h.i(this.a, this.b);
            } catch (k e2) {
                String.format("Error loading resource 0x%x: %s", Integer.valueOf(this.b), e2.getMessage());
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public void onPostExecute(h hVar) {
            SVGImageView.this.f826g = hVar;
            SVGImageView.this.c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class c extends AsyncTask<InputStream, Integer, h> {
        private c() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public h doInBackground(InputStream... inputStreamArr) {
            try {
                try {
                    h h2 = h.h(inputStreamArr[0]);
                    try {
                        inputStreamArr[0].close();
                    } catch (IOException unused) {
                    }
                    return h2;
                } catch (Throwable th) {
                    try {
                        inputStreamArr[0].close();
                    } catch (IOException unused2) {
                    }
                    throw th;
                }
            } catch (k e2) {
                String str = "Parse error loading URI: " + e2.getMessage();
                try {
                    inputStreamArr[0].close();
                    return null;
                } catch (IOException unused3) {
                    return null;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public void onPostExecute(h hVar) {
            SVGImageView.this.f826g = hVar;
            SVGImageView.this.c();
        }
    }

    static {
        try {
            f825i = View.class.getMethod("setLayerType", Integer.TYPE, Paint.class);
        } catch (NoSuchMethodException unused) {
        }
    }

    public SVGImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.f826g = null;
        this.f827h = new g();
        d(attributeSet, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        h hVar = this.f826g;
        if (hVar == null) {
            return;
        }
        Picture p = hVar.p(this.f827h);
        h();
        setImageDrawable(new PictureDrawable(p));
    }

    private void d(AttributeSet attributeSet, int i2) {
        if (isInEditMode()) {
            return;
        }
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.SVGImageView, i2, 0);
        try {
            String string = obtainStyledAttributes.getString(R.styleable.SVGImageView_css);
            if (string != null) {
                this.f827h.a(string);
            }
            int resourceId = obtainStyledAttributes.getResourceId(R.styleable.SVGImageView_svg, -1);
            if (resourceId != -1) {
                setImageResource(resourceId);
                return;
            }
            String string2 = obtainStyledAttributes.getString(R.styleable.SVGImageView_svg);
            if (string2 != null) {
                if (f(Uri.parse(string2))) {
                    return;
                }
                if (e(string2)) {
                    return;
                }
                g(string2);
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    private boolean e(String str) {
        try {
            new c().execute(getContext().getAssets().open(str));
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    private boolean f(Uri uri) {
        try {
            new c().execute(getContext().getContentResolver().openInputStream(uri));
            return true;
        } catch (FileNotFoundException unused) {
            return false;
        }
    }

    private void g(String str) {
        try {
            this.f826g = h.k(str);
            c();
        } catch (k unused) {
            String str2 = "Could not find SVG at: " + str;
        }
    }

    private void h() {
        if (f825i == null) {
            return;
        }
        try {
            f825i.invoke(this, Integer.valueOf(View.class.getField("LAYER_TYPE_SOFTWARE").getInt(new View(getContext()))), null);
        } catch (Exception unused) {
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i2) {
        new b(getContext(), i2).execute(new Integer[0]);
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        if (f(uri)) {
            return;
        }
        String str = "File not found: " + uri;
    }

    public SVGImageView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f826g = null;
        this.f827h = new g();
        d(attributeSet, i2);
    }
}
