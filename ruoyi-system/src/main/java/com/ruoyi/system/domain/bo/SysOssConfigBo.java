package com.ruoyi.system.domain.bo;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *  sys_oss_config
 *
 * @author Lion Li
 * @author
 * @date 2021-08-13
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class SysOssConfigBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "", groups = {EditGroup.class})
    private Long ossConfigId;

    /**
     * key
     */
    @NotBlank(message = "key", groups = {AddGroup.class, EditGroup.class})
    @Size(min = 2, max = 100, message = "configKey{min}{max} ")
    private String configKey;

    /**
     * accessKey
     */
    @NotBlank(message = "accessKey", groups = {AddGroup.class, EditGroup.class})
    @Size(min = 2, max = 100, message = "accessKey{min}{max} ")
    private String accessKey;

    /**
     *
     */
    @NotBlank(message = "secretKey", groups = {AddGroup.class, EditGroup.class})
    @Size(min = 2, max = 100, message = "secretKey{min}{max} ")
    private String secretKey;

    /**
     *
     */
    @NotBlank(message = "", groups = {AddGroup.class, EditGroup.class})
    @Size(min = 2, max = 100, message = "bucketName{min}{max}")
    private String bucketName;

    /**
     *
     */
    private String prefix;

    /**
     *
     */
    @NotBlank(message = "", groups = {AddGroup.class, EditGroup.class})
    @Size(min = 2, max = 100, message = "endpoint{min}{max}")
    private String endpoint;

    /**
     *
     */
    private String domain;

    /**
     * https（Y=,N=）
     */
    private String isHttps;

    /**
     * （0=,1=）
     */
    private String status;

    /**
     *
     */
    private String region;

    /**
     *
     */
    private String ext1;

    /**
     *
     */
    private String remark;

    /**
     * (0private 1public 2custom)
     */
    @NotBlank(message = "", groups = {AddGroup.class, EditGroup.class})
    private String accessPolicy;

}
