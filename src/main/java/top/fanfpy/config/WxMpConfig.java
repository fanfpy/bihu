package top.fanfpy.config;

import com.blade.ioc.annotation.Bean;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;

@Bean
public class WxMpConfig  {

    public WxMpConfigStorage wxMpConfigStorage(){
        WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
        config.setAppId("wx946eb9e88682a6a8");
        // 设置微信公众号的app corpSecret
        config.setSecret("076bee6a74ffa0b53a7fb8234172d986");
        return config;
    }

    public WxMpService wxMpService(){
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }
}
