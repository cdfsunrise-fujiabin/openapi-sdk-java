package org.cdfsunrise.open;

import com.alibaba.fastjson.JSON;
import org.cdfsunrise.OkHttpHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SyncCdfStock {
    public static class SyncCdfStockResponse {
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
    
	public static class SyncCdfGoodsStockReq {
		private List<SyncCdfGoodsStockItem> items;
		public List<SyncCdfGoodsStockItem> getItems()
		{
			return this.items;
		}
		public void setItems(List<SyncCdfGoodsStockItem> items)
		{
			this.items = items;
		}

		private String occurTime;
		public String getOccurTime()
		{
			return this.occurTime;
		}
		public void setOccurTime(String occurTime)
		{
			this.occurTime = occurTime;
		}
	}
	
	public static class SyncCdfGoodsStockItem {
		private int beforeQuantity;
		public int getBeforeQuantity()
		{
			return this.beforeQuantity;
		}
		public void setBeforeQuantity(int beforeQuantity)
		{
			this.beforeQuantity = beforeQuantity;
		}

		private int buyTypeAgg;
		public int getBuyTypeAgg()
		{
			return this.buyTypeAgg;
		}
		public void setBuyTypeAgg(int buyTypeAgg)
		{
			this.buyTypeAgg = buyTypeAgg;
		}

		private int quantity;
		public int getQuantity()
		{
			return this.quantity;
		}
		public void setQuantity(int quantity)
		{
			this.quantity = quantity;
		}

		private String rTMerchantId;
		public String getRTmerchantId()
		{
			return this.rTMerchantId;
		}
		public void setRTmerchantId(String rTMerchantId)
		{
			this.rTMerchantId = rTMerchantId;
		}

		private String rTSkuId;
		public String getRTskuId()
		{
			return this.rTSkuId;
		}
		public void setRTskuId(String rTSkuId)
		{
			this.rTSkuId = rTSkuId;
		}

		private String stockChannelId;
		public String getStockChannelId()
		{
			return this.stockChannelId;
		}
		public void setStockChannelId(String stockChannelId)
		{
			this.stockChannelId = stockChannelId;
		}
	}
	
	
	

    /*SyncCdfStock
     *Description: 同步cdf中免会员商品库存
     * @param: body SyncCdfGoodsStockReq SyncCdfGoodsStockReq 必填项
     * @return: *SyncCdfStockResponse
    */
    public SyncCdfStockResponse SyncCdfStock(String host, String authToken, SyncCdfGoodsStockReq body) throws Exception {
    	OkHttpHelper httpHelper = new OkHttpHelper();
    	Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", authToken);
    	
    	String bodyString = JSON.toJSONString(body);
        String respStr = httpHelper.Post(String.format("%s%s", host, String.format("/sync/cdf/stock")), headers, bodyString);
        
        SyncCdfStockResponse respEntity = new SyncCdfStockResponse();
        respEntity.SetData(respStr);
        return respEntity;
    }
}
