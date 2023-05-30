package com.jingdong.common.XView2.entity;

import com.jd.lib.flexcube.layout.entity.MaterialGroup;
import com.jingdong.common.XView2.entity.dynamic.ActionEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes5.dex */
public class XViewConfigEntity {
    public ArrayList<TargetsEntity> targets = new ArrayList<>();
    public String version;

    /* loaded from: classes5.dex */
    public static class ControlEntity {
        public String androidJumpUrl;
        public String bufEnable;
        public String closeAfterClk;
        public long height;
        public String img;
        public String isShow;
        public long left;
        public String mustLocal;
        public String name;
        public long top;
        public String type;
        public long width;
    }

    /* loaded from: classes5.dex */
    public static class LayersEntity extends BaseLayersEntity {
        public String androidJumpUrl;
        public String backgroundColor;
        public String businessID;
        public String channel;
        public String clkUrl;
        public String dragMode;
        public String expoUrl;
        public String fullScreen;
        public boolean ignorePriority;
        public boolean isGlobalWindow;
        public String jmpCls;
        public String layerId;
        public String logicKey;
        public int mutexGroupID;
        public String name;
        public String popUpAnimTy;
        public int priority;
        public int queueStatus;
        public long renderEndTime;
        public long renderStartTime;
        public int renderType;
        public int targetId;
        public String useCloseAnimation;
        public String userInteractionEnabled;
        public RenderDataEntity renderData = new RenderDataEntity();
        public LayoutEntity layout = new LayoutEntity();
        public RuleEntity rule = new RuleEntity();
        public ArrayList<ControlEntity> controls = new ArrayList<>();
        public TimerEntity timer = new TimerEntity();
        public int exclusiveType = 0;
        public ArrayList<PreDownLoadResourceEntity> preDownLoadList = new ArrayList<>();
        public ExtraInfoEntity extraInfo = new ExtraInfoEntity();
    }

    /* loaded from: classes5.dex */
    public static class LayoutEntity {
        public String aBarType;
        public String align;
        public boolean baseNavBottom;
        public boolean baseTabTop;
        public long bottom;
        public String containerTy;
        public String contentAlign;
        public String contentFillMode;
        public String contentForceCenter;
        public String contentVerticalAlign;
        public String fill;
        public long height;
        public long left;
        public String leftOrRight;
        public long radius;
        public long right;
        public int screenHeight;
        public int screenWidth;
        public long top;
        public String topOrBottom;
        public String verticalAlign;
        public long width;
        public long zIndex;
    }

    /* loaded from: classes5.dex */
    public static class RenderDataEntity {
        public String blankCls;
        public int bufBeforeDays;
        public int bufDays;
        public String bufEnable;
        public String contentMode;
        public String disableCloseAfterEnd;
        public String loop;
        public List<MaterialGroup> materialGroupList;
        public String mustLocal;
        public String url;
        public LayoutEntity layout = new LayoutEntity();
        public HashMap<String, ArrayList<ActionEntity>> mInitActionMap = new HashMap<>();
        public HashMap<String, ArrayList<ActionEntity>> mClickActionMap = new HashMap<>();
    }

    /* loaded from: classes5.dex */
    public static class RuleEntity {
        public long beginTime;
        public String channelWhitelist;
        public int dayTimes;
        public long endTime;
        public String loadRequiredBefore;
        public int pageMultiTimes;
        public PopSceneEntity popScene = new PopSceneEntity();
        public int showDuration;
        public int times;
    }

    /* loaded from: classes5.dex */
    public static class TargetsEntity {
        public int blankRateAfterLoad;
        public String desc;
        public int targetId;
        public String targetName;
        public int targetType;
        public ArrayList<LayersEntity> layers = new ArrayList<>();
        public TargetLayoutInfoEntity targetLayoutInfo = new TargetLayoutInfoEntity();
    }
}
