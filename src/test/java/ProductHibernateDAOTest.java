import org.example.connectivity.HibernateSession;
import org.example.dao.Impl.ProductHibernateDAO;
import org.example.model.Product;
import org.hibernate.Session;
import org.junit.jupiter.api.*;

import java.util.HashSet;

public class ProductHibernateDAOTest {
    private ProductHibernateDAO productHibernateDAO = new ProductHibernateDAO();
    private Product product = new Product();
    private Session session;

    @BeforeEach
    public void getProduct() {
        session = HibernateSession.getSessionFactory().openSession();
        product = session.createQuery("from Product order by id desc limit 1", Product.class).getSingleResult();
    }

    @Test
    @Order(1)
    public void getAll() {
        Assertions.assertNotNull(productHibernateDAO.getAll());
    }

    @Test
    @Order(3)
    public void addObj() {
        product.setName("Test");
        product.setCategory("Test");
        product.setPrice(0f);
        product.setId(null);
        product.setUsers(new HashSet<>());
        product.setId(productHibernateDAO.addObj(product));
        Assertions.assertNotNull(product.getId());
    }

    @Test
    @Order(2)
    public void getById() {
        Product product1 = productHibernateDAO.getById(product.getId());
        Assertions.assertEquals(product.getId(), product1.getId());
    }

    @Test
    @Order(4)
    public void update() {
        product.setPrice(10f);
        productHibernateDAO.update(product);
        Assertions.assertNotNull(session.find(Product.class, product.getId()));
    }

    @Test
    @Order(5)
    public void delete(){
        productHibernateDAO.delete(product.getId() - 1);
        Assertions.assertNull(session.find(Product.class, product.getId() - 1));
    }

    @AfterEach
    public void closeConnection() {
        session.close();
    }
}
