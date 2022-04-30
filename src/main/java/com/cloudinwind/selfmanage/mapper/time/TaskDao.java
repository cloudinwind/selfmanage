package com.cloudinwind.selfmanage.mapper.time;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloudinwind.selfmanage.entity.time.Task;
import com.cloudinwind.selfmanage.vo.time.TaskVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Task)表数据库访问层
 *
 * @author makejava
 * @since 2022-03-30 15:22:51
 */
public interface TaskDao extends BaseMapper<Task> {

    // 根据用户id分页查询任务
    List<TaskVo> selectByPage(@Param("page")Integer page, @Param("limit") Integer limit, @Param("taskVo") TaskVo taskVo);

    List<TaskVo> selectUserTask(@Param("taskVo") TaskVo taskVo);

    // 根据用户id查询所有任务
    Integer countTask(@Param("taskVo") TaskVo taskVo);

    // 查询某一天的各标签占比
    List<TaskVo> selectUserTaskGroupByLabelDay(@Param("taskVo") TaskVo taskVo);
    List<TaskVo> selectUserTaskGroupByPriorityDay(@Param("taskVo") TaskVo taskVo);

    Integer selectTaskDuration(@Param("taskVo") TaskVo taskVo);
}

