import org.example.connectivity.HibernateSession;
import org.example.dao.Impl.ShoppingCartHibernateDAO;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShoppingCartHibernateDAOTest {

    private final ShoppingCartHibernateDAO shoppingCartHibernateDAO = new ShoppingCartHibernateDAO();
    private Session session;

    @BeforeEach
    public void getConnection() {
        session = HibernateSession.getSessionFactory().openSession();
        try {
            shoppingCart = session.createQuery("from ShoppingCart order by userId desc limit 1", ShoppingCart.class)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println("add data...\n" + e);
        }
    }

    @Test
    public void getAllProductByUser() {
        Assertions.assertNotNull(shoppingCartHibernateDAO.getAllProductByUser(shoppingCart.getUserId()));
    }

    @Test
    public void deleteAllProductByUser() {
        Long id = shoppingCart.getUserId();
        shoppingCartHibernateDAO.deleteAllProductByUser(id);
        Assertions.assertNotNull(session.createQuery("from ShoppingCart where userId = :userId", ShoppingCart.class).setParameter("userId", shoppingCart.getUserId()));
    }

    @Test
    public void deleteShopCart() {
        shoppingCartHibernateDAO.deleteShopCart(shoppingCart.getUserId(), shoppingCart.getProductId());
        session = HibernateSession.getSessionFactory().openSession();
        Assertions.assertNull(session.find(ShoppingCart.class, shoppingCart));
    }

    @Test
    public void addShopCart() {
        shoppingCart.setUserId(5L);
        shoppingCart.setProductId(5L);
        shoppingCartHibernateDAO.addShopCart(shoppingCart);
        Assertions.assertNotNull(session.find(ShoppingCart.class, shoppingCart));
    }

    @AfterEach
    public void closeConnection() {
        session.close();
    }
}
