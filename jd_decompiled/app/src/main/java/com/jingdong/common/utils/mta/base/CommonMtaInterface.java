package com.jingdong.common.utils.mta.base;

import androidx.fragment.app.Fragment;
import com.facebook.react.uimanager.events.TouchesHelper;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.entity.personal.LibMtaEntity;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b`\u0018\u00002\u00020\u0001J%\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H&\u00a2\u0006\u0004\b\u0007\u0010\bJ%\u0010\n\u001a\u00020\u00062\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\t\u001a\u0004\u0018\u00010\u0002H&\u00a2\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\u0006H&\u00a2\u0006\u0004\b\f\u0010\rJ!\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00022\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH&\u00a2\u0006\u0004\b\u0011\u0010\u0012J!\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00022\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H&\u00a2\u0006\u0004\b\u0011\u0010\u0015J\u0017\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0016\u0010\u0017\u00a8\u0006\u0018"}, d2 = {"Lcom/jingdong/common/utils/mta/base/CommonMtaInterface;", "", "", "refId", "Lcom/jingdong/common/entity/personal/LibMtaEntity;", "mtaEntity", "", "addExposureEntity", "(Ljava/lang/String;Lcom/jingdong/common/entity/personal/LibMtaEntity;)V", "jsonParam", "addJsonParam", "(Ljava/lang/String;Ljava/lang/String;)V", "doMta", "()V", "key", "Landroidx/fragment/app/Fragment;", TouchesHelper.TARGET_KEY, "attachLifecycleOwner", "(Ljava/lang/String;Landroidx/fragment/app/Fragment;)V", "Lcom/jingdong/common/BaseActivity;", "activity", "(Ljava/lang/String;Lcom/jingdong/common/BaseActivity;)V", "release", "(Ljava/lang/String;)V", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public interface CommonMtaInterface {

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes6.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ void addExposureEntity$default(CommonMtaInterface commonMtaInterface, String str, LibMtaEntity libMtaEntity, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 1) != 0) {
                    str = null;
                }
                commonMtaInterface.addExposureEntity(str, libMtaEntity);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addExposureEntity");
        }

        public static /* synthetic */ void addJsonParam$default(CommonMtaInterface commonMtaInterface, String str, String str2, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 1) != 0) {
                    str = null;
                }
                commonMtaInterface.addJsonParam(str, str2);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addJsonParam");
        }
    }

    void addExposureEntity(@Nullable String refId, @Nullable LibMtaEntity mtaEntity);

    void addJsonParam(@Nullable String refId, @Nullable String jsonParam);

    void attachLifecycleOwner(@NotNull String key, @Nullable Fragment target);

    void attachLifecycleOwner(@NotNull String key, @Nullable BaseActivity activity);

    void doMta();

    void release(@NotNull String key);
}
