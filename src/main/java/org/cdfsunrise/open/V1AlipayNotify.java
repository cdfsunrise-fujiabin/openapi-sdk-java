package org.cdfsunrise.open;

import com.alibaba.fastjson.JSON;
import org.cdfsunrise.OkHttpHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class V1AlipayNotify {
    public static class V1AlipayNotifyResponse {
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

    	private String data;
        public String getData() {
            return this.data;
        }
        public void setData(String data) {
            this.data = data;
        }
    }
    
	public static class AlipayNotifyRequest {
		private String app_id;
		public String getAppId()
		{
			return this.app_id;
		}
		public void setAppId(String app_id)
		{
			this.app_id = app_id;
		}

		private String biz_content;
		public String getBizContent()
		{
			return this.biz_content;
		}
		public void setBizContent(String biz_content)
		{
			this.biz_content = biz_content;
		}

		private String charset;
		public String getCharset()
		{
			return this.charset;
		}
		public void setCharset(String charset)
		{
			this.charset = charset;
		}

		private String msg_method;
		public String getMsgMethod()
		{
			return this.msg_method;
		}
		public void setMsgMethod(String msg_method)
		{
			this.msg_method = msg_method;
		}

		private String notify_id;
		public String getNotifyId()
		{
			return this.notify_id;
		}
		public void setNotifyId(String notify_id)
		{
			this.notify_id = notify_id;
		}

		private String sign;
		public String getSign()
		{
			return this.sign;
		}
		public void setSign(String sign)
		{
			this.sign = sign;
		}

		private String utc_timestamp;
		public String getUtcTimestamp()
		{
			return this.utc_timestamp;
		}
		public void setUtcTimestamp(String utc_timestamp)
		{
			this.utc_timestamp = utc_timestamp;
		}

		private String version;
		public String getVersion()
		{
			return this.version;
		}
		public void setVersion(String version)
		{
			this.version = version;
		}
	}
	
	

    /*V1AlipayNotify
     *Description: 接收支付宝消息推送
     * @param: body AlipayNotifyRequest AlipayNotifyRequest 必填项
     * @return: *V1AlipayNotifyResponse
    */
    public V1AlipayNotifyResponse V1AlipayNotify(String host, String authToken, AlipayNotifyRequest body) throws Exception {
    	OkHttpHelper httpHelper = new OkHttpHelper();
    	Map<String, String> headers = new HashMap<String, String>();
    	headers.put("Authorization", authToken);
        
    	String bodyString = JSON.toJSONString(body);
        String respStr = httpHelper.Post(String.format("%s%s", host, String.format("/v1/alipay/notify")), headers, bodyString);
        
        return JSON.parseObject(respStr, V1AlipayNotifyResponse.class);
    }
}
