package net.wendal.nutzbook2;

import org.nutz.integration.shiro.ShiroSessionProvider;
import org.nutz.mvc.annotation.*;

/**
 * Created by wendal on 2016/7/21.
 */
@Modules(scanPackage = true)
@IocBy(args={
    "*js","ioc/",
    "*anno", "net.wendal.nutzbook2",
    "*async",
    "*tx",
    "*quartz"})
@SessionBy(ShiroSessionProvider.class)
@Localization(value="msg/", defaultLocalizationKey = "zh_CN")
@Ok("json:full")
@Fail("http:500")
@SetupBy(MainSetup.class)
public class MainModule{
}
