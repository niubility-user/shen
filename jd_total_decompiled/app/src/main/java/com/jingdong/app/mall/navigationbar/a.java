package com.jingdong.app.mall.navigationbar;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.category.JDNewCategoryFragment;
import com.jingdong.app.mall.common.view.JDCommonHostFragment;
import com.jingdong.app.mall.faxianV2.FaxianMainHostFragment;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.jdvideo.view.JDVideoHostFragment;
import com.jingdong.app.mall.navigationbar.entity.NavigationBubbleEntity;
import com.jingdong.app.mall.shopping.JDShopingCartHostFragment;
import com.jingdong.common.unification.customtheme.entity.NavigationInfo;
import com.jingdong.common.unification.navigationbar.IButtonAction;
import com.jingdong.common.unification.navigationbar.NavigationBase;
import com.jingdong.common.unification.navigationbar.NavigationConstants;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.common.utils.PersonalChangeSkinUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.FileService;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes4.dex */
public class a implements IButtonAction {
    private Runnable a;
    private int b;

    /* renamed from: c */
    private Context f11356c;
    private NavigationInfo d;

    public a(int i2) {
        this(c.G().Q(i2, null), i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:494:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:495:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:498:0x01ea A[Catch: Exception -> 0x00e7, TryCatch #1 {Exception -> 0x00e7, blocks: (B:407:0x0086, B:411:0x009c, B:425:0x00c7, B:432:0x00d6, B:438:0x00fe, B:440:0x0103, B:442:0x0109, B:445:0x0113, B:450:0x0123, B:452:0x0129, B:454:0x0131, B:455:0x0138, B:457:0x013e, B:459:0x0146, B:460:0x014d, B:462:0x0153, B:464:0x015b, B:465:0x0162, B:469:0x0170, B:473:0x017f, B:474:0x0182, B:483:0x01bc, B:492:0x01da, B:496:0x01e5, B:498:0x01ea, B:501:0x01f3, B:490:0x01c9, B:472:0x017d, B:468:0x016e, B:448:0x011b, B:428:0x00ce, B:437:0x00ee), top: B:577:0x0082 }] */
    /* JADX WARN: Removed duplicated region for block: B:503:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:504:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:509:0x020a A[Catch: Exception -> 0x0247, TryCatch #2 {Exception -> 0x0247, blocks: (B:403:0x007e, B:507:0x0205, B:509:0x020a, B:513:0x0213), top: B:579:0x007e }] */
    /* JADX WARN: Removed duplicated region for block: B:516:0x0229 A[Catch: Exception -> 0x0245, TryCatch #3 {Exception -> 0x0245, blocks: (B:515:0x0225, B:516:0x0229, B:517:0x022f), top: B:582:0x0101 }] */
    /* JADX WARN: Removed duplicated region for block: B:527:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:528:0x025b  */
    /* JADX WARN: Removed duplicated region for block: B:532:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:535:0x0292  */
    /* JADX WARN: Removed duplicated region for block: B:536:0x02bd  */
    /* JADX WARN: Removed duplicated region for block: B:539:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:544:0x02f3  */
    /* JADX WARN: Removed duplicated region for block: B:554:0x034a  */
    /* JADX WARN: Removed duplicated region for block: B:555:0x0369  */
    /* JADX WARN: Removed duplicated region for block: B:556:0x0388  */
    /* JADX WARN: Removed duplicated region for block: B:557:0x03a7  */
    /* JADX WARN: Removed duplicated region for block: B:562:0x03d1  */
    /* JADX WARN: Removed duplicated region for block: B:563:0x03ef  */
    /* JADX WARN: Removed duplicated region for block: B:564:0x040d  */
    /* JADX WARN: Removed duplicated region for block: B:565:0x042b  */
    /* JADX WARN: Removed duplicated region for block: B:571:0x045e  */
    /* JADX WARN: Removed duplicated region for block: B:586:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v34 */
    /* JADX WARN: Type inference failed for: r1v73 */
    /* JADX WARN: Type inference failed for: r1v74 */
    /* JADX WARN: Type inference failed for: r1v75 */
    @Override // com.jingdong.common.unification.navigationbar.IButtonAction
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void run() {
        String str;
        String str2;
        a aVar;
        String str3;
        boolean z;
        boolean z2;
        Object obj;
        String str4;
        Object obj2;
        String str5;
        String str6 = "marketpic";
        String str7 = "1";
        c.G().d = false;
        c.G().f11363e = false;
        c.G().f11364f = false;
        if (this.d == null) {
            this.d = c.G().O(this.b);
        }
        NavigationBubbleEntity L = c.G().L();
        JDJSONObject jDJSONObject = new JDJSONObject();
        try {
            if (JDElderModeUtils.isElderMode()) {
                jDJSONObject.put("shapetype", "0");
                jDJSONObject.put("shapestatus", "0");
                jDJSONObject.put("bigpic", "0");
                jDJSONObject.put("sourceid", "-100");
                jDJSONObject.put("bubbletype", "-100");
                jDJSONObject.put("bubblestatus", "0");
                jDJSONObject.put("marketpic", "-100");
                jDJSONObject.put("bigmarketpic", "-100");
                jDJSONObject.put("redpoint", "0");
                jDJSONObject.put("text", "");
            } else {
                str = this;
                try {
                    NavigationInfo navigationInfo = str.d;
                    Object obj3 = "2";
                    try {
                        if (navigationInfo != null) {
                            str3 = "marketpic";
                            String str8 = navigationInfo.functionId;
                            z = navigationInfo.hasMarketPic;
                            boolean z3 = navigationInfo.hasAngle;
                            Object obj4 = TextUtils.isEmpty(navigationInfo.marketingLottieUrl) ? "0" : str7;
                            jDJSONObject.put("shapetype", obj4);
                            z2 = z3;
                            try {
                                jDJSONObject.put("shapestatus", (str7.equals(obj4) && NavigationConstants.navigationMarketingShow) ? str7 : "0");
                                jDJSONObject.put("bigpic", str7.equals(str.d.bigIconIsOpen) ? str7 : "0");
                                NavigationInfo navigationInfo2 = str.d;
                                str7 = str7;
                                if (navigationInfo2.hasAngle) {
                                    str5 = "2";
                                } else {
                                    str5 = navigationInfo2.hasRedPoint ? str7 : "0";
                                }
                                jDJSONObject.put("redpoint", str5);
                                jDJSONObject.put("text", str.d.angleLabel);
                                str6 = str8;
                            } catch (Exception e2) {
                                e = e2;
                                str7 = str7;
                                str = "bigmarketpic";
                                str6 = str3;
                                if (OKLog.E) {
                                }
                                jDJSONObject.put("shapetype", "0");
                                jDJSONObject.put("shapestatus", "0");
                                jDJSONObject.put("bigpic", "0");
                                jDJSONObject.put("sourceid", "-100");
                                jDJSONObject.put("bubbletype", "-100");
                                jDJSONObject.put("bubblestatus", "0");
                                jDJSONObject.put(str6, "-100");
                                jDJSONObject.put(str, "-100");
                                jDJSONObject.put("redpoint", "0");
                                jDJSONObject.put("text", "");
                                if (c.G().V()) {
                                }
                                String jSONString = jDJSONObject.toJSONString();
                                if (Log.D) {
                                }
                                switch (aVar.b) {
                                }
                                aVar.a.run();
                                if (NavigationBase.getInstance().navigationCurrentMode != 2) {
                                }
                            }
                        } else {
                            str3 = "marketpic";
                            jDJSONObject.put("shapetype", "0");
                            jDJSONObject.put("shapestatus", "0");
                            jDJSONObject.put("bigpic", "0");
                            str6 = "";
                            z = false;
                            z2 = false;
                        }
                        jDJSONObject.put("sourceid", "-100");
                        try {
                            if (L != null && !TextUtils.isEmpty(str6) && str6.equals(L.position)) {
                                if ((z2 && NavigationBarUtil.angleSwitch()) || z || c.G().f11365g) {
                                    if (!TextUtils.isEmpty(str6) && str6.contains("cart")) {
                                        c.G().f11363e = true;
                                    }
                                    if (!TextUtils.isEmpty(str6) && str6.contains("new")) {
                                        c.G().f11364f = true;
                                    }
                                    if (!TextUtils.isEmpty(str6) && str6.contains("find")) {
                                        c.G().d = true;
                                    }
                                    jDJSONObject.put("biinfo", TextUtils.isEmpty(L.impr) ? "-100" : L.impr);
                                    jDJSONObject.put("sourceid", TextUtils.isEmpty(L.id) ? "-100" : L.id);
                                }
                                jDJSONObject.put("iconstyle", L.shapeType + "");
                                jDJSONObject.put("animationstyle", L.dynamicType + "");
                                int i2 = L.bubbleType;
                                if (i2 != 0 && i2 != 1 && i2 != 2) {
                                    if (i2 == 4) {
                                        int i3 = L.combinationTypes;
                                        if (i3 == 2) {
                                            obj = str7;
                                            jDJSONObject.put("bubbletype", obj);
                                            jDJSONObject.put("bubblestatus", !NavigationConstants.BUBBLE_STUTE_BEFOR ? str7 : "0");
                                            if (z && !TextUtils.isEmpty(L.iconUrl)) {
                                                str4 = !L.iconUrl.endsWith(FileService.CACHE_EXT_NAME_JSON) ? str7 : "0";
                                                str6 = str3;
                                                jDJSONObject.put(str6, str4);
                                                if (!z) {
                                                    if (L.iconSize == -1) {
                                                        obj2 = "-100";
                                                    } else {
                                                        obj2 = L.iconSize + "";
                                                    }
                                                    String str9 = "bigmarketpic";
                                                    jDJSONObject.put(str9, obj2);
                                                    str = str9;
                                                } else {
                                                    String str10 = "bigmarketpic";
                                                    jDJSONObject.put(str10, "-100");
                                                    str = str10;
                                                }
                                            }
                                            str4 = "-100";
                                            str6 = str3;
                                            jDJSONObject.put(str6, str4);
                                            if (!z) {
                                            }
                                        } else if (i3 == 3) {
                                            obj = obj3;
                                            jDJSONObject.put("bubbletype", obj);
                                            jDJSONObject.put("bubblestatus", !NavigationConstants.BUBBLE_STUTE_BEFOR ? str7 : "0");
                                            if (z) {
                                                if (!L.iconUrl.endsWith(FileService.CACHE_EXT_NAME_JSON)) {
                                                }
                                                str6 = str3;
                                                jDJSONObject.put(str6, str4);
                                                if (!z) {
                                                }
                                            }
                                            str4 = "-100";
                                            str6 = str3;
                                            jDJSONObject.put(str6, str4);
                                            if (!z) {
                                            }
                                        }
                                    }
                                    obj = "-100";
                                    jDJSONObject.put("bubbletype", obj);
                                    jDJSONObject.put("bubblestatus", !NavigationConstants.BUBBLE_STUTE_BEFOR ? str7 : "0");
                                    if (z) {
                                    }
                                    str4 = "-100";
                                    str6 = str3;
                                    jDJSONObject.put(str6, str4);
                                    if (!z) {
                                    }
                                }
                                obj3 = i2 + "";
                                obj = obj3;
                                jDJSONObject.put("bubbletype", obj);
                                jDJSONObject.put("bubblestatus", !NavigationConstants.BUBBLE_STUTE_BEFOR ? str7 : "0");
                                if (z) {
                                }
                                str4 = "-100";
                                str6 = str3;
                                jDJSONObject.put(str6, str4);
                                if (!z) {
                                }
                            } else {
                                String str11 = "bigmarketpic";
                                str6 = str3;
                                jDJSONObject.put("sourceid", "-100");
                                jDJSONObject.put("bubbletype", "-100");
                                jDJSONObject.put("bubblestatus", "0");
                                jDJSONObject.put(str6, "-100");
                                jDJSONObject.put(str11, "-100");
                                str = str11;
                            }
                        } catch (Exception e3) {
                            e = e3;
                            if (OKLog.E) {
                                str2 = "ButtonAction";
                            } else {
                                str2 = "ButtonAction";
                                OKLog.e(str2, e.toString());
                            }
                            jDJSONObject.put("shapetype", "0");
                            jDJSONObject.put("shapestatus", "0");
                            jDJSONObject.put("bigpic", "0");
                            jDJSONObject.put("sourceid", "-100");
                            jDJSONObject.put("bubbletype", "-100");
                            jDJSONObject.put("bubblestatus", "0");
                            jDJSONObject.put(str6, "-100");
                            jDJSONObject.put(str, "-100");
                            jDJSONObject.put("redpoint", "0");
                            jDJSONObject.put("text", "");
                            if (c.G().V()) {
                            }
                            String jSONString2 = jDJSONObject.toJSONString();
                            if (Log.D) {
                            }
                            switch (aVar.b) {
                            }
                            aVar.a.run();
                            if (NavigationBase.getInstance().navigationCurrentMode != 2) {
                                return;
                            }
                            return;
                        }
                    } catch (Exception e4) {
                        e = e4;
                    }
                } catch (Exception e5) {
                    e = e5;
                    str = "bigmarketpic";
                }
            }
            str2 = "ButtonAction";
        } catch (Exception e6) {
            e = e6;
            str = "bigmarketpic";
        }
        if (c.G().V()) {
            jDJSONObject.put("text", "\u8fd4\u56de\u539f\u7248\u672c");
        }
        String jSONString22 = jDJSONObject.toJSONString();
        if (Log.D) {
            StringBuilder sb = new StringBuilder();
            sb.append("ButtonAction_run_index=");
            aVar = this;
            sb.append(aVar.b);
            Log.d(str2, sb.toString());
            Log.d(str2, "ButtonAction_run_index=" + jSONString22);
        } else {
            aVar = this;
        }
        switch (aVar.b) {
            case 0:
                if (c.p) {
                    c.p = false;
                    break;
                } else {
                    JDMtaUtils.sendClickDataWithExt(aVar.f11356c, "NavigationBar_Home", "", "", "NavigationBar_Main", "", "", JDHomeFragment.class.getName(), jSONString22, null);
                    break;
                }
            case 1:
                JDMtaUtils.sendClickDataWithExt(aVar.f11356c, "NavigationBar_Classification", "", "", "NavigationBar_Main", "", "", JDNewCategoryFragment.class.getName(), jSONString22, null);
                break;
            case 2:
                JDMtaUtils.sendClickDataWithExt(aVar.f11356c, "NavigationBar_DiscoverNew", "", "", "NavigationBar_Main", "", "", FaxianMainHostFragment.class.getName(), jSONString22, null);
                break;
            case 3:
                JDMtaUtils.sendClickDataWithExt(aVar.f11356c, "NavigationBar_Shopcart", "", "", "NavigationBar_Main", "", "", JDShopingCartHostFragment.class.getName(), jSONString22, null);
                break;
            case 4:
                String str12 = PersonalChangeSkinUtils.isAlreadyChanged ? str7 : "0";
                c.G().j0(false);
                JDMtaUtils.sendClickDataWithExt(aVar.f11356c, "NavigationBar_MyJD", str12, "", "NavigationBar_Main", "", "", "com.jingdong.app.mall.personel.home.JDPersonalHostFragment", jSONString22, null);
                break;
            case 5:
            default:
                NavigationInfo navigationInfo3 = aVar.d;
                if (navigationInfo3 != null && !TextUtils.isEmpty(navigationInfo3.clickEventId)) {
                    JDMtaUtils.sendClickDataWithExt(aVar.f11356c, "NavigationBar_DeployButton", aVar.d.clickEventId, "", "NavigationBar_Main", "", "", JDCommonHostFragment.class.getName(), jSONString22, null);
                    break;
                }
                break;
            case 6:
                JDMtaUtils.sendClickDataWithExt(aVar.f11356c, "NavigationBar_Video", "", "", "NavigationBar_Main", "", "", JDVideoHostFragment.class.getName(), jSONString22, null);
                break;
            case 7:
                JDMtaUtils.sendClickDataWithExt(aVar.f11356c, "NavigationBar_appSearch", "", "", "NavigationBar_Main", "", "", JDNewCategoryFragment.class.getName(), jSONString22, null);
                break;
            case 8:
                JDMtaUtils.sendClickDataWithExt(aVar.f11356c, "NavigationBar_DeployButton", "New", "", "NavigationBar_Main", "", "", JDCommonHostFragment.class.getName(), jSONString22, null);
                break;
            case 9:
                JDJSONObject jDJSONObject2 = new JDJSONObject();
                NavigationInfo navigationInfo4 = aVar.d;
                jDJSONObject2.put("type", (Object) ((navigationInfo4 == null || TextUtils.isEmpty(navigationInfo4.url)) ? "0" : str7));
                if (OKLog.D) {
                    OKLog.d(str2, "gameParam=" + jDJSONObject2.toJSONString());
                }
                JDMtaUtils.sendClickDataWithExt(aVar.f11356c, "NavigationBar_Game", jDJSONObject2.toJSONString(), "", "NavigationBar_Main", "", "", JDMFragment.class.getName(), jSONString22, null);
                break;
        }
        aVar.a.run();
        if (NavigationBase.getInstance().navigationCurrentMode != 2 || NavigationBase.getInstance().mCurrentIndex == 2) {
            return;
        }
        JDRouter.to(aVar.f11356c, "router://JDNavigationModule/setNavigationNormal").open();
    }

    public a(int i2, NavigationInfo navigationInfo) {
        this(c.G().Q(i2, navigationInfo), i2);
        this.d = navigationInfo;
    }

    public a(Runnable runnable, int i2) {
        this(runnable, true, i2);
    }

    public a(Runnable runnable, boolean z, int i2) {
        this.f11356c = JdSdk.getInstance().getApplicationContext();
        new Handler();
        this.b = i2;
        if (runnable == null) {
            return;
        }
        this.a = runnable;
    }
}
