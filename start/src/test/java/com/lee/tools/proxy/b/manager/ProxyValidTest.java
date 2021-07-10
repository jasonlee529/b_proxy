package com.lee.tools.proxy.b.manager;

import com.lee.tools.proxy.b.api.model.ProxyDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

@Slf4j
public class ProxyValidTest {

    @Test
    public void valid1() throws IOException {
        Boolean res = ProxyValid.valid(ProxyDTO.builder().host("1.20.217.221").port("8080").build());
        log.info(res + "");
    }

    @Test
    public void test2() throws IOException {
        Boolean res = ProxyValid.valid(ProxyDTO.builder().host("114.253.247.76").port("8888").build());
        log.info(res + "");
    }
}