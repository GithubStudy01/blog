package com.chen.blog.security;

import com.chen.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * 查询用户的权限信息
 */
@Service("userDetailService")
public class UserDetailService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String loginNums) throws UsernameNotFoundException {
		com.chen.blog.entity.User user = userRepository.findUserByPhoneOrAccount(loginNums,loginNums);
		if(user==null) {
			throw new UsernameNotFoundException("账号不存在");
		}
		boolean accountNonLocked=true;
		boolean enabled=true;
		if(1==user.getLockSign()){
			accountNonLocked=false;//冻结
		}
		if(1==user.getDeleteSign()){
			enabled=false;//删除
		}

		return new User(loginNums, user.getPassword(),enabled, true, true, accountNonLocked,null);//在上面的代码中可以查询该用户是否为锁定、删除信息，并注入到User中
	}

}
