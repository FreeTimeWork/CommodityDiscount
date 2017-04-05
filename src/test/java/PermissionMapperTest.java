import java.util.ArrayList;
import java.util.List;

import com.mwb.dao.mapper.PermissionMapper;
import com.mwb.dao.model.permission.Permission;

/**
 * Created by Fangchen.chai on 2017/4/1.
 */
public class PermissionMapperTest extends AbstractPersistenceTest{

//    @Autowired
    public PermissionMapper mapper;

//    @Test
    public void test1(){

        List<Integer> permissionIds = new ArrayList<>();
        permissionIds.add(1);
        permissionIds.add(2);

        mapper.batchInsertPositionPermissions(1,permissionIds);
    }

//    @Test
    public void test2(){
        List<Permission> permissions = mapper.selectPermissionsByPositionId(1);
        System.out.println(permissions);

    }

//    @Test
    public void test3(){
        List<Permission> permissions = mapper.selectAllPermissions();
        System.out.println(permissions);

    }
}
