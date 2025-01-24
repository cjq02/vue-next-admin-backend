package com.jacquinc.admin.application.utils;

import cn.hutool.core.codec.Base64;
import com.jiujie.framework.cache.cache.ICacheClient;
import com.jiujie.framework.spring.context.utils.SpringContextUtil;
import com.jacquinc.admin.application.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Created by zhengzheng on 2020/11/18.
 */
public class ValidateCodeUtil {

    private static final Logger log = LoggerFactory.getLogger(ValidateCodeUtil.class);

    public static String generator(String deviceuuid) throws IOException {
        ICacheClient cacheClient = SpringContextUtil.getBean(ICacheClient.class);

        int width = 80;
        int height = 32;
        //create the image
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        // set the background color
        g.setColor(new Color(0xA9A9A9));
        g.fillRect(0, 0, width, height);
        // draw the border
        g.setColor(Color.black);
        g.drawRect(0, 0, width - 1, height - 1);
        // create a random instance to generate the codes
        Random rdm = new Random();
        String hash1 = Integer.toHexString(rdm.nextInt());
        log.info("设备uuid：{}，验证码：{}", deviceuuid, hash1);
        // make some confusion
        for (int i = 0; i < 50; i++) {
            int x = rdm.nextInt(width);
            int y = rdm.nextInt(height);
            g.drawOval(x, y, 0, 0);
        }
        // generate a random code
        String capstr = hash1.substring(0, 4);
        //将生成的验证码存入cache
        cacheClient.put(Constants.CACHE_GROUP, Constants.CacheKey.LOGIN_VALIDATE_CODE_PREFIX_KEY + deviceuuid, capstr);
        g.setColor(new Color(220, 20, 60));
        g.setFont(new Font("Candara", Font.BOLD, 24));
        g.drawString(capstr, 8, 24);
        g.dispose();

        //输出图片
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", stream);
        String base64 = Base64.encode(stream.toByteArray());
        log.info("base64：{}", base64);
        return base64;
    }

    public static boolean compare(String captcha, String code) {
        log.debug("验证码对比：{}，{}", captcha, code);
        return !(StringUtils.isEmpty(captcha) || StringUtils.isEmpty(code)) && captcha.toUpperCase().equals(code.toUpperCase());
    }
}
