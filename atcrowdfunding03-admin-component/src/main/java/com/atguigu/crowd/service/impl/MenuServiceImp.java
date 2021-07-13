package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.entity.Menu;
import com.atguigu.crowd.entity.MenuExample;
import com.atguigu.crowd.mapper.MenuMapper;
import com.atguigu.crowd.service.api.MenuSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImp implements MenuSerivce {

    @Autowired
    private MenuMapper menuMapper;

    public List<Menu> getAll() {
        return menuMapper.selectByExample(new MenuExample());
    }
}
