package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.entity.Auth;
import com.atguigu.crowd.entity.AuthExample;
import com.atguigu.crowd.mapper.AuthMapper;
import com.atguigu.crowd.service.api.AuthSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AuthSerivceImp implements AuthSerivce {

    @Autowired
    private AuthMapper authMapper;

    public List<Auth> getAll() {

        return authMapper.selectByExample(new AuthExample());
    }

    public List<Integer> getAssignedAuthIdByRoleId(Integer roleId) {

        return authMapper.selectAssignedAuthIdByRoleId(roleId);

    }

    public void saveRoleAuthRelationship(Map<String, List<Integer>> map) {

        // 1.获取roleId的值
        List<Integer> roleIdList = map.get("roleId");
        Integer roleId = roleIdList.get(0);

        // 2.删除旧关联关系数据
        authMapper.deleteOldRelationship(roleId);

        // 3.获取authIdList
        List<Integer> authIdList = map.get("authIds");

        //4.判断authIdList是否有效
        if(authIdList != null && authIdList.size()>0){
            authMapper.insertNewRelationship(roleId,authIdList);
        }
    }
}
