package top.fanfpy.config;

import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;


public class WxMpMessageRouterConfig {

    private static WxMpMessageRouter newRouter;

    private WxMpMessageRouterConfig() {
    }

    public static WxMpMessageRouter messageRouter(WxMpService wxMpService) {

        if (newRouter==null){
            newRouter = new WxMpMessageRouter(wxMpService);
        }
        return newRouter;
    }
}
