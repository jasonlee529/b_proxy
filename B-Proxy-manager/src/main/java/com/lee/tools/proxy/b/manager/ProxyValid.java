package com.lee.tools.proxy.b.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lee.tools.proxy.b.api.model.ProxyDTO;
import com.lee.tools.proxy.b.manager.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;

/**
 * @Title: ProxyValid
 * @Description: 测试代理有效性
 * @author: libo
 * @date: 2021/7/10 0010 11:15
 * @Version: 1.0
 */
@Slf4j
public class ProxyValid {

    private static String HTTP_URL = "http://httpbin.org/ip";
    private static String HTTPS_URL = "https://httpbin.org/ip";
    private static ObjectMapper om = new ObjectMapper();

    /**
     * 测试代理可用性
     *
     * @param proxyDTO
     * @return
     */
    public static boolean valid(ProxyDTO proxyDTO) {
        String res = HttpUtils.doGetWithProxy(HTTP_URL, null, proxyDTO.getHost(), Integer.valueOf(proxyDTO.getPort()));
        HashMap map = null;
        try {
            map = om.readValue(res, HashMap.class);
            return map.containsKey("origin");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }
}