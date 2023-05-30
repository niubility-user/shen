package com.jd.lib.productdetail.core.views;

import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import com.jd.lib.productdetail.core.R;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes15.dex */
public class CartEditCountDialog implements View.OnClickListener, View.OnTouchListener {
    private static final int NUM_DECREASE = 0;
    private static final int NUM_INCREASE = 1;
    private static final int TYPE_2 = 2;
    private Context context;
    private ImageView descreaseView;
    private InputMethodManager imm;
    private ImageView increaseView;
    private JDDialog jdDialogWithStyle9;
    private final EditTextNumChangeListener listener;
    private int maxNum;
    private int minMum;
    private TextWatcher newWatcher = new TextWatcher() { // from class: com.jd.lib.productdetail.core.views.CartEditCountDialog.1
        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            CartEditCountDialog.this.numEdit.removeTextChangedListener(this);
            if (TextUtils.isEmpty(editable.toString())) {
                CartEditCountDialog.this.descreaseView.setEnabled(false);
            } else {
                try {
                    int parseInt = Integer.parseInt(editable.toString());
                    if (CartEditCountDialog.this.isYBMoreParent(parseInt)) {
                        PDUtils.showToastCenterNormal(CartEditCountDialog.this.context, CartEditCountDialog.this.context.getString(R.string.lib_pd_core_cart_pack_not_exceed_main_product_num));
                        CartEditCountDialog.this.numEdit.addTextChangedListener(this);
                        return;
                    }
                    if (parseInt > CartEditCountDialog.this.maxNum) {
                        parseInt = CartEditCountDialog.this.maxNum;
                        if (CartEditCountDialog.this.type == 2) {
                            PDUtils.showToastCenterNormal(CartEditCountDialog.this.context, CartEditCountDialog.this.context.getString(R.string.lib_pd_core_cart_pack_num_less_than, Integer.valueOf(CartEditCountDialog.this.maxNum)));
                        } else {
                            PDUtils.showToastCenterNormal(CartEditCountDialog.this.context, CartEditCountDialog.this.context.getString(R.string.lib_pd_core_cart_product_num_less_than, Integer.valueOf(CartEditCountDialog.this.maxNum)));
                        }
                    } else if (parseInt < 1) {
                        parseInt = 1;
                    }
                    CartEditCountDialog.this.setEditTextByNum(parseInt);
                } catch (NumberFormatException unused) {
                    CartEditCountDialog cartEditCountDialog = CartEditCountDialog.this;
                    cartEditCountDialog.setEditTextByNum(cartEditCountDialog.num);
                }
            }
            CartEditCountDialog.this.numEdit.addTextChangedListener(this);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }
    };
    private int num;
    private EditText numEdit;
    private int type;

    /* loaded from: classes15.dex */
    public interface EditTextNumChangeListener {
        void onCallBack(int i2);

        void onError();
    }

    public CartEditCountDialog(Context context, int i2, int i3, int i4, int i5, EditTextNumChangeListener editTextNumChangeListener) {
        this.context = context;
        this.num = i2;
        this.listener = editTextNumChangeListener;
        this.type = i5;
        this.maxNum = i4;
        this.minMum = i3;
        View inflate = ImageUtil.inflate(context, R.layout.lib_pd_core_giftshopping_mainsku_numedit_layout, (ViewGroup) null);
        this.descreaseView = (ImageView) inflate.findViewById(R.id.lib_pd_edit_num_reduce);
        this.increaseView = (ImageView) inflate.findViewById(R.id.lib_pd_edit_num_add);
        this.numEdit = (EditText) inflate.findViewById(R.id.lib_pd_edit_num);
        JDDialogFactory jDDialogFactory = JDDialogFactory.getInstance();
        Context context2 = this.context;
        JDDialog createJdDialogWithStyle9 = jDDialogFactory.createJdDialogWithStyle9(context2, context2.getString(R.string.lib_pd_core_modify_purchase_count), "", inflate, this.context.getString(R.string.lib_pd_core_cancel), this.context.getString(R.string.lib_pd_core_ok));
        this.jdDialogWithStyle9 = createJdDialogWithStyle9;
        createJdDialogWithStyle9.setCanceledOnTouchOutside(false);
        this.jdDialogWithStyle9.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.jd.lib.productdetail.core.views.CartEditCountDialog.2
            @Override // android.content.DialogInterface.OnShowListener
            public void onShow(DialogInterface dialogInterface) {
                CartEditCountDialog.this.showInputMethod();
                CartEditCountDialog.this.setEditFocusable(true);
            }
        });
        this.jdDialogWithStyle9.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.core.views.CartEditCountDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CartEditCountDialog.this.closeInputMethod();
                CartEditCountDialog.this.jdDialogWithStyle9.dismiss();
            }
        });
        this.jdDialogWithStyle9.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.core.views.CartEditCountDialog.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                try {
                    int parseInt = Integer.parseInt(CartEditCountDialog.this.numEdit.getText().toString());
                    if (CartEditCountDialog.this.minMum >= CartEditCountDialog.this.maxNum) {
                        if (parseInt > CartEditCountDialog.this.maxNum) {
                            parseInt = CartEditCountDialog.this.maxNum;
                        }
                        CartEditCountDialog.this.closeDialog(parseInt);
                        return;
                    }
                    if (parseInt > CartEditCountDialog.this.maxNum) {
                        parseInt = CartEditCountDialog.this.maxNum;
                    }
                    if (parseInt < CartEditCountDialog.this.minMum) {
                        int i6 = CartEditCountDialog.this.minMum;
                        if (CartEditCountDialog.this.minMum > 1) {
                            PDUtils.showToastCenterNormal(CartEditCountDialog.this.context, CartEditCountDialog.this.context.getString(R.string.lib_pd_core_cart_product_num_more_than, Integer.valueOf(CartEditCountDialog.this.minMum)));
                            return;
                        } else {
                            CartEditCountDialog.this.closeDialog(i6);
                            return;
                        }
                    }
                    CartEditCountDialog.this.closeDialog(parseInt);
                } catch (NumberFormatException unused) {
                    if (CartEditCountDialog.this.listener != null) {
                        CartEditCountDialog.this.listener.onError();
                    }
                }
            }
        });
        this.numEdit.addTextChangedListener(this.newWatcher);
        this.numEdit.setOnTouchListener(this);
        setEditTextByNum(i2);
        this.descreaseView.setOnClickListener(this);
        this.increaseView.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeDialog(int i2) {
        this.listener.onCallBack(i2);
        closeInputMethod();
        this.jdDialogWithStyle9.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeInputMethod() {
        InputMethodManager inputMethodManager;
        EditText editText = this.numEdit;
        if (editText == null || (inputMethodManager = this.imm) == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isYBMoreParent(int i2) {
        return false;
    }

    private void refreshEditNum(int i2) {
        EditText editText = this.numEdit;
        if (editText == null) {
            return;
        }
        if (!TextUtils.isEmpty(editText.getText().toString())) {
            int parseInt = Integer.parseInt(this.numEdit.getText().toString());
            if (i2 != 0) {
                if (i2 == 1 && parseInt < this.maxNum) {
                    parseInt++;
                }
            } else if (parseInt > 1) {
                parseInt--;
            }
            if (isYBMoreParent(parseInt)) {
                Context context = this.context;
                PDUtils.showToastCenterNormal(context, context.getString(R.string.lib_pd_core_cart_pack_not_exceed_main_product_num));
                return;
            }
            setEditTextByNum(parseInt);
            return;
        }
        setEditTextByNum(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEditTextByNum(int i2) {
        EditText editText = this.numEdit;
        if (editText == null || editText.getVisibility() != 0) {
            return;
        }
        if (i2 <= 1) {
            this.descreaseView.setEnabled(false);
            i2 = 1;
        } else {
            this.descreaseView.setEnabled(true);
        }
        if (i2 < this.maxNum && !isYBMoreParent(i2)) {
            this.increaseView.setEnabled(true);
        } else {
            i2 = this.maxNum;
            this.increaseView.setEnabled(false);
        }
        this.numEdit.setText(i2 + "");
        try {
            EditText editText2 = this.numEdit;
            editText2.setSelection(editText2.getText().toString().length());
        } catch (Exception e2) {
            if (Log.E) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showInputMethod() {
        if (this.numEdit != null) {
            Object systemService = this.context.getSystemService("input_method");
            if (systemService instanceof InputMethodManager) {
                InputMethodManager inputMethodManager = (InputMethodManager) systemService;
                this.imm = inputMethodManager;
                inputMethodManager.toggleSoftInput(2, 0);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.lib_pd_edit_num_reduce) {
            refreshEditNum(0);
        } else if (id == R.id.lib_pd_edit_num_add) {
            refreshEditNum(1);
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            showInputMethod();
            if (view instanceof EditText) {
                EditText editText = (EditText) view;
                try {
                    editText.setSelection(editText.getText().toString().length());
                } catch (Exception e2) {
                    if (Log.E) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        return true;
    }

    public void setEditFocusable(boolean z) {
        EditText editText = this.numEdit;
        if (editText != null) {
            editText.setFocusable(z);
            this.numEdit.setFocusableInTouchMode(z);
            this.numEdit.requestFocus();
        }
    }

    public void show() {
        JDDialog jDDialog = this.jdDialogWithStyle9;
        if (jDDialog == null || jDDialog.isShowing()) {
            return;
        }
        this.jdDialogWithStyle9.show();
    }
}
