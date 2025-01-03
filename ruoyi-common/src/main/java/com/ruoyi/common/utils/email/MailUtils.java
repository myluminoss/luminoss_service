package com.ruoyi.common.utils.email;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.mail.*;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.mail.Authenticator;
import javax.mail.Session;
import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MailUtils {

    private static final MailAccount ACCOUNT = SpringUtils.getBean(MailAccount.class);

    /**
     *
     */
    public static MailAccount getMailAccount() {
        return ACCOUNT;
    }

    /**
     *  ()
     *
     * @param user
     * @param pass
     */
    public static MailAccount getMailAccount(String from, String user, String pass) {
        ACCOUNT.setFrom(StringUtils.blankToDefault(from, ACCOUNT.getFrom()));
        ACCOUNT.setUser(StringUtils.blankToDefault(user, ACCOUNT.getUser()));
        ACCOUNT.setPass(StringUtils.blankToDefault(pass, ACCOUNT.getPass()));
        return ACCOUNT;
    }

    /**
     * ,<br>
     * “,”,“;”
     *
     * @param to
     * @param subject
     * @param content
     * @param files
     * @return message-id
     * @since 3.2.0
     */
    public static String sendText(String to, String subject, String content, File... files) {
        return send(to, subject, content, false, files);
    }

    /**
     * HTML,<br>
     * “,”,“;”
     *
     * @param to
     * @param subject
     * @param content
     * @param files
     * @return message-id
     * @since 3.2.0
     */
    public static String sendHtml(String to, String subject, String content, File... files) {
        return send(to, subject, content, true, files);
    }

    /**
     * ,<br>
     * “,”,“;”
     *
     * @param to
     * @param subject
     * @param content
     * @param isHtml  HTML
     * @param files
     * @return message-id
     */
    public static String send(String to, String subject, String content, boolean isHtml, File... files) {
        return send(splitAddress(to), subject, content, isHtml, files);
    }

    /**
     * ,<br>
     * 、、“,”,“;”
     *
     * @param to      ,“,”,“;”
     * @param cc      ,“,”,“;”
     * @param bcc     ,“,”,“;”
     * @param subject
     * @param content
     * @param isHtml  HTML
     * @param files
     * @return message-id
     * @since 4.0.3
     */
    public static String send(String to, String cc, String bcc, String subject, String content, boolean isHtml, File... files) {
        return send(splitAddress(to), splitAddress(cc), splitAddress(bcc), subject, content, isHtml, files);
    }

    /**
     * ,
     *
     * @param tos
     * @param subject
     * @param content
     * @param files
     * @return message-id
     */
    public static String sendText(Collection<String> tos, String subject, String content, File... files) {
        return send(tos, subject, content, false, files);
    }

    /**
     * HTML,
     *
     * @param tos
     * @param subject
     * @param content
     * @param files
     * @return message-id
     * @since 3.2.0
     */
    public static String sendHtml(Collection<String> tos, String subject, String content, File... files) {
        return send(tos, subject, content, true, files);
    }

    /**
     * ,
     *
     * @param tos
     * @param subject
     * @param content
     * @param isHtml  HTML
     * @param files
     * @return message-id
     */
    public static String send(Collection<String> tos, String subject, String content, boolean isHtml, File... files) {
        return send(tos, null, null, subject, content, isHtml, files);
    }

    /**
     * ,
     *
     * @param tos
     * @param ccs     ,null
     * @param bccs    ,null
     * @param subject
     * @param content
     * @param isHtml  HTML
     * @param files
     * @return message-id
     * @since 4.0.3
     */
    public static String send(Collection<String> tos, Collection<String> ccs, Collection<String> bccs, String subject, String content, boolean isHtml, File... files) {
        return send(getMailAccount(), true, tos, ccs, bccs, subject, content, null, isHtml, files);
    }

    // ------------------------------------------------------------------------------------------------------------------------------- Custom MailAccount

    /**
     *
     *
     * @param mailAccount
     * @param to          ,
     * @param subject
     * @param content
     * @param isHtml      HTML
     * @param files
     * @return message-id
     * @since 3.2.0
     */
    public static String send(MailAccount mailAccount, String to, String subject, String content, boolean isHtml, File... files) {
        return send(mailAccount, splitAddress(to), subject, content, isHtml, files);
    }

    /**
     *
     *
     * @param mailAccount
     * @param tos
     * @param subject
     * @param content
     * @param isHtml      HTML
     * @param files
     * @return message-id
     */
    public static String send(MailAccount mailAccount, Collection<String> tos, String subject, String content, boolean isHtml, File... files) {
        return send(mailAccount, tos, null, null, subject, content, isHtml, files);
    }

    /**
     *
     *
     * @param mailAccount
     * @param tos
     * @param ccs         ,null
     * @param bccs        ,null
     * @param subject
     * @param content
     * @param isHtml      HTML
     * @param files
     * @return message-id
     * @since 4.0.3
     */
    public static String send(MailAccount mailAccount, Collection<String> tos, Collection<String> ccs, Collection<String> bccs, String subject, String content, boolean isHtml, File... files) {
        return send(mailAccount, false, tos, ccs, bccs, subject, content, null, isHtml, files);
    }

    /**
     * HTML,<br>
     * “,”,“;”
     *
     * @param to
     * @param subject
     * @param content
     * @param imageMap ,cid:$IMAGE_PLACEHOLDER
     * @param files
     * @return message-id
     * @since 3.2.0
     */
    public static String sendHtml(String to, String subject, String content, Map<String, InputStream> imageMap, File... files) {
        return send(to, subject, content, imageMap, true, files);
    }

    /**
     * ,<br>
     * “,”,“;”
     *
     * @param to
     * @param subject
     * @param content
     * @param imageMap ,cid:$IMAGE_PLACEHOLDER
     * @param isHtml   HTML
     * @param files
     * @return message-id
     */
    public static String send(String to, String subject, String content, Map<String, InputStream> imageMap, boolean isHtml, File... files) {
        return send(splitAddress(to), subject, content, imageMap, isHtml, files);
    }

    /**
     * ,<br>
     * 、、“,”,“;”
     *
     * @param to       ,“,”,“;”
     * @param cc       ,“,”,“;”
     * @param bcc      ,“,”,“;”
     * @param subject
     * @param content
     * @param imageMap ,cid:$IMAGE_PLACEHOLDER
     * @param isHtml   HTML
     * @param files
     * @return message-id
     * @since 4.0.3
     */
    public static String send(String to, String cc, String bcc, String subject, String content, Map<String, InputStream> imageMap, boolean isHtml, File... files) {
        return send(splitAddress(to), splitAddress(cc), splitAddress(bcc), subject, content, imageMap, isHtml, files);
    }

    /**
     * HTML,
     *
     * @param tos
     * @param subject
     * @param content
     * @param imageMap ,cid:$IMAGE_PLACEHOLDER
     * @param files
     * @return message-id
     * @since 3.2.0
     */
    public static String sendHtml(Collection<String> tos, String subject, String content, Map<String, InputStream> imageMap, File... files) {
        return send(tos, subject, content, imageMap, true, files);
    }

    /**
     * ,
     *
     * @param tos
     * @param subject
     * @param content
     * @param imageMap ,cid:$IMAGE_PLACEHOLDER
     * @param isHtml   HTML
     * @param files
     * @return message-id
     */
    public static String send(Collection<String> tos, String subject, String content, Map<String, InputStream> imageMap, boolean isHtml, File... files) {
        return send(tos, null, null, subject, content, imageMap, isHtml, files);
    }

    /**
     * ,
     *
     * @param tos
     * @param ccs      ,null
     * @param bccs     ,null
     * @param subject
     * @param content
     * @param imageMap ,cid:$IMAGE_PLACEHOLDER
     * @param isHtml   HTML
     * @param files
     * @return message-id
     * @since 4.0.3
     */
    public static String send(Collection<String> tos, Collection<String> ccs, Collection<String> bccs, String subject, String content, Map<String, InputStream> imageMap, boolean isHtml, File... files) {
        return send(getMailAccount(), true, tos, ccs, bccs, subject, content, imageMap, isHtml, files);
    }

    // ------------------------------------------------------------------------------------------------------------------------------- Custom MailAccount

    /**
     *
     *
     * @param mailAccount
     * @param to          ,
     * @param subject
     * @param content
     * @param imageMap    ,cid:$IMAGE_PLACEHOLDER
     * @param isHtml      HTML
     * @param files
     * @return message-id
     * @since 3.2.0
     */
    public static String send(MailAccount mailAccount, String to, String subject, String content, Map<String, InputStream> imageMap, boolean isHtml, File... files) {
        return send(mailAccount, splitAddress(to), subject, content, imageMap, isHtml, files);
    }

    /**
     *
     *
     * @param mailAccount
     * @param tos
     * @param subject
     * @param content
     * @param imageMap    ,cid:$IMAGE_PLACEHOLDER
     * @param isHtml      HTML
     * @param files
     * @return message-id
     * @since 4.6.3
     */
    public static String send(MailAccount mailAccount, Collection<String> tos, String subject, String content, Map<String, InputStream> imageMap, boolean isHtml, File... files) {
        return send(mailAccount, tos, null, null, subject, content, imageMap, isHtml, files);
    }

    /**
     *
     *
     * @param mailAccount
     * @param tos
     * @param ccs         ,null
     * @param bccs        ,null
     * @param subject
     * @param content
     * @param imageMap    ,cid:$IMAGE_PLACEHOLDER
     * @param isHtml      HTML
     * @param files
     * @return message-id
     * @since 4.6.3
     */
    public static String send(MailAccount mailAccount, Collection<String> tos, Collection<String> ccs, Collection<String> bccs, String subject, String content, Map<String, InputStream> imageMap,
                              boolean isHtml, File... files) {
        return send(mailAccount, false, tos, ccs, bccs, subject, content, imageMap, isHtml, files);
    }

    /**
     * ,
     *
     * @param mailAccount
     * @param isSingleton （）
     * @return {@link Session}
     * @since 5.5.7
     */
    public static Session getSession(MailAccount mailAccount, boolean isSingleton) {
        Authenticator authenticator = null;
        if (mailAccount.isAuth()) {
            authenticator = new UserPassAuthenticator(mailAccount.getUser(), mailAccount.getPass());
        }

        return isSingleton ? Session.getDefaultInstance(mailAccount.getSmtpProps(), authenticator) //
            : Session.getInstance(mailAccount.getSmtpProps(), authenticator);
    }

    // ------------------------------------------------------------------------------------------------------------------------ Private method start

    /**
     *
     *
     * @param mailAccount
     * @param useGlobalSession Session
     * @param tos
     * @param ccs              ,null
     * @param bccs             ,null
     * @param subject
     * @param content
     * @param imageMap         ,cid:${cid}
     * @param isHtml           HTML
     * @param files
     * @return message-id
     * @since 4.6.3
     */
    private static String send(MailAccount mailAccount, boolean useGlobalSession, Collection<String> tos, Collection<String> ccs, Collection<String> bccs, String subject, String content,
                               Map<String, InputStream> imageMap, boolean isHtml, File... files) {
        final Mail mail = Mail.create(mailAccount).setUseGlobalSession(useGlobalSession);

        //
        if (CollUtil.isNotEmpty(ccs)) {
            mail.setCcs(ccs.toArray(new String[0]));
        }
        //
        if (CollUtil.isNotEmpty(bccs)) {
            mail.setBccs(bccs.toArray(new String[0]));
        }

        mail.setTos(tos.toArray(new String[0]));
        mail.setTitle(subject);
        mail.setContent(content);
        mail.setHtml(isHtml);
        mail.setFiles(files);

        //
        if (MapUtil.isNotEmpty(imageMap)) {
            for (Map.Entry<String, InputStream> entry : imageMap.entrySet()) {
                mail.addImage(entry.getKey(), entry.getValue());
                //
                IoUtil.close(entry.getValue());
            }
        }

        return mail.send();
    }

    /**
     * ,
     *
     * @param addresses ,null
     * @return
     */
    private static List<String> splitAddress(String addresses) {
        if (StrUtil.isBlank(addresses)) {
            return null;
        }

        List<String> result;
        if (StrUtil.contains(addresses, CharUtil.COMMA)) {
            result = StrUtil.splitTrim(addresses, CharUtil.COMMA);
        } else if (StrUtil.contains(addresses, ';')) {
            result = StrUtil.splitTrim(addresses, ';');
        } else {
            result = CollUtil.newArrayList(addresses);
        }
        return result;
    }
    // ------------------------------------------------------------------------------------------------------------------------ Private method end

}
