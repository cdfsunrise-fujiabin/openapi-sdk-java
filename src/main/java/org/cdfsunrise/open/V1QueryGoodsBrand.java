package org.cdfsunrise.open;

import com.alibaba.fastjson.JSON;
import org.cdfsunrise.OkHttpHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class V1QueryGoodsBrand {
    public static class V1QueryGoodsBrandResponse {
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

    	private List<GoodsBrand> data;
        public List<GoodsBrand> getData() {
            return this.data;
        }
        public void setData(List<GoodsBrand> data) {
            this.data = data;
        }
    }
    
	public static class OpenDataReq {
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
	
	
	public static class GoodsBrand {
		private String brandId;
		public String getBrandId()
		{
			return this.brandId;
		}
		public void setBrandId(String brandId)
		{
			this.brandId = brandId;
		}

		private String brandNameCN;
		public String getBrandNameCn()
		{
			return this.brandNameCN;
		}
		public void setBrandNameCn(String brandNameCN)
		{
			this.brandNameCN = brandNameCN;
		}

		private String brandNameEn;
		public String getBrandNameEn()
		{
			return this.brandNameEn;
		}
		public void setBrandNameEn(String brandNameEn)
		{
			this.brandNameEn = brandNameEn;
		}
	}
	
	

    /*V1QueryGoodsBrand
     *Description: 开放平台商品品牌查询
     * @param: body OpenDataReq OpenDataReq 必填项
     * @return: *V1QueryGoodsBrandResponse
    */
    public V1QueryGoodsBrandResponse V1QueryGoodsBrand(String host, String authToken, OpenDataReq body) throws Exception {
    	OkHttpHelper httpHelper = new OkHttpHelper();
    	Map<String, String> headers = new HashMap<String, String>();
    	headers.put("Authorization", authToken);
        
    	String bodyString = JSON.toJSONString(body);
        String respStr = httpHelper.Post(String.format("%s%s", host, String.format("/v1/query/goodsBrand")), headers, bodyString);
        
        return JSON.parseObject(respStr, V1QueryGoodsBrandResponse.class);
    }
}
