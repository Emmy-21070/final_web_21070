package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.Schedule;

public class ScheduleDao extends GenericDao<Schedule> {
	public List<Schedule> findAllSchedules() {
        Session ss = SessionManager.getSession();
        return ss.createCriteria(Schedule.class).list();
    }

    public Schedule find1Schedule(final String id) {
        Schedule k;
        Session ss = SessionManager.getSession();
        k= (Schedule) ss.get(Schedule.class, id);
        ss.close();
        return k;
    }
    
}
