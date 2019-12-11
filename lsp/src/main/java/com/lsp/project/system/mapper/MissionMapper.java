package com.lsp.project.system.mapper;

import com.lsp.project.system.domain.Mission;
import java.util.List;

/**
 * 任务Mapper接口
 *
 * @author lsp
 * @date 2019-12-11
 */
public interface MissionMapper
{
    /**
     * 查询任务
     *
     * @param id 任务ID
     * @return 任务
     */
    public Mission selectMissionById(Long id);

    /**
     * 查询任务列表
     *
     * @param mission 任务
     * @return 任务集合
     */
    public List<Mission> selectMissionList(Mission mission);

    /**
     * 新增任务
     *
     * @param mission 任务
     * @return 结果
     */
    public int insertMission(Mission mission);

    /**
     * 修改任务
     *
     * @param mission 任务
     * @return 结果
     */
    public int updateMission(Mission mission);

    /**
     * 删除任务
     *
     * @param id 任务ID
     * @return 结果
     */
    public int deleteMissionById(Long id);

    /**
     * 批量删除任务
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMissionByIds(Long[] ids);

    /**
     * 根据用户id查询自己的任务
     *
     * @param userId
     * @return
     */
    public Mission selectMissionByUserId(Long userId);
}
