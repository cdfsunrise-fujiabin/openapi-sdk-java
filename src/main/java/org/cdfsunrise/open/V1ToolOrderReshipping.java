package org.cdfsunrise.open;

import com.alibaba.fastjson.JSON;
import org.cdfsunrise.OkHttpHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class V1ToolOrderReshipping {
    public static class V1ToolOrderReshippingResponse {
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
    
	public static class ToolReShipmentReq {
		private List<String> orderNo;
		public List<String> getOrderNo()
		{
			return this.orderNo;
		}
		public void setOrderNo(List<String> orderNo)
		{
			this.orderNo = orderNo;
		}
	}
	
	

    /*V1ToolOrderReshipping
     *Description: 订单发货重推到商户
     * @param: body ToolReShipmentReq ToolReShipmentReq 必填项
     * @return: *V1ToolOrderReshippingResponse
    */
    public V1ToolOrderReshippingResponse V1ToolOrderReshipping(String host, String authToken, ToolReShipmentReq body) throws Exception {
    	OkHttpHelper httpHelper = new OkHttpHelper();
    	Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", authToken);
    	
    	String bodyString = JSON.toJSONString(body);
        String respStr = httpHelper.Post(String.format("%s%s", host, String.format("/v1/tool/order/reshipping")), headers, bodyString);
        
        V1ToolOrderReshippingResponse respEntity = new V1ToolOrderReshippingResponse();
        respEntity.SetData(respStr);
        return respEntity;
    }
}
