package net.wendal.nutzbook2.modeuls;

import cn.apiclub.captcha.Captcha;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.impl.NutMessageMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

/**
 * Created by wendal on 2016/7/22.
 */
@At("/api/base/")
public class BaseToolsModule {

    @At
    @Ok("raw:png")
    public BufferedImage captcha(HttpSession session, @Param("w") int w, @Param("h") int h) {
        if (w * h < 1) { //长或宽为0?重置为默认长宽.
            w = 100;
            h = 50;
        }
        Captcha captcha = new Captcha.Builder(w, h)
                .addText()
//								.addBackground(new GradiatedBackgroundProducer())
//								.addNoise(new StraightLineNoiseProducer()).addBorder()
//								.gimp(new FishEyeGimpyRenderer())
                .build();
        String text = captcha.getAnswer();
        session.setAttribute("captcha", text);
        return captcha.getImage();
    }

    @At
    public NutMessageMap msg(HttpServletRequest req) {
        return Mvcs.getMessageMap(req);
    }
}
