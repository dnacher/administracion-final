package hibernateControls;

import modelo.Monto;
import org.hibernate.Query;
import org.hibernate.Session;

public class MontosControl {
    
    public Monto TraeMontoPesos(){
       /* SessionFactory sf= NewHibernateUtil.getSessionFactory();
        Session session;*/
        //Session session;
        Session session = SessionConnection.getConnection().useSession();
        Query query= session.createQuery("from Monto where IdMonto=2");                         
        Monto monto=(Monto) query.uniqueResult();
        session.close();        
        return monto;
    }
    
    public Monto TraeMontoUI(){
        /*SessionFactory sf= NewHibernateUtil.getSessionFactory();
        Session session;*/
        Session session = SessionConnection.getConnection().useSession();
        Query query= session.createQuery("from Monto where IdMonto=1");            
        query.setParameter("id", 11);        
        Monto monto=(Monto) query.uniqueResult();
        session.close();        
        return monto;
    }
}
