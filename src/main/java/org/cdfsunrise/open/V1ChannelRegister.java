package org.cdfsunrise.open;

import com.alibaba.fastjson.JSON;
import org.cdfsunrise.OkHttpHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class V1ChannelRegister {
    public static class V1ChannelRegisterResponse {
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
    
	public static class ChannelRegisterReq {
		private String channelId;
		public String getChannelId()
		{
			return this.channelId;
		}
		public void setChannelId(String channelId)
		{
			this.channelId = channelId;
		}

		private String channelName;
		public String getChannelName()
		{
			return this.channelName;
		}
		public void setChannelName(String channelName)
		{
			this.channelName = channelName;
		}

		private int channelType;
		public int getChannelType()
		{
			return this.channelType;
		}
		public void setChannelType(int channelType)
		{
			this.channelType = channelType;
		}
	}
	
	

    /*V1ChannelRegister
     *Description: 渠道库存注册
     * @param: body ChannelRegisterReq ChannelRegisterReq 必填项
     * @return: *V1ChannelRegisterResponse
    */
    public V1ChannelRegisterResponse V1ChannelRegister(String host, String authToken, ChannelRegisterReq body) throws Exception {
    	OkHttpHelper httpHelper = new OkHttpHelper();
    	Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", authToken);
    	
    	String bodyString = JSON.toJSONString(body);
        String respStr = httpHelper.Post(String.format("%s%s", host, String.format("/v1/channel/register")), headers, bodyString);
        
        V1ChannelRegisterResponse respEntity = new V1ChannelRegisterResponse();
        respEntity.SetData(respStr);
        return respEntity;
    }
}
