package hibernateControls;

import control.ConfiguracionControl;
import java.util.List;
import modelo.Gastoscomunes;
import modelo.Monto;
import modelo.Unidad;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import persistencia.NewHibernateUtil;

public class GastosComunesControl {
    
    public void guardarGastosComunes(Gastoscomunes gastosComunes)throws Exception{    
        try{
        SessionFactory sf= NewHibernateUtil.getSessionFactory();
        Session session;
        session = sf.openSession();
        Transaction tx= session.beginTransaction(); 
        session.save(gastosComunes); 
        tx.commit();
        session.flush();
        session.close();        
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
    }
    
    public void guardarListaGastosComunes(List<Gastoscomunes> lista)throws Exception{
        try{
        SessionFactory sf= NewHibernateUtil.getSessionFactory();
        StatelessSession session = sf.openStatelessSession();
        Transaction tx = session.beginTransaction();
        for (Gastoscomunes gastosComunes: lista) {         
         session.insert(gastosComunes);
        }
        tx.commit();        
        session.close();    
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
    }
    
    public void CargaGastosComunesXUnidad(Unidad unidad){
        List<String> li=ConfiguracionControl.mesesLista(unidad);
        int i=0;
        int periodo=0;
        MontosControl mc= new MontosControl();        
        Monto m= mc.TraeMontoPesos();        
            for(String s: li){       
                periodo=Integer.parseInt(s);
                Gastoscomunes gc= new Gastoscomunes(i,m , unidad, 2000, true, 1, null, s,periodo);
                try{
                    guardarGastosComunes(gc);
                }
                catch(Exception ex){
                    System.out.println(ex.getMessage());
                }
                i++;
            }   
    }
    
    public void CargasGastosComunesXListaUnidades(List<Unidad> lista){
        for(Unidad uni: lista){
            CargaGastosComunesXUnidad(uni);
        }   
        System.out.println("termino");
    }
}
