import java.util.ArrayList;
import java.util.List;

import com.mwb.dao.mapper.BpmMapper;
import com.mwb.dao.model.bpm.Task;
import com.mwb.dao.model.bpm.Variable;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Fangchen.chai on 2017/4/5.
 */
public class BpmMapperTest extends AbstractPersistenceTest {

//    @Autowired
    private BpmMapper mapper;

//    @Test
    public void test1() {
        Task task = new Task();
//        task.setEmployeeId(1);
        mapper.insertTask (task);
        List<Variable> variables = new ArrayList<>();
        Variable variable = new Variable();
//        variable.setTaskId(3);
        variable.setTaskId(task.getId());
        variable.setName("employeeName");
        variable.setText("管理员11");
        Variable variable2 = new Variable();
//        variable2.setTaskId(3);
        variable2.setTaskId(task.getId());
        variable2.setName("hehe");
        variable2.setText("hha11");
        variables.add(variable);
        variables.add(variable2);

        mapper.batchInsertOrUpdateVariable(variables);
    }

//    @Test
    public void test2() {
        List<Variable> variables = mapper.selectVariableByTaskId(1);
        System.out.println(variables);
    }

//    @Test
    public void Test3() {
        Task task = new Task();
        task.setId(1);
        task.setEmployeeId(2);
        mapper.updateTask(task);
    }
}
