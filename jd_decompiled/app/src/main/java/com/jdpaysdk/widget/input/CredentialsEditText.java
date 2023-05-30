package com.jdpaysdk.widget.input;

import android.content.Context;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpaysdk.widget.input.abs.AbsEditText;
import com.jdpaysdk.widget.input.fiilter.ForeignIDCardFormatFilter;
import com.jdpaysdk.widget.input.fiilter.IDCardFormatFilter;
import com.jdpaysdk.widget.input.fiilter.abs.AbsMaxInputFilter;
import com.jdpaysdk.widget.input.listener.ForeignIDCardKeyListener;
import com.jdpaysdk.widget.input.listener.IDCardKeyListener;
import com.jdpaysdk.widget.input.listener.abs.AbsKeyListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes18.dex */
public class CredentialsEditText extends AbsEditText {
    @NonNull
    CredentialsType type;

    /* loaded from: classes18.dex */
    private static class CredentialsFormatFilter implements InputFilter, CredentialsType.Listener {
        private int currentType = -1;
        private final IDCardFormatFilter idCardFormatFilter = new IDCardFormatFilter();
        private final ForeignIDCardFormatFilter foreignIDCardFormatFilter = new ForeignIDCardFormatFilter();

        public CredentialsFormatFilter(@NonNull CredentialsType credentialsType) {
            credentialsType.addListener(this);
        }

        @Override // android.text.InputFilter
        public CharSequence filter(CharSequence charSequence, int i2, int i3, Spanned spanned, int i4, int i5) {
            int i6 = this.currentType;
            if (i6 != 0) {
                if (i6 != 4) {
                    return null;
                }
                return this.foreignIDCardFormatFilter.filter(charSequence, i2, i3, spanned, i4, i5);
            }
            return this.idCardFormatFilter.filter(charSequence, i2, i3, spanned, i4, i5);
        }

        @Override // com.jdpaysdk.widget.input.CredentialsEditText.CredentialsType.Listener
        public void onChange(int i2) {
            this.currentType = i2;
        }
    }

    /* loaded from: classes18.dex */
    private static class CredentialsListener extends AbsKeyListener implements CredentialsType.Listener {
        private int currentType;
        private final ForeignIDCardKeyListener foreignIDCardKeyListener;
        private final IDCardKeyListener idCardKeyListener;

        public CredentialsListener(@NonNull AbsEditText absEditText, @NonNull CredentialsType credentialsType) {
            super(absEditText);
            this.currentType = -1;
            this.idCardKeyListener = new IDCardKeyListener(absEditText);
            this.foreignIDCardKeyListener = new ForeignIDCardKeyListener(absEditText);
            credentialsType.addListener(this);
        }

        @Override // com.jdpaysdk.widget.input.listener.abs.AbsKeyListener
        public boolean accept(int i2, int i3, int i4, @NonNull SpannableStringBuilder spannableStringBuilder, @NonNull SpannableStringBuilder spannableStringBuilder2, int i5) {
            int i6 = this.currentType;
            if (i6 != 0) {
                if (i6 != 4) {
                    return true;
                }
                return this.foreignIDCardKeyListener.accept(i2, i3, i4, spannableStringBuilder, spannableStringBuilder2, i5);
            }
            return this.idCardKeyListener.accept(i2, i3, i4, spannableStringBuilder, spannableStringBuilder2, i5);
        }

        @Override // com.jdpaysdk.widget.input.listener.abs.AbsKeyListener, android.text.method.KeyListener
        public int getInputType() {
            int i2 = this.currentType;
            if (i2 != 0) {
                if (i2 != 4) {
                    return 2;
                }
                return this.foreignIDCardKeyListener.getInputType();
            }
            return this.idCardKeyListener.getInputType();
        }

        @Override // com.jdpaysdk.widget.input.CredentialsEditText.CredentialsType.Listener
        public void onChange(int i2) {
            this.currentType = i2;
        }

