package com.jinhong.th58.controller;

import com.jinhong.th58.mapper.UserMapper;
import com.jinhong.th58.service.UserTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccountController {

    @Autowired
    UserTransferService userTransferService;

    @RequestMapping("/transfer")
    @ResponseBody
    @Transactional
    public Object transfer(@RequestParam(name = "fromId") Integer fromId, @RequestParam(name = "destId") Integer desId, @RequestParam(name = "money") double money) throws Exception {
        //使用声明式事物来保障事物
        //在该Handler方法中完成由1给2转1000元的业务,要求包含事务
        //此时使用@RequestParam能获取到请求中的参数
        //先获取两者余额
        double fromIdOriginalMoney = userTransferService.selectMoneyById(fromId);
        double desIdOriginalMoney = userTransferService.selectMoneyById(desId);
        System.out.println(fromIdOriginalMoney);
        System.out.println(desIdOriginalMoney);
        if (fromIdOriginalMoney < money) {
            throw new Exception("余额不足");
        }
        userTransferService.updateMoneyByIdAndAmount(fromId,-money);
        userTransferService.updateMoneyByIdAndAmount(desId,money);
        return "转账成功";
    }
}
