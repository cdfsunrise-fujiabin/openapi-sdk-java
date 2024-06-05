package org.cdfsunrise.open;

import com.alibaba.fastjson.JSON;
import org.cdfsunrise.OkHttpHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SyncGoodPrice {
    public static class SyncGoodPriceResponse {
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

    	private List<GoodResp> data;
    	public List<GoodResp> GetData() {
            return this.data;
        }
        public void SetData(List<GoodResp> data) {
            this.data = data;
        }
    }
    
	public static class SyncGoodPriceReq {
		private List<GoodPriceInfo> goodPriceList;
		public List<GoodPriceInfo> getGoodPriceList()
		{
			return this.goodPriceList;
		}
		public void setGoodPriceList(List<GoodPriceInfo> goodPriceList)
		{
			this.goodPriceList = goodPriceList;
		}

		private String supplier;
		public String getSupplier()
		{
			return this.supplier;
		}
		public void setSupplier(String supplier)
		{
			this.supplier = supplier;
		}
	}
	
	public static class GoodPriceInfo {
		private String lefoxId;
		public String getLefoxId()
		{
			return this.lefoxId;
		}
		public void setLefoxId(String lefoxId)
		{
			this.lefoxId = lefoxId;
		}

		private String merchantID;
		public String getMerchantId()
		{
			return this.merchantID;
		}
		public void setMerchantId(String merchantID)
		{
			this.merchantID = merchantID;
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

		private String salePrice;
		public String getSalePrice()
		{
			return this.salePrice;
		}
		public void setSalePrice(String salePrice)
		{
			this.salePrice = salePrice;
		}
	}
	
	
	
	public static class GoodResp {
		private String errInfo;
		public String getErrInfo()
		{
			return this.errInfo;
		}
		public void setErrInfo(String errInfo)
		{
			this.errInfo = errInfo;
		}

		private String lefoxId;
		public String getLefoxId()
		{
			return this.lefoxId;
		}
		public void setLefoxId(String lefoxId)
		{
			this.lefoxId = lefoxId;
		}

		private boolean success;
		public boolean getSuccess()
		{
			return this.success;
		}
		public void setSuccess(boolean success)
		{
			this.success = success;
		}
	}
	
	

    /*SyncGoodPrice
     *Description: 同步商品价格信息
     * @param: body SyncGoodPriceReq SyncGoodPriceReq 必填项
     * @return: *SyncGoodPriceResponse
    */
    public SyncGoodPriceResponse SyncGoodPrice(String host, String authToken, SyncGoodPriceReq body) throws Exception {
    	OkHttpHelper httpHelper = new OkHttpHelper();
    	Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", authToken);
    	
    	String bodyString = JSON.toJSONString(body);
        String respStr = httpHelper.Post(String.format("%s%s", host, String.format("/sync/good/price")), headers, bodyString);
        
        SyncGoodPriceResponse respEntity = new SyncGoodPriceResponse();
        var data = JSON.parseArray(respStr, GoodResp.class);
        respEntity.SetData(data);
        return respEntity;
    }
}
