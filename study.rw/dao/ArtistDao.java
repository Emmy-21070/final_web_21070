package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.Artist;

public class ArtistDao extends GenericDao<Artist> {
	public List<Artist> findAllArtists() {
        Session ss = SessionManager.getSession();
        return ss.createCriteria(Artist.class).list();
    }

    public Artist find1Artist(final String id) {
        Artist k;
        Session ss = SessionManager.getSession();
        k= (Artist) ss.get(Artist.class, id);
        ss.close();
        return k;
    }
    
}