        @Override // com.jdpaysdk.widget.input.listener.abs.AbsKeyListener
        public void onInputError(int i2, CharSequence charSequence) {
            int i3 = this.currentType;
            if (i3 == 0) {
                this.idCardKeyListener.onInputError(i2, charSequence);
            } else if (i3 != 4) {
            } else {
                this.foreignIDCardKeyListener.onInputError(i2, charSequence);
            }
        }
    }

    /* loaded from: classes18.dex */
    private static class CredentialsMaxInputFilter extends AbsMaxInputFilter implements CredentialsType.Listener {
        private int currentType = -1;

        public CredentialsMaxInputFilter(@NonNull CredentialsType credentialsType) {
            credentialsType.addListener(this);
        }

        @Override // com.jdpaysdk.widget.input.fiilter.abs.AbsMaxInputFilter
        protected int getMaxLength() {
            int i2 = this.currentType;
            if (i2 != 0) {
                return i2 != 4 ? Integer.MAX_VALUE : 15;
            }
            return 18;
        }

        @Override // com.jdpaysdk.widget.input.CredentialsEditText.CredentialsType.Listener
        public void onChange(int i2) {
            this.currentType = i2;
        }
    }

    /* loaded from: classes18.dex */
    public static class CredentialsType {
        public static final int TYPE_CE = 5;
        public static final int TYPE_FRP = 4;
        public static final int TYPE_HO = 2;
        public static final int TYPE_ID = 0;
        public static final int TYPE_OF = 6;
        public static final int TYPE_PA = 1;
        public static final int TYPE_SO = 7;
        public static final int TYPE_TW = 3;
        public static final int TYPE_UNKNOWN = -1;
        private final Set<Listener> listeners = new HashSet();
        private int currentType = -1;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes18.dex */
        public interface Listener {
            void onChange(int i2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addListener(Listener listener) {
            if (listener == null) {
                return;
            }
            this.listeners.add(listener);
            listener.onChange(this.currentType);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCurrentType(int i2) {
            if (this.currentType != i2) {
                this.currentType = i2;
                Iterator<Listener> it = this.listeners.iterator();
                while (it.hasNext()) {
                    it.next().onChange(i2);
                }
            }
        }
    }

    public CredentialsEditText(@NonNull Context context) {
        super(context);
        this.type = new CredentialsType();
        KeyListener credentialsListener = new CredentialsListener(this, this.type);
        setKeyListener(credentialsListener);
        InputFilter[] filters = getFilters();
        int length = filters.length + 4;
        InputFilter[] inputFilterArr = new InputFilter[length];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = credentialsListener;
        inputFilterArr[1] = new CredentialsMaxInputFilter(this.type);
        inputFilterArr[length - 2] = new InputFilter.AllCaps();
        inputFilterArr[length - 1] = new CredentialsFormatFilter(this.type);
        setFilters(inputFilterArr);
    }

    public void setType(int i2) {
        this.type.setCurrentType(i2);
        refreshContent();
    }

    public CredentialsEditText(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.type = new CredentialsType();
        KeyListener credentialsListener = new CredentialsListener(this, this.type);
        setKeyListener(credentialsListener);
        InputFilter[] filters = getFilters();
        int length = filters.length + 4;
        InputFilter[] inputFilterArr = new InputFilter[length];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = credentialsListener;
        inputFilterArr[1] = new CredentialsMaxInputFilter(this.type);
        inputFilterArr[length - 2] = new InputFilter.AllCaps();
        inputFilterArr[length - 1] = new CredentialsFormatFilter(this.type);
        setFilters(inputFilterArr);
    }

    public CredentialsEditText(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.type = new CredentialsType();
        KeyListener credentialsListener = new CredentialsListener(this, this.type);
        setKeyListener(credentialsListener);
        InputFilter[] filters = getFilters();
        int length = filters.length + 4;
        InputFilter[] inputFilterArr = new InputFilter[length];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = credentialsListener;
        inputFilterArr[1] = new CredentialsMaxInputFilter(this.type);
        inputFilterArr[length - 2] = new InputFilter.AllCaps();
        inputFilterArr[length - 1] = new CredentialsFormatFilter(this.type);
        setFilters(inputFilterArr);
    }
}
