package com.chen.blog.service;

import com.chen.blog.common.Constant;
import com.chen.blog.common.WordDefined;
import com.chen.blog.entity.Blog;
import com.chen.blog.entity.User;
import com.chen.blog.exception.BlogException;
import com.chen.blog.redis.RedisUtils;
import com.chen.blog.repository.UserRepository;
import com.chen.blog.utils.OthersUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.connection.SortParameters;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisUtils redisUtils;


    public void register(User user,String token) {
        if (!checkToken(user.getPhone(), token)) {
            throw new BlogException(WordDefined.ERROR_TOKEN);
        }
        user.setCreatetime(OthersUtils.getCreateTime());
        Blog blog = new Blog();
        blog.setBlogName(user.getNickname());
        user.setBlog(blog);
        user.setHeadurl(WordDefined.DEFAULT_HEAD_URL);
        userRepository.save(user);
    }


    public List<User> getTopList(){
        Sort sort = Sort.by("goodSum desc", "viewSum", "commentSum");
//        userRepository.findTop10(sort);
        return null;
    }



    /**
     * 校验手机验证码
     *
     * @param code
     */
    public String checkCode(String phone,String code) {
        String value = redisUtils.getValue(Constant.connectBlogCodePhone(phone));
        if (value != null && value.equals(code)) {
            //移除验证码
            redisUtils.delKey(Constant.connectBlogCodePhone(phone));
            //创建token
            String token = createToken(phone);
            return token;
        }
        throw new BlogException(WordDefined.ERROR_CODE);
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
            //....
            //存储redis
            //2分钟过期
            redisUtils.setKeyAndTimeOut(Constant.connectBlogCodePhone(phone),WordDefined.PHONE_CODE_TIME_OUT,code);
            //当天剩余时间
            long seconds = OthersUtils.getCurrentDateRemainSeconds();
            redisUtils.setKeyAndTimeOut(Constant.connectBlogCodePhoneCount(phone),seconds,"1");
            return;
        }
        if (Integer.valueOf(count) < WordDefined.MAX_PHONE_SEND_COUNT) {
            String code = OthersUtils.createCode(WordDefined.PHOME_CODE_DIGIT);
            //发送验证码
            //....
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
     * 创建token
     *
     * @param phone
     * @return
     */
    public String createToken(String phone){
        String codeToken = OthersUtils.createCodeToken();
        String key = Constant.connectBlogTokenCode(phone);
        redisUtils.setKeyAndTimeOut(key,WordDefined.PHONE_TOKEN_TIME_OUT,codeToken);
        return codeToken;
    }

    /**
     * 校验token
     * @param token
     * @return
     */
    public boolean checkToken(String phone,String token){
        String value = redisUtils.getValue(Constant.connectBlogTokenCode(phone));
        if (token.equals(value)) {
            //移除token
            redisUtils.delKey(Constant.connectBlogTokenCode(phone));
            return true;
        }
        return false;
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
