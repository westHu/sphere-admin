package com.sphere.pay.config;

import cn.hutool.core.lang.Assert;
import com.sphere.pay.exception.AdminException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@Data
@Slf4j
@Component
@ConfigurationProperties(prefix = "pay.platform.sandbox")
public class SandboxPlatformKeyConfig {

    /**
     * 公钥
     */
    private String key;

    /**
     * 私钥
     */
    private String merchant;


    /**
     * 从配置文件解析公钥
     */
    public String parsePublicKey() {
        Assert.notBlank(this.getKey(), () -> new AdminException("sandbox public key miss"));
        Assert.notBlank(this.getMerchant(), () -> new AdminException("sandbox public key password miss"));
        try {
            byte[] decode = Base64.getDecoder().decode(this.getKey().getBytes());
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(new ByteArrayInputStream(decode), null);

            String certAlias = this.getMerchant().substring(8, 24);
            PublicKey publicKey = keyStore.getCertificate(certAlias).getPublicKey();

            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(publicKey.getEncoded());
            byte[] encoded = spec.getEncoded();
            return Base64.getEncoder().encodeToString(encoded);
        } catch (Exception e) {
            log.error("sandbox merchant parse public key exception", e);
            throw new AdminException("sandbox merchant parse public key exception");
        }
    }
}

