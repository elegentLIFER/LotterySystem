package com.sxmh.wt.lotterysystem.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.sxmh.wt.lotterysystem.ApiService;
import com.sxmh.wt.lotterysystem.BaseUrl;
import com.sxmh.wt.lotterysystem.IView;
import com.sxmh.wt.lotterysystem.MyApplication;
import com.sxmh.wt.lotterysystem.base.BasePresenter;
import com.sxmh.wt.lotterysystem.bean.UserInfo;
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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Net extends BasePresenter {
    public static final int REQUEST_GAME_LIST = 0;
    public static final int REQUEST_GET_CHARGE_INFO = 1;
    public static final int REQUEST_CASH_INFO_QUERY = 2;
    public static final int REQUEST_CASH = 3;
    public static final int REQUEST_CHANGE_PSW = 4;
    public static final int REQUEST_CHRCK_LOGIN = 5;
    public static final int REQUEST_COMMIT_36$7 = 6;
    public static final int REQUEST_LOGIN = 7;
    public static final int REQUEST_LOGOUT = 8;
    public static final int REQUEST_GET_MESSAGE_LIST = 9;
    public static final int REQUEST_GET_MESSAGE = 10;
    public static final int REQUEST_GET_NOT_OPEN = 11;
    public static final int REQUEST_PRINTER_NOTICE = 12;
    public static final int REQUEST_GET_REPORT = 13;
    public static final int REQUEST_GET_REST_MONEY = 14;
    public static final int REQUEST_COMMIT_LOTTERY = 15;
    public static final int REQUEST_UPLOAD_TROUBLE = 16;
    public static final int REQUEST_COMMIT_FAST_3 = 17;
    public static final int REQUEST_FIXED_TYPE = 18;
    public static final int REQUEST_MULTI_BET = 19;
    public static final int REQUEST_COMMIT_HAPPY8 = 20;
    public static final int REQUEST_COMMIT_ARRANGE_5 = 21;
    public static final int REQUEST_SERVER_TIME = 22;

    public static final int REQUEST_PRINTER_PL5 = 23;
    public static final int REQUEST_PRINTER_HAPPY8 = 24;
    public static final int REQUEST_PRINTER_FAST3 = 25;
    public static final int REQUEST_PRINTER_36$7 = 26;
    public static final int REQUEST_PRINTER_DOUBLE_BALL = 27;

    public static final int REQUEST_BET_HISTORY = 28;
    public static final int REQUEST_ACTIVATION = 29;

    public static final int REQUEST_MESSAGE_LOOK = 30;
    public static final int REQUEST_FIND_RULE = 31;
    public static final int REQUEST_REFUND_TICKET = 32;
    public static final int REQUEST_USER_DATA = 33;
    public static final int REQUEST_CHECK_VERSION = 34;

    public Net(IView iView) {
        super(iView);
    }

    private ApiService getApiService(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService.class);
    }

    public void getGameList(GameListQueryRequest gameListQueryRequest) {
        iView.get().showLoading();
        Call<GameListQueryResponse> call = getApiService(BaseUrl.SONG).getGameList(gameListQueryRequest);
        call.enqueue(new Callback<GameListQueryResponse>() {
            @Override
            public void onResponse(Call<GameListQueryResponse> call, Response<GameListQueryResponse> response) {
                iView.get().cancelLoading();
                GameListQueryResponse body = response.body();
                if (body == null) {
                    return;
                }
                iView.get().showToast(body.getMessage());
                if (Constants.CODE_SUCCESS.equals(body.getCode())) {
                    iView.get().updateContent(REQUEST_GAME_LIST, body);
                }
            }

            @Override
            public void onFailure(Call<GameListQueryResponse> call, Throwable t) {
                iView.get().cancelLoading();
                iView.get().showToast(t.getMessage());
            }
        });
    }

    public void getChargeInfo(AccountRechargeInfoRequest accountRechargeInfoRequest) {
        iView.get().showLoading();

        ApiService apiService = new Retrofit.Builder()
                .baseUrl(BaseUrl.SONG)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService.class);
        Call<AccountRechargeInfoResponse> call = apiService.getChargeInfo(accountRechargeInfoRequest);
        call.enqueue(new Callback<AccountRechargeInfoResponse>() {
            @Override
            public void onResponse(Call<AccountRechargeInfoResponse> call, Response<AccountRechargeInfoResponse> response) {
                iView.get().cancelLoading();
                AccountRechargeInfoResponse body = response.body();
                if (body == null) {
                    return;
                }
                iView.get().showToast(body.getMessage());
                if (Constants.CODE_SUCCESS.equals(body.getCode())) {
                    iView.get().updateContent(REQUEST_GET_CHARGE_INFO, body);
                }
            }

            @Override
            public void onFailure(Call<AccountRechargeInfoResponse> call, Throwable t) {
                iView.get().cancelLoading();
                iView.get().showToast(t.getMessage());
            }
        });
    }

    public void cashInfoQuery(CashQueryRequest cashQueryRequest) {
        iView.get().showLoading();
        Call<CashInfoResponse> call = getApiService(BaseUrl.SONG).cashInfoQuery(cashQueryRequest);
        call.enqueue(new Callback<CashInfoResponse>() {
            @Override
            public void onResponse(Call<CashInfoResponse> call, Response<CashInfoResponse> response) {
                iView.get().cancelLoading();
                CashInfoResponse body = response.body();
                if (body == null) {
                    return;
                }
                iView.get().showToast(body.getMessage());
                if (Constants.CODE_SUCCESS.equals(body.getCode())) {
                    iView.get().updateContent(REQUEST_CASH_INFO_QUERY, body);
                }
            }

            @Override
            public void onFailure(Call<CashInfoResponse> call, Throwable t) {
                iView.get().cancelLoading();
                iView.get().showToast(t.getMessage());
            }
        });
    }

    public void cash(CashRequest cashRequest) {
        iView.get().showLoading();
        Call<CashResponse> call = getApiService(BaseUrl.SONG).cash(cashRequest);
        call.enqueue(new Callback<CashResponse>() {
            @Override
            public void onResponse(Call<CashResponse> call, Response<CashResponse> response) {
                iView.get().cancelLoading();
                CashResponse body = response.body();
                if (body == null) {
                    return;
                }
                iView.get().showToast(body.getMessage());
                if (Constants.CODE_SUCCESS.equals(body.getCode())) {
                    iView.get().updateContent(REQUEST_CASH, body);
                }
            }

            @Override
            public void onFailure(Call<CashResponse> call, Throwable t) {
                iView.get().cancelLoading();
                iView.get().showToast(t.getMessage());
            }
        });
    }

    public void changePsw(ChangePswRequest changePswRequest) {
        iView.get().showLoading();
        UserInfo instance = UserInfo.getInstance();
        String cookie = instance.getSessionName() + "=" + instance.getSessionId();
        Call<ChangePswResponse> call = getApiService(BaseUrl.LI).changePsw(Constants.ACCEPT, Constants.CONTENT_TYPE, cookie, instance.getToken(), changePswRequest);
        call.enqueue(new Callback<ChangePswResponse>() {
            @Override
            public void onResponse(Call<ChangePswResponse> call, Response<ChangePswResponse> response) {
                iView.get().cancelLoading();
                ChangePswResponse body = response.body();
                if (body != null && Constants.CODE_SUCCESS.equals(body.getCode())) {
                    iView.get().updateContent(REQUEST_CHANGE_PSW, null);
                    return;
                }
                iView.get().showToast(body.getMessage());
            }

            @Override
            public void onFailure(Call<ChangePswResponse> call, Throwable t) {
                iView.get().cancelLoading();
                iView.get().showToast(t.getMessage());
            }
        });
    }

    public void checkLogin(CheckLoginRequest checkLoginRequest) {
        iView.get().showLoading();
        UserInfo instance = UserInfo.getInstance();
        Call<CheckLoginResponse> call = getApiService(BaseUrl.LI).checkLogin(instance.getSessionName(), instance.getUid(), instance.getToken(), checkLoginRequest);
        call.enqueue(new Callback<CheckLoginResponse>() {
            @Override
            public void onResponse(Call<CheckLoginResponse> call, Response<CheckLoginResponse> response) {
                iView.get().cancelLoading();
                CheckLoginResponse body = response.body();
                if (body == null) {
                    return;
                }
                iView.get().showToast(body.getMessage());
                if (Constants.CODE_SUCCESS.equals(body.getCode())) {
                    iView.get().updateContent(REQUEST_CHRCK_LOGIN, body);
                }
            }

            @Override
            public void onFailure(Call<CheckLoginResponse> call, Throwable t) {
                iView.get().cancelLoading();
                iView.get().showToast(t.getMessage());
            }
        });
    }

    public void commit36$7Lottery(Commit36$7Request commit36$7Request) {
        iView.get().showLoading();
        Call<Commit36$7Response> call = getApiService(BaseUrl.SONG).commit36$7Lottery(commit36$7Request);
        call.enqueue(new Callback<Commit36$7Response>() {
            @Override
            public void onResponse(Call<Commit36$7Response> call, Response<Commit36$7Response> response) {
                iView.get().cancelLoading();
                Commit36$7Response body = response.body();
                if (body == null) {
                    return;
                }
                iView.get().showToast(body.getMessage());
                if (Constants.CODE_SUCCESS.equals(body.getCode())) {
                    iView.get().updateContent(REQUEST_COMMIT_36$7, body);
                }
            }

            @Override
            public void onFailure(Call<Commit36$7Response> call, Throwable t) {
                iView.get().cancelLoading();
                iView.get().showToast(t.getMessage());
            }
        });
    }

    public void login(LoginRequest loginRequest) {
        iView.get().showLoading();
        Call<LoginResponse> call = getApiService(BaseUrl.LI).login(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                iView.get().cancelLoading();
                LoginResponse body = response.body();
                if (body == null) {
                    iView.get().showToast("登录失败" + response.message());
                    return;
                }
                iView.get().showToast(body.getMessage());
                iView.get().updateContent(REQUEST_LOGIN, body);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                iView.get().cancelLoading();
                iView.get().showToast(t.getMessage());
            }
        });
    }

    public void logout(LogoutRequest logoutRequest) {
        iView.get().showLoading();
        UserInfo instance = UserInfo.getInstance();
        String cookie = instance.getSessionName() + "=" + instance.getSessionId();
        Call<LogoutResponse> call = getApiService(BaseUrl.LI).logout(Constants.ACCEPT, Constants.CONTENT_TYPE, cookie, instance.getToken(), logoutRequest);
        call.enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                iView.get().cancelLoading();
                LogoutResponse body = response.body();
                if (body == null) {
                    return;
                }
                iView.get().showToast(body.getMessage());
                if (Constants.CODE_SUCCESS.equals(body.getCode())) {
                    iView.get().updateContent(REQUEST_LOGOUT, body);
                }
            }

            @Override
            public void onFailure(Call<LogoutResponse> call, Throwable t) {
                iView.get().cancelLoading();
                iView.get().showToast(t.getMessage());
            }
        });
    }

    public void getMessageList(MessageListRequest messageListRequest) {
//        iView.get().showLoading();
        UserInfo instance = UserInfo.getInstance();
        String cookie = instance.getSessionName() + "=" + instance.getSessionId();
        Call<MessageListResponse> call = getApiService(BaseUrl.LI).getMessageList(Constants.ACCEPT, Constants.CONTENT_TYPE, cookie, instance.getToken(), messageListRequest);
        call.enqueue(new Callback<MessageListResponse>() {
            @Override
            public void onResponse(Call<MessageListResponse> call, Response<MessageListResponse> response) {
//                iView.get().cancelLoading();
                MessageListResponse body = response.body();
                if (body == null) {
                    return;
                }
//                iView.get().showToast(body.getMessage());
                if (Constants.CODE_SUCCESS.equals(body.getCode())) {
                    iView.get().updateContent(REQUEST_GET_MESSAGE_LIST, body);
                }
            }

            @Override
            public void onFailure(Call<MessageListResponse> call, Throwable t) {
//                iView.get().cancelLoading();
                iView.get().showToast(t.getMessage());
                iView.get().updateContent(0, 0);
            }
        });
    }

    public void terminalnewsLook(MessageLookRequest messageLookRequest) {
//        iView.get().showLoading();
        UserInfo instance = UserInfo.getInstance();
        String cookie = instance.getSessionName() + "=" + instance.getSessionId();
        Call<MessageLookResponse> call = getApiService(BaseUrl.LI).terminalnewsLook(Constants.ACCEPT, Constants.CONTENT_TYPE, cookie, instance.getToken(), messageLookRequest);
        call.enqueue(new Callback<MessageLookResponse>() {
            @Override
            public void onResponse(Call<MessageLookResponse> call, Response<MessageLookResponse> response) {
//                iView.get().cancelLoading();
                MessageLookResponse body = response.body();
                if (body == null) {
                    return;
                }
                iView.get().showToast(body.getMessage());
                if (Constants.CODE_SUCCESS.equals(body.getCode())) {
                    iView.get().updateContent(REQUEST_MESSAGE_LOOK, body);
                }
            }

            @Override
            public void onFailure(Call<MessageLookResponse> call, Throwable t) {
//                iView.get().cancelLoading();
                iView.get().showToast(t.getMessage());
                iView.get().updateContent(0, 0);
            }
        });
    }

    public void getMessage(MessageRequest messageRequest) {
        iView.get().showLoading();
        // 获取保存的用户信息
        SharedPreferences sp = MyApplication.getInstance().getSharedPreferences(Constants.SP_NAME, Context.MODE_PRIVATE);
        String session_name = sp.getString(Constants.SESSION_NAME, "");
        String sessid = sp.getString(Constants.SESSION_ID, "");
        String token = sp.getString(Constants.TOKEN, "");
        String cookie = session_name + "=" + sessid;
        Call<MessageResponse> call = getApiService(BaseUrl.SONG).getMessage(Constants.ACCEPT, Constants.CONTENT_TYPE, cookie, token, messageRequest);
        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                iView.get().cancelLoading();
                MessageResponse body = response.body();
                if (body == null) {
                    return;
                }
                iView.get().showToast(body.getMessage());
                if (Constants.CODE_SUCCESS.equals(body.getCode())) {
                    iView.get().updateContent(REQUEST_GET_MESSAGE, body);
                }
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                iView.get().cancelLoading();
                iView.get().showToast(t.getMessage());
            }
        });
    }

    public void getNotOpen(NotOpenQueryRequest notOpenQueryRequest) {
        iView.get().showLoading();
        Call<NotOpenQueryResponse> call = getApiService(BaseUrl.SONG).getNotOpen(notOpenQueryRequest);
        call.enqueue(new Callback<NotOpenQueryResponse>() {
            @Override
            public void onResponse(Call<NotOpenQueryResponse> call, Response<NotOpenQueryResponse> response) {
                iView.get().cancelLoading();
                NotOpenQueryResponse body = response.body();
                if (body == null) {
                    return;
                }
                iView.get().showToast(body.getMessage());
                if (Constants.CODE_SUCCESS.equals(body.getCode())) {
                    iView.get().updateContent(REQUEST_GET_NOT_OPEN, body);
                }
            }

            @Override
            public void onFailure(Call<NotOpenQueryResponse> call, Throwable t) {
                iView.get().cancelLoading();
                iView.get().showToast(t.getMessage());
            }
        });
    }

    public void printerNotice(PrinterNoticeRequest printerNoticeRequest) {
        iView.get().showLoading();
        Call<PrinterNoticeResponse> call = getApiService(BaseUrl.SONG).printerNotice(printerNoticeRequest);
        call.enqueue(new Callback<PrinterNoticeResponse>() {
            @Override
            public void onResponse(Call<PrinterNoticeResponse> call, Response<PrinterNoticeResponse> response) {
                iView.get().cancelLoading();
                PrinterNoticeResponse body = response.body();
                if (body == null) {
                    return;
                }
                iView.get().showToast(body.getMessage());
                if (Constants.CODE_SUCCESS.equals(body.getCode())) {
                    iView.get().updateContent(REQUEST_PRINTER_NOTICE, body);
                }
            }

            @Override
            public void onFailure(Call<PrinterNoticeResponse> call, Throwable t) {
                iView.get().cancelLoading();
                iView.get().showToast(t.getMessage());
            }
        });
    }

    public void getReport(ReportRequest reportRequest) {
        iView.get().showLoading();
        Call<ReportResponse> call = getApiService(BaseUrl.SONG).getReport(reportRequest);
        call.enqueue(new Callback<ReportResponse>() {
            @Override
            public void onResponse(Call<ReportResponse> call, Response<ReportResponse> response) {
                iView.get().cancelLoading();
                ReportResponse body = response.body();
                if (body == null) {
                    return;
                }
                iView.get().showToast(body.getMessage());
                if (Constants.CODE_SUCCESS.equals(body.getCode())) {
                    iView.get().updateContent(REQUEST_GET_REPORT, body);
                }
            }

            @Override
            public void onFailure(Call<ReportResponse> call, Throwable t) {
                iView.get().cancelLoading();
                iView.get().showToast(t.getMessage());
            }
        });
    }

    public void getRestMoney(RestMoneyRequest restMoneyRequest) {
        UserInfo instance = UserInfo.getInstance();
        String cookie = instance.getSessionName() + "=" + instance.getSessionId();

        Call<RestMoneyResponse> call = getApiService(BaseUrl.LI).getRestMoney(Constants.ACCEPT, Constants.CONTENT_TYPE, cookie, instance.getToken(), restMoneyRequest);
        call.enqueue(new Callback<RestMoneyResponse>() {
            @Override
            public void onResponse(Call<RestMoneyResponse> call, Response<RestMoneyResponse> response) {
                RestMoneyResponse body = response.body();
                if (body == null) {
                    return;
                }
                iView.get().showToast(body.getMessage());
                if (Constants.CODE_SUCCESS.equals(body.getCode())) {
                    iView.get().updateContent(REQUEST_GET_REST_MONEY, body);
                }
            }

            @Override
            public void onFailure(Call<RestMoneyResponse> call, Throwable t) {
                iView.get().showToast(t.getMessage());
            }
        });
    }

    public void uploadTrouble(TroubleUploadRequest request) {
        iView.get().showLoading();
        UserInfo instance = UserInfo.getInstance();
        String cookie = instance.getSessionName() + "=" + instance.getSessionId();
        Call<TroubleUploadResponse> call = getApiService(BaseUrl.LI).uploadTrouble(Constants.ACCEPT, Constants.CONTENT_TYPE, cookie, instance.getToken(), request);
        call.enqueue(new Callback<TroubleUploadResponse>() {
            @Override
            public void onResponse(Call<TroubleUploadResponse> call, Response<TroubleUploadResponse> response) {
                iView.get().cancelLoading();
                TroubleUploadResponse body = response.body();
                if (body == null) {
                    return;
                }
                iView.get().showToast(body.getMessage());
                if (Constants.CODE_SUCCESS.equals(body.getCode())) {
                    iView.get().updateContent(REQUEST_UPLOAD_TROUBLE, body);
                }
            }

            @Override
            public void onFailure(Call<TroubleUploadResponse> call, Throwable t) {
                iView.get().cancelLoading();
                iView.get().showToast(t.getMessage());
            }
        });
    }

    public void commitLottery(CommitLotteryRequest commitLotteryRequest) {
        iView.get().showLoading();
        Call<CommitLotteryResponse> call = getApiService(BaseUrl.SONG).commitLottery(commitLotteryRequest);
        call.enqueue(new Callback<CommitLotteryResponse>() {
            @Override
            public void onResponse(Call<CommitLotteryResponse> call, Response<CommitLotteryResponse> response) {
                iView.get().cancelLoading();
                CommitLotteryResponse body = response.body();
                if (body == null) {
                    return;
                }
                iView.get().showToast(body.getMessage());
                if (Constants.CODE_SUCCESS.equals(body.getCode())) {
                    iView.get().updateContent(REQUEST_COMMIT_LOTTERY, body);
                }
            }

            @Override
            public void onFailure(Call<CommitLotteryResponse> call, Throwable t) {
                iView.get().cancelLoading();
                iView.get().showToast(t.getMessage());
            }
        });
    }

    public void commitFast3(Fast3CommitRequest fast3CommitRequest) {
        iView.get().showLoading();
        Call<Fast3CommitResponse> call = getApiService(BaseUrl.SONG).commitFast3(fast3CommitRequest);
        call.enqueue(new Callback<Fast3CommitResponse>() {
            @Override
            public void onResponse(Call<Fast3CommitResponse> call, Response<Fast3CommitResponse> response) {
                iView.get().cancelLoading();
                Fast3CommitResponse body = response.body();
                if (body == null) {
                    return;
                }
                iView.get().showToast(body.getMessage());
                if (Constants.CODE_SUCCESS.equals(body.getCode())) {
                    iView.get().updateContent(REQUEST_COMMIT_FAST_3, body);
                }
            }

            @Override
            public void onFailure(Call<Fast3CommitResponse> call, Throwable t) {
                iView.get().cancelLoading();
                iView.get().showToast(t.getMessage());
            }
        });
    }

    public void commitHappy8(Happy8CommitRequest happy8CommitRequest) {
        iView.get().showLoading();
        Call<Happy8CommitResponse> call = getApiService(BaseUrl.SONG).commitHappy8(happy8CommitRequest);
        call.enqueue(new Callback<Happy8CommitResponse>() {
            @Override
            public void onResponse(Call<Happy8CommitResponse> call, Response<Happy8CommitResponse> response) {
                iView.get().cancelLoading();
                Happy8CommitResponse body = response.body();
                if (body == null) {
                    return;
                }
                iView.get().showToast(body.getMessage());
                if (Constants.CODE_SUCCESS.equals(body.getCode())) {
                    iView.get().updateContent(REQUEST_COMMIT_HAPPY8, body);
                }
            }

            @Override
            public void onFailure(Call<Happy8CommitResponse> call, Throwable t) {
                iView.get().cancelLoading();
                iView.get().showToast(t.getMessage());
            }
        });
    }

    public void commitArrange5(Arrange5CommitRequest arrange5CommitRequest) {
        iView.get().showLoading();
        Call<Arrange5CommitResponse> call = getApiService(BaseUrl.SONG).commitArrange5(arrange5CommitRequest);
        call.enqueue(new Callback<Arrange5CommitResponse>() {
            @Override
            public void onResponse(Call<Arrange5CommitResponse> call, Response<Arrange5CommitResponse> response) {
                iView.get().cancelLoading();
                Arrange5CommitResponse body = response.body();
                if (body == null) {
                    return;
                }
                iView.get().showToast(body.getMessage());
                if (Constants.CODE_SUCCESS.equals(body.getCode())) {
                    iView.get().updateContent(REQUEST_COMMIT_ARRANGE_5, body);
                }
            }

            @Override
            public void onFailure(Call<Arrange5CommitResponse> call, Throwable t) {
                iView.get().cancelLoading();
                iView.get().showToast(t.getMessage());
            }
        });
    }

    public void selectGameAdd(FixedTypeRequest fixedTypeRequest) {
        iView.get().showLoading();
        Call<FixedTypeResponse> call = getApiService(BaseUrl.SONG).selectGameAdd(fixedTypeRequest);
        call.enqueue(new Callback<FixedTypeResponse>() {
            @Override
            public void onResponse(Call<FixedTypeResponse> call, Response<FixedTypeResponse> response) {
                iView.get().cancelLoading();
                FixedTypeResponse body = response.body();
                if (body == null) {
                    return;
                }
                iView.get().showToast(body.getMessage());
                if (Constants.CODE_SUCCESS.equals(body.getCode())) {
                    iView.get().updateContent(REQUEST_FIXED_TYPE, body);
                }
            }

            @Override
            public void onFailure(Call<FixedTypeResponse> call, Throwable t) {
                iView.get().cancelLoading();
                iView.get().showToast(t.getMessage());
            }
        });
    }

    public void timeCalibration(TimeRequest timeRequest) {
        iView.get().showLoading();
        Call<TimeResponse> call = getApiService(BaseUrl.SONG).timeCalibration(timeRequest);
        call.enqueue(new Callback<TimeResponse>() {
            @Override
            public void onResponse(Call<TimeResponse> call, Response<TimeResponse> response) {
                iView.get().cancelLoading();
                TimeResponse body = response.body();
                if (body == null) {
                    iView.get().showToast("时间同步失败");
                    return;
                }
                iView.get().showToast(body.getMessage());
                if (Constants.CODE_SUCCESS.equals(body.getCode())) {
                    iView.get().updateContent(REQUEST_SERVER_TIME, body);
                }
            }

            @Override
            public void onFailure(Call<TimeResponse> call, Throwable t) {
                iView.get().cancelLoading();
                iView.get().showToast(t.getMessage());
            }
        });
    }

    public void pl5Printer(Pl5NotifyRequest pl5NotifyRequest) {
        iView.get().showLoading();
        Call<Pl5NotifyResponse> call = getApiService(BaseUrl.SONG).pl5Printer(pl5NotifyRequest);
        call.enqueue(new Callback<Pl5NotifyResponse>() {
            @Override
            public void onResponse(Call<Pl5NotifyResponse> call, Response<Pl5NotifyResponse> response) {
                iView.get().cancelLoading();
                Pl5NotifyResponse body = response.body();
                if (body == null) {
                    return;
                }
                iView.get().showToast(body.getMessage());
                if (Constants.CODE_SUCCESS.equals(body.getCode())) {
                    iView.get().updateContent(REQUEST_PRINTER_PL5, body);
                }
            }

            @Override
            public void onFailure(Call<Pl5NotifyResponse> call, Throwable t) {
                iView.get().cancelLoading();
                iView.get().showToast(t.getMessage());
            }
        });
    }

    public void kl8Printer(Happy8NotifyRequest happy8NotifyRequest) {
        iView.get().showLoading();
        Call<Happy8NotifyResponse> call = getApiService(BaseUrl.SONG).kl8Printer(happy8NotifyRequest);
        call.enqueue(new Callback<Happy8NotifyResponse>() {
            @Override
            public void onResponse(Call<Happy8NotifyResponse> call, Response<Happy8NotifyResponse> response) {
                iView.get().cancelLoading();
                Happy8NotifyResponse body = response.body();
                if (body == null) {
                    return;
                }
                iView.get().showToast(body.getMessage());
                if (Constants.CODE_SUCCESS.equals(body.getCode())) {
                    iView.get().updateContent(REQUEST_PRINTER_HAPPY8, body);
                }
            }

            @Override
            public void onFailure(Call<Happy8NotifyResponse> call, Throwable t) {
                iView.get().cancelLoading();
                iView.get().showToast(t.getMessage());
            }
        });
    }

    public void k3Printer(Fast3NotifyRequest fast3NotifyRequest) {
        iView.get().showLoading();
        Call<Fast3NotifyResponse> call = getApiService(BaseUrl.SONG).k3Printer(fast3NotifyRequest);
        call.enqueue(new Callback<Fast3NotifyResponse>() {
            @Override
            public void onResponse(Call<Fast3NotifyResponse> call, Response<Fast3NotifyResponse> response) {
                iView.get().cancelLoading();
                Fast3NotifyResponse body = response.body();
                if (body == null) {
                    return;
                }
                iView.get().showToast(body.getMessage());
                if (Constants.CODE_SUCCESS.equals(body.getCode())) {
                    iView.get().updateContent(REQUEST_PRINTER_FAST3, body);
                }
            }

            @Override
            public void onFailure(Call<Fast3NotifyResponse> call, Throwable t) {
                iView.get().cancelLoading();
                iView.get().showToast(t.getMessage());
            }
        });
    }

    public void _36Selection7Printer(Notify36$7Request notify36$7Request) {
        iView.get().showLoading();
        Call<Notify36$7Response> call = getApiService(BaseUrl.SONG)._36Selection7Printer(notify36$7Request);
        call.enqueue(new Callback<Notify36$7Response>() {
            @Override
            public void onResponse(Call<Notify36$7Response> call, Response<Notify36$7Response> response) {
                iView.get().cancelLoading();
                Notify36$7Response body = response.body();
                if (body == null) {
                    return;
                }
                iView.get().showToast(body.getMessage());
                if (Constants.CODE_SUCCESS.equals(body.getCode())) {
                    iView.get().updateContent(REQUEST_PRINTER_36$7, body);
                }
            }

            @Override
            public void onFailure(Call<Notify36$7Response> call, Throwable t) {
                iView.get().cancelLoading();
                iView.get().showToast(t.getMessage());
            }
        });
    }

    public void ssqPrinter(DoubleBallNotifyRequest doubleBallNotifyRequest) {
        iView.get().showLoading();
        Call<DoubleBallNotifyResponse> call = getApiService(BaseUrl.SONG).ssqPrinter(doubleBallNotifyRequest);
        call.enqueue(new Callback<DoubleBallNotifyResponse>() {
            @Override
            public void onResponse(Call<DoubleBallNotifyResponse> call, Response<DoubleBallNotifyResponse> response) {
                iView.get().cancelLoading();
                DoubleBallNotifyResponse body = response.body();
                if (body == null) {
                    return;
                }
                iView.get().showToast(body.getMessage());
                if (Constants.CODE_SUCCESS.equals(body.getCode())) {
                    iView.get().updateContent(REQUEST_PRINTER_DOUBLE_BALL, body);
                }
            }

            @Override
            public void onFailure(Call<DoubleBallNotifyResponse> call, Throwable t) {
                iView.get().cancelLoading();
                iView.get().showToast(t.getMessage());
            }
        });
    }

    public void historyBettingQuery(BetHistoryRequest betHistoryRequest) {
//        iView.get().showLoading();
        Call<BetHistoryResponse> call = getApiService(BaseUrl.SONG).historyBettingQuery(betHistoryRequest);
        call.enqueue(new Callback<BetHistoryResponse>() {
            @Override
            public void onResponse(Call<BetHistoryResponse> call, Response<BetHistoryResponse> response) {
                iView.get().cancelLoading();
                BetHistoryResponse body = response.body();
                if (body == null) {
                    return;
                }
                iView.get().showToast(body.getMessage());
                if (Constants.CODE_SUCCESS.equals(body.getCode())) {
                    iView.get().updateContent(REQUEST_BET_HISTORY, body);
                }
            }

            @Override
            public void onFailure(Call<BetHistoryResponse> call, Throwable t) {
                iView.get().cancelLoading();
                iView.get().showToast(t.getMessage());
            }
        });
    }

    public void activationTerminal(ActivationRequest activationRequest) {
        iView.get().showLoading();
        UserInfo instance = UserInfo.getInstance();
        String cookie = instance.getSessionName() + "=" + instance.getSessionId();
        Call<ActivationResponse> call = getApiService(BaseUrl.LI).activationTerminal(Constants.ACCEPT, Constants.CONTENT_TYPE, cookie, instance.getToken(), activationRequest);
        call.enqueue(new Callback<ActivationResponse>() {
            @Override
            public void onResponse(Call<ActivationResponse> call, Response<ActivationResponse> response) {
                iView.get().cancelLoading();
                ActivationResponse body = response.body();
                if (body == null) {
                    return;
                }
                iView.get().showToast(body.getMessage());
                if (Constants.CODE_SUCCESS.equals(body.getCode())) {
                    iView.get().updateContent(REQUEST_ACTIVATION, body);
                }
            }

            @Override
            public void onFailure(Call<ActivationResponse> call, Throwable t) {
                iView.get().cancelLoading();
                iView.get().showToast(t.getMessage());
            }
        });
    }

    public void findRule(FindRuleRequest findRuleRequest) {
        iView.get().showLoading();
        Call<FindRuleResponse> call = getApiService(BaseUrl.SONG).findRule(findRuleRequest);
        call.enqueue(new Callback<FindRuleResponse>() {
            @Override
            public void onResponse(Call<FindRuleResponse> call, Response<FindRuleResponse> response) {
                iView.get().cancelLoading();
                FindRuleResponse body = response.body();
                if (body == null) {
                    return;
                }
                iView.get().showToast(body.getMessage());
                if (Constants.CODE_SUCCESS.equals(body.getCode())) {
                    iView.get().updateContent(REQUEST_FIND_RULE, body);
                }
            }

            @Override
            public void onFailure(Call<FindRuleResponse> call, Throwable t) {
                iView.get().cancelLoading();
                iView.get().showToast(t.getMessage());
            }
        });
    }

    public void refundTicket(RefundTicketRequest refundTicketRequest) {
        iView.get().showLoading();
        Call<RefundTicketResponse> call = getApiService(BaseUrl.SONG).refundTicket(refundTicketRequest);
        call.enqueue(new Callback<RefundTicketResponse>() {
            @Override
            public void onResponse(Call<RefundTicketResponse> call, Response<RefundTicketResponse> response) {
                iView.get().cancelLoading();
                RefundTicketResponse body = response.body();
                if (body == null) {
                    return;
                }
                iView.get().showToast(body.getMessage());
                if (Constants.CODE_SUCCESS.equals(body.getCode())) {
                    iView.get().updateContent(REQUEST_REFUND_TICKET, body);
                }
            }

            @Override
            public void onFailure(Call<RefundTicketResponse> call, Throwable t) {
                iView.get().cancelLoading();
                iView.get().showToast(t.getMessage());
            }
        });
    }

    public void userData(UserDataRequest userDataRequest) {
        iView.get().showLoading();
        UserInfo instance = UserInfo.getInstance();
        String cookie = instance.getSessionName() + "=" + instance.getSessionId();
        Call<UserDataResponse> call = getApiService(BaseUrl.LI).userData(Constants.ACCEPT, Constants.CONTENT_TYPE, cookie, instance.getToken(), userDataRequest);
        call.enqueue(new Callback<UserDataResponse>() {
            @Override
            public void onResponse(Call<UserDataResponse> call, Response<UserDataResponse> response) {
                iView.get().cancelLoading();
                UserDataResponse body = response.body();
                if (body == null) {
                    return;
                }
                iView.get().showToast(body.getMessage());
                if (Constants.CODE_SUCCESS.equals(body.getCode())) {
                    iView.get().updateContent(REQUEST_USER_DATA, body);
                }
            }

            @Override
            public void onFailure(Call<UserDataResponse> call, Throwable t) {
                iView.get().cancelLoading();
                iView.get().showToast(t.getMessage());
            }
        });
    }

    public void queryupdate(String packageName, String version) {
        iView.get().showLoading();
        StringBuilder sb = new StringBuilder("/userapi/queryupdate?package=").append(packageName).append("&version=").append(version);
        Call<VersionResponse> call = getApiService(BaseUrl.LI).queryupdate(sb.toString());
        call.enqueue(new Callback<VersionResponse>() {
            @Override
            public void onResponse(Call<VersionResponse> call, Response<VersionResponse> response) {
                iView.get().cancelLoading();
                VersionResponse body = response.body();
                if (body == null) return;
                iView.get().showToast(body.getMessage());
                if (Constants.CODE_SUCCESS.equals(body.getCode()))
                    iView.get().updateContent(REQUEST_CHECK_VERSION, body);
            }

            @Override
            public void onFailure(Call<VersionResponse> call, Throwable t) {
                iView.get().cancelLoading();
                iView.get().showToast(t.getMessage());
            }
        });
    }

    public static void main(String[] args) {
    }
}
