package com.cloud.transaction.msg.mapper;

import com.cloud.transaction.msg.entity.Msg;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MsgMapper extends Mapper<Msg> {

    public List<Msg> selectOverdueMsg(@Param("status") int status, @Param("time") int time);
}