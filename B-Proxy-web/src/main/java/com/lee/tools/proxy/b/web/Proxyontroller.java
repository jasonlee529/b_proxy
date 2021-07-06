package com.lee.tools.proxy.b.web;

import com.lee.tools.proxy.b.api.ProxyService;
import com.lee.tools.proxy.b.api.model.ProxyModel;
import com.lee.tools.proxy.b.web.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Component
@RestController
@RequestMapping("proxy")
public class Proxyontroller {

    @Autowired
    private ProxyService proxyService;

    @GetMapping("getOne")
    public R<ProxyModel> get() {
        return R.ok(proxyService.getOne());
    }

}
