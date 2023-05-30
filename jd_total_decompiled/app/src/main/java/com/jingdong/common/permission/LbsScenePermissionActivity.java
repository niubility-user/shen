package com.jingdong.common.permission;

import android.content.Context;
import android.content.res.JDMobiSec;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.R;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.common.ui.UnAddressSelectUtils;
import com.jingdong.common.unification.title.theme.JdThemeTitle;
import com.jingdong.common.widget.button.UnCheckBox;
import com.jingdong.sdk.jdtoast.ToastUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class LbsScenePermissionActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private JdThemeTitle title;

    /* loaded from: classes5.dex */
    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private Context context;
        private List<Map<String, Object>> data;

        public MyAdapter(List<Map<String, Object>> list, Context context) {
            this.data = list;
            this.context = context;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String checkSceneContent(String str) {
            str.hashCode();
            char c2 = '\uffff';
            switch (str.hashCode()) {
                case 126020275:
                    if (str.equals("marketingActivities")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case 179587985:
                    if (str.equals("receiveAddress")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 215622105:
                    if (str.equals("basicShoppingProcess")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case 1580766037:
                    if (str.equals("locService")) {
                        c2 = 3;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    return PermissionConstants.LBS_SCENE_DIALOG_CONTENT_MARKET;
                case 1:
                    return PermissionConstants.LBS_SCENE_DIALOG_CONTENT_ADDRESS;
                case 2:
                    return PermissionConstants.LBS_SCENE_DIALOG_CONTENT_BASE;
                case 3:
                    return PermissionConstants.LBS_SCENE_DIALOG_CONTENT_LOC;
                default:
                    return PermissionConstants.LBS_SCENE_BACKUP_CONTENT;
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            List<Map<String, Object>> list = this.data;
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i2) {
            Map<String, Object> map = this.data.get(i2);
            final String str = (String) map.get("sceneId");
            boolean booleanValue = ((Boolean) map.get("sceneSwitch")).booleanValue();
            myViewHolder.title.setText((String) map.get("title"));
            myViewHolder.sceneSwitch.setChecked(booleanValue);
            myViewHolder.content.setText((String) map.get("content"));
            myViewHolder.sceneSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.jingdong.common.permission.LbsScenePermissionActivity.MyAdapter.1
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public void onCheckedChanged(CompoundButton compoundButton, final boolean z) {
                    if (!UnAddressSelectUtils.hasLocationPermission() && z) {
                        Bundle generateBundle = PermissionHelper.generateBundle(null, "lbsSceneSetting", LbsScenePermissionActivity.class.getSimpleName(), "switchChange", new String[]{PermissionConstants.LBS_DEFAULT_TITLE}, new String[]{MyAdapter.this.checkSceneContent(str)});
                        generateBundle.putBoolean(PermissionHelper.PARAM_USER_INITIATIVE, true);
                        PermissionHelper.hasGrantedLocation(LbsScenePermissionActivity.this, generateBundle, new PermissionHelper.PermissionResultCallBack() { // from class: com.jingdong.common.permission.LbsScenePermissionActivity.MyAdapter.1.1
                            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                            public void onCanceled() {
                                super.onCanceled();
                                myViewHolder.sceneSwitch.setChecked(false);
                            }

                            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                            public void onDenied() {
                                super.onDenied();
                                myViewHolder.sceneSwitch.setChecked(false);
                                ToastUtils.showToast(LbsScenePermissionActivity.this, "\u5f53\u524d\u6ca1\u6709\u5b9a\u4f4d\u6743\u9650\uff0c\u8bbe\u7f6e\u573a\u666f\u5f00\u5173\u5c06\u4e0d\u8d77\u4f5c\u7528");
                            }

                            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                            public void onGranted() {
                                super.onGranted();
                                LBSSceneSwitchHelper.saveLbsSceneSwitch(str, z);
                            }

                            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                            public void onOpenSetting() {
                                super.onOpenSetting();
                                myViewHolder.sceneSwitch.setChecked(false);
                            }
                        });
                        return;
                    }
                    LBSSceneSwitchHelper.saveLbsSceneSwitch(str, z);
                }
            });
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        @NonNull
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
            return new MyViewHolder(LayoutInflater.from(this.context).inflate(R.layout.lbs_scene_permission_item, viewGroup, false));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView content;
        UnCheckBox sceneSwitch;
        TextView title;

        public MyViewHolder(@NonNull View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.title);
            this.sceneSwitch = (UnCheckBox) view.findViewById(R.id.scene_switch);
            this.content = (TextView) view.findViewById(R.id.content);
        }
    }

    private List<Map<String, Object>> createMaps() {
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        String n1 = JDMobiSec.n1("dd9d1f485ceb06");
        String n12 = JDMobiSec.n1("cc9f094f5af10a304850fe334e9b4393d5db5582");
        hashMap.put(n1, n12);
        String n13 = JDMobiSec.n1("da970e4a5c");
        hashMap.put(n13, JDMobiSec.n1("f28b4c155d923e2a0c46ae3f75be04cbd0df7a841186200725d7441cee174f1132dbde4931df52c2663a6482699a721e9ed1973419982c4b0793a8c4adf76cd2c9de05829667b400ad14d4f0acd87ff69e11fe7a803af729a6bef97d526bf2ba476729e32354538b9ae8541e6b89cae30cf557c069bf4283d4d210669c6226b7eda18a3d0f7147e0fcbc1a50c2bf441c165ae85efd69"));
        String n14 = JDMobiSec.n1("cd9114525ccc16");
        hashMap.put(n14, JDMobiSec.n1("f28b431608c33e2a0046f46a75be049a868e7a841285200225d7481ee8174f11328fde1531df50c0373a648268cb741a9ed1943642962c4b069caa92adf76d82ce8905829563b609ad14d6a5add97ff69c47f17e803af12ca2b9f97d523aa8b8476728e7735d538b9fe852496b89c9b55ff657c068b345daa7fb5063cc62429eadfa8f3d635805b6adba777981be104c2c73a854a53b5e368edae7175a79f2bc668a6a94eff43bb9f0d98ac73a17f3e76cfbb65c859ca51d97c7a5e896ff711b030c271e35886179d4513918fac5dd8729f32738262542d59e456c986d452db1ecd86190b75bfddc1c0dab2dc81b00c23a1570f18891fc31b8b23243f9d8dd8994b148c8c7d6f5330a6c7895e3d7166ab5560f5df77e1a469491074103d9166d6c3f26b1529b063f5ba000f57d17dd4ae548d879a4897ac78cd0fb4d5f338664299217a7869da399772f3e3f1e726e6df9e89bfa267c91ab2ee5a8b7fb125d28a3ef7765db3ab3083b3dce165a28664602d22b8cef716edff8cd55ba39a96cb5dfdcac7b1ec33ae2fb871b656f0c28510e83c58bd477bfb5d5b1dc07baaa5d33fbbd7904ea60e63d308381303f2a7c15bcb9d6d085601e"));
        Boolean valueOf = Boolean.valueOf(LBSSceneSwitchHelper.getLbsSceneSwitch(n12));
        String n15 = JDMobiSec.n1("dd9d1f485cf115364c43ff");
        hashMap.put(n15, valueOf);
        arrayList.add(hashMap);
        HashMap hashMap2 = new HashMap();
        String n16 = JDMobiSec.n1("c29119755cd014365b45");
        hashMap2.put(n1, n16);
        hashMap2.put(n13, JDMobiSec.n1("f28b4c155d923e2a0c46ae3f75be04c880867a841fd7705d25d7454db94b4f11308cd14731df5193663d64826b99204a9ed196601f9b2c4b0598fdc0adf76c859bd105829636ee05ad14d7a0f1dd7ff69d14fc2a803af12df5bef97d523af3ea47672ab62608538b9cea051e"));
        hashMap2.put(n14, JDMobiSec.n1("f28b431608c33e2a0046f46a75be049a868e7a841285200225d7481ee8174f11328fde1531df50c0373a648268cb741a9ed1943642962c4b069caa92adf76d82ce8905829563b609ad14d6a5add97ff69c47f17e803afd2fa2bbf97d5f68afb8476725b47354538b9cbd5d1818a089e35ff73de92abf42d8b9fb5066cf334b9eadf78e3b635805b0ffe8707981be43482e73a859ae3957368edae7175a79f2b0308c6e94eff43ceff4d98ac13944a3e76cfbe659d29ca51dc79fa4e896f8244a060c271f328e6f79d4523b4dfdc5dd8578a52038262b42d7cb456c9c6f4325b1ecdb3297e75bfdd24405ff2dc81c55c06f1570f38fc5af31b8bd6e47abd8dd86c6b149c8c7d6a5370e6c7893e784166ab55c5c54a57e1a4bc3ca054103db113e323f26b45b9b533f5ba756f47417dd44e34e8a79a48a7fc08ad0fb435a678e64299616a68e9da393762b6b3f1e7c6b6efde89bf9752d96ab2ee5fbb2f7125d2ea2b92765db34b00b3b3dce160e26624602df7bd8e8716eddf6cc52ba39a96cb288dcac7b4dc46de2fb841f366a0c28515fd4c18bd473eae4d1"));
        hashMap2.put(n15, Boolean.valueOf(LBSSceneSwitchHelper.getLbsSceneSwitch(n16)));
        arrayList.add(hashMap2);
        HashMap hashMap3 = new HashMap();
        String n17 = JDMobiSec.n1("c39f084d5cd60b315f61f42940bd5888dfdb55");
        hashMap3.put(n1, n17);
        hashMap3.put(n13, JDMobiSec.n1("f28b4c155d923e2a0c46ae3f75be08ca828a7a841fd7765525d74b1dbe154f11318cd81131df52c2616964826bc923449ed191354a9f2c4b07cefcc7adf76d82cd8a05829a66b209ad14d5f3f0de7ff69f17f028803afc2da4baf97d5e3bfbb9476728e72754538b9fe8011f"));
        hashMap3.put(n14, JDMobiSec.n1("f28b431608c33e2a0046f46a75be049a868e7a841285200225d7481ee8174f11328fde1531df50c0373a648268cb741a9ed1943642962c4b069caa92adf73f81cd8b05829736b204ad14d4f0ab8f7ff69e11a924803af12af5bdf97d536bfbec476724b52459538b90ed541f6b89cab259a157c06ab81783d4d24333cf6026b7eda78b3e0f7148b6ffbd1a50c1ee4d4f165aea5afa603a1fced8e0143750b3ee328254bdacf16dbc99f0c9c33e149ece2ef8b60becb5e44c9797cfc1d5fb221c6b25611a60de"));
        hashMap3.put(n15, Boolean.valueOf(LBSSceneSwitchHelper.getLbsSceneSwitch(n17)));
        arrayList.add(hashMap3);
        HashMap hashMap4 = new HashMap();
        String n18 = JDMobiSec.n1("dc9b194350d4071e5c44e5385ab8");
        hashMap4.put(n1, n18);
        hashMap4.put(n13, JDMobiSec.n1("f28b4c155d923e2a0c46ae3f75be04cdd5887a841089240125d7494de8454f1130dd8b4131df51c5643e6482699e234a9ed19b344eca2c4b099bae90adf76cd49e8905829666e554ad14d5a2f98c7ff69e16f878803af12bf7be"));
        hashMap4.put(n14, JDMobiSec.n1("f28b431608c33e2a0046f46a75be049a868e7a841285200225d7481ee8174f11328fde1531df50c0373a648268cb741a9ed1943642962c4b069caa92adf73f81cd8b05829566b508ad14d4f0ab8f7ff69e11a924803af121a0edf97d523ff2b047672ab4255a538b91bc56186b89c9e159f357c06abd428bd4d211339a3226b7eef28a6e0f7116e3ade91a50c0ed114d165ae90afe3e3a1fcf8ce4463750b1b8628254bdaff06bba99f0c8cc3c429ece2ffdb15aecb5e44ccbc4cfc1d6fe204c6b25671e33dd05509407384bc7ec9fd47ff718116a2c1283a66c209f6f424898acdd66c5da72bdde4f589504881b5595023c33f389969618f8b76b44c0f19b8e92bb"));
        hashMap4.put(n15, Boolean.valueOf(LBSSceneSwitchHelper.getLbsSceneSwitch(n18)));
        arrayList.add(hashMap4);
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.statusBarTransparentEnable = true;
        setContentView(R.layout.lbs_scene_permission_activity);
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        JdThemeTitle jdThemeTitle = (JdThemeTitle) findViewById(R.id.title);
        this.title = jdThemeTitle;
        jdThemeTitle.setLeftIv1ClickListener(new View.OnClickListener() { // from class: com.jingdong.common.permission.LbsScenePermissionActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LbsScenePermissionActivity.this.onBackPressed();
            }
        });
        this.title.getTitleBgImageView().setImageResource(R.color.un_bg_level_1);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setAdapter(new MyAdapter(createMaps(), this));
    }
}
