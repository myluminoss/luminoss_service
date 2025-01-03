package com.ruoyi.common.utils.ip;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.file.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.xdb.Searcher;

import java.io.File;

/**
 * ip,
 * :<a href="https://gitee.com/lionsoul/ip2region/tree/master/binding/java"> ip2region IP</a>
 *
 * @author lishuyan
 */
@Slf4j
public class RegionUtils {

    private static final Searcher SEARCHER;

    static {
        String fileName = "/ip2region.xdb";
        File existFile = FileUtils.file(FileUtil.getTmpDir() + FileUtil.FILE_SEPARATOR + fileName);
        if (!FileUtils.exist(existFile)) {
            ClassPathResource fileStream = new ClassPathResource(fileName);
            if (ObjectUtil.isEmpty(fileStream.getStream())) {
                throw new ServiceException("RegionUtils,:IP!");
            }
            FileUtils.writeFromStream(fileStream.getStream(), existFile);
        }

        String dbPath = existFile.getPath();

        // 1、 dbPath  xdb .
        byte[] cBuff;
        try {
            cBuff = Searcher.loadContentFromFile(dbPath);
        } catch (Exception e) {
            throw new ServiceException("RegionUtils,:ip2region.xdb!" + e.getMessage());
        }
        // 2、 cBuff .
        try {
            SEARCHER = Searcher.newWithBuffer(cBuff);
        } catch (Exception e) {
            throw new ServiceException("RegionUtils,:" + e.getMessage());
        }
    }

    /**
     * IP
     */
    public static String getCityInfo(String ip) {
        try {
            ip = ip.trim();
            // 3、
            String region = SEARCHER.search(ip);
            return region.replace("0|", "").replace("|0", "");
        } catch (Exception e) {
            log.error("IP {}", ip);
            return "";
        }
    }
}
