package com.sxmh.wt.lotterysystem;

import com.sxmh.wt.lotterysystem.bean.request.AccountRechargeInfoRequest;
import com.sxmh.wt.lotterysystem.bean.request.ActivationRequest;
import com.sxmh.wt.lotterysystem.bean.request.Arrange5CommitRequest;
import com.sxmh.wt.lotterysystem.bean.request.BetHistoryRequest;
import com.sxmh.wt.lotterysystem.bean.request.CashQueryRequest;
import com.sxmh.wt.lotterysystem.bean.request.CashRequest;
import com.sxmh.wt.lotterysystem.bean.request.ChangePswRequest;
import com.sxmh.wt.lotterysystem.bean.request.CheckLoginRequest;
import com.sxmh.wt.lotterysystem.bean.request.Commit36$7Request;
import com.sxmh.wt.lotterysystem.bean.request.CommitLotteryRequest;
import com.sxmh.wt.lotterysystem.bean.request.DoubleBallNotifyRequest;
import com.sxmh.wt.lotterysystem.bean.request.Fast3CommitRequest;
import com.sxmh.wt.lotterysystem.bean.request.Fast3NotifyRequest;
import com.sxmh.wt.lotterysystem.bean.request.FindRuleRequest;
import com.sxmh.wt.lotterysystem.bean.request.FixedTypeRequest;
import com.sxmh.wt.lotterysystem.bean.request.GameListQueryRequest;
import com.sxmh.wt.lotterysystem.bean.request.Happy8CommitRequest;
import com.sxmh.wt.lotterysystem.bean.request.Happy8NotifyRequest;
import com.sxmh.wt.lotterysystem.bean.request.LoginRequest;
import com.sxmh.wt.lotterysystem.bean.request.LogoutRequest;
import com.sxmh.wt.lotterysystem.bean.request.MessageListRequest;
import com.sxmh.wt.lotterysystem.bean.request.MessageLookRequest;
import com.sxmh.wt.lotterysystem.bean.request.MessageRequest;
import com.sxmh.wt.lotterysystem.bean.request.MultiBetRequest;
import com.sxmh.wt.lotterysystem.bean.request.NotOpenQueryRequest;
import com.sxmh.wt.lotterysystem.bean.request.Notify36$7Request;
import com.sxmh.wt.lotterysystem.bean.request.Pl5NotifyRequest;
import com.sxmh.wt.lotterysystem.bean.request.PrinterNoticeRequest;
import com.sxmh.wt.lotterysystem.bean.request.RefundTicketRequest;
import com.sxmh.wt.lotterysystem.bean.request.ReportRequest;
import com.sxmh.wt.lotterysystem.bean.request.RestMoneyRequest;
import com.sxmh.wt.lotterysystem.bean.request.TimeRequest;
import com.sxmh.wt.lotterysystem.bean.request.TroubleUploadRequest;
import com.sxmh.wt.lotterysystem.bean.request.UserDataRequest;
import com.sxmh.wt.lotterysystem.bean.response.AccountRechargeInfoResponse;
import com.sxmh.wt.lotterysystem.bean.response.ActivationResponse;
import com.sxmh.wt.lotterysystem.bean.response.Arrange5CommitResponse;
import com.sxmh.wt.lotterysystem.bean.response.BetHistoryResponse;
import com.sxmh.wt.lotterysystem.bean.response.CashInfoResponse;
import com.sxmh.wt.lotterysystem.bean.response.CashResponse;
import com.sxmh.wt.lotterysystem.bean.response.ChangePswResponse;
import com.sxmh.wt.lotterysystem.bean.response.CheckLoginResponse;
import com.sxmh.wt.lotterysystem.bean.response.Commit36$7Response;
import com.sxmh.wt.lotterysystem.bean.response.CommitLotteryResponse;
import com.sxmh.wt.lotterysystem.bean.response.DoubleBallNotifyResponse;
import com.sxmh.wt.lotterysystem.bean.response.Fast3CommitResponse;
import com.sxmh.wt.lotterysystem.bean.response.Fast3NotifyResponse;
import com.sxmh.wt.lotterysystem.bean.response.FindRuleResponse;
import com.sxmh.wt.lotterysystem.bean.response.FixedTypeResponse;
import com.sxmh.wt.lotterysystem.bean.response.GameListQueryResponse;
import com.sxmh.wt.lotterysystem.bean.response.Happy8CommitResponse;
import com.sxmh.wt.lotterysystem.bean.response.Happy8NotifyResponse;
import com.sxmh.wt.lotterysystem.bean.response.LoginResponse;
import com.sxmh.wt.lotterysystem.bean.response.LogoutResponse;
import com.sxmh.wt.lotterysystem.bean.response.MessageListResponse;
import com.sxmh.wt.lotterysystem.bean.response.MessageLookResponse;
import com.sxmh.wt.lotterysystem.bean.response.MessageResponse;
import com.sxmh.wt.lotterysystem.bean.response.MultiBetResponse;
import com.sxmh.wt.lotterysystem.bean.response.NotOpenQueryResponse;
import com.sxmh.wt.lotterysystem.bean.response.Notify36$7Response;
import com.sxmh.wt.lotterysystem.bean.response.Pl5NotifyResponse;
import com.sxmh.wt.lotterysystem.bean.response.PrinterNoticeResponse;
import com.sxmh.wt.lotterysystem.bean.response.RefundTicketResponse;
import com.sxmh.wt.lotterysystem.bean.response.ReportResponse;
import com.sxmh.wt.lotterysystem.bean.response.RestMoneyResponse;
import com.sxmh.wt.lotterysystem.bean.response.TimeResponse;
import com.sxmh.wt.lotterysystem.bean.response.TroubleUploadResponse;
import com.sxmh.wt.lotterysystem.bean.response.UserDataResponse;
import com.sxmh.wt.lotterysystem.bean.response.VersionResponse;
import com.sxmh.wt.lotterysystem.util.Constants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2018/5/9 0009.
 */

