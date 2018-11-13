package com.sxmh.wt.lotterysystem.bean;

import com.sxmh.wt.lotterysystem.bean.response.NotOpenQueryResponse;

public interface NotOpenResponseCallBack {
    void notifyResponse(NotOpenQueryResponse notOpenQueryResponse);
}
