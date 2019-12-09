package com.chen.blog.service;

import com.chen.blog.common.Constant;
import com.chen.blog.common.WordDefined;
import com.chen.blog.entity.User;
import com.chen.blog.exception.BlogException;
import com.chen.blog.redis.RedisUtils;
import com.chen.blog.repository.UserRepository;
import com.chen.blog.utils.OthersUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisUtils redisUtils;


    public void register(User user) {


    }

    /**
     * 校验手机验证码
     *
     * @param code
     */
    public void checkCode(String phone,String code) {

//        TMember member = (TMember)session.getAttribute(Contants.LOGIN_MEMBER);
//        String phone = member.getPhone();
//        if(phone!=null) {
//            String codeTemp = redisTemplate.opsForValue().get(Contants.WEBCODEREALNAMEKEY+phone);
//            if(codeTemp!=null&&codeTemp.trim().equals(code)) {
//                String token = Utils.createCodeToken();
//                redisTemplate.opsForValue().set(Contants.CODETOKEN+phone,token, 60*10, TimeUnit.SECONDS);//保存10分钟
//                return WebVO.success().put("token", token).getMap();
//            }
//        }
//        return WebVO.fail().getMap();


    }

    /**
     * 发送手机验证码
     *
     *  同一个手机号一天只可以发送 5 次验证码
     *  验证码 2 分钟后失效
     *
     * @param phone
     */
    public void sendCode(String phone) {
        //发送次数
        String count = redisUtils.getValue(Constant.connectBlogCodePhoneCount(phone));
        if (count == null) {
            String code = OthersUtils.createCode(WordDefined.PHOME_CODE_DIGIT);
            //发送验证码

            //存储redis
            redisUtils.setKeyAndTimeOut(Constant.connectBlogCodePhone(phone),WordDefined.PHONE_CODE_TIME_OUT,code);
            //当天剩余时间
            long seconds = OthersUtils.getCurrentDateRemainSeconds();
            redisUtils.setKeyAndTimeOut(Constant.connectBlogCodePhoneCount(phone),seconds,"1");
            return;
        }
        if (Integer.valueOf(count) < WordDefined.MAX_PHONE_SEND_COUNT) {
            String code = OthersUtils.createCode(WordDefined.PHOME_CODE_DIGIT);
            //发送验证码

            //存储redis
            redisUtils.setKeyAndTimeOut(Constant.connectBlogCodePhone(phone),WordDefined.PHONE_CODE_TIME_OUT,code);
            //自增1
            redisUtils.increment(Constant.connectBlogCodePhone(phone),1);
            return;
        }
        //超次数
        throw new BlogException(WordDefined.PHONE_SEND_CODE_PASS_MAX_COUNT);
    }


    /**
     * 检查手机号码唯一性
     * @param phone
     * @return
     */
    public boolean checkPhoneUnique(String phone){
        if (phone == null||phone.trim().equals("")) {
            return false;
        }
        User user = userRepository.findUserByPhone(phone);
        return user==null?false:true;
    }


}