public interface ApiService {

    /**
     * 登录
     *
     * @param loginRequest
     * @return
     */
    @POST("/user/userLogin")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    /**
     * 检查登录状态
     *
     * @param session_name
     * @param sessid
     * @param token
     * @param checkLoginRequest
     * @return
     */
    @POST("/user/checkLogin")
    Call<CheckLoginResponse> checkLogin(@Header(Constants.SESSION_NAME) String session_name,
                                        @Header(Constants.SESSION_ID) String sessid,
                                        @Header(Constants.TOKEN) String token,
                                        @Body CheckLoginRequest checkLoginRequest);

    /**
     * 退出登录
     *
     * @param accept
     * @param contenttype
     * @param cookie
     * @param token
     * @param logoutRequest
     * @return
     */
    @POST("/user/userLogout")
    Call<LogoutResponse> logout(
            @Header("Accept") String accept,
            @Header("Content-Type") String contenttype,
            @Header("Cookie") String cookie,
            @Header("X-CSRF-Token") String token,
            @Body LogoutRequest logoutRequest);

    /**
     * 修改密码
     *
     * @param accept
     * @param contenttype
     * @param cookie
     * @param token
     * @param changePswRequest
     * @return
     */
    @POST("/user/modifyPass")
    Call<ChangePswResponse> changePsw(@Header("Accept") String accept,
                                      @Header("Content-Type") String contenttype,
                                      @Header("Cookie") String cookie,
                                      @Header("X-CSRF-Token") String token,
                                      @Body ChangePswRequest changePswRequest);

    /**
     * 兑奖查询
     *
     * @param cashRequest
     * @return
     */
    @POST("/order/terminal/cashPrizeQuery")
    Call<CashInfoResponse> cashInfoQuery(@Body CashQueryRequest cashRequest);

    /**
     * 兑奖
     *
     * @param cashRequest
     * @return
     */
    @POST("/order/terminal/terminalCashPrize")
    Call<CashResponse> cash(@Body CashRequest cashRequest);

    /**
     * 充值信息查询
     *
     * @param rechargeInfoRequest
     * @return
     */
    @POST("/lottery/recharge/queryListRecharge")
    Call<AccountRechargeInfoResponse> getChargeInfo(@Body AccountRechargeInfoRequest rechargeInfoRequest);

