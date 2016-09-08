package hibernateControls;

import control.ControlVentana;
import java.util.List;
import modelo.Cotizaciones;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import persistencia.NewHibernateUtil;

public class CotizacionesControl {
    
    public void guardarCotizacion(Cotizaciones cotizaciones){    
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
            control.ControlVentana cv= new ControlVentana();
            String str="Hubo un error al intentar Guardar: " + ex.getMessage();            
            cv.creaVentanaError(str, "error");
        }
    }
    
    public void guardarCotizaciones(List<Cotizaciones> lista) {       
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
            control.ControlVentana cv= new ControlVentana();
            String str="Hubo un error al intentar Guardar: " + ex.getMessage();            
            cv.creaVentanaError(str, "error");
        }
    }
}
