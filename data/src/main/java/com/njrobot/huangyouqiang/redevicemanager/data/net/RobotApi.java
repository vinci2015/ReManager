package com.njrobot.huangyouqiang.redevicemanager.data.net;


import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author huangyouqiang
 * @date 2016/7/28
 */
interface RobotApi {
    @POST("mission/mission_list")
    Observable<ResMissionList> getMissionList(@Body ReqMissionList reqMissionList);

    @POST("mission/cancel_mission")
    Observable<ResCancelMission> cancelMission(@Body ReqCancelMission reqCancelMission);

    @POST("robot/request_info_lst")
    Observable<ResRobot> getRobot(@Body ReqRobot reqRobot);

    @POST("mission/send_mission")
    Observable<ResSendMission> sendMission(@Body ReqSendMission reqSendMission);
}
