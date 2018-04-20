package kr.ac.jejunu;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ProductDaoTest {
    private ProductDao productDao;
    private DaoFactory daoFactory;

    @Before
    public void setup() {
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(DaoFactory.class);
        productDao=applicationContext.getBean("productDao", ProductDao.class);
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1L;
        String title = "제주감귤";
        Integer price = 15000;

        Product product = productDao.get(id);
        assertEquals(id, product.getId());
        assertEquals(title, product.getTitle());
        assertEquals(price, product.getPrice());
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        Product product=new Product();
        product.setTitle("a");
        product.setPrice(100);
        Long id=productDao.insert(product);

        Product InsertProduct=productDao.get(id);
        assertThat(InsertProduct.getId(),is(id));
        assertThat(InsertProduct.getTitle(),is(product.getTitle()));
        assertThat(InsertProduct.getPrice(),is(product.getPrice()));

    }
}
