package org.cdfsunrise.open;

import org.cdfsunrise.OkHttpHelper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryGoodsStock {
    public static class QueryGoodsStockResponse {
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
    

    /*QueryGoodsStock
     *Description: 查询商品库存信息
     * @param: channelId string 商户渠道 必填项
     * @param: lefoxId string lefoxid 必填项
     * @return: *QueryGoodsStockResponse
    */
    public QueryGoodsStockResponse QueryGoodsStock(String host, String authToken, String channelId, String lefoxId) throws Exception {
    	OkHttpHelper httpHelper = new OkHttpHelper();
    	Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", authToken);
    	
        String respStr = httpHelper.Get(String.format("%s%s", host, String.format("/query/goods/stock?channelId=%s&lefoxId=%s", channelId, lefoxId)), headers);
        
        QueryGoodsStockResponse respEntity = new QueryGoodsStockResponse();
        respEntity.SetData(respStr);
        return respEntity;
    }
}
