package com.jingdong.common.unification.video;

import com.jingdong.common.unification.filter.FilterTools;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes6.dex */
public class VideoParam implements Serializable {
    public int editorFunctionControl;
    public String editorVideoPath;
    public List<FilterTools.FilterType> filterTypes;
    public boolean isSquarePhoto;
    public boolean needEditor;
    public boolean needEditorPic;
    public List<FilterTools.FilterType> picFilterTypes;
    public int recordCurrentState;
    public int recordFunctionControl;
    public int squarePhotoWidth;
    public int recordMinTime = 3;
    public int recordMaxTime = 10;
    public long cutMinTime = 3000;
    public long cutMaxTime = 10000;
}