    /**
     * 36$7下注
     *
     * @param commit36$7Request
     * @return
     */
    @POST("/order/36Selection7/terminalOrder")
    Call<Commit36$7Response> commit36$7Lottery(@Body Commit36$7Request commit36$7Request);

    /**
     * 查询玩法
     *
     * @param gameListQueryRequest
     * @return
     */
    @POST("/lottery/game/queryGameList")
    Call<GameListQueryResponse> getGameList(@Body GameListQueryRequest gameListQueryRequest);

    /**
     * 消息列表
     *
     * @param accept
     * @param contenttype
     * @param cookie
     * @param token
     * @param messageListRequest
     * @return
     */
    @POST("/news/terminalnewsList")
    Call<MessageListResponse> getMessageList(@Header("Accept") String accept,
                                             @Header("Content-Type") String contenttype,
                                             @Header("Cookie") String cookie,
                                             @Header("X-CSRF-Token") String token,
                                             @Body MessageListRequest messageListRequest);

    /**
     * 消息
     *
     * @param accept
     * @param contenttype
     * @param cookie
     * @param token
     * @param messageRequest
     * @return
     */
    @POST("/news/terminalnewsLook")
    Call<MessageResponse> getMessage(@Header("Accept") String accept,
                                     @Header("Content-Type") String contenttype,
                                     @Header("Cookie") String cookie,
                                     @Header("X-CSRF-Token") String token,
                                     @Body MessageRequest messageRequest);

    /**
     * 未开奖奖期查询
     *
     * @param notOpenQueryRequest
     * @return
     */
    @POST("/lottery/draw/drawNotOpenQuery")
    Call<NotOpenQueryResponse> getNotOpen(@Body NotOpenQueryRequest notOpenQueryRequest);

    /**
     * 报表查询
     *
     * @param reportRequest
     * @return
     */
    @POST("/order/terminal/reportQuery")
    Call<ReportResponse> getReport(@Body ReportRequest reportRequest);

    /**
     * 查询余额
     *
     * @param accept
     * @param contenttype
     * @param cookie
     * @param token
     * @param restMoneyRequest
     * @return
     */
    @POST("/walle/walleQuery")
    Call<RestMoneyResponse> getRestMoney(@Header("Accept") String accept,
                                         @Header("Content-Type") String contenttype,
                                         @Header("Cookie") String cookie,
                                         @Header("X-CSRF-Token") String token,
                                         @Body RestMoneyRequest restMoneyRequest);


    /**
     * 故障上传
     *
     * @param request
     * @return
     */
    @POST("/customerservicelist/customerserviceAdd")
    Call<TroubleUploadResponse> uploadTrouble(@Header("Accept") String accept,
                                              @Header("Content-Type") String contenttype,
                                              @Header("Cookie") String cookie,
                                              @Header("X-CSRF-Token") String token,
                                              @Body TroubleUploadRequest request);

    /**
     * @param request
     * @return
     */
    @POST("/pay/pay/printerNotice")
    Call<PrinterNoticeResponse> printerNotice(@Body PrinterNoticeRequest request);

    /**
     * 双色球下注
     *
     * @param commitLotteryRequest
     * @return
     */
    @POST("/order/order/terminalOrder")
    Call<CommitLotteryResponse> commitLottery(@Body CommitLotteryRequest commitLotteryRequest);

    /**
     * 快三下注
     *
     * @param fast3CommitRequest
     * @return
     */
    @POST("/order/quick3/terminalOrder")
    Call<Fast3CommitResponse> commitFast3(@Body Fast3CommitRequest fast3CommitRequest);

    /**
     * 快乐8下注
     *
     * @param happy8CommitRequest
     * @return
     */
    @POST("/order/happiness8/terminalOrder")
    Call<Happy8CommitResponse> commitHappy8(@Body Happy8CommitRequest happy8CommitRequest);

    /**
     * 排列五下注
     *
     * @param arrange5CommitRequest
     * @return
     */
    @POST("/order/array5/terminalOrder")
    Call<Arrange5CommitResponse> commitArrange5(@Body Arrange5CommitRequest arrange5CommitRequest);

