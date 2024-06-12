package org.cdfsunrise.open;

import com.alibaba.fastjson.JSON;
import org.cdfsunrise.OkHttpHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenGoodsUpdate {
    public static class OpenGoodsUpdateResponse {
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
    
	public static class OpenGoodUpdateReq {
		private int buyType;
		public int getBuyType()
		{
			return this.buyType;
		}
		public void setBuyType(int buyType)
		{
			this.buyType = buyType;
		}

		private String channelId;
		public String getChannelId()
		{
			return this.channelId;
		}
		public void setChannelId(String channelId)
		{
			this.channelId = channelId;
		}

		private List<UpdateGood> goodListInfo;
		public List<UpdateGood> getGoodListInfo()
		{
			return this.goodListInfo;
		}
		public void setGoodListInfo(List<UpdateGood> goodListInfo)
		{
			this.goodListInfo = goodListInfo;
		}
	}
	
	public static class UpdateGood {
		private String lefoxId;
		public String getLefoxId()
		{
			return this.lefoxId;
		}
		public void setLefoxId(String lefoxId)
		{
			this.lefoxId = lefoxId;
		}

		private int sellState;
		public int getSellState()
		{
			return this.sellState;
		}
		public void setSellState(int sellState)
		{
			this.sellState = sellState;
		}

		private String type;
		public String getType()
		{
			return this.type;
		}
		public void setType(String type)
		{
			this.type = type;
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
	
	

    /*OpenGoodsUpdate
     *Description: 开放平台商品信息通知
     * @param: body OpenGoodUpdateReq OpenGoodUpdateReq 必填项
     * @return: *OpenGoodsUpdateResponse
    */
    public OpenGoodsUpdateResponse OpenGoodsUpdate(String host, String authToken, OpenGoodUpdateReq body) throws Exception {
    	OkHttpHelper httpHelper = new OkHttpHelper();
    	Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", authToken);
    	
    	String bodyString = JSON.toJSONString(body);
        String respStr = httpHelper.Post(String.format("%s%s", host, String.format("/open/goods/update")), headers, bodyString);
        
        OpenGoodsUpdateResponse respEntity = new OpenGoodsUpdateResponse();
        List<GoodResp> data = JSON.parseArray(respStr, GoodResp.class);
        respEntity.SetData(data);
        return respEntity;
    }
}
