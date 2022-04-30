package com.cloudinwind.selfmanage.controller.time;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloudinwind.selfmanage.constant.PageConstant;
import com.cloudinwind.selfmanage.dto.UserDTO;
import com.cloudinwind.selfmanage.entity.time.*;
import com.cloudinwind.selfmanage.service.time.TaskService;
import com.cloudinwind.selfmanage.service.time.UserTaskService;
import com.cloudinwind.selfmanage.vo.time.TaskVo;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (Task)表控制层
 */
@RestController
@RequestMapping("task")
public class TaskController extends BaseController{

    @Resource
    TaskService taskService;

    @Resource
    UserTaskService userTaskService;


    // 查询任务
    @RequestMapping("selectByPage")
    public ReturnBean selectByPage(HttpServletRequest request,
                                   Integer page, Integer limit, TaskVo taskVo)
    {
        if (page == null){
            page = PageConstant.page;
            limit=PageConstant.limit;
        }

        System.out.println("selectByCondition:" + taskVo.toString());
        if (Strings.isEmpty(taskVo.getStartDateCondition()) && Strings.isEmpty(taskVo.getEndDateCondition()))
        {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            taskVo.setNowDate(sdf.format(date));
        }
        System.out.println("selectByCondition:" + taskVo.toString());
        if (taskVo.getLabelId() !=null && taskVo.getLabelId()== -1) taskVo.setLabelId(null);


        // 获取user
        UserDTO loginuser = (UserDTO) request.getAttribute("loginUser");
        System.out.println("得到user:");
        System.out.println(loginuser.toString());
        taskVo.setUserId(loginuser.getId().intValue());
        // 按照用户id分页查找
        List<TaskVo> taskVoList = taskService.selectByPage(page, limit, taskVo);
        Long countTask = taskService.countTask(taskVo).longValue();
        return success(taskVoList, countTask);
    }

    @PostMapping("saveTask")
    public ReturnBean save(@RequestBody TaskVo taskVo){
        System.out.println("taskVo post:" + taskVo.toString());
        Task task = new Task();
        task.setChildTask(taskVo.getChildTask());
        task.setCreateTime(new Date());
        task.setExplan(taskVo.getExplan());
        task.setDuration(taskVo.getDuration());
        task.setLabelId(taskVo.getLabelId());
        task.setPriority(taskVo.getPriority());
        task.setTaskContent(taskVo.getTaskContent());
        task.setTimePeriod(taskVo.getTimePeriod());
        task.setUpdateTime(new Date());

        taskService.save(task);

        UserTask userTask = new UserTask();
        userTask.setTaskId(task.getTaskId());
        userTask.setUserId(taskVo.getUserId());
        userTaskService.save(userTask);

        return success(taskVo);

    }

    @PutMapping("updateTask")
    public ReturnBean update(@RequestBody Task task) {
        System.out.println("修改:" + task.toString());
        //设置修改时间
        task.setUpdateTime(new Date());
        if (task.getFinished() == null) task.setFinished(0);
        else task.setFinished(1);
        boolean update = taskService.updateById(task);
        if (update) return super.success(task);
        else return super.fail(task);
    }

    @RequestMapping("deleteByIdList")
    public ReturnBean delete(@RequestParam(value = "idList[]", required = false) List<Long> idList) {
        System.out.println("idList:"+idList.size());

        for (Long id : idList)
        {
            System.out.println(id);
        }
        boolean delete = taskService.removeByIds(idList);
        if (delete) return success(idList);
        else return fail(idList);
    }


    // 日分析 标签占比
    @RequestMapping("selectUserTaskGroupByLabelDay")
    public ReturnBean selectUserTaskGroupByLabelDay(@RequestParam(required = false, value = "nowDate")String nowDate,
            HttpServletRequest request){

        TaskVo taskVo = new TaskVo();

        // 设置用户id
        UserDTO loginuser = (UserDTO) request.getAttribute("loginUser");
        taskVo.setUserId(loginuser.getId().intValue());

        // 设置查询时间
//        if (nowDate==null) {
//            Date date = new Date();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            nowDate = sdf.format(date);
//        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        nowDate = sdf.format(date);

        System.out.println("nowDate Task:"+nowDate);
        taskVo.setNowDate(nowDate);

        List<TaskVo> taskVos = taskService.selectUserTaskGroupByLabelDay(taskVo);

        return success(taskVos, "success");
    }

    // 日分析 优先级占比
    @RequestMapping("selectUserTaskGroupByPriorityDay")
    public ReturnBean selectUserTaskGroupByPriorityDay(@RequestParam(required = false, value = "nowDate")String nowDate,
                                                       HttpServletRequest request){

        TaskVo taskVo = new TaskVo();

        // 设置用户id
        UserDTO loginuser = (UserDTO) request.getAttribute("loginUser");
        taskVo.setUserId(loginuser.getId().intValue());

        // 设置查询时间
//        if (nowDate==null) {
//            Date date = new Date();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            nowDate = sdf.format(date);
//        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        nowDate = sdf.format(date);

        taskVo.setNowDate(nowDate);

        List<TaskVo> taskVos = taskService.selectUserTaskGroupByPriorityDay(taskVo);

        return success(taskVos, "success");
    }
}