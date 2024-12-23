package dao;

import java.util.List;

import org.hibernate.Session;

import domain.Producer;

public class ProducerDao extends GenericDao<Producer> {
	public List<Producer> findAllProducers() {
        Session ss = SessionManager.getSession();
        return ss.createCriteria(Producer.class).list();
    }

    public Producer find1Producer(final String id) {
        Producer k;
        Session ss = SessionManager.getSession();
        k= (Producer) ss.get(Producer.class, id);
        ss.close();
        return k;
    }
    
}
