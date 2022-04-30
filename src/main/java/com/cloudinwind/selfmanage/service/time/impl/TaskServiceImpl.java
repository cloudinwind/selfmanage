package com.cloudinwind.selfmanage.service.time.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloudinwind.selfmanage.entity.time.Task;
import com.cloudinwind.selfmanage.mapper.time.TaskDao;
import com.cloudinwind.selfmanage.service.time.TaskService;
import com.cloudinwind.selfmanage.vo.time.TaskVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Task)表服务实现类
 *
 * @author makejava
 * @since 2022-03-30 15:22:51
 */
@Service("taskService")
public class TaskServiceImpl extends ServiceImpl<TaskDao, Task> implements TaskService {

    @Resource
    TaskDao taskDao;

    @Override
    public List<TaskVo> selectByPage(Integer page, Integer limit, TaskVo taskVo) {
        return taskDao.selectByPage(page, limit, taskVo);
    }

    @Override
    public List<TaskVo> selectUserTask(TaskVo taskVo) {
        return taskDao.selectUserTask(taskVo);
    }

    @Override
    public Integer countTask(TaskVo taskVo) {
        return taskDao.countTask(taskVo);
    }

    @Override
    public List<TaskVo> selectUserTaskGroupByLabelDay(TaskVo taskVo) {
        return taskDao.selectUserTaskGroupByLabelDay(taskVo);
    }

    @Override
    public List<TaskVo> selectUserTaskGroupByPriorityDay(TaskVo taskVo) {
        return taskDao.selectUserTaskGroupByPriorityDay(taskVo);
    }

    @Override
    public Integer selectTaskDuration(TaskVo taskVo) {
        return taskDao.selectTaskDuration(taskVo);
    }
}

