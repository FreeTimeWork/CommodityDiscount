package com.mwb.service.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mwb.dao.mapper.BpmMapper;
import com.mwb.dao.model.bpm.Task;
import com.mwb.dao.model.bpm.Variable;
import com.mwb.service.bpm.api.IBpmService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Fangchen.chai on 2017/4/5.
 */
@Service("bpmService")
public class BpmService implements IBpmService{

    @Autowired
    private BpmMapper bpmMapper;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void createTask(Task task) {

        bpmMapper.insertTask(task);
        if (CollectionUtils.isNotEmpty(task.getVariables())) {
            bpmMapper.batchInsertOrUpdateVariable(task.getVariables());
        }

    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void modifyTask(Task task) {

        bpmMapper.updateTask(task);
        if (CollectionUtils.isNotEmpty(task.getVariables())) {
            bpmMapper.batchInsertOrUpdateVariable(task.getVariables());
        }
    }

    @Override
    public Map<String, String> getBpmVariable(Integer taskId) {

        List<Variable> variables = bpmMapper.selectVariableByTaskId(taskId);
        Map<String, String> variableMap = new HashMap<>();

        for (Variable var : variables) {
            variableMap.put(var.getName(), var.getText());
        }
        return variableMap;
    }
}
