package com.mwb.service.bpm.api;

import java.util.Map;

import com.mwb.dao.model.bpm.Task;

/**
 * Created by Fangchen.chai on 2017/4/5.
 */
public interface IBpmService {

    public void createTask(Task task);

    public void modifyTask(Task task);

    public Map<String, String> getBpmVariable(Integer taskId);

}
