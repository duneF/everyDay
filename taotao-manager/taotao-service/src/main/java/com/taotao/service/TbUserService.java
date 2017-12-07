package com.taotao.service;

import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 上午10:25 2017/12/5
 * @Modified By:
 */
@Service
public class TbUserService {
    @Autowired
    private TbUserMapper tbUserMapper;
    private TbUser tbUser;

    public TbUser findUserByNameAndPasswd(HashMap<String, String> map) {

        tbUser = tbUserMapper.findUserByNameAndPasswd(map);
        return tbUser;
    }
}
