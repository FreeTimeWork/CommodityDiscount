import com.mwb.dao.mapper.PositionMapper;
import com.mwb.dao.model.position.Position;

/**
 * Created by Fangchen.chai on 2017/3/31.
 */
public class PositionMapperTest extends AbstractPersistenceTest {

//    @Autowired
    private PositionMapper mapper;

//    @Test1
    public void Test1(){
        Position position = new Position();
        position.setName("管理员");
        mapper.insertPosition(position);
    }

//    @Test
    public void Test2(){
        Position position = mapper.selectPositionById(2);
        System.out.println(position);
    }
}
