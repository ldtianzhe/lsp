package com.lsp.project.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lsp.project.system.mapper.SysMissionMapper;
import com.lsp.project.system.domain.SysMission;
import com.lsp.project.system.service.ISysMissionService;

/**
 * 任务信息Service业务层处理
 *
 * @author lsp
 * @date 2019-12-13
 */
@Service
public class SysMissionServiceImpl implements ISysMissionService
{
    @Autowired
    private SysMissionMapper sysMissionMapper;

    /**
     * 查询任务信息
     *
     * @param id 任务信息ID
     * @return 任务信息
     */
    @Override
    public SysMission selectSysMissionById(Long id)
    {
        return sysMissionMapper.selectSysMissionById(id);
    }

    /**
     * 查询任务信息列表
     *
     * @param sysMission 任务信息
     * @return 任务信息
     */
    @Override
    public List<SysMission> selectSysMissionList(SysMission sysMission)
    {
        return sysMissionMapper.selectSysMissionList(sysMission);
    }

    /**
     * 新增任务信息
     *
     * @param sysMission 任务信息
     * @return 结果
     */
    @Override
    public int insertSysMission(SysMission sysMission)
    {
        return sysMissionMapper.insertSysMission(sysMission);
    }

    /**
     * 修改任务信息
     *
     * @param sysMission 任务信息
     * @return 结果
     */
    @Override
    public int updateSysMission(SysMission sysMission)
    {
        return sysMissionMapper.updateSysMission(sysMission);
    }

    /**
     * 批量删除任务信息
     *
     * @param ids 需要删除的任务信息ID
     * @return 结果
     */
    @Override
    public int deleteSysMissionByIds(Long[] ids)
    {
        return sysMissionMapper.deleteSysMissionByIds(ids);
    }

    /**
     * 删除任务信息信息
     *
     * @param id 任务信息ID
     * @return 结果
     */
    @Override
    public int deleteSysMissionById(Long id)
    {
        return sysMissionMapper.deleteSysMissionById(id);
    }

    @Override
    public SysMission selectSysMissionByUserId(Long userId) {
        return sysMissionMapper.selectSysMissionByUserId(userId);
    }

}
