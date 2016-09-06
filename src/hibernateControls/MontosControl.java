package hibernateControls;

import modelo.Monto;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import persistencia.NewHibernateUtil;

public class MontosControl {
    
    public Monto TraeMontoPesos(){
        SessionFactory sf= NewHibernateUtil.getSessionFactory();
        Session session;
        session = sf.openSession();
        Query query= session.createQuery("from Monto where IdMonto=2");                         
        Monto monto=(Monto) query.uniqueResult();
        session.close();        
        return monto;
    }
    
    public Monto TraeMontoUI(){
        SessionFactory sf= NewHibernateUtil.getSessionFactory();
        Session session;
        session = sf.openSession();
        Query query= session.createQuery("from Monto where IdMonto=1");            
        query.setParameter("id", 11);        
        Monto monto=(Monto) query.uniqueResult();
        session.close();        
        return monto;
    }
}
