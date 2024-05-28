package org.cdfsunrise.open;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QueryGoodInfoTest {
    @Test
    void testQueryGoodInfo() {
        var test = new QueryGoodInfo();
        try {
            QueryGoodInfo.QueryGoodInfoResponse response = test.QueryGoodInfo("http://lefox-marketing-openapi-gateway-dev.cdfsunrise.com", "658a3d2ab7084300011bcf69", "721371");
            String data = response.GetData();
            assertNotEquals("", data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}