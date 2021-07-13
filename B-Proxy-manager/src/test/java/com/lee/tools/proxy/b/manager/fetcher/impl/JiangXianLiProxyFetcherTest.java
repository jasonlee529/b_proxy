package com.lee.tools.proxy.b.manager.fetcher.impl;

import com.lee.tools.proxy.b.manager.fetcher.ProxyFetcher;
import org.junit.Test;

import static org.junit.Assert.*;

public class JiangXianLiProxyFetcherTest {

    @Test
    public void fetch() {
        ProxyFetcher fetcher = new JiangXianLiProxyFetcher();
        fetcher.fetch();
    }
}