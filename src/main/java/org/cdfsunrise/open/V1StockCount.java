package org.cdfsunrise.open;

import com.alibaba.fastjson.JSON;
import org.cdfsunrise.OkHttpHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class V1StockCount {
    public static class V1StockCountResponse {
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

    	private List<OpenGoodsStock> data;
    	public List<OpenGoodsStock> GetData() {
            return this.data;
        }
        public void SetData(List<OpenGoodsStock> data) {
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
	
	
	public static class OpenGoodsStock {
		private String lefoxId;
		public String getLefoxId()
		{
			return this.lefoxId;
		}
		public void setLefoxId(String lefoxId)
		{
			this.lefoxId = lefoxId;
		}

		private int stock;
		public int getStock()
		{
			return this.stock;
		}
		public void setStock(int stock)
		{
			this.stock = stock;
		}
	}
	
	

    /*V1StockCount
     *Description: 开放平台批量查询商品库存
     * @param: body OpenDataReq OpenDataReq 必填项
     * @return: *V1StockCountResponse
    */
    public V1StockCountResponse V1StockCount(String host, String authToken, OpenDataReq body) throws Exception {
    	OkHttpHelper httpHelper = new OkHttpHelper();
    	Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", authToken);
    	
    	String bodyString = JSON.toJSONString(body);
        String respStr = httpHelper.Post(String.format("%s%s", host, String.format("/v1/stock/count")), headers, bodyString);
        
        V1StockCountResponse respEntity = new V1StockCountResponse();
        List<OpenGoodsStock> data = JSON.parseArray(respStr, OpenGoodsStock.class);
        respEntity.SetData(data);
        return respEntity;
    }
}
