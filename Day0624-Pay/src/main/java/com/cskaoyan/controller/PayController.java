package com.cskaoyan.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

@RestController
public class PayController {
    @Autowired
    AlipayConfig alipayConfig;
    
    //获取二维码接口
    @GetMapping("/test/page/pay")
    public String testPagePay() throws AlipayApiException {
        //基于已有配置，构造发送请求的client对象
        DefaultAlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);
        
        //创建表示获取支付页面的请求request对象
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        
        //设置通知参数
        setCallbackParam(request);

        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        //设置订单号
        long l = System.currentTimeMillis();
        String no = "cskaoyan" + l;
        System.out.println(no);
        model.setOutTradeNo(no);
        
        //设置订单总金额
        model.setTotalAmount("88.88");
        //设置订单标题
        model.setSubject("Iphone 18");
        //设置产品码
        model.setProductCode("FAST_INSTANT_TRADE_PAY");
        //设置超时时间
        setTimeOutParam(model);
        //向request对象发送具体请求参数
        request.setBizModel(model);
        
        //获取支付二维码
        AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
        System.out.println(response.getBody());
        if (response.isSuccess()){
            System.out.println("调用成功");
        }else {
            System.out.println("调用失败");
        }
        //表单字符串
        return response.getBody();
    }

    //查询订单支付结果
    @GetMapping("/test/pay/query")
    public String testPayQuery(String outTradeNo) throws AlipayApiException {
        DefaultAlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        AlipayTradeQueryModel model = new AlipayTradeQueryModel();
        model.setOutTradeNo(outTradeNo);
        
        request.setBizModel(model);
        //发起请求
        AlipayTradeQueryResponse response = alipayClient.execute(request);
        System.out.println(response.getBody());
        if (response.isSuccess()){
            System.out.println("调用成功");
        }else {
            System.out.println("调用失败");
        }
        return response.getTradeStatus();
    }
    
    //关闭订单交易
    @GetMapping("/test/pay/close")
    public String testClosed(String outTradeNo) throws AlipayApiException {
        DefaultAlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);

        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        AlipayTradeQueryModel model = new AlipayTradeQueryModel();
        
        //指定关闭支付交易的订单号
        model.setOutTradeNo(outTradeNo);
        request.setBizModel(model);
        AlipayTradeQueryResponse response = alipayClient.execute(request);
        System.out.println(response.getBody());
        if (response.isSuccess()){
            System.out.println("调用成功");
        }else {
            System.out.println("调用失败");
        }
        return null;
    }
    
    private void setTimeOutParam(AlipayTradePagePayModel model) {
        //设置二维码超时时间
        long now = System.currentTimeMillis();
        //订单超时时间
        //获取支付二维码创建时间- 订单创建时间= 剩余的订单时间
        long remain = 20*1000;
        long timeout = now+remain;
        String timeoutStr = DateFormatUtils.format(new Date(timeout), "yyyy-MM-dd HH:mm:ss");
        //指定过期时间
        model.setTimeExpire(timeoutStr);
    }

    private void setCallbackParam(AlipayTradePagePayRequest request) {
        //指定异步通知的地址
        request.setNotifyUrl("http://baiabai.natapp1.cc/callback/notify");
        
        //指定同步通知的地址
        request.setReturnUrl("http://localhost:8080/return/notify");
    }
}
