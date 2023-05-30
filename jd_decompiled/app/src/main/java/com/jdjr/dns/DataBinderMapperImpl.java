package com.jdjr.dns;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.jdjr.dns.databinding.SecurityBaseNumberPointCanFinishBindingImpl;
import com.jdjr.dns.databinding.SecurityBaseNumberPointNoFinishBindingImpl;
import com.jdjr.dns.databinding.SecurityBaseNumberPureCanFinishBindingImpl;
import com.jdjr.dns.databinding.SecurityBaseNumberPureNoFinishBindingImpl;
import com.jdjr.dns.databinding.SecurityBaseNumberXCanFinishBindingImpl;
import com.jdjr.dns.databinding.SecurityBaseNumberXNoFinishBindingImpl;
import com.jdjr.dns.databinding.SecurityCompomentVerifyCodeEdittextBindingImpl;
import com.jdjr.dns.databinding.SecurityFunctionalCommonPwdBindingImpl;
import com.jdjr.dns.databinding.SecurityFunctionalLoadingBindingImpl;
import com.jdjr.dns.databinding.SecurityFunctionalPaymentBindingImpl;
import com.jdjr.dns.databinding.SecurityFunctionalSixSquareBindingImpl;
import com.jdjr.dns.databinding.SecurityFunctionalSixUnderlineBindingImpl;
import com.jdjr.dns.databinding.SecurityFunctionalVerifyCodeBindingImpl;
import com.jdjr.dns.databinding.SecurityGeneralBasicKeyboardBindingImpl;
import com.jdjr.dns.databinding.SecurityGeneralFunctionalKeyboardBindingImpl;
import com.jdjr.dns.databinding.SecurityGeneralKeyboardTotalBindingImpl;
import com.jdjr.dns.databinding.SecurityKeyboardKeyPreviewLayoutBindingImpl;
import com.jdjr.dns.databinding.SecurityLayoutFunctionalActionBindingImpl;
import com.jdjr.dns.databinding.SecurityLayoutFunctionalTileBindingImpl;
import com.jdjr.dns.databinding.SecurityLayoutFunctionalUnderlineActionBindingImpl;
import com.jdjr.dns.databinding.SecuritySixSqaureInputLayoutBindingImpl;
import com.jdjr.dns.databinding.SecuritySixUnderlineInputLayoutBindingImpl;
import com.jdjr.dns.databinding.SecurityTotalLetterKeyboardBindingImpl;
import com.jdjr.dns.databinding.SecurityTotalNumberKeyboardBindingImpl;
import com.jdjr.dns.databinding.SecurityTotalSymbolKeyboardPayBindingImpl;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes18.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_SECURITYBASENUMBERPOINTCANFINISH = 1;
    private static final int LAYOUT_SECURITYBASENUMBERPOINTNOFINISH = 2;
    private static final int LAYOUT_SECURITYBASENUMBERPURECANFINISH = 3;
    private static final int LAYOUT_SECURITYBASENUMBERPURENOFINISH = 4;
    private static final int LAYOUT_SECURITYBASENUMBERXCANFINISH = 5;
    private static final int LAYOUT_SECURITYBASENUMBERXNOFINISH = 6;
    private static final int LAYOUT_SECURITYCOMPOMENTVERIFYCODEEDITTEXT = 7;
    private static final int LAYOUT_SECURITYFUNCTIONALCOMMONPWD = 8;
    private static final int LAYOUT_SECURITYFUNCTIONALLOADING = 9;
    private static final int LAYOUT_SECURITYFUNCTIONALPAYMENT = 10;
    private static final int LAYOUT_SECURITYFUNCTIONALSIXSQUARE = 11;
    private static final int LAYOUT_SECURITYFUNCTIONALSIXUNDERLINE = 12;
    private static final int LAYOUT_SECURITYFUNCTIONALVERIFYCODE = 13;
    private static final int LAYOUT_SECURITYGENERALBASICKEYBOARD = 14;
    private static final int LAYOUT_SECURITYGENERALFUNCTIONALKEYBOARD = 15;
    private static final int LAYOUT_SECURITYGENERALKEYBOARDTOTAL = 16;
    private static final int LAYOUT_SECURITYKEYBOARDKEYPREVIEWLAYOUT = 17;
    private static final int LAYOUT_SECURITYLAYOUTFUNCTIONALACTION = 18;
    private static final int LAYOUT_SECURITYLAYOUTFUNCTIONALTILE = 19;
    private static final int LAYOUT_SECURITYLAYOUTFUNCTIONALUNDERLINEACTION = 20;
    private static final int LAYOUT_SECURITYSIXSQAUREINPUTLAYOUT = 21;
    private static final int LAYOUT_SECURITYSIXUNDERLINEINPUTLAYOUT = 22;
    private static final int LAYOUT_SECURITYTOTALLETTERKEYBOARD = 23;
    private static final int LAYOUT_SECURITYTOTALNUMBERKEYBOARD = 24;
    private static final int LAYOUT_SECURITYTOTALSYMBOLKEYBOARDPAY = 25;

    /* loaded from: classes18.dex */
    private static class InnerBrLookup {
        static final SparseArray<String> sKeys;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(3);
            sKeys = sparseArray;
            sparseArray.put(0, "_all");
            sparseArray.put(1, CustomThemeConstance.NAVI_IMAGE_DARK_TAG);
            sparseArray.put(2, "uiMode");
        }

        private InnerBrLookup() {
        }
    }

    /* loaded from: classes18.dex */
    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(25);
            sKeys = hashMap;
            hashMap.put("layout/security_base_number_point_can_finish_0", Integer.valueOf(R.layout.security_base_number_point_can_finish));
            hashMap.put("layout/security_base_number_point_no_finish_0", Integer.valueOf(R.layout.security_base_number_point_no_finish));
            hashMap.put("layout/security_base_number_pure_can_finish_0", Integer.valueOf(R.layout.security_base_number_pure_can_finish));
            hashMap.put("layout/security_base_number_pure_no_finish_0", Integer.valueOf(R.layout.security_base_number_pure_no_finish));
            hashMap.put("layout/security_base_number_x_can_finish_0", Integer.valueOf(R.layout.security_base_number_x_can_finish));
            hashMap.put("layout/security_base_number_x_no_finish_0", Integer.valueOf(R.layout.security_base_number_x_no_finish));
            hashMap.put("layout/security_compoment_verify_code_edittext_0", Integer.valueOf(R.layout.security_compoment_verify_code_edittext));
            hashMap.put("layout/security_functional_common_pwd_0", Integer.valueOf(R.layout.security_functional_common_pwd));
            hashMap.put("layout/security_functional_loading_0", Integer.valueOf(R.layout.security_functional_loading));
            hashMap.put("layout/security_functional_payment_0", Integer.valueOf(R.layout.security_functional_payment));
            hashMap.put("layout/security_functional_six_square_0", Integer.valueOf(R.layout.security_functional_six_square));
            hashMap.put("layout/security_functional_six_underline_0", Integer.valueOf(R.layout.security_functional_six_underline));
            hashMap.put("layout/security_functional_verify_code_0", Integer.valueOf(R.layout.security_functional_verify_code));
            hashMap.put("layout/security_general_basic_keyboard_0", Integer.valueOf(R.layout.security_general_basic_keyboard));
            hashMap.put("layout/security_general_functional_keyboard_0", Integer.valueOf(R.layout.security_general_functional_keyboard));
            hashMap.put("layout/security_general_keyboard_total_0", Integer.valueOf(R.layout.security_general_keyboard_total));
            hashMap.put("layout/security_keyboard_key_preview_layout_0", Integer.valueOf(R.layout.security_keyboard_key_preview_layout));
            hashMap.put("layout/security_layout_functional_action_0", Integer.valueOf(R.layout.security_layout_functional_action));
            hashMap.put("layout/security_layout_functional_tile_0", Integer.valueOf(R.layout.security_layout_functional_tile));
            hashMap.put("layout/security_layout_functional_underline_action_0", Integer.valueOf(R.layout.security_layout_functional_underline_action));
            hashMap.put("layout/security_six_sqaure_input_layout_0", Integer.valueOf(R.layout.security_six_sqaure_input_layout));
            hashMap.put("layout/security_six_underline_input_layout_0", Integer.valueOf(R.layout.security_six_underline_input_layout));
            hashMap.put("layout/security_total_letter_keyboard_0", Integer.valueOf(R.layout.security_total_letter_keyboard));
            hashMap.put("layout/security_total_number_keyboard_0", Integer.valueOf(R.layout.security_total_number_keyboard));
            hashMap.put("layout/security_total_symbol_keyboard_pay_0", Integer.valueOf(R.layout.security_total_symbol_keyboard_pay));
        }

        private InnerLayoutIdLookup() {
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(25);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.security_base_number_point_can_finish, 1);
        sparseIntArray.put(R.layout.security_base_number_point_no_finish, 2);
        sparseIntArray.put(R.layout.security_base_number_pure_can_finish, 3);
        sparseIntArray.put(R.layout.security_base_number_pure_no_finish, 4);
        sparseIntArray.put(R.layout.security_base_number_x_can_finish, 5);
        sparseIntArray.put(R.layout.security_base_number_x_no_finish, 6);
        sparseIntArray.put(R.layout.security_compoment_verify_code_edittext, 7);
        sparseIntArray.put(R.layout.security_functional_common_pwd, 8);
        sparseIntArray.put(R.layout.security_functional_loading, 9);
        sparseIntArray.put(R.layout.security_functional_payment, 10);
        sparseIntArray.put(R.layout.security_functional_six_square, 11);
        sparseIntArray.put(R.layout.security_functional_six_underline, 12);
        sparseIntArray.put(R.layout.security_functional_verify_code, 13);
        sparseIntArray.put(R.layout.security_general_basic_keyboard, 14);
        sparseIntArray.put(R.layout.security_general_functional_keyboard, 15);
        sparseIntArray.put(R.layout.security_general_keyboard_total, 16);
        sparseIntArray.put(R.layout.security_keyboard_key_preview_layout, 17);
        sparseIntArray.put(R.layout.security_layout_functional_action, 18);
        sparseIntArray.put(R.layout.security_layout_functional_tile, 19);
        sparseIntArray.put(R.layout.security_layout_functional_underline_action, 20);
        sparseIntArray.put(R.layout.security_six_sqaure_input_layout, 21);
        sparseIntArray.put(R.layout.security_six_underline_input_layout, 22);
        sparseIntArray.put(R.layout.security_total_letter_keyboard, 23);
        sparseIntArray.put(R.layout.security_total_number_keyboard, 24);
        sparseIntArray.put(R.layout.security_total_symbol_keyboard_pay, 25);
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        return arrayList;
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int i2) {
        return InnerBrLookup.sKeys.get(i2);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i2) {
        int i3 = INTERNAL_LAYOUT_ID_LOOKUP.get(i2);
        if (i3 > 0) {
            Object tag = view.getTag();
            if (tag != null) {
                switch (i3) {
                    case 1:
                        if ("layout/security_base_number_point_can_finish_0".equals(tag)) {
                            return new SecurityBaseNumberPointCanFinishBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for security_base_number_point_can_finish is invalid. Received: " + tag);
                    case 2:
                        if ("layout/security_base_number_point_no_finish_0".equals(tag)) {
                            return new SecurityBaseNumberPointNoFinishBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for security_base_number_point_no_finish is invalid. Received: " + tag);
                    case 3:
                        if ("layout/security_base_number_pure_can_finish_0".equals(tag)) {
                            return new SecurityBaseNumberPureCanFinishBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for security_base_number_pure_can_finish is invalid. Received: " + tag);
                    case 4:
                        if ("layout/security_base_number_pure_no_finish_0".equals(tag)) {
                            return new SecurityBaseNumberPureNoFinishBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for security_base_number_pure_no_finish is invalid. Received: " + tag);
                    case 5:
                        if ("layout/security_base_number_x_can_finish_0".equals(tag)) {
                            return new SecurityBaseNumberXCanFinishBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for security_base_number_x_can_finish is invalid. Received: " + tag);
                    case 6:
                        if ("layout/security_base_number_x_no_finish_0".equals(tag)) {
                            return new SecurityBaseNumberXNoFinishBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for security_base_number_x_no_finish is invalid. Received: " + tag);
                    case 7:
                        if ("layout/security_compoment_verify_code_edittext_0".equals(tag)) {
                            return new SecurityCompomentVerifyCodeEdittextBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for security_compoment_verify_code_edittext is invalid. Received: " + tag);
                    case 8:
                        if ("layout/security_functional_common_pwd_0".equals(tag)) {
                            return new SecurityFunctionalCommonPwdBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for security_functional_common_pwd is invalid. Received: " + tag);
                    case 9:
                        if ("layout/security_functional_loading_0".equals(tag)) {
                            return new SecurityFunctionalLoadingBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for security_functional_loading is invalid. Received: " + tag);
                    case 10:
                        if ("layout/security_functional_payment_0".equals(tag)) {
                            return new SecurityFunctionalPaymentBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for security_functional_payment is invalid. Received: " + tag);
                    case 11:
                        if ("layout/security_functional_six_square_0".equals(tag)) {
                            return new SecurityFunctionalSixSquareBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for security_functional_six_square is invalid. Received: " + tag);
                    case 12:
                        if ("layout/security_functional_six_underline_0".equals(tag)) {
                            return new SecurityFunctionalSixUnderlineBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for security_functional_six_underline is invalid. Received: " + tag);
                    case 13:
                        if ("layout/security_functional_verify_code_0".equals(tag)) {
                            return new SecurityFunctionalVerifyCodeBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for security_functional_verify_code is invalid. Received: " + tag);
                    case 14:
                        if ("layout/security_general_basic_keyboard_0".equals(tag)) {
                            return new SecurityGeneralBasicKeyboardBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for security_general_basic_keyboard is invalid. Received: " + tag);
                    case 15:
                        if ("layout/security_general_functional_keyboard_0".equals(tag)) {
                            return new SecurityGeneralFunctionalKeyboardBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for security_general_functional_keyboard is invalid. Received: " + tag);
                    case 16:
                        if ("layout/security_general_keyboard_total_0".equals(tag)) {
                            return new SecurityGeneralKeyboardTotalBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for security_general_keyboard_total is invalid. Received: " + tag);
                    case 17:
                        if ("layout/security_keyboard_key_preview_layout_0".equals(tag)) {
                            return new SecurityKeyboardKeyPreviewLayoutBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for security_keyboard_key_preview_layout is invalid. Received: " + tag);
                    case 18:
                        if ("layout/security_layout_functional_action_0".equals(tag)) {
                            return new SecurityLayoutFunctionalActionBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for security_layout_functional_action is invalid. Received: " + tag);
                    case 19:
                        if ("layout/security_layout_functional_tile_0".equals(tag)) {
                            return new SecurityLayoutFunctionalTileBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for security_layout_functional_tile is invalid. Received: " + tag);
                    case 20:
                        if ("layout/security_layout_functional_underline_action_0".equals(tag)) {
                            return new SecurityLayoutFunctionalUnderlineActionBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for security_layout_functional_underline_action is invalid. Received: " + tag);
                    case 21:
                        if ("layout/security_six_sqaure_input_layout_0".equals(tag)) {
                            return new SecuritySixSqaureInputLayoutBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for security_six_sqaure_input_layout is invalid. Received: " + tag);
                    case 22:
                        if ("layout/security_six_underline_input_layout_0".equals(tag)) {
                            return new SecuritySixUnderlineInputLayoutBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for security_six_underline_input_layout is invalid. Received: " + tag);
                    case 23:
                        if ("layout/security_total_letter_keyboard_0".equals(tag)) {
                            return new SecurityTotalLetterKeyboardBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for security_total_letter_keyboard is invalid. Received: " + tag);
                    case 24:
                        if ("layout/security_total_number_keyboard_0".equals(tag)) {
                            return new SecurityTotalNumberKeyboardBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for security_total_number_keyboard is invalid. Received: " + tag);
                    case 25:
                        if ("layout/security_total_symbol_keyboard_pay_0".equals(tag)) {
                            return new SecurityTotalSymbolKeyboardPayBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException("The tag for security_total_symbol_keyboard_pay is invalid. Received: " + tag);
                    default:
                        return null;
                }
            }
            throw new RuntimeException("view must have a tag");
        }
        return null;
    }

    @Override // androidx.databinding.DataBinderMapper
    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = InnerLayoutIdLookup.sKeys.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i2) {
        if (viewArr == null || viewArr.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(i2) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }
}
