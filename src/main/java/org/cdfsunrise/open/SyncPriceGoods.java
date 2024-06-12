package org.cdfsunrise.open;

import com.alibaba.fastjson.JSON;
import org.cdfsunrise.OkHttpHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SyncPriceGoods {
    public static class SyncPriceGoodsResponse {
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
    
	public static class SyncPriceGoodsReq {
		private List<String> goodIds;
		public List<String> getGoodIds()
		{
			return this.goodIds;
		}
		public void setGoodIds(List<String> goodIds)
		{
			this.goodIds = goodIds;
		}
	}
	
	

    /*SyncPriceGoods
     *Description: 需要同步商品价格集合
     * @param: body SyncPriceGoodsReq SyncPriceGoodsReq 必填项
     * @return: *SyncPriceGoodsResponse
    */
    public SyncPriceGoodsResponse SyncPriceGoods(String host, String authToken, SyncPriceGoodsReq body) throws Exception {
    	OkHttpHelper httpHelper = new OkHttpHelper();
    	Map<String, String> headers = new HashMap<String, String>();
    	headers.put("Authorization", authToken);
        
    	String bodyString = JSON.toJSONString(body);
        String respStr = httpHelper.Post(String.format("%s%s", host, String.format("/sync/price/goods")), headers, bodyString);
        
        SyncPriceGoodsResponse respEntity = new SyncPriceGoodsResponse();
        respEntity.setData(respStr);
        return respEntity;
    }
}
