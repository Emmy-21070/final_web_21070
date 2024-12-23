package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.User;

public class UserDao extends GenericDao<User> {
	public List<User> findAllUsers() {
        Session ss = SessionManager.getSession();
        return ss.createCriteria(User.class).list();
    }

    public User find1User(final String id) {
        User k;
        Session ss = SessionManager.getSession();
        k= (User) ss.get(User.class, id);
        ss.close();
        return k;
    }
    
    public User getUserByUsername(final String username) {
        Session ss = SessionManager.getSession();
        Transaction t = ss.beginTransaction();
        String qry = "FROM User where user_name='"+username+"'";
        Query query = ss.createQuery(qry);
        User results = (User) query.uniqueResult();
        ss.close();
        return results;
    }
    
    public List<User> getUserByRole(final String rol) {
        Session ss = SessionManager.getSession();
        Transaction t = ss.beginTransaction();
        String qry = "FROM User where role='"+rol+"'";
        Query query = ss.createQuery(qry);
        List<User> results = (List<User>) query.list();
        ss.close();
        return results;
    }
}
