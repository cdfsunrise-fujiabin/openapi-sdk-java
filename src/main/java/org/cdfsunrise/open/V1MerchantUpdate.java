package org.cdfsunrise.open;

import com.alibaba.fastjson.JSON;
import org.cdfsunrise.OkHttpHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class V1MerchantUpdate {
    public static class V1MerchantUpdateResponse {
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
    
	public static class MerchantUpdateReq {
		private String appid;
		public String getAppid()
		{
			return this.appid;
		}
		public void setAppid(String appid)
		{
			this.appid = appid;
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

		private String distributionChannel;
		public String getDistributionChannel()
		{
			return this.distributionChannel;
		}
		public void setDistributionChannel(String distributionChannel)
		{
			this.distributionChannel = distributionChannel;
		}

		private int expireTime;
		public int getExpireTime()
		{
			return this.expireTime;
		}
		public void setExpireTime(int expireTime)
		{
			this.expireTime = expireTime;
		}

		private String extInfo;
		public String getExtInfo()
		{
			return this.extInfo;
		}
		public void setExtInfo(String extInfo)
		{
			this.extInfo = extInfo;
		}

		private String goodsChannelId;
		public String getGoodsChannelId()
		{
			return this.goodsChannelId;
		}
		public void setGoodsChannelId(String goodsChannelId)
		{
			this.goodsChannelId = goodsChannelId;
		}

		private String merchantName;
		public String getMerchantName()
		{
			return this.merchantName;
		}
		public void setMerchantName(String merchantName)
		{
			this.merchantName = merchantName;
		}

		private String orderSource;
		public String getOrderSource()
		{
			return this.orderSource;
		}
		public void setOrderSource(String orderSource)
		{
			this.orderSource = orderSource;
		}

		private String password;
		public String getPassword()
		{
			return this.password;
		}
		public void setPassword(String password)
		{
			this.password = password;
		}

		private String postHost;
		public String getPostHost()
		{
			return this.postHost;
		}
		public void setPostHost(String postHost)
		{
			this.postHost = postHost;
		}

		private String postPath;
		public String getPostPath()
		{
			return this.postPath;
		}
		public void setPostPath(String postPath)
		{
			this.postPath = postPath;
		}
	}
	
	

    /*V1MerchantUpdate
     *Description: 商户信息编辑
     * @param: body MerchantUpdateReq MerchantUpdateReq 必填项
     * @return: *V1MerchantUpdateResponse
    */
    public V1MerchantUpdateResponse V1MerchantUpdate(String host, String authToken, MerchantUpdateReq body) throws Exception {
    	OkHttpHelper httpHelper = new OkHttpHelper();
    	Map<String, String> headers = new HashMap<String, String>();
    	headers.put("Authorization", authToken);
        
    	String bodyString = JSON.toJSONString(body);
        String respStr = httpHelper.Post(String.format("%s%s", host, String.format("/v1/merchant/update")), headers, bodyString);
        
        return JSON.parseObject(respStr, V1MerchantUpdateResponse.class);
    }
}
