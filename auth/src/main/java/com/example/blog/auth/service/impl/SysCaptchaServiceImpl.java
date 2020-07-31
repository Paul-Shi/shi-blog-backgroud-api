package com.example.blog.auth.service.impl;

import com.example.blog.auth.service.SysCaptchaService;
import com.example.blog.common.constants.RedisKeyConstants;
import com.example.blog.common.exception.MyException;
import com.example.blog.common.exception.enums.ErrorEnum;
import com.example.blog.common.util.RedisUtils;
import com.google.code.kaptcha.Producer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

@Service
public class SysCaptchaServiceImpl implements SysCaptchaService {
    @Autowired
    private Producer producer;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 验证码过期时长5s
     */
    public final static long CAPTCHA_EXPIRE = 60 * 5;

    /**
     * 获取验证码
     *
     * @param uuid
     * @return
     */
    @Override
    public BufferedImage getCaptcha(String uuid) {
        if (StringUtils.isEmpty(uuid)) {
            throw new MyException(ErrorEnum.NO_UUID);
        }
        //生成文字验证码
        String code = producer.createText();
        //存进redis,5分钟后过期
        redisUtils.set(genRedisKey(uuid), code, CAPTCHA_EXPIRE);
        return producer.createImage(code);
    }

    /**
     * 验证验证码
     *
     * @param uuid
     * @param code
     * @return
     */
    @Override
    public boolean validate(String uuid, String code) {
        if (StringUtils.isEmpty(uuid) || StringUtils.isEmpty(code)) {
            return false;
        }
        //从redis中获取
        String redisKey = genRedisKey(uuid);
        String captchaCode = redisUtils.get(redisKey);
        //删除验证码
        redisUtils.delete(redisKey);
        if (code.equalsIgnoreCase(captchaCode)) {
            return true;
        }

        return false;
    }

    /**
     * 生成rediskey
     *
     * @param uuid
     * @return
     */
    private String genRedisKey(String uuid) {
        return RedisKeyConstants.MANAGE_SYS_CAPTCHA + uuid;
    }
}
