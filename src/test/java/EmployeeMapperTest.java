import java.util.Date;

import com.mwb.dao.filter.EmployeeFilter;
import com.mwb.dao.mapper.EmployeeMapper;
import com.mwb.dao.model.employee.Employee;
import com.mwb.dao.model.employee.EmployeeStatus;
import com.mwb.dao.model.employee.Gender;
import com.mwb.dao.model.position.Position;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Fangchen.chai on 2017/3/30.
 */
public class EmployeeMapperTest extends AbstractPersistenceTest {

    @Autowired
    private EmployeeMapper employeeMapper;

//    @Test
    public void employeeByPassword() {
        System.out.println(employeeMapper.selectEmployeeByMobileAndPassword("123456789","123456789"));
    }

//    @Test
    public void employeeByFilter() {
        EmployeeFilter filter = new EmployeeFilter();
        filter.setPositionId(1);
        System.out.println(employeeMapper.selectEmployeeByFilter(filter));;

    }

//    @Test
    public  void  test(){
        Employee employee = employeeMapper.selectEmployeeById(1);
        System.out.println(employee);
    }

//    @Test
    public  void  insertEmployeeTest(){

        Employee employee = new Employee();
        employee.setCreateTime(new Date());
        employee.setFullName("chaifangchen");
        employee.setGender(Gender.F);
        employee.setMobile("15104569789123");
        employee.setPassword("123456789");
        Position position = new Position();
        position.setId(1);
//        employee.setGroup(new Group(1));
        employee.setPosition(position);
        employee.setStatus(EmployeeStatus.IN_POSITION);
        employeeMapper.insertEmployee(employee);
    }

}
