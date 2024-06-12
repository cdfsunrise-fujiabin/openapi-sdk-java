package org.cdfsunrise.open;

import com.alibaba.fastjson.JSON;
import org.cdfsunrise.OkHttpHelper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryGoodsStock {
    public static class QueryGoodsStockResponse {
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

        return JSON.parseObject(respStr, QueryGoodsStockResponse.class);
    }
}
