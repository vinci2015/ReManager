package com.njrobot.huangyouqiang.redevicemanager.domain.interactor;

import com.njrobot.huangyouqiang.redevicemanager.domain.executor.PostExecutorThread;
import com.njrobot.huangyouqiang.redevicemanager.domain.executor.ThreadExecutor;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.observers.Subscribers;
import rx.schedulers.Schedulers;

/**
 * ������������
 * Created by huangyouqiang on 2016/7/28.
 */
public abstract class UseCase {
    private ThreadExecutor threadExecutor;
    private PostExecutorThread postExecutorThread;

    private Subscription subscription = Subscribers.empty();

    protected UseCase(ThreadExecutor threadExecutor, PostExecutorThread postExecutorThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutorThread = postExecutorThread;
    }

    /**
     * �����¼�Observable
     * ����ʵ�־����¼�
     * @return
     */
    protected abstract Observable buildUseCaseObservable();

    public void execute(Subscriber userCaseObservable){
        this.subscription = this.buildUseCaseObservable()
                .subscribeOn(Schedulers.from(threadExecutor))//�¼��������߳�
                .observeOn(postExecutorThread.getScheduler())//�¼��������߳�
                .subscribe(userCaseObservable);
    }

    /**
     * ȡ������
     */
    public void unSubscrib(){
        if(subscription.isUnsubscribed()){
            this.subscription.unsubscribe();
        }
    }

}
