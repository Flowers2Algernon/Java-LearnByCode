package com.cskaoyan.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayConfig;
import com.alipay.api.internal.util.AlipaySignature;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.UserDataHandler;

import java.util.Map;

@RestController
public class AliCallController {
    @Autowired
    RedissonClient redissonClient;

    @Autowired
    AlipayConfig alipayConfig;

    //接收同步通知
    @GetMapping("return/notify")
    public String returnCallback(@RequestParam Map<String, String> paramsMap) {
        System.out.println(paramsMap);
        return "return notify success";
    }

    //接收异步通知
    @PostMapping("/callback/notify")
    public String notifyCallback(@RequestParam Map<String, String> paramsMap) throws AlipayApiException {
        //1.校验参数
        boolean isSuccess = checkParam(paramsMap);
        if (!isSuccess){
            return "failure";
        }
        
        //2.过滤重复请求
        boolean isOk = checkDuplicate(paramsMap);
        if (!isOk){
            //重复通知
            return "success";
        }
        
        //校验通过，执行业务逻辑
        boolean ok = true;
        if (ok){
            return "success";
        }
        
        //处理失败，返回
        return "failure";
    }

    private boolean checkParam(Map<String, String> paramsMap) throws AlipayApiException {
        //1. 检查数字前面
        boolean isSuccess = AlipaySignature.rsaCheckV1(paramsMap, alipayConfig.getAlipayPublicKey(), alipayConfig.getCharset(), alipayConfig.getSignType());
        if (!isSuccess){
            return false;
        }
        
        //2. 验证参数的正确性
        //2.1 校验订单
        String outTradeNo = paramsMap.get("outTradeNo");
        //根据通知中的订单id，去数据库中查询是否有该订单
        String outTradeNoFromDB = "1111";
        if (outTradeNoFromDB==null){
            //此时订单不是我们的
            return false;
        }
        
        //2.2 校验支付金额
        String amountStr = paramsMap.get("total_mount");
        double amount = Double.parseDouble(amountStr);
        //去数据库中查询应该支付的金额
        Double amountFromDB = 88.88;
        if (Double.compare(amountFromDB,amount)!=0){
            //参数不正确
            return false;
        }
        
        //2.3 判断app_id
        String appId = paramsMap.get("app_id");
        if (!appId.equals(alipayConfig.getAppId())){
            //参数不正确
            return false;
        }
        
        //2.4 判断支付状态
        String tradeStatus = paramsMap.get("trade_status");
        if (tradeStatus==null||(!tradeStatus.equals("TRADE_SUCCESS"))&&!tradeStatus.equals("TRADE_FINISHED")){
            //参数不正确
            return false;
        }
        return true;
    }

    private boolean checkDuplicate(Map<String, String> paramsMap) {
        String key = "pay:callback:notify"+paramsMap.get("notify_id");
        RBucket<Object> bucket = redissonClient.getBucket(key);
        //利用setnx命令添加
        boolean isOk = bucket.trySet(paramsMap.get("notify_id"));
        return isOk;
    }

}
