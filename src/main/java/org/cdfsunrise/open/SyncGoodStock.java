package org.cdfsunrise.open;

import com.alibaba.fastjson.JSON;
import org.cdfsunrise.OkHttpHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SyncGoodStock {
    public static class SyncGoodStockResponse {
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
    
	public static class SyncGoodsStockReq {
		private String channelId;
		public String getChannelId()
		{
			return this.channelId;
		}
		public void setChannelId(String channelId)
		{
			this.channelId = channelId;
		}

		private List<GoodsStockItem> goodsStockList;
		public List<GoodsStockItem> getGoodsStockList()
		{
			return this.goodsStockList;
		}
		public void setGoodsStockList(List<GoodsStockItem> goodsStockList)
		{
			this.goodsStockList = goodsStockList;
		}

		private String requestMessageId;
		public String getRequestMessageId()
		{
			return this.requestMessageId;
		}
		public void setRequestMessageId(String requestMessageId)
		{
			this.requestMessageId = requestMessageId;
		}
	}
	
	public static class GoodsStockItem {
		private String goodsId;
		public String getGoodsId()
		{
			return this.goodsId;
		}
		public void setGoodsId(String goodsId)
		{
			this.goodsId = goodsId;
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

		private int num;
		public int getNum()
		{
			return this.num;
		}
		public void setNum(int num)
		{
			this.num = num;
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

		private int type;
		public int getType()
		{
			return this.type;
		}
		public void setType(int type)
		{
			this.type = type;
		}
	}
	
	
	

    /*SyncGoodStock
     *Description: 同步库存信息
     * @param: body SyncGoodsStockReq SyncGoodsStockReq 必填项
     * @return: *SyncGoodStockResponse
    */
    public SyncGoodStockResponse SyncGoodStock(String host, String authToken, SyncGoodsStockReq body) throws Exception {
    	OkHttpHelper httpHelper = new OkHttpHelper();
    	Map<String, String> headers = new HashMap<String, String>();
    	headers.put("Authorization", authToken);
        
    	String bodyString = JSON.toJSONString(body);
        String respStr = httpHelper.Post(String.format("%s%s", host, String.format("/sync/good/stock")), headers, bodyString);
        
        SyncGoodStockResponse respEntity = new SyncGoodStockResponse();
        respEntity.setData(respStr);
        return respEntity;
    }
}
