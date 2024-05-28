package org.cdfsunrise.open;

import org.cdfsunrise.OkHttpHelper;
import java.util.List;

public class RefreshZltToken {
    public static class RefreshZltTokenResponse {
    	private String requestId;
    	public String GetRequestId() {
    	    return this.requestId;
    	}
    	public void SetRequestId(String requestId) {
    	    this.requestId = requestId;
    	}

    	private int code;
    	public int GetCode() {
            return this.code;
        }
        public void SetCode(int code) {
            this.code = code;
        }

    	private String message;
    	public String GetMessage() {
            return this.message;
        }
        public void SetMessage(String message) {
            this.message = message;
        }

    	private String data;
    	public String GetData() {
            return this.data;
        }
        public void SetData(String data) {
            this.data = data;
        }
    }
    

    /*RefreshZltToken
     *Description: 更新中旅通accessToken
     * @param: appid string appid 必填项
     * @return: *RefreshZltTokenResponse
    */
    public RefreshZltTokenResponse RefreshZltToken(String host, String appid) throws Exception {
    	OkHttpHelper httpHelper = new OkHttpHelper();
    	
        String respStr = httpHelper.Get(String.format("%s%s", host, String.format("/refresh/zlt/token?appid=%s", appid)));
        
        RefreshZltTokenResponse respEntity = new RefreshZltTokenResponse();
        respEntity.SetData(respStr);
        return respEntity;
    }
}
