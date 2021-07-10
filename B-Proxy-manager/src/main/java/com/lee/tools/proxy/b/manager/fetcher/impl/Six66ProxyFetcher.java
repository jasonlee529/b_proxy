package com.lee.tools.proxy.b.manager.fetcher.impl;

import com.google.common.collect.Lists;
import com.lee.tools.proxy.b.api.model.ProxyDTO;
import com.lee.tools.proxy.b.manager.fetcher.ProxyFetcher;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * @author libo
 * @Title: 66 代理 http://www.66ip.cn/areaindex_1/1.html
 * @Description:
 * @date 2020/6/15 12:02
 * @Version 1.0
 */
@Slf4j
@Data
@NoArgsConstructor
public class Six66ProxyFetcher implements ProxyFetcher {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String[] urls;

    public Six66ProxyFetcher(String[] urls) {
        this.urls = urls;
    }

    @Override
    public List<ProxyDTO> call() throws Exception {
        return fetch();
    }

    @Override
    public List<ProxyDTO> fetch() {
        List<ProxyDTO> models = Lists.newArrayList();
        for (int i = 1; i < 34; i++) {
            String url = String.format("http://www.66ip.cn/areaindex_%d/1.html", i);
            int count = 0;
            Document doc = null;
            try {
                doc = Jsoup.connect(url).get();
                Elements trs = doc.select("#footer").select("tr:gt(0)");
                for (Element tr : trs) {
                    models.add(ProxyDTO.builder()
                            .host(tr.select("td:eq(0)").text())
                            .port(tr.select("td:eq(1)").text())
                            .region(tr.select("td:eq(2)").text())
                            .anonymous(tr.select("td:eq(3)").text())
//                            .type(tr.select("td:eq(5)").text())
                            .source(url).quality(0).nextVerifyTime(System.currentTimeMillis() + 1000 * 60 * 60 * 6).build());
                    count++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            }
            log.info("{} 爬取链接 {} at {}", url, count, new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
        }
        return models;
    }
}
