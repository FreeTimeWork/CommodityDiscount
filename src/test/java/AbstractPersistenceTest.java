import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by MengWeiBo on 2017-03-28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/dao-test.root.xml")
//@ContextConfiguration(locations = "classpath:configs/spring.xml")
public abstract class AbstractPersistenceTest {
}
