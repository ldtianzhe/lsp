package com.lsp.project.system.service.impl;

import java.util.List;
import com.lsp.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lsp.project.system.mapper.SysVerifyMapper;
import com.lsp.project.system.domain.SysVerify;
import com.lsp.project.system.service.ISysVerifyService;

/**
 * 审核信息Service业务层处理
 *
 * @author lsp
 * @date 2019-12-09
 */
@Service
public class SysVerifyServiceImpl implements ISysVerifyService
{
    @Autowired
    private SysVerifyMapper sysVerifyMapper;

    /**
     * 查询审核信息
     *
     * @param verifyId 审核信息ID
     * @return 审核信息
     */
    @Override
    public SysVerify selectSysVerifyById(String verifyId)
    {
        return sysVerifyMapper.selectSysVerifyById(verifyId);
    }

    /**
     * 查询审核信息列表
     *
     * @param sysVerify 审核信息
     * @return 审核信息
     */
    @Override
    public List<SysVerify> selectSysVerifyList(SysVerify sysVerify)
    {
        return sysVerifyMapper.selectSysVerifyList(sysVerify);
    }

    /**
     * 新增审核信息
     *
     * @param sysVerify 审核信息
     * @return 结果
     */
    @Override
    public int insertSysVerify(SysVerify sysVerify)
    {
        sysVerify.setCreateTime(DateUtils.getNowDate());
        return sysVerifyMapper.insertSysVerify(sysVerify);
    }

    /**
     * 修改审核信息
     *
     * @param sysVerify 审核信息
     * @return 结果
     */
    @Override
    public int updateSysVerify(SysVerify sysVerify)
    {
        sysVerify.setUpdateTime(DateUtils.getNowDate());
        return sysVerifyMapper.updateSysVerify(sysVerify);
    }

    /**
     * 批量删除审核信息
     *
     * @param verifyIds 需要删除的审核信息ID
     * @return 结果
     */
    @Override
    public int deleteSysVerifyByIds(String[] verifyIds)
    {
        return sysVerifyMapper.deleteSysVerifyByIds(verifyIds);
    }

    /**
     * 删除审核信息信息
     *
     * @param verifyId 审核信息ID
     * @return 结果
     */
    @Override
    public int deleteSysVerifyById(String verifyId)
    {
        return sysVerifyMapper.deleteSysVerifyById(verifyId);
    }
}
