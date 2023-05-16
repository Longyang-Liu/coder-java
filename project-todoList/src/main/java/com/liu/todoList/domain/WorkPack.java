package com.liu.todoList.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("todo_pack")
public class WorkPack {

    @TableField("id")
    String id;

    @TableField("pid")
    String pid;

    @TableField("code")
    String code;

    @TableField("name")
    String name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private Date createTime;
}