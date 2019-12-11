package com.lsp.project.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lsp.project.system.mapper.MissionMapper;
import com.lsp.project.system.domain.Mission;
import com.lsp.project.system.service.IMissionService;

/**
 * 任务Service业务层处理
 *
 * @author lsp
 * @date 2019-12-11
 */
@Service
public class MissionServiceImpl implements IMissionService
{
    @Autowired
    private MissionMapper missionMapper;

    /**
     * 查询任务
     *
     * @param id 任务ID
     * @return 任务
     */
    @Override
    public Mission selectMissionById(Long id)
    {
        return missionMapper.selectMissionById(id);
    }

    /**
     * 查询任务列表
     *
     * @param mission 任务
     * @return 任务
     */
    @Override
    public List<Mission> selectMissionList(Mission mission)
    {
        return missionMapper.selectMissionList(mission);
    }

    /**
     * 新增任务
     *
     * @param mission 任务
     * @return 结果
     */
    @Override
    public int insertMission(Mission mission)
    {
        return missionMapper.insertMission(mission);
    }

    /**
     * 修改任务
     *
     * @param mission 任务
     * @return 结果
     */
    @Override
    public int updateMission(Mission mission)
    {
        return missionMapper.updateMission(mission);
    }

    /**
     * 批量删除任务
     *
     * @param ids 需要删除的任务ID
     * @return 结果
     */
    @Override
    public int deleteMissionByIds(Long[] ids)
    {
        return missionMapper.deleteMissionByIds(ids);
    }

    /**
     * 删除任务信息
     *
     * @param id 任务ID
     * @return 结果
     */
    @Override
    public int deleteMissionById(Long id)
    {
        return missionMapper.deleteMissionById(id);
    }

    /**
     * 根据用户id查询自己的任务
     *
     * @param userId
     * @return
     */
    @Override
    public Mission selectMissionByUserId(Long userId) {
        return missionMapper.selectMissionByUserId(userId);
    }
}
