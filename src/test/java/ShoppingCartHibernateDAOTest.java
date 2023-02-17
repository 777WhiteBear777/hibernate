import org.example.connectivity.HibernateSession;
import org.example.dao.Impl.ShoppingCartHibernateDAO;
import org.example.model.Product;
import org.example.model.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.junit.jupiter.api.*;

import java.util.List;

public class ShoppingCartHibernateDAOTest {
    private User user;

    private final ShoppingCartHibernateDAO shoppingCartHibernateDAO = new ShoppingCartHibernateDAO();
    private Session session;

    @BeforeEach
    public void getConnection() {
        session = HibernateSession.getSessionFactory().openSession();
        try {
            String hql = "from User u join u.products p  order by u.id desc limit 1";
            user = session.createQuery(hql, User.class).getSingleResult();
        } catch (Exception e) {
            System.out.println("add data...\n" + e);
        }
    }

    @Test
    @Order(1)
    public void getAllProductByUser() {
        Assertions.assertNotNull(shoppingCartHibernateDAO.getAllProductByUser(user.getId()));
    }

    @Test
    @Order(4)
    public void deleteAllProductByUser() {
        Long id = user.getId();
        shoppingCartHibernateDAO.deleteAllProductByUser(id);
        String hql = "from Product p join p.users u  where u.id = :id";
        Assertions.assertNotNull(session.createQuery(hql, Product.class).setParameter("id", id).list());
    }

    @Test
    @Order(3)
    public void deleteShopCart() {
        shoppingCartHibernateDAO.deleteShopCart(user.getId(),6L);
        session = HibernateSession.getSessionFactory().openSession();
        Assertions.assertNotNull(session.find(User.class, user.getId()));
    }

    @Test
    @Order(2)
    public void addShopCart() {
        shoppingCartHibernateDAO.addShopCart(5L,6L);
        Assertions.assertNotNull(session.find(User.class, user.getId()));
    }

    @AfterEach
    public void closeConnection() {
        session.close();
    }
}
