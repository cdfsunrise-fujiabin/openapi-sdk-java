package org.cdfsunrise.open;

import com.alibaba.fastjson.JSON;
import org.cdfsunrise.OkHttpHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class V2UserAuth {
    public static class V2UserAuthResponse {
    	private String requestId;
    	public String getRequestId() {
    	    return this.requestId;
    	}
    	public void setRequestId(String requestId) {
    	    this.requestId = requestId;
    	}

    	private int code;
    	public int getCode() {
            return this.code;
        }
        public void setCode(int code) {
            this.code = code;
        }

    	private String message;
    	public String getMessage() {
            return this.message;
        }
        public void setMessage(String message) {
            this.message = message;
        }

    	private OpenAuthResponseData data;
        public OpenAuthResponseData getData() {
            return this.data;
        }
        public void setData(OpenAuthResponseData data) {
            this.data = data;
        }
    }

	public static class OpenAuthResponseData {
		private OpenAuthDetail auth;
		public OpenAuthDetail getAuth()
		{
			return this.auth;
		}
		public void setAuth(OpenAuthDetail auth)
		{
			this.auth = auth;
		}
	}

	public static class OpenAuthDetail {
		private String accessToken;
		public String getAccessToken()
		{
			return this.accessToken;
		}
		public void setAccessToken(String accessToken)
		{
			this.accessToken = accessToken;
		}

		private Integer expiresIn;
		public Integer getExpiresIn()
		{
			return this.expiresIn;
		}
		public void setExpiresIn(Integer expiresIn)
		{
			this.expiresIn = expiresIn;
		}
	}

	public static class OpenAuthReq {
		private String appid;
		public String getAppid()
		{
			return this.appid;
		}
		public void setAppid(String appid)
		{
			this.appid = appid;
		}

		private String password;
		public String getPassword()
		{
			return this.password;
		}
		public void setPassword(String password)
		{
			this.password = password;
		}
	}
	
	

    /*V2UserAuth
     *Description: 
     * @param: body OpenAuthReq OpenAuthReq 必填项
     * @return: *V2UserAuthResponse
    */
    public V2UserAuthResponse V2UserAuth(String host, OpenAuthReq body) throws Exception {
    	OkHttpHelper httpHelper = new OkHttpHelper();
    	Map<String, String> headers = new HashMap<String, String>();
    	
    	String bodyString = JSON.toJSONString(body);
        String respStr = httpHelper.Post(String.format("%s%s", host, String.format("/v2/user/auth")), headers, bodyString);
        
        return JSON.parseObject(respStr, V2UserAuthResponse.class);
    }
}