    /**
     * 2.1.11.附加玩法查询
     *
     * @param fixedTypeRequest
     * @return
     */
    @POST("/order/happiness8/selectGameAdd")
    Call<FixedTypeResponse> selectGameAdd(@Body FixedTypeRequest fixedTypeRequest);

    /**
     * 2.1.19.多期查询
     *
     * @param multiBetRequest
     * @return
     */
    @POST("/order/order/findMultiphase")
    Call<MultiBetResponse> findMultiphase(@Body MultiBetRequest multiBetRequest);

    /**
     * 获取系统时间
     *
     * @param timeRequest
     * @return
     */
    @POST("screen/quick3/timeCalibration")
    Call<TimeResponse> timeCalibration(@Body TimeRequest timeRequest);

    /**
     * 排列5打印通知
     *
     * @param pl5NotifyRequest
     * @return
     */
    @POST("/pay/printer/pl5Printer")
    Call<Pl5NotifyResponse> pl5Printer(@Body Pl5NotifyRequest pl5NotifyRequest);

    /**
     * 快乐8打印通知
     *
     * @param happy8NotifyRequest
     * @return
     */
    @POST("/pay/printer/kl8Printer")
    Call<Happy8NotifyResponse> kl8Printer(@Body Happy8NotifyRequest happy8NotifyRequest);

    /**
     * 快三打印通知
     *
     * @param fast3NotifyRequest
     * @return
     */
    @POST("/pay/printer/k3Printer")
    Call<Fast3NotifyResponse> k3Printer(@Body Fast3NotifyRequest fast3NotifyRequest);

    /**
     * 36选7打印通知
     *
     * @param notify36$7Request
     * @return
     */
    @POST("/pay/printer/36Selection7Printer")
    Call<Notify36$7Response> _36Selection7Printer(@Body Notify36$7Request notify36$7Request);

    /**
     * 双色球打印通知
     *
     * @param doubleBallNotifyRequest
     * @return
     */
    @POST("/pay/printer/ssqPrinter")
    Call<DoubleBallNotifyResponse> ssqPrinter(@Body DoubleBallNotifyRequest doubleBallNotifyRequest);

    /**
     * 投注历史记录
     *
     * @param betHistoryRequest
     * @return
     */
    @POST("/order/terminal/historyBettingQuery")
    Call<BetHistoryResponse> historyBettingQuery(@Body BetHistoryRequest betHistoryRequest);

    /**
     * 激活设备
     *
     * @param activationRequest
     * @return
     */
    @POST("/terminal/activationTerminal")
    Call<ActivationResponse> activationTerminal(@Header("Accept") String accept,
                                                @Header("Content-Type") String contenttype,
                                                @Header("Cookie") String cookie,
                                                @Header("X-CSRF-Token") String token,
                                                @Body ActivationRequest activationRequest);

    /**
     * 消息查看
     *
     * @param accept
     * @param contenttype
     * @param cookie
     * @param token
     * @param messageLookRequest
     * @return
     */
    @POST("/news/terminalnewsLook")
    Call<MessageLookResponse> terminalnewsLook(@Header("Accept") String accept,
                                               @Header("Content-Type") String contenttype,
                                               @Header("Cookie") String cookie,
                                               @Header("X-CSRF-Token") String token,
                                               @Body MessageLookRequest messageLookRequest);

    /**
     * 查询游戏规则
     *
     * @param findRuleRequest
     * @return
     */
    @POST("/order/order/findRule")
    Call<FindRuleResponse> findRule(@Body FindRuleRequest findRuleRequest);

    /**
     * 退票
     *
     * @param refundTicketRequest
     * @return
     */
    @POST("/order/order/refundTicket")
    Call<RefundTicketResponse> refundTicket(@Body RefundTicketRequest refundTicketRequest);

    /**
     * 设备信息
     *
     * @param userDataRequest
     * @return
     */
    @POST("/userapi/app_services/userData")
    Call<UserDataResponse> userData(@Header("Accept") String accept,
                                    @Header("Content-Type") String contenttype,
                                    @Header("Cookie") String cookie,
                                    @Header("X-CSRF-Token") String token,
                                    @Body UserDataRequest userDataRequest);

    /**
     * 检查新版本
     *
     * @return
     */
    @POST
    Call<VersionResponse> queryupdate(@Url String url);
}
