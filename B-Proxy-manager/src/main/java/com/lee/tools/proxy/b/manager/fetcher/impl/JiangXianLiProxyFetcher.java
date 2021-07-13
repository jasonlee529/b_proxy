package com.lee.tools.proxy.b.manager.fetcher.impl;

import com.google.common.collect.Lists;
import com.lee.tools.proxy.b.api.model.ProxyDTO;
import com.lee.tools.proxy.b.manager.fetcher.ProxyFetcher;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @Title: JiangXianLiProxyFetcher
 * @Description:
 * @author: libo
 * @date: 2021/7/14 0014 1:21
 * @Version: 1.0
 */
@Slf4j
public class JiangXianLiProxyFetcher implements ProxyFetcher {
    private List<String> indexUrlsTpls = Arrays.asList("http://ip.jiangxianli.com/?page=%d");

    @Override
    public List<ProxyDTO> fetch() {
        List<ProxyDTO> models = Lists.newArrayList();
        //首页
        for (String urlTpl : indexUrlsTpls) {
            for (int i = 1; i <= 5; i++) {
                String url = String.format(urlTpl, i);
                int count = 0;
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                    Elements trs = doc.select("table").select("tbody").select("tr");
                    for (Element tr : trs) {
                        models.add(ProxyDTO.builder()
                                .host(tr.select("td:eq(0)").text())
                                .port(tr.select("td:eq(1)").text())
                                .anonymous(tr.select("td:eq(2)").text())
                                .type(tr.select("td:eq(3)").text())
                                .region(tr.select("td:eq(4)").text())
                                .source(url).quality(10).nextVerifyTime(System.currentTimeMillis() + 1000 * 60 * 60 * 6).build());
                        count++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                }
                log.info("{} 爬取链接 {} at {}", url, count, new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
            }
        }
        return models;
    }
}