import com.alibaba.fastjson.JSON;
import com.mwb.controller.product.api.CreateProductRequest;
import com.mwb.dao.filter.FinanceFilter;
import com.mwb.dao.filter.ProductFilter;
import com.mwb.dao.mapper.FinanceMapper;
import com.mwb.dao.mapper.ProductMapper;
import com.mwb.dao.model.comm.Bool;
import com.mwb.dao.model.employee.Employee;
import com.mwb.dao.model.finance.Finance;
import com.mwb.dao.model.product.*;
import com.mwb.dao.model.product.voucher.ProductVoucher;
import com.mwb.dao.model.product.voucher.VoucherPicture;
import com.mwb.util.DateTimeUtility;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by MengWeiBo on 2017-04-05
 */
public class ProductMapperTest extends AbstractPersistenceTest {

    //    @Autowired
    public ProductMapper mapper;

//        @Autowired
    public FinanceMapper mapper1;

    @Test
    public void request() throws Exception {
        System.out.println(DateTimeUtility.parseYYYYMMDDHHMMSS("2014-11-11 00:00:00"));
        CreateProductRequest request = newInstance(CreateProductRequest.class);
        request.setActivityId(1);
        request.setActivityTime("2017-11-11");
        request.setProductTypeId(1);
        request.setCouponEndTime("2017-11-11");
        request.setCouponBeginTime("2017-11-11");
        request.setHireTypeId(1);
        request.setStoreTypeId(1);
        printJSON(request);
    }
//    @Test
    public void insertFiance() throws Exception {
        Finance finance = newInstance(Finance.class);
        Employee employee = new Employee();
        employee.setId(1);
        finance.setEmployee(employee);

        FinanceFilter financeFilter = new FinanceFilter();

        List<Finance> finances = mapper1.selectFinanceByFilter(financeFilter);
       int a=   mapper1.selectCurrentFinanceRank(3);
        printJSON(finances);
    }

    //    @Test
    public void insertStore() throws Exception {
        Store store = newInstance(Store.class);
        store.setType(StoreType.TMALL);
        mapper.insertStore(store);
        printJSON(store);
    }

    //    @Test
    public void selectProduct() throws Exception {

        Product product = mapper.selectProductById(1);
        mapper.updateProduct(product);
//        List<Product> products = mapper.selectProductByStatus(null, ProductStatus.AUDIT_RUN);

        ProductFilter productFilter = newInstance(ProductFilter.class);

        List<ProductVoucher> products = mapper.selectProductVoucherByFilter(productFilter);
        int a = mapper.countProductByFilter(productFilter);
        printJSON(products);
    }

    //    @Test
    public void insertProductVoucher() throws Exception {
        ProductVoucher productVoucher = newInstance(ProductVoucher.class);
        Product product = new Product();
        product.setId(1);
        productVoucher.setProduct(product);
        mapper.insertProductVoucher(productVoucher);

        productVoucher.setId(1);
        VoucherPicture picture = newInstance(VoucherPicture.class);
        picture.setVoucher(productVoucher);
        mapper.insertVoucherPicture(picture);
        printJSON(productVoucher);
    }

    //    @Test
    public void insertProduct() throws Exception {
        Product product = newInstance(Product.class);
        product.setStatus(ProductStatus.AUDIT_RUN);
        product.setHireType(HireType.DIRECTIONAL);
        product.setProductType(ProductType.APPLIANCE);
        product.setActivity(Activity.BARGAIN);
        Store store = newInstance(Store.class);
        store.setId(1);
        Employee employee = newInstance(Employee.class);
        employee.setId(1);
        product.setStore(store);
        product.setEmployee(employee);

        mapper.insertProduct(product);

        product.setId(1);
        ProductPicture productPicture = newInstance(ProductPicture.class);
        productPicture.setProduct(product);
        mapper.insertProductPicture(productPicture);

        printJSON(product);
    }


    public static void printJSON(Object obj) {
        System.out.println(JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd HH:mm:ss.SSS"));
    }

    public <T> List<T> newList(Class<T> cla, int size)
            throws InstantiationException, IllegalAccessException, ParseException {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(newInstance(cla));
        }
        return list;
    }

    public <T> T newInstance(Class<T> cla) throws InstantiationException, IllegalAccessException, ParseException {
        Date start = DateTimeUtility.parseYYYYMMDD("2017-1-1");
        Date end = DateTimeUtility.parseYYYYMMDD("2017-1-31");
        T o = cla.newInstance();
        Class<?> clazz = cla;
        while (clazz != Object.class) {
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);

                if (field.getName().equals("serialVersionUID")) {
                    continue;
                }

                if (field.getType() == Integer.class) {
                    field.set(o, Integer.valueOf(new Random().nextInt(10)));
                } else if (field.getType() == int.class) {
                    field.set(o, new Random().nextInt(5558));
                } else if (field.getType() == Long.class) {
                    field.set(o, Long.valueOf(new Random().nextInt(1000)));
                } else if (field.getType() == long.class) {
                    field.set(o, new Random().nextInt(1000));
                } else if (field.getType() == Date.class) {
                    long result = start.getTime() + (long) ((end.getTime() - start.getTime()) * Math.random());
                    field.set(o, new Date(result));
                } else if (field.getType() == Bool.class) {
                    if (Math.random() >= 0.5) {
                        field.set(o, Bool.Y);
                    } else {
                        field.set(o, Bool.N);
                    }
                } else if (field.getType() == Boolean.class || field.getType() == boolean.class) {
                    field.set(o, new Random().nextBoolean());
                } else if (field.getType() == BigDecimal.class) {
                    field.set(o, BigDecimal.valueOf(new Random().nextInt(100)));
                } else if (field.getType() == String.class) {
                    String str = field.getName();
                    str = str.length() >= 5 ? str.substring(0, 5) : str;
                    field.set(o, str + new Random().nextInt(10));
                } else if (Enum.class.isAssignableFrom(field.getType())) {
                    System.out.println(field.getType());
                }
            }
            clazz = clazz.getSuperclass();
        }

        return o;
    }
}
