package com.jinhong.th58.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 远志 zhangsong@cskaoyan.onaliyun.com
 * @Date 2024/5/14 15:22
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private Integer id;

    private String name;

    private Double money;
}
