package org.cdfsunrise.open;

import com.alibaba.fastjson.JSON;
import org.cdfsunrise.OkHttpHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class V1MallAfterSale {
    public static class V1MallAfterSaleResponse {
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
    
	public static class BaseRequest {
		private String appid;
		public String getAppid()
		{
			return this.appid;
		}
		public void setAppid(String appid)
		{
			this.appid = appid;
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

		private String dataEncryptMethod;
		public String getDataEncryptMethod()
		{
			return this.dataEncryptMethod;
		}
		public void setDataEncryptMethod(String dataEncryptMethod)
		{
			this.dataEncryptMethod = dataEncryptMethod;
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

		private String signEncryptMethod;
		public String getSignEncryptMethod()
		{
			return this.signEncryptMethod;
		}
		public void setSignEncryptMethod(String signEncryptMethod)
		{
			this.signEncryptMethod = signEncryptMethod;
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
	
	

    /*V1MallAfterSale
     *Description: 【商户入驻】- 查询售后单详情
     * @param: body BaseRequest BaseRequest 必填项
     * @return: *V1MallAfterSaleResponse
    */
    public V1MallAfterSaleResponse V1MallAfterSale(String host, String authToken, BaseRequest body) throws Exception {
    	OkHttpHelper httpHelper = new OkHttpHelper();
    	Map<String, String> headers = new HashMap<String, String>();
    	headers.put("Authorization", authToken);
        
    	String bodyString = JSON.toJSONString(body);
        String respStr = httpHelper.Post(String.format("%s%s", host, String.format("/v1/mall/afterSale")), headers, bodyString);
        
        return JSON.parseObject(respStr, V1MallAfterSaleResponse.class);
    }
}
