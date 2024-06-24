package com.cskaoyan.config;

import com.alipay.api.AlipayConfig;
import org.bouncycastle.crypto.signers.RSADigestSigner;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyPayConfig {
    @Bean
    public RedissonClient redisClient() {
        // 1.new 一个config对象
        Config config = new Config();
        // 2.配置config对象
        config.useSingleServer().setAddress("redis://192.168.192.129:6379");
        // 设置序列化
        config.setCodec(new JsonJacksonCodec());
        // 创建redis客户端，该对象创建的时候，就会向redis-server发起连接请求
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }

    @Bean
    public AlipayConfig getPublicConfig() {
// 应用私匙
        String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDjIjwW1A4LCx4XeqcGFiIfmTfRhFIFUGZtpSAvLO7Lflm3XcxEtNrU9+0FRZy0LcOg5/wuo3C4i/ZgTRVbopbxw9SymKRVddoOhZxxvcZqr0TFK0sX11R6DzYC1jYJ1nuco0PgX2mkb3JWqRncxHjfuKq7afRbD+9S7CbAFt2rvlVJsVrhW+H3CrId/j1rln8QtBMQ9sDCzcBztXgAh/pmxmzHfXbPVGjifP3ohh4MtsKtaZ0r6PYw02UZriDpvATEuulxc7G4+ncQEtHZrHTnjLdSO3ig8xqUMGrSrelWe5ehehEyEcNzOzQrpnKefLoMvBTzBaX1KQDe9hvQHV3tAgMBAAECggEALya7PK324FoFfDBNHhlYKiEfklIayNmg+eAOp1x3kiz/qO5C6nx09UMmpDKITms1smCWXIQ3cUg09FbsOSKunS/fJthDPS6C/wLDsGUKHLmX2CqdoyxjcBdaSgp4G2C4NpM6EwkboDQ2k0D1inwK776hSti7DnBnaVp2zOeySuWLJvR43bkASvfLmyCx3gQ6EQAaaWqTpGhDRuO8hu0D25CgOu6JRoWubJmSGwYYJtQtrjHa7HoE7GemCTIyRJYNBH7hgcUvHk3s5i6cFKiONOpTEybn76Ta6bWXsbAxvMXyZJ9kiuKpDwcFygBVyAygm66y+fRXREnqa5nAssljYQKBgQDxjzPOaeTQ1nV/WmDR4Q1pk5wdgdNR23YNJ4HiV5PV+phSgE7G9DwniDqOrNvcpP4J7+cNS+8u4QSI2AF6esjTDGNVRWIrT4q9yCli+XuX9uPsni1fXdPfI4Y1Ig+/dXdRYbrMNXcKaLwWF2GAgB9OWPsmX34HsyQYh2xqw0RuNQKBgQDwtkOFidn3exbL24Zx8blY1DrpqEJA9C1jRIr63LBs5KwthFc7mxAIjwpKkS7L4MgML1cmXSnnZ6ggo5d5A6ICDB2WAQDHxYYrC0tBd1n/qqudTwdvme1NHfmlV6etQdWH47NHuo3kFJl7p1s/X1S737jnVqIbmjC8qKv9GMaH2QKBgQDII0jeHZiKexVwxdXwLpGpRraTMAyBBt7heTlA6iS/plfsCwi6CIOKkXFK4udrAXzFiPIbby2l3qOeKbHX48FeMyZd8RWsIsQmB6Bas6/2MYWYd1nKaPfxKgK5JRZ9H/sS/3EAXs/ZXPtxJkAPcpf9lvXHTlkiUgd3F0T5k/2vfQKBgQCPPIYVcqBHifFYwXrRVvvqBfBAliVGrYZMqb7h5Gz/f1uNglC+YVk7HpMLKDVmkI254nnDlHCZCK+dLHu4QRcojLkWR+aE+d8Kwh+4ByOA5bhVVW+hU0FIQGwLnYtjp+c+g5ptNm+0pyVl1YKQxVOQsGJBFuZD8o7J1LzzUMjikQKBgCPGEYANNRoiwXdMjali5Z3va8SNdQygOlKtfrR197YU0rvKXxAZ+ZWKsvPnZUTeA07R+xBbL4VTzA5mX8iELtZDXFRzDqM3etKEDoOpWYA+5x8kjN0qHOUjSKtGotwy47OgpW03yihhTLOe2n5lU/UpsSWW2oUqrcKYmKB+AI9V";
        // 支付宝公
        String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4yI8FtQOCwseF3qnBhYiH5k30YRSBVBmbaUgLyzuy35Zt13MRLTa1PftBUWctC3DoOf8LqNwuIv2YE0VW6KW8cPUspikVXXaDoWccb3Gaq9ExStLF9dUeg82AtY2CdZ7nKND4F9ppG9yVqkZ3MR437iqu2n0Ww/vUuwmwBbdq75VSbFa4Vvh9wqyHf49a5Z/ELQTEPbAws3Ac7V4AIf6ZsZsx312z1Ro4nz96IYeDLbCrWmdK+j2MNNlGa4g6bwExLrpcXOxuPp3EBLR2ax054y3Ujt4oPMalDBq0q3pVnuXoXoRMhHDczs0K6Zynny6DLwU8wWl9SkA3vYb0B1d7QIDAQAB";
        AlipayConfig alipayConfig = new AlipayConfig();
        alipayConfig.setServerUrl("https://openapi-sandbox.dl.alipaydev.com/gateway.do");
        alipayConfig.setAppId("2088721038161496");
        alipayConfig.setPrivateKey(privateKey);
        alipayConfig.setFormat("json");
        alipayConfig.setAlipayPublicKey(alipayPublicKey);
        alipayConfig.setCharset("UTF8");
        alipayConfig.setSignType("RSA2");
        return alipayConfig;
    }
}
