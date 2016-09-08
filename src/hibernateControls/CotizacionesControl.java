package hibernateControls;

import java.util.ArrayList;
import java.util.List;
import modelo.Cotizaciones;
import modelo.Unidad;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import persistencia.NewHibernateUtil;

public class CotizacionesControl {
    
    public void guardarCotizacion(Cotizaciones cotizaciones)throws Exception{    
        try{          
        SessionFactory sf= NewHibernateUtil.getSessionFactory();
        Session session;
        session = sf.openSession();
        Transaction tx= session.beginTransaction(); 
        session.save(cotizaciones); 
        tx.commit();
        session.close();          
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
    }
    
    public void guardarCotizaciones(List<Cotizaciones> lista)throws Exception {       
        try{
            SessionFactory sf= NewHibernateUtil.getSessionFactory();
            StatelessSession session = sf.openStatelessSession();      
            Transaction tx = session.beginTransaction();  
            for (Cotizaciones c: lista) {              
                 session.insert(c);             
            }   
            tx.commit();       
            session.close();        
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
    }
    
    public List<Cotizaciones> TraePeriodosSinPagarUsuario(Unidad unidad){
        List<Cotizaciones> lista;
        SessionFactory sf= NewHibernateUtil.getSessionFactory();
        Session session;
        session = sf.openSession();
        Query query= session.createQuery("SELECT cotizaciones FROM Cotizaciones cotizaciones "
                                       + "WHERE cotizaciones.periodo "
                                       + "NOT IN (SELECT gastoscomunes.periodo "
                                               + "FROM Gastoscomunes gastoscomunes "
                                               + "WHERE gastoscomunes.unidad=:unidad)"); 
        query.setParameter("unidad", unidad);
        lista=query.list();           
        session.close();       
        return lista;
    }
}
