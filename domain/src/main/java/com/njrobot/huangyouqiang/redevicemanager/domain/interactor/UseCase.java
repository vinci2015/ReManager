package com.njrobot.huangyouqiang.redevicemanager.domain.interactor;

import com.njrobot.huangyouqiang.redevicemanager.domain.executor.PostExecutorThread;
import com.njrobot.huangyouqiang.redevicemanager.domain.executor.ThreadExecutor;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.observers.Subscribers;
import rx.schedulers.Schedulers;

/**
 * 交互器基础类
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
     * 生产事件Observable
     * 子类实现具体事件
     * @return
     */
    protected abstract Observable buildUseCaseObservable();

    public void execute(Subscriber userCaseObservable){
        this.subscription = this.buildUseCaseObservable()
                .subscribeOn(Schedulers.from(threadExecutor))//事件的生产线程
                .observeOn(postExecutorThread.getScheduler())//事件的消费线程
                .subscribe(userCaseObservable);
    }

    /**
     * 取消订阅
     */
    public void unSubscrib(){
        if(subscription.isUnsubscribed()){
            this.subscription.unsubscribe();
        }
    }

}
