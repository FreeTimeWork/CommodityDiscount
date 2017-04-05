import java.util.ArrayList;
import java.util.List;

import com.mwb.dao.mapper.BpmMapper;
import com.mwb.dao.model.bpm.Task;
import com.mwb.dao.model.bpm.Variable;

/**
 * Created by Fangchen.chai on 2017/4/5.
 */
public class BpmMapperTest extends AbstractPersistenceTest {

//    @Autowired
    private BpmMapper mapper;

//    @Test
    public void test1() {
        Task task = new Task();
        task.setEmployeeId(1);
        mapper.insertTask (task);
        List<Variable> variables = new ArrayList<>();
        Variable variable = new Variable();
        variable.setName("employeeName");
        variable.setTaskId(task.getId());
        variable.setText("管理员");
        Variable variable2 = new Variable();
        variable2.setName("hehe");
        variable2.setTaskId(task.getId());
        variable2.setText("hha");
        variables.add(variable);
        variables.add(variable2);

        mapper.batchInsertVariable(variables);
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
