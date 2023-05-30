package com.jingdong.common.hotfix;

/* loaded from: classes5.dex */
public class PatchInfo {
    private static IPatchInstaller installer;
    private static IPatchInfoTeller teller;

    public static String getPatchInfo() {
        IPatchInfoTeller iPatchInfoTeller = teller;
        if (iPatchInfoTeller != null) {
            return iPatchInfoTeller.getPatchInfo();
        }
        return null;
    }

    public static void installPatch(String str) {
        IPatchInstaller iPatchInstaller = installer;
        if (iPatchInstaller != null) {
            iPatchInstaller.installPatch(str);
        }
    }

    public static void setInstaller(IPatchInstaller iPatchInstaller) {
        installer = iPatchInstaller;
    }

    public static void setPatchInfoTeller(IPatchInfoTeller iPatchInfoTeller) {
        teller = iPatchInfoTeller;
    }
}
