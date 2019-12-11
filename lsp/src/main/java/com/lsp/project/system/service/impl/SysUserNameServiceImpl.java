package com.lsp.project.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lsp.project.system.mapper.SysUserNameMapper;
import com.lsp.project.system.domain.SysUserName;
import com.lsp.project.system.service.ISysUserNameService;

/**
 * 实名信息Service业务层处理
 *
 * @author lsp
 * @date 2019-12-10
 */
@Service
public class SysUserNameServiceImpl implements ISysUserNameService
{
    @Autowired
    private SysUserNameMapper sysUserNameMapper;

    /**
     * 查询实名信息
     *
     * @param id 实名信息ID
     * @return 实名信息
     */
    @Override
    public SysUserName selectSysUserNameById(String id)
    {
        return sysUserNameMapper.selectSysUserNameById(id);
    }

    /**
     * 根据用户id查询实名信息
     *
     * @param userid 用户id
     * @return
     */
    @Override
    public SysUserName selectSysUserNameByUserId(Long userid) {
        return sysUserNameMapper.selectSysUserNameByUserId(userid);
    }

    /**
     * 查询实名信息列表
     *
     * @param sysUserName 实名信息
     * @return 实名信息
     */
    @Override
    public List<SysUserName> selectSysUserNameList(SysUserName sysUserName)
    {
        return sysUserNameMapper.selectSysUserNameList(sysUserName);
    }

    /**
     * 新增实名信息
     *
     * @param sysUserName 实名信息
     * @return 结果
     */
    @Override
    public int insertSysUserName(SysUserName sysUserName)
    {
        return sysUserNameMapper.insertSysUserName(sysUserName);
    }

    /**
     * 修改实名信息
     *
     * @param sysUserName 实名信息
     * @return 结果
     */
    @Override
    public int updateSysUserName(SysUserName sysUserName)
    {
        return sysUserNameMapper.updateSysUserName(sysUserName);
    }

    /**
     * 批量删除实名信息
     *
     * @param ids 需要删除的实名信息ID
     * @return 结果
     */
    @Override
    public int deleteSysUserNameByIds(String[] ids)
    {
        return sysUserNameMapper.deleteSysUserNameByIds(ids);
    }

    /**
     * 删除实名信息信息
     *
     * @param id 实名信息ID
     * @return 结果
     */
    @Override
    public int deleteSysUserNameById(String id)
    {
        return sysUserNameMapper.deleteSysUserNameById(id);
    }
}
