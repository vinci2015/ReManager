package com.njrobot.huangyouqiang.redevicemanager.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.njrobot.huangyouqiang.redevicemanager.data.executor.JobExecutor;
import com.njrobot.huangyouqiang.redevicemanager.data.repository.MissionDataRepository;
import com.njrobot.huangyouqiang.redevicemanager.data.repository.datasource.MissionDataStoreFactory;
import com.njrobot.huangyouqiang.redevicemanager.domain.entity.MissionEntity;
import com.njrobot.huangyouqiang.redevicemanager.domain.executor.ThreadExecutor;
import com.njrobot.huangyouqiang.redevicemanager.domain.interactor.DefaultSubscriber;
import com.njrobot.huangyouqiang.redevicemanager.domain.interactor.GetMissionDetails;
import com.njrobot.huangyouqiang.redevicemanager.domain.interactor.UseCase;
import com.njrobot.huangyouqiang.redevicemanager.presentation.model.Mission;
import com.njrobot.huangyouqiang.redevicemanager.presentation.view.activity.MissionInfoActivity;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private UseCase useCase;
    private ThreadExecutor threadExecutor;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tv);
        threadExecutor = new JobExecutor();

        useCase = new GetMissionDetails(new MissionDataRepository(new MissionDataStoreFactory(this)),threadExecutor,new UIThread());

    }

    private final class GetMissionSubscriber extends DefaultSubscriber<MissionEntity>{
        @Override
        public void onCompleted() {
            Log.i("mainactivity","oncomplete()");
        }

        @Override
        public void onError(Throwable e) {
            Log.i("mainactivity","onError()"+e.getMessage());
            e.printStackTrace();
        }

        @Override
        public void onNext(MissionEntity entity) {
            if(entity != null) {
                Log.i("mainactivity", "onNext()");
                Mission mission = Mission.transform(entity);
                textView.setText(mission.toString());
            }else{
                Log.i(TAG,"mission list is empty!!");
            }
        }
    }

    public void doRequest(View view){
        //useCase.execute(new GetMissionSubscriber());
        startActivity(new Intent(MainActivity.this, MissionInfoActivity.class));
    }
}

