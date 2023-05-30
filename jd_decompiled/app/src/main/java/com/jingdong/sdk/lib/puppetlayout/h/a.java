package com.jingdong.sdk.lib.puppetlayout.h;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.yoga2.YogaDisplay;
import com.facebook.yoga2.YogaNode;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.sdk.lib.puppetlayout.view.ui.builder.k;
import com.jingdong.sdk.lib.puppetlayout.ylayout.ActionProcessor;
import com.jingdong.sdk.lib.puppetlayout.ylayout.DrawableUtils;
import com.jingdong.sdk.lib.puppetlayout.ylayout.LayoutUtils;
import com.jingdong.sdk.lib.puppetlayout.ylayout.PuppetContext;
import com.jingdong.sdk.lib.puppetlayout.ylayout.android.YogaLayout;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.Action;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.Iterate;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes8.dex */
public abstract class a {
    protected View a;
    public PuppetContext b;

    /* renamed from: c */
    private com.jingdong.sdk.lib.puppetlayout.h.d.a f15218c = new com.jingdong.sdk.lib.puppetlayout.h.d.a();
    private HashMap<String, String> d = new HashMap<>();

    /* renamed from: e */
    boolean f15219e = false;

    /* renamed from: f */
    private HashMap<String, Integer> f15220f = new HashMap<>();

    /* renamed from: g */
    boolean f15221g = false;

    /* renamed from: h */
    private View.OnClickListener f15222h = null;

    /* renamed from: i */
    private ActionProcessor f15223i = new ActionProcessor();

    /* renamed from: j */
    public ArrayList<Action> f15224j;

    /* renamed from: com.jingdong.sdk.lib.puppetlayout.h.a$a */
    /* loaded from: classes8.dex */
    public class ViewOnClickListenerC0739a implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ PuppetContext f15225g;

