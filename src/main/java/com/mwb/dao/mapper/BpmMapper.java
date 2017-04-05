package com.mwb.dao.mapper;

import java.util.List;

import com.mwb.dao.model.bpm.Task;
import com.mwb.dao.model.bpm.Variable;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Fangchen.chai on 2017/4/5.
 */
public interface BpmMapper {

    public void insertTask(Task task);

    public void updateTask(Task task);

    public void batchInsertOrUpdateVariable(@Param("variables") List<Variable> variables);

    public List<Variable> selectVariableByTaskId(@Param("taskId") Integer taskId);

}
