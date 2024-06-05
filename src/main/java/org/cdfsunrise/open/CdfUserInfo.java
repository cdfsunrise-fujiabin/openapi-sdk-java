package org.cdfsunrise.open;

import com.alibaba.fastjson.JSON;
import org.cdfsunrise.OkHttpHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CdfUserInfo {
    public static class CdfUserInfoResponse {
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
    
	public static class CdfUserInfoReq {
		private String channelId;
		public String getChannelId()
		{
			return this.channelId;
		}
		public void setChannelId(String channelId)
		{
			this.channelId = channelId;
		}

		private String token;
		public String getToken()
		{
			return this.token;
		}
		public void setToken(String token)
		{
			this.token = token;
		}

		private String userId;
		public String getUserId()
		{
			return this.userId;
		}
		public void setUserId(String userId)
		{
			this.userId = userId;
		}
	}
	
	

    /*CdfUserInfo
     *Description: sso获取用户信息
     * @param: body CdfUserInfoReq CdfUserInfoReq 必填项
     * @return: *CdfUserInfoResponse
    */
    public CdfUserInfoResponse CdfUserInfo(String host, String authToken, CdfUserInfoReq body) throws Exception {
    	OkHttpHelper httpHelper = new OkHttpHelper();
    	Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", authToken);
    	
    	String bodyString = JSON.toJSONString(body);
        String respStr = httpHelper.Post(String.format("%s%s", host, String.format("/cdf/userInfo")), headers, bodyString);
        
        CdfUserInfoResponse respEntity = new CdfUserInfoResponse();
        respEntity.SetData(respStr);
        return respEntity;
    }
}
