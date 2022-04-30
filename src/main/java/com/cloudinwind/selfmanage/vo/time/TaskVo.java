package com.cloudinwind.selfmanage.vo.time;

import lombok.Data;

import java.util.Date;

@Data
public class TaskVo {
    private Integer taskId;

    private Integer userId;

    private Integer priority;

    private String priorityStr;
    //任务开始时间
    private Date startTime;
    //任务结束时间
    private Date endTime;

    private String taskContent;
    //时间段 如08:00-09:00
    private String timePeriod;
    //标记是否完成,0未完成
    private Integer finished;
    //标签外键
    private Integer labelId;

    private String labelName;

    private String labelColor;

    private Date createTime;

    private Date updateTime;
    //任务说明
    private String explan;
    // 判断是否为父任务
    private Integer parentId;
    // 任务持续时间
    private Integer duration;

    private Integer flag;

    private String childTask;

    private String startDateCondition;

    private String endDateCondition;

    private String nowDate;

    private Integer sumDuration;

    private Integer sumDurationAll;
}
