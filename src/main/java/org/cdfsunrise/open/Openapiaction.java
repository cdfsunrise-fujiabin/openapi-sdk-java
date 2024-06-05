package org.cdfsunrise.open;

import com.alibaba.fastjson.JSON;
import org.cdfsunrise.OkHttpHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Openapiaction {
    public static class OpenapiactionResponse {
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
    
	public static class YZFOrderNotifyRequest {
		private String appId;
		public String getAppId()
		{
			return this.appId;
		}
		public void setAppId(String appId)
		{
			this.appId = appId;
		}

		private String data;
		public String getData()
		{
			return this.data;
		}
		public void setData(String data)
		{
			this.data = data;
		}

		private String method;
		public String getMethod()
		{
			return this.method;
		}
		public void setMethod(String method)
		{
			this.method = method;
		}

		private String requestNo;
		public String getRequestNo()
		{
			return this.requestNo;
		}
		public void setRequestNo(String requestNo)
		{
			this.requestNo = requestNo;
		}

		private String timestamp;
		public String getTimestamp()
		{
			return this.timestamp;
		}
		public void setTimestamp(String timestamp)
		{
			this.timestamp = timestamp;
		}
	}
	
	

    /*Openapiaction
     *Description: 翼支付消息推送
     * @param: action string  必填项
     * @param: body YZFOrderNotifyRequest YZFOrderNotifyRequest 必填项
     * @return: *OpenapiactionResponse
    */
    public OpenapiactionResponse Openapiaction(String host, String authToken, String action, YZFOrderNotifyRequest body) throws Exception {
    	OkHttpHelper httpHelper = new OkHttpHelper();
    	Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", authToken);
    	
    	String bodyString = JSON.toJSONString(body);
        String respStr = httpHelper.Post(String.format("%s%s", host, String.format("/openapi/%v", action)), headers, bodyString);
        
        OpenapiactionResponse respEntity = new OpenapiactionResponse();
        respEntity.SetData(respStr);
        return respEntity;
    }
}
