import org.example.connectivity.HibernateSession;
import org.example.dao.Impl.ProductHibernateDAO;
import org.example.model.Product;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void getAll() {
        Assertions.assertNotNull(productHibernateDAO.getAll());
    }

    @Test
    public void addObj() {
        product.setName("Test");
        product.setCategory("Test");
        product.setPrice(0f);
        product.setId(null);
        product.setId(productHibernateDAO.addObj(product));
        Assertions.assertNotNull(product.getId());
    }

    @Test
    public void getById() {
        Product product1 = productHibernateDAO.getById(product.getId());
        Assertions.assertEquals(product.getId(), product1.getId());
    }

    @Test
    public void update() {
        product.setPrice(10f);
        productHibernateDAO.update(product);
        Assertions.assertNotNull(session.find(Product.class, product.getId()));
    }

    @Test
    public void delete(){
        productHibernateDAO.delete(product.getId() - 1);
        Assertions.assertNull(session.find(Product.class, product.getId() - 1));
    }

    @AfterEach
    public void closeConnection() {
        session.close();
    }
}
