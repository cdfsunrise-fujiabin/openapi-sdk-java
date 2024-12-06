package org.cdfsunrise.open;

import org.cdfsunrise.service.RsaService;
import org.cdfsunrise.service.SignService;
import org.junit.jupiter.api.Test;

import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class V2UserAuthTest {
    @Test
    void v2UserAuth() {
        try {
            V2UserAuth test = new V2UserAuth();
            V2UserAuth.OpenAuthReq req = new V2UserAuth.OpenAuthReq();
            req.setAppid("202410063240");
            req.setPassword(RsaService.encrypt("JOgpwjPX", "-----BEGIN RSA PUBLIC KEY-----\n" +
                    "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvpU12jUnOULLW369R062\n" +
                    "KPBshdpsJYewvHMk2dJSfrBYT3Y2lpvLDVIhsgsi1HRhd66zHlaYezTqi4617SNH\n" +
                    "wRRdnQSmdMWrPOIZwqCK3HvKko8FyWMQU/deEy6ltKcvF+a+x+GS1x1Nlk1LstCY\n" +
                    "DeQN7ajZW1nfdSJBPm/+MHPEoA/hm1R2e9hpv/JIWE0tTeT4vU5Ds1AMyzmyK055\n" +
                    "+e74oLg4HAqwRNmonq75q12XfsjWW/104g0jSA/K4pM15YTzUI3r+cwGhYT12EQ0\n" +
                    "EGyUsjxvXxpPPE816UV3tZ+xoySQVFS5yTYb60tZBx1Nv6QphOlvmvP/f5+v4aX7\n" +
                    "AwIDAQAB\n" +
                    "-----END RSA PUBLIC KEY-----"));

            V2UserAuth.V2UserAuthResponse response = test.V2UserAuth("http://lefox-marketing-openapi-gateway-dev.cdfsunrise.com", req);

            String requestId = response.getRequestId();
            assertNotEquals("", requestId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}