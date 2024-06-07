package org.cdfsunrise.open;

import org.cdfsunrise.OkHttpHelper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryStockRecord {
    public static class QueryStockRecordResponse {
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
        
        QueryStockRecordResponse respEntity = new QueryStockRecordResponse();
        respEntity.SetData(respStr);
        return respEntity;
    }
}
