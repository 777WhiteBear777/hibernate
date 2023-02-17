import org.example.connectivity.HibernateSession;
import org.example.dao.Impl.UserDetailsHibernateDAO;
import org.example.model.User;
import org.example.model.UserDetails;
import org.hibernate.Session;
import org.junit.jupiter.api.*;

public class UserDetailsHibernateDaoTest {

    private final UserDetailsHibernateDAO userDetailsHibernateDAO = new UserDetailsHibernateDAO();
    private UserDetails userDetails = new UserDetails();
    private Session session;

    @BeforeEach
    public void getProduct() {
        session = HibernateSession.getSessionFactory().openSession();
        userDetails = session.createQuery("from UserDetails order by id desc limit 1", UserDetails.class).getSingleResult();
    }

    @Test
    @Order(2)

    public void testGetAll() {
        Assertions.assertNotNull(userDetailsHibernateDAO.getAll());
    }

    @Test
    @Order(3)
    public void addObj() {
        UserDetails userDetails = new UserDetails();
        User user;
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            user = session.find(User.class, 1L);
        }
        userDetails.setAge((byte) 5);
        userDetails.setGender("Test");
        userDetails.setUserId(user);
        Assertions.assertNotNull(userDetailsHibernateDAO.addObj(userDetails));
    }

    @Test
    @Order(1)
    public void getById() {
        UserDetails user = userDetailsHibernateDAO.getById(1L);
        Assertions.assertEquals(1, user.getId());
    }

    @Test
    @Order(4)
    public void update() {
        userDetails.setAge((byte) 20);
        userDetailsHibernateDAO.update(userDetails);
        Assertions.assertNotNull(session.find(UserDetails.class, userDetails.getId()));
    }

    @Test
    @Order(5)
    public void delete(){
        userDetailsHibernateDAO.delete(userDetails.getId() - 1);
        Assertions.assertNull(session.find(UserDetails.class, userDetails.getId() - 1));
    }


    @AfterEach
    public void closeConnection() {
        session.close();
    }

}
