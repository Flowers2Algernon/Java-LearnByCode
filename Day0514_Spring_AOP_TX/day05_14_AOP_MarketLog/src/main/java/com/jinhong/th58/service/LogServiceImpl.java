package com.jinhong.th58.service;

import com.jinhong.th58.domain.LogReflect;
import com.jinhong.th58.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LogServiceImpl implements LogService{

    @Override
    public void addLog(LogReflect logReflect) {
        //此处实现往数据库中写入log
        @Autowired
        LogMapper logMapper;

    }
}
