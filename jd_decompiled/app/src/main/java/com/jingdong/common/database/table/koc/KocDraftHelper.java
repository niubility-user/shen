package com.jingdong.common.database.table.koc;

import com.jingdong.common.login.LoginUserBase;
import java.util.List;

/* loaded from: classes5.dex */
public class KocDraftHelper {
    private static boolean check(KocDraftEntity kocDraftEntity) {
        return kocDraftEntity != null && kocDraftEntity.check();
    }

    public static int delete(KocDraftEntity kocDraftEntity) {
        if (kocDraftEntity != null) {
            return KocDraftDb.getInstance().getKocDraftDao().delete(kocDraftEntity);
        }
        return 0;
    }

    public static int getDraftCount() {
        return KocDraftDb.getInstance().getKocDraftDao().queryCount(LoginUserBase.getUserPin());
    }

    public static long insert(KocDraftEntity kocDraftEntity) {
        if (check(kocDraftEntity)) {
            return KocDraftDb.getInstance().getKocDraftDao().insert(kocDraftEntity);
        }
        return -1L;
    }

    public static List<KocDraftEntity> query() {
        return KocDraftDb.getInstance().getKocDraftDao().query(LoginUserBase.getUserPin());
    }

    public static int update(KocDraftUpdateEntity kocDraftUpdateEntity) {
        if (check(kocDraftUpdateEntity)) {
            return KocDraftDb.getInstance().getKocDraftDao().update(kocDraftUpdateEntity);
        }
        return 0;
    }

    private static boolean check(KocDraftUpdateEntity kocDraftUpdateEntity) {
        return kocDraftUpdateEntity != null && kocDraftUpdateEntity.check();
    }

    public static KocDraftEntity query(long j2) {
        return KocDraftDb.getInstance().getKocDraftDao().query(LoginUserBase.getUserPin(), j2);
    }

    public static KocDraftEntity query(String str, String str2) {
        return KocDraftDb.getInstance().getKocDraftDao().query(LoginUserBase.getUserPin(), str, str2);
    }
}
