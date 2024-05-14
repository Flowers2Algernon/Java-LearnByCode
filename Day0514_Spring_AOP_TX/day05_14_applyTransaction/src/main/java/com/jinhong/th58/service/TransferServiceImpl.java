package com.jinhong.th58.service;

import com.jinhong.th58.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class TransferServiceImpl implements TransferService{

    //因为之前已经做了配置，已经将创建好的mapper实例放入到spring容器中了，所以此处可以直接从容器中取出
    @Autowired
    AccountMapper accountMapper;

    @Autowired
    TransactionTemplate transactionTemplate;

    @Override
    public void transfer(String from, String to, Double money) {
        //将保障事务的代码用doInTransaction包裹起来即可
        transactionTemplate.execute(new TransactionCallback<Object>() {

            @Override
            public Object doInTransaction(TransactionStatus status) {
                accountMapper.updateMoneyByName(from, money);

                //如果转账过程出现异常
                int i = 1 / 0;

                accountMapper.updateMoneyByName(to, -money);

                return null;
            }
        });
    }
}
