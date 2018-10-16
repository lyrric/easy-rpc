package com.demo.rpc.commom.model;

import com.demo.rpc.commom.exception.RequestFailException;
import lombok.Data;
import sun.awt.RequestFocusController;

import java.io.Serializable;

/**
 * Created on 2018/10/16.
 * Rpc响应
 * @author wangxiaodong
 */
public class RpcResponse implements Serializable {
    /**
     * 请求的ID
     */
    private String requestId;
    /**
     * 是否成功
     */
    private Boolean success;
    /**
     * 出错信息
     */
    private String errMsg;
    /**
     * 返回数据
     */
    private Object data;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Object getData() throws RequestFailException {
        if(!this.success){
            throw new RequestFailException(this.errMsg);
        }
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
