package com.njrobot.huangyouqiang.redevicemanager.presentation.presenter;

import android.os.Looper;
import android.util.Log;

import com.njrobot.huangyouqiang.redevicemanager.domain.exception.DefaultErrorBundle;
import com.njrobot.huangyouqiang.redevicemanager.domain.exception.ErrorBundle;
import com.njrobot.huangyouqiang.redevicemanager.domain.interactor.DefaultSubscriber;
import com.njrobot.huangyouqiang.redevicemanager.domain.interactor.UseCase;
import com.njrobot.huangyouqiang.redevicemanager.domain.model.Mission;
import com.njrobot.huangyouqiang.redevicemanager.presentation.exception.ErrorMessageFactory;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.MissionInfoView;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author huangyouqiang
 * @date 2016/8/3
 */
class MissionInfoPresenter implements Presenter {
    private UseCase getMissionInfoUseCase;
    private MissionInfoView missionInfoView;

    @Inject
    MissionInfoPresenter(@Named("mission") UseCase getMissionInfoUseCase) {
        this.getMissionInfoUseCase = getMissionInfoUseCase;
    }
    public void setView(MissionInfoView view){
        this.missionInfoView = view;
    }

    public void init(){
        Looper looper = Looper.myLooper();
        Log.i("MissionPResenter",(looper == Looper.getMainLooper())+"");
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
    public void showErrorMessage(ErrorBundle errorBundle){
        String errorMessage = ErrorMessageFactory.create(errorBundle.getException());
        this.missionInfoView.showError(errorMessage);
    }
    private class GetMissionInfo extends DefaultSubscriber<Mission>{
        @Override
        public void onCompleted() {
            Log.i("Presenter","onComplete()");
            missionInfoView.hideLoading();
        }

        @Override
        public void onError(Throwable e) {
            Log.i("Presenter","onError()");
            e.printStackTrace();
            missionInfoView.hideLoading();
            showErrorMessage(new DefaultErrorBundle((Exception) e));
            missionInfoView.showRetry();
        }

        @Override
        public void onNext(Mission entity) {
            Log.i("Presenter","onNext()"+(entity == null));
            missionInfoView.renderMission(entity);
        }
    }
}
