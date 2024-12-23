package dao;

import java.util.List;

import org.hibernate.Session;

import domain.SessionFeedback;

public class SessionFeedbackDao extends GenericDao<SessionFeedback> {
	public List<SessionFeedback> findAllSessionFeedbacks() {
        Session ss = SessionManager.getSession();
        return ss.createCriteria(SessionFeedback.class).list();
    }

    public SessionFeedback find1SessionFeedback(final String id) {
        SessionFeedback k;
        Session ss = SessionManager.getSession();
        k= (SessionFeedback) ss.get(SessionFeedback.class, id);
        ss.close();
        return k;
    }
    
}
