package com.ruoyi.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.system.domain.bo.UserTokenBo;
import com.ruoyi.system.domain.vo.UserTokenVo;
import com.ruoyi.system.domain.UserToken;
import com.ruoyi.system.mapper.UserTokenMapper;
import com.ruoyi.system.service.IUserTokenService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * Service
 *
 * @author ruoyi
 * @date 2024-12-05
 */
@RequiredArgsConstructor
@Service
public class UserTokenServiceImpl implements IUserTokenService {

    private final UserTokenMapper baseMapper;

    /**
     *
     */
    @Override
    public UserTokenVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     *
     */
    @Override
    public UserTokenVo queryByToken(String token){
        UserTokenBo bo = new UserTokenBo();
        bo.setToken(token);
        LambdaQueryWrapper<UserToken> lqw = buildQueryWrapper(bo);
        lqw.last("limit 1");
        return baseMapper.selectVoOne(lqw);
    }

    /**
     *
     */
    @Override
    public TableDataInfo<UserTokenVo> queryPageList(UserTokenBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<UserToken> lqw = buildQueryWrapper(bo);
        Page<UserTokenVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     *
     */
    @Override
    public List<UserTokenVo> queryList(UserTokenBo bo) {
        LambdaQueryWrapper<UserToken> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<UserToken> buildQueryWrapper(UserTokenBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<UserToken> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getUserId() != null, UserToken::getUserId, bo.getUserId());
        lqw.eq(StringUtils.isNotBlank(bo.getType()), UserToken::getType, bo.getType());
        lqw.eq(StringUtils.isNotBlank(bo.getToken()), UserToken::getToken, bo.getToken());
        return lqw;
    }

    /**
     *
     */
    @Override
    public Boolean insertByBo(UserTokenBo bo) {
        UserToken add = BeanUtil.toBean(bo, UserToken.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     *
     */
    @Override
    public Boolean updateByBo(UserTokenBo bo) {
        UserToken update = BeanUtil.toBean(bo, UserToken.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     *
     */
    private void validEntityBeforeSave(UserToken entity){
        //TODO ,
    }

    /**
     *
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO ,
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
