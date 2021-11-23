package com.yc.yc.lifehelper.ui.me.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.IntentUtils;
import com.ns.yc.lifehelper.R;
import com.yc.configlayer.arounter.ARouterUtils;
import com.yc.configlayer.arounter.RouterConfig;
import com.ycbjie.library.base.mvp.BaseFragment;
import com.ycbjie.library.utils.AppToolUtils;


/**
 * <pre>
 *     @author yangchong
 *     blog  : https://github.com/yangchong211
 *     time  : 2017/11/21
 *     desc  : 我的页面
 *     revise: v1.4 17年6月8日
 *             v1.5 17年10月3日修改
 * </pre>
 */
public class MeFragment extends BaseFragment implements View.OnClickListener{


    private LinearLayout mLlPerson;
    private ImageView mIvPersonImage;
    private TextView mTvPersonName;
    private RelativeLayout mRlMeTimer;
    private RelativeLayout mRlMeQone;
    private RelativeLayout mRlMeProject;
    private RelativeLayout mRlMeCollect;
    private RelativeLayout mRlMeQuestion;
    private RelativeLayout mRlMeSetting;
    private RelativeLayout mRlMeFeedBack;
    private LinearLayout mRlMePhone;
    private TextView mTvMePhoneNumber;
    private Activity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(activity!=null){
            activity = null;
        }
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_me;
    }

    @Override
    public void initView(View view) {
        initFindViewById(view);
        setRipperView();
    }

    private void initFindViewById(View view) {
        mLlPerson = view.findViewById(R.id.ll_person);
        mIvPersonImage = view.findViewById(R.id.iv_person_image);
        mTvPersonName = view.findViewById(R.id.tv_person_name);
        mRlMeTimer = view.findViewById(R.id.rl_me_timer);
        mRlMeQone = view.findViewById(R.id.rl_me_qone);
        mRlMeProject = view.findViewById(R.id.rl_me_project);
        mRlMeCollect = view.findViewById(R.id.rl_me_collect);
        mRlMeQuestion = view.findViewById(R.id.rl_me_question);
        mRlMeSetting = view.findViewById(R.id.rl_me_setting);
        mRlMeFeedBack = view.findViewById(R.id.rl_me_feed_back);
        mRlMePhone = view.findViewById(R.id.rl_me_phone);
        mTvMePhoneNumber = view.findViewById(R.id.tv_me_phone_number);
    }

    private void setRipperView() {
        AppToolUtils.setRipper(mRlMeTimer);
        AppToolUtils.setRipper(mRlMeQone);
        AppToolUtils.setRipper(mRlMeProject);
        AppToolUtils.setRipper(mRlMeCollect);
        AppToolUtils.setRipper(mRlMeQuestion);
        AppToolUtils.setRipper(mRlMeSetting);
        AppToolUtils.setRipper(mRlMeFeedBack);
        AppToolUtils.setRipper(mRlMePhone);
    }

    @Override
    public void initListener() {
        mRlMeTimer.setOnClickListener(this);
        mRlMeQone.setOnClickListener(this);
        mRlMeProject.setOnClickListener(this);
        mRlMeCollect.setOnClickListener(this);
        mRlMeQuestion.setOnClickListener(this);
        mRlMeSetting.setOnClickListener(this);
        mRlMeFeedBack.setOnClickListener(this);
        mRlMePhone.setOnClickListener(this);
        mIvPersonImage.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_me_timer:
                break;
            case R.id.rl_me_qone:
                break;
            case R.id.rl_me_project:
                ARouterUtils.navigation(RouterConfig.Love.ACTIVITY_LOVE_ACTIVITY);
                break;
            case R.id.rl_me_setting:
                ARouterUtils.navigation(RouterConfig.App.ACTIVITY_APP_SETTING_ACTIVITY);
                break;
            case R.id.rl_me_feed_back:
                ARouterUtils.navigation(RouterConfig.Demo.ACTIVITY_OTHER_FEEDBACK);
                break;
            case R.id.rl_me_phone:
                toCallMe();
                break;
            case R.id.iv_person_image:
                break;
            default:
                break;
        }
    }


    private void toCallMe() {
        String number = mTvMePhoneNumber.getText().toString().trim();
        Intent callIntent = IntentUtils.getCallIntent(number);
        startActivity(callIntent);
    }

}