package com.taotao.mapper;

import com.taotao.pojo.TbUser;

import java.util.HashMap; /**
 * @Author:dunef
 * @Description:
 * @Date:Created in 上午10:07 2017/12/5
 * @Modified By:
 */
public interface TbUserMapper {
    TbUser findUserByNameAndPasswd(HashMap<String, String> map);
}
