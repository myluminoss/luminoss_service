package com.ruoyi.framework.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JavaMail
 *
 * @author Michelle.Chung
 */
@Data
@Component
@ConfigurationProperties(prefix = "mail")
public class MailProperties {

    /**
     *
     */
    private Boolean enabled;

    /**
     * SMTP
     */
    private String host;

    /**
     * SMTP
     */
    private Integer port;

    /**
     *
     */
    private Boolean auth;

    /**
     *
     */
    private String user;

    /**
     *
     */
    private String pass;

    /**
     * ,RFC-822
     */
    private String from;

    /**
     *  STARTTLS,STARTTLS.（TLSSSL）, .
     */
    private Boolean starttlsEnable;

    /**
     *  SSL
     */
    private Boolean sslEnable;

    /**
     * SMTP,,
     */
    private Long timeout;

    /**
     * Socket,,
     */
    private Long connectionTimeout;
}
