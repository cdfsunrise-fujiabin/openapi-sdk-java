package org.cdfsunrise.open;

import com.alibaba.fastjson.JSON;
import org.cdfsunrise.OkHttpHelper;
import java.util.List;

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
		public int GetBuyType()
		{
			return this.buyType;
		}
		public void SetBuyType(int buyType)
		{
			this.buyType = buyType;
		}

		private String channelId;
		public String GetChannelId()
		{
			return this.channelId;
		}
		public void SetChannelId(String channelId)
		{
			this.channelId = channelId;
		}

		private List<UpdateGood> goodListInfo;
		public List<UpdateGood> GetGoodListInfo()
		{
			return this.goodListInfo;
		}
		public void SetGoodListInfo(List<UpdateGood> goodListInfo)
		{
			this.goodListInfo = goodListInfo;
		}
	}

	public static class UpdateGood {
		private String lefoxId;
		public String GetLefoxId()
		{
			return this.lefoxId;
		}
		public void SetLefoxId(String lefoxId)
		{
			this.lefoxId = lefoxId;
		}

		private int sellState;
		public int GetSellState()
		{
			return this.sellState;
		}
		public void SetSellState(int sellState)
		{
			this.sellState = sellState;
		}

		private String type;
		public String GetType()
		{
			return this.type;
		}
		public void SetType(String type)
		{
			this.type = type;
		}
	}

	public static class GoodResp {
		private String errInfo;
		public String GetErrInfo()
		{
			return this.errInfo;
		}
		public void SetErrInfo(String errInfo)
		{
			this.errInfo = errInfo;
		}

		private String lefoxId;
		public String GetLefoxId()
		{
			return this.lefoxId;
		}
		public void SetLefoxId(String lefoxId)
		{
			this.lefoxId = lefoxId;
		}

		private boolean success;
		public boolean GetSuccess()
		{
			return this.success;
		}
		public void SetSuccess(boolean success)
		{
			this.success = success;
		}
	
	}
	

    /*OpenGoodsUpdate
     *Description: 开放平台商品信息通知
     * @param: body OpenGoodUpdateReq OpenGoodUpdateReq 必填项
     * @return: *OpenGoodsUpdateResponse
    */
    public OpenGoodsUpdateResponse OpenGoodsUpdate(String host, OpenGoodUpdateReq body) throws Exception {
    	OkHttpHelper httpHelper = new OkHttpHelper();
    	
    	String bodyString = JSON.toJSONString(body);
        String respStr = httpHelper.Post(String.format("%s%s", host, String.format("/open/goods/update")), bodyString);

		OpenGoodsUpdateResponse respEntity = new OpenGoodsUpdateResponse();
		var data = JSON.parseArray(respStr, GoodResp.class);
        respEntity.SetData(data);
        return respEntity;
    }
}
