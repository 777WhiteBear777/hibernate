import org.example.connectivity.HibernateSession;
import org.example.dao.Impl.UserHibernateDAO;
import org.example.model.User;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserHibernateDAOTest {

    UserHibernateDAO userHibernateDAO = new UserHibernateDAO();
    private User user = new User();
    private Session session;

    @BeforeEach
    public void getProduct() {
        session = HibernateSession.getSessionFactory().openSession();
        user = session.createQuery("from User order by id desc limit 1", User.class).getSingleResult();
    }

    @Test
    public void testGetAll() {
        Assertions.assertNotNull(userHibernateDAO.getAll());
    }

    @Test
    public void addObj() {
        User user = new User();
        user.setFirstname("Test");
        user.setLastname("Test");
        Assertions.assertNotNull(userHibernateDAO.addObj(user));
    }

    @Test
    public void getById() {
        User user = userHibernateDAO.getById(1L);
        Assertions.assertEquals(1, user.getId());
    }

    @Test
    public void update() {
        user.setLastname("TEST_TEST");
        userHibernateDAO.update(user);
        Assertions.assertNotNull(session.find(User.class, user.getId()));
    }

    @Test
    public void delete(){
        userHibernateDAO.delete(user.getId() - 1);
        Assertions.assertNull(session.find(User.class, user.getId() - 1));
    }

    @AfterEach
    public void closeConnection() {
        session.close();
    }
}
