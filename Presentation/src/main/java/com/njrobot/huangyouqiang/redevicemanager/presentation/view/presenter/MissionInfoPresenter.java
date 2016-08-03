package com.njrobot.huangyouqiang.redevicemanager.presentation.view.presenter;

import com.njrobot.huangyouqiang.redevicemanager.domain.entity.MissionEntity;
import com.njrobot.huangyouqiang.redevicemanager.domain.interactor.DefaultSubscriber;
import com.njrobot.huangyouqiang.redevicemanager.domain.interactor.UseCase;
import com.njrobot.huangyouqiang.redevicemanager.presentation.model.Mission;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.MissionInfoView;

import javax.inject.Inject;

/**
 * Created by huangyouqiang on 2016/8/3.
 */
public class MissionInfoPresenter implements Presenter {
    private UseCase getMissionInfoUseCase;
    private MissionInfoView missionInfoView;

    @Inject
    public MissionInfoPresenter(UseCase getMissionInfoUseCase) {
        this.getMissionInfoUseCase = getMissionInfoUseCase;
    }
    public void setView(MissionInfoView view){
        this.missionInfoView = view;
    }
    public void init(){
        this.missionInfoView.hideRetry();
        this.missionInfoView.showLoading();
        getMissionInfoUseCase.execute(new GetMissionInfo());
    }
    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.getMissionInfoUseCase.unSubscrib();
        missionInfoView = null;
    }
    private class GetMissionInfo extends DefaultSubscriber<MissionEntity>{
        @Override
        public void onCompleted() {
            missionInfoView.hideLoading();
        }

        @Override
        public void onError(Throwable e) {
            missionInfoView.hideLoading();
            missionInfoView.showError(e.getMessage());
            missionInfoView.showRetry();
        }

        @Override
        public void onNext(MissionEntity entity) {
            Mission mission = Mission.transform(entity);
            missionInfoView.renderMission(mission);
        }
    }
}
