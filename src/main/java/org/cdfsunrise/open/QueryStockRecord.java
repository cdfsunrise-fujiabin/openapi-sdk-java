package org.cdfsunrise.open;

import com.alibaba.fastjson.JSON;
import org.cdfsunrise.OkHttpHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryStockRecord {
    public static class QueryStockRecordResponse {
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
    

    /*QueryStockRecord
     *Description: 查询库存操作记录
     * @param: channelId string channelId 必填项
     * @param: requestNo string requestNo 必填项
     * @return: *QueryStockRecordResponse
    */
    public QueryStockRecordResponse QueryStockRecord(String host, String authToken, String channelId, String requestNo) throws Exception {
    	OkHttpHelper httpHelper = new OkHttpHelper();
    	Map<String, String> headers = new HashMap<String, String>();
    	headers.put("Authorization", authToken);
        
        String respStr = httpHelper.Get(String.format("%s%s", host, String.format("/query/stock/record?channelId=%s&requestNo=%s", channelId, requestNo)), headers);
        
        return JSON.parseObject(respStr, QueryStockRecordResponse.class);
    }
}
