package com.ruoyi.oss.entity;

import lombok.Builder;
import lombok.Data;

/**
 *
 *
 * @author Lion Li
 */
@Data
@Builder
public class UploadResult {

    /**
     *
     */
    private String url;

    /**
     *
     */
    private String filename;
}
