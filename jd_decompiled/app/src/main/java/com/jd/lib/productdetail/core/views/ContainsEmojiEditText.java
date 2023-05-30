package com.jd.lib.productdetail.core.views;

import android.content.Context;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

/* loaded from: classes15.dex */
public class ContainsEmojiEditText extends EditText {
    private static final int LENGTH_2 = 2;
    private static final String TAG = "ContainsEmojiEditText";
    private int cursorPos;
    private String inputAfterText;
    private boolean resetText;

    public ContainsEmojiEditText(Context context) {
        super(context);
        initEditText();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean containsEmoji(String str) {
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            if (!isEmojiCharacter(str.charAt(i2))) {
                return true;
            }
        }
        return false;
    }

    private void initEditText() {
        addTextChangedListener(new TextWatcher() { // from class: com.jd.lib.productdetail.core.views.ContainsEmojiEditText.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                if (ContainsEmojiEditText.this.resetText) {
                    return;
                }
                ContainsEmojiEditText containsEmojiEditText = ContainsEmojiEditText.this;
                containsEmojiEditText.cursorPos = containsEmojiEditText.getSelectionEnd();
                ContainsEmojiEditText.this.inputAfterText = charSequence.toString();
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                if (ContainsEmojiEditText.this.resetText) {
                    ContainsEmojiEditText.this.resetText = false;
                } else if (i4 >= 2) {
                    try {
                        if (ContainsEmojiEditText.containsEmoji(charSequence.subSequence(ContainsEmojiEditText.this.cursorPos, ContainsEmojiEditText.this.cursorPos + i4).toString())) {
                            ContainsEmojiEditText.this.resetText = true;
                            ContainsEmojiEditText containsEmojiEditText = ContainsEmojiEditText.this;
                            containsEmojiEditText.setText(containsEmojiEditText.inputAfterText);
                            Editable text = ContainsEmojiEditText.this.getText();
                            if (TextUtils.isEmpty(text)) {
                                return;
                            }
                            Selection.setSelection(text, text.length());
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });
    }

    private static boolean isEmojiCharacter(char c2) {
        return c2 == 0 || c2 == '\t' || c2 == '\n' || c2 == '\r' || (c2 >= ' ' && c2 <= '\ud7ff') || ((c2 >= '\ue000' && c2 <= '\ufffd') || (c2 >= 0 && c2 <= '\uffff'));
    }

    public ContainsEmojiEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initEditText();
    }

    public ContainsEmojiEditText(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        initEditText();
    }
}
