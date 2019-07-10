package egovframework.com.ext.jstree.support.security.service;

import egovframework.com.ext.jstree.support.security.database.dao.UserDao;
import egovframework.com.ext.jstree.support.security.database.model.Role;
import egovframework.com.ext.jstree.support.security.database.model.User;
import egovframework.com.ext.jstree.support.security.dto.LocalUser;
import egovframework.com.ext.jstree.support.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("localUserDetailService")
public class LocalUserDetailService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalUserDetailService.class);

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public LocalUser loadUserByUsername(final String userId) throws UsernameNotFoundException {

        if(StringUtils.isEmpty(userId)){
            LOGGER.error("사용자 정보가 없는 요청 ( userid empty )" + userId);
        }

        User user = userDao.get(userId);
        LOGGER.info("사용자 정보 ( userid check )" + userId);

        List<SimpleGrantedAuthority> simpleGrantedAuthorities = buildSimpleGrantedAuthorities(user);
        return new LocalUser(user.getUserId(), user.getName(), user.getPassword(), user.getActive() == 1 ? true : false, true
                , true, true, simpleGrantedAuthorities);
    }

    private List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(final User user) {
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        if (user.getRoles() != null) {
            for (Role role : user.getRoles()) {
                simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            }
        }
        return simpleGrantedAuthorities;
    }
}
