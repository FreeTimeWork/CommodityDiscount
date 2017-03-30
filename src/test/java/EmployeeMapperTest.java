import com.mwb.dao.mapper.EmployeeMapper;
import com.mwb.dao.model.employee.Employee;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Fangchen.chai on 2017/3/30.
 */
public class EmployeeMapperTest extends AbstractPersistenceTest {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public  void  test(){
        Employee employee = employeeMapper.selectEmployeeById("1");
        System.out.println(employee);
    }


}
