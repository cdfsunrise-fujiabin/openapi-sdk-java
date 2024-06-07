package org.cdfsunrise.open;

import org.cdfsunrise.OkHttpHelper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryGoodInfo {
    public static class QueryGoodInfoResponse {
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
    

    /*QueryGoodInfo
     *Description: 查询商品信息工具
     * @param: channelId string 渠道id 必填项
     * @param: lefoxId string 商品lefoxid 必填项
     * @return: *QueryGoodInfoResponse
    */
    public QueryGoodInfoResponse QueryGoodInfo(String host, String authToken, String channelId, String lefoxId) throws Exception {
    	OkHttpHelper httpHelper = new OkHttpHelper();
    	Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", authToken);
    	
        String respStr = httpHelper.Get(String.format("%s%s", host, String.format("/query/good/info?channelId=%s&lefoxId=%s", channelId, lefoxId)), headers);
        
        QueryGoodInfoResponse respEntity = new QueryGoodInfoResponse();
        respEntity.SetData(respStr);
        return respEntity;
    }
}
