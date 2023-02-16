import org.example.connectivity.HibernateSession;
import org.example.dao.Impl.OrderHibernateDAO;
import org.example.model.Order;
import org.example.model.User;
import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class OrderHibernateDAOTest {

    OrderHibernateDAO orderHibernateDAO = new OrderHibernateDAO();

    @Test
    public void getAllOrder() {
        Assertions.assertNotNull(orderHibernateDAO.getAllOrder());
    }

    @Test
    public void getAllOrderByUser() {
        Assertions.assertNotNull(orderHibernateDAO.getAllOrderByUser(1L));
    }

    @Test
    public void addOrder() throws SQLException {
        Order order = new Order();
        User user;
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            user = session.find(User.class, 1L);
        }
        order.setProduct("TEST, TEST, TEST");
        order.setUserId(user);
        order.setTotalPrice(9999F);
        Assertions.assertNotNull(orderHibernateDAO.addOrder(order));
    }

}
