package com.lee.tools.proxy.b.web;

import com.lee.tools.proxy.b.api.ProxyService;
import com.lee.tools.proxy.b.api.model.ProxyDTO;
import com.lee.tools.proxy.b.web.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 处理代理相关
 */
@Component
@RestController
@RequestMapping("proxy")
public class Proxyontroller {

    @Autowired
    private ProxyService proxyService;

    /**
     * 随机获取一个代理
     *
     * @return
     */
    @GetMapping("getOne")
    public R<ProxyDTO> get() {
        return R.ok(proxyService.getOne());
    }

    /**
     * 降级一个代理
     *
     * @return
     */
    @GetMapping("degrade")
    public R<Boolean> get(@RequestParam("host") String host, @RequestParam("port") String port) {
        proxyService.degrade(host, port);
        return R.ok(Boolean.TRUE);
    }
}
