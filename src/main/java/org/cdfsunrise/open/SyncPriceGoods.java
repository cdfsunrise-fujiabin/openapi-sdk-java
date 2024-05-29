package org.cdfsunrise.open;

import com.alibaba.fastjson.JSON;
import org.cdfsunrise.OkHttpHelper;
import java.util.List;

public class SyncPriceGoods {
    public static class SyncPriceGoodsResponse {
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
    
	public static class SyncPriceGoodsReq {
		private List<String> goodIds;
		public List<String> GetGoodIds()
		{
			return this.goodIds;
		}
		public void SetGoodIds(List<String> goodIds)
		{
			this.goodIds = goodIds;
		}
	}
	
	

    /*SyncPriceGoods
     *Description: 需要同步商品价格集合
     * @param: body SyncPriceGoodsReq SyncPriceGoodsReq 必填项
     * @return: *SyncPriceGoodsResponse
    */
    public SyncPriceGoodsResponse SyncPriceGoods(String host, SyncPriceGoodsReq body) throws Exception {
    	OkHttpHelper httpHelper = new OkHttpHelper();
    	
    	String bodyString = JSON.toJSONString(body);
        String respStr = httpHelper.Post(String.format("%s%s", host, String.format("/sync/price/goods")), bodyString);
        
        SyncPriceGoodsResponse respEntity = new SyncPriceGoodsResponse();
        respEntity.SetData(respStr);
        return respEntity;
    }
}