        ViewOnClickListenerC0739a(PuppetContext puppetContext) {
            a.this = r1;
            this.f15225g = puppetContext;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            a.this.f15223i.process(this.f15225g, a.this.f15224j, false);
            if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                com.jingdong.sdk.lib.puppetlayout.g.b.a("setClickAction", "setClickAction onClick : PuppetViewCreator " + a.this + ", actions : " + a.this.f15224j);
            }
        }
    }

    private int g(String str) {
        Integer num = this.f15220f.get(str);
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x007c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x00f2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0156 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean i() {
        /*
            Method dump skipped, instructions count: 388
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.lib.puppetlayout.h.a.i():boolean");
    }

    private boolean j() {
        if (this.f15220f.size() == 0) {
            return false;
        }
        this.a.setPadding(g("paddingLeft"), g("paddingTop"), g("paddingRight"), g("paddingBottom"));
        return true;
    }

    private void k(String str, String str2) {
        this.d.put(str, str2);
        this.f15219e = true;
    }

    private void p(String str, int i2) {
        this.f15220f.put(str, Integer.valueOf(i2));
        this.f15221g = true;
    }

    public abstract void d(Context context);

    public void e(Context context, View view) {
    }

    public ActionProcessor f() {
        return this.f15223i;
    }

    public View h() {
        return this.a;
    }

    public void l(PuppetContext puppetContext, ArrayList<Action> arrayList) {
        if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
            com.jingdong.sdk.lib.puppetlayout.g.b.a("setClickAction", "setClickAction set : PuppetViewCreator " + this + ", actions : " + arrayList);
        }
        if (arrayList != null && arrayList.size() > 0) {
            this.f15224j = arrayList;
            if (this.f15222h == null) {
                ViewOnClickListenerC0739a viewOnClickListenerC0739a = new ViewOnClickListenerC0739a(puppetContext);
                this.f15222h = viewOnClickListenerC0739a;
                this.a.setOnClickListener(viewOnClickListenerC0739a);
            }
        } else if (this.f15224j != null) {
            this.f15224j = null;
            this.f15222h = null;
            this.a.setOnClickListener(null);
        }
    }

    public void m(JDJSONObject jDJSONObject, JDJSONArray jDJSONArray, Iterate iterate) {
    }

    protected boolean n(String str, String str2) {
        if (ViewProps.DISPLAY.equals(str)) {
            YogaNode yogaNode = null;
            View view = this.a;
            if (view instanceof YogaLayout) {
                yogaNode = ((YogaLayout) view).getYogaNode();
            } else if (view.getParent() instanceof YogaLayout) {
                yogaNode = ((YogaLayout) this.a.getParent()).getYogaNodeForView(this.a);
            }
            if (yogaNode != null) {
                try {
                    YogaDisplay fromInt = YogaDisplay.fromInt(Math.round(LayoutUtils.getValue(str, str2)));
                    yogaNode.setDisplay(fromInt);
                    if (fromInt == YogaDisplay.FLEX) {
                        this.a.setVisibility(0);
                    } else {
                        this.a.setVisibility(8);
                    }
                    this.b.isRequestLayoutOnBind = true;
                    return true;
                } catch (Exception e2) {
                    if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        return false;
    }

    public void o(String str, String str2) {
        n(str, str2);
    }

    public boolean q(String str, String str2, String str3) {
        if ("id".equals(str)) {
            this.a.setTag(str2);
            return true;
        } else if (!DYConstants.DY_BG_COLOR.equals(str) && !ViewProps.BACKGROUND_COLOR.equals(str)) {
            if ("highlightBackgroundColor".equals(str)) {
                k(str, str2);
                return true;
            } else if ("borderColor".equals(str)) {
                k(str, str2);
                return true;
            } else if ("borderWidth".equals(str)) {
                k(str, str2);
                return true;
            } else if ("roundCornerTopLeft".equals(str)) {
                k(str, str2);
                return true;
            } else if ("roundCornerTopRight".equals(str)) {
                k(str, str2);
                return true;
            } else if ("roundCornerBottomLeft".equals(str)) {
                k(str, str2);
                return true;
            } else if ("roundCornerBottomRight".equals(str)) {
                k(str, str2);
                return true;
            } else if ("gravity_in".equals(str)) {
                this.f15218c.a(this.a, str2);
                return true;
            } else if ("hiddenType".equals(str)) {
                if (!TextUtils.isEmpty(str2)) {
                    if (this.b.isEditing) {
                        if ("0".equals(str2)) {
                            this.a.setVisibility(0);
                        } else if ("1".equals(str2)) {
                            this.a.setVisibility(8);
                        } else if ("2".equals(str2)) {
                            this.a.setVisibility(0);
                        } else if ("3".equals(str2)) {
                            this.a.setVisibility(8);
                        }
                    } else if ("0".equals(str2)) {
                        this.a.setVisibility(0);
                    } else if ("1".equals(str2)) {
                        this.a.setVisibility(8);
                    } else if ("2".equals(str2)) {
                        this.a.setVisibility(8);
                    } else if ("3".equals(str2)) {
                        this.a.setVisibility(0);
                    }
                }
                return true;
            } else if ("showType".equals(str)) {
                if (!TextUtils.isEmpty(str2) && !DYConstants.DY_FALSE.equals(str2)) {
                    this.a.setVisibility(0);
                } else {
                    this.a.setVisibility(8);
                }
                return true;
            } else if ("backgroundImage".equals(str)) {
                try {
                    if (!TextUtils.isEmpty(str2)) {
                        HashMap hashMap = new HashMap();
                        for (String str4 : str2.split(DYConstants.DY_REGEX_COMMA)) {
                            if (!TextUtils.isEmpty(str4)) {
                                String[] split = str4.split(":");
                                if (split.length == 2 && split[0] != null && split[1] != null) {
                                    hashMap.put(split[0], split[1]);
                                }
                            }
                        }
                        String str5 = (String) hashMap.get("android");
                        if (!TextUtils.isEmpty(str5)) {
                            String[] split2 = str5.split("/");
                            String packageName = this.a.getContext().getPackageName();
                            String str6 = "";
                            if (split2.length >= 2) {
                                str6 = split2[1];
                                packageName = split2[0];
                            } else if (split2.length == 1) {
                                str6 = split2[0];
                            }
                            int identifier = this.a.getContext().getResources().getIdentifier(str6, "drawable", packageName);
                            if (identifier != 0) {
                                this.a.setBackgroundResource(identifier);
                            }
                        }
                    }
                } catch (Exception e2) {
                    if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                        e2.printStackTrace();
                    }
                }
                this.f15219e = false;
                return true;
            } else if ("tag".equals(str)) {
                this.a.setTag(str2);
                return true;
            } else if ("roundCornerRadius".equals(str)) {
                if (this instanceof k) {
                    return false;
                }
                k(str, str2);
                return true;
            } else if ("paddingLeft".equals(str)) {
                float dpValue = LayoutUtils.getDpValue(str2, -1.0f);
                if (dpValue != -1.0f) {
                    p(str, (int) dpValue);
                }
                return true;
            } else if ("paddingTop".equals(str)) {
                float dpValue2 = LayoutUtils.getDpValue(str2, -1.0f);
                if (dpValue2 != -1.0f) {
                    p(str, (int) dpValue2);
                }
                return true;
            } else if ("paddingRight".equals(str)) {
                float dpValue3 = LayoutUtils.getDpValue(str2, -1.0f);
                if (dpValue3 != -1.0f) {
                    p(str, (int) dpValue3);
                }
                return true;
            } else if ("paddingBottom".equals(str)) {
                float dpValue4 = LayoutUtils.getDpValue(str2, -1.0f);
                if (dpValue4 != -1.0f) {
                    p(str, (int) dpValue4);
                }
                return true;
            } else if ("roundAsCircle".equals(str)) {
                if (this instanceof k) {
                    return false;
                }
                k(str, str2);
                return true;
            } else if ("backgroundDash".equals(str)) {
                try {
                    if (!TextUtils.isEmpty(str2)) {
                        String[] split3 = str2.split(DYConstants.DY_REGEX_COMMA);
                        if (split3.length == 4) {
                            DrawableUtils.setCompactBg(this.a, DrawableUtils.createDashLine(Integer.valueOf(split3[0]).intValue(), Color.parseColor(split3[1]), Float.valueOf(split3[2]).floatValue(), Float.valueOf(split3[3]).floatValue()));
                        }
                    }
                } catch (Exception e3) {
                    if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                        e3.printStackTrace();
                    }
                }
                this.f15219e = false;
                return true;
            } else {
                return false;
            }
        } else {
            k(str, str2);
            return true;
        }
    }

    public void r(String str, String str2) {
        q(str, str2, "attribute");
    }

    public void s() {
        if (this.f15219e) {
            i();
            this.f15219e = false;
        }
        if (this.f15221g) {
            j();
            this.f15221g = false;
        }
        t();
    }

    protected void t() {
    }
}
