package org.cdfsunrise.open;

import com.alibaba.fastjson.JSON;
import org.cdfsunrise.OkHttpHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class V1MerchantRegister {
    public static class V1MerchantRegisterResponse {
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
    
	public static class MerchantRegisterReq {
		private int businessType;
		public int getBusinessType()
		{
			return this.businessType;
		}
		public void setBusinessType(int businessType)
		{
			this.businessType = businessType;
		}

		private String merchantName;
		public String getMerchantName()
		{
			return this.merchantName;
		}
		public void setMerchantName(String merchantName)
		{
			this.merchantName = merchantName;
		}
	}
	
	

    /*V1MerchantRegister
     *Description: 商户注册
     * @param: body MerchantRegisterReq MerchantRegisterReq 必填项
     * @return: *V1MerchantRegisterResponse
    */
    public V1MerchantRegisterResponse V1MerchantRegister(String host, String authToken, MerchantRegisterReq body) throws Exception {
    	OkHttpHelper httpHelper = new OkHttpHelper();
    	Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", authToken);
    	
    	String bodyString = JSON.toJSONString(body);
        String respStr = httpHelper.Post(String.format("%s%s", host, String.format("/v1/merchant/register")), headers, bodyString);
        
        V1MerchantRegisterResponse respEntity = new V1MerchantRegisterResponse();
        respEntity.SetData(respStr);
        return respEntity;
    }
}