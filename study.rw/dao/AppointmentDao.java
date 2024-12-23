package dao;

import java.util.List;

import org.hibernate.Session;

import domain.Appointment;

public class AppointmentDao extends GenericDao<Appointment> {
	public List<Appointment> findAllAppointments() {
        Session ss = SessionManager.getSession();
        return ss.createCriteria(Appointment.class).list();
    }

    public Appointment find1Appointment(final String id) {
        Appointment k;
        Session ss = SessionManager.getSession();
        k= (Appointment) ss.get(Appointment.class, id);
        ss.close();
        return k;
    }
    
}
