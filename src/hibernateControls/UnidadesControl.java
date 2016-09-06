package hibernateControls;

import control.ConfiguracionControl;
import control.ControlVentana;
import control.controlXML;
import java.util.List;
import modelo.Unidad;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import persistencia.NewHibernateUtil;

public class UnidadesControl {
    private Object sessionFactory;
    
    public void guardarUnidad(Unidad unidad){    
        try{
            if(!UnidadConstrain(unidad)){
        SessionFactory sf= NewHibernateUtil.getSessionFactory();
        Session session;
        session = sf.openSession();
        Transaction tx= session.beginTransaction(); 
        session.save(unidad); 
        tx.commit();
        session.close();
        ConfiguracionControl.ActualizaId("Unidad");
            }
        }
        catch(Exception ex){
             control.ControlVentana cv= new ControlVentana();
            String str="Hubo un error al intentar Guardar: " + ex.getMessage();            
            cv.creaVentanaError(str, "error");
        }
    }
    
    public void guardarUnidades(List<Unidad> lista, int idUnidadViejo) throws Exception{
        String str="\n\n           Unidad              ";
        str+= String.format("%n%-5s%5s%20s","Id","Insertado","Error");
        str+= String.format("%n%-5s%5s%20s","-----","-----","--------------------");
        SessionFactory sf= NewHibernateUtil.getSessionFactory();
        StatelessSession session = sf.openStatelessSession();      
        Transaction tx = session.beginTransaction();
        int idUnidad=lista.size();
        for (Unidad u: lista) { 
             if(!UnidadConstrain(u)){
                 session.insert(u);
                 str+= String.format("%n%-5s%5s%20s",u.getIdUnidad(),"Si","SIN ERROR");
             } 
             else{
                 str+= String.format("%n%-5s%5s%20s",u.getIdUnidad(),"No","Unidad ya agregada");
                 idUnidad--;
             }
        }        
        tx.commit();       
        session.close();
        idUnidad+=idUnidadViejo;
        ConfiguracionControl.ActualizaIdXId("Unidad", idUnidad);
        controlXML.creaArchivo(str);        
    }
    
   
    
    public boolean permitido(Unidad uni){
        boolean permitido= true;
        SessionFactory sf= NewHibernateUtil.getSessionFactory();
        Session session;
        session = sf.openSession();
        Query query= session.createQuery("from Unidad where Block=:block and Torre=:torre and Puerta=:puerta and Activo=:activo");            
        query.setParameter("block", uni.getBlock());
        query.setParameter("torre", uni.getTorre());
        query.setParameter("puerta", uni.getPuerta());
        query.setParameter("activo", uni.getActivo());
        Unidad unidad=(Unidad)query.uniqueResult();           
        session.close();
        if(unidad!=null){
            permitido=false;
        }
        return permitido;
    }
    
    public List<Unidad> TraeUnidadesXBlockTorre(String block, int torre){
        List<Unidad> lista;
        SessionFactory sf= NewHibernateUtil.getSessionFactory();
        Session session;
        session = sf.openSession();
        Query query= session.createQuery("from Unidad where Block=:block and Torre=:torre");            
        query.setParameter("block", block);
        query.setParameter("torre", torre);        
        lista=query.list();           
        session.close();       
        return lista;
    }
    
    public boolean UnidadConstrain(Unidad unidad){
       boolean Existe=false;
       SessionFactory sf= NewHibernateUtil.getSessionFactory();
        Session session;
        session = sf.openSession();
        Query query= session.createQuery("from Unidad where Block=:block and Torre=:torre and Puerta=:puerta and Nombre=:nombre and Apellido=:apellido and Activo=:activo");            
        query.setParameter("block", unidad.getBlock());
        query.setParameter("torre", unidad.getTorre()); 
        query.setParameter("puerta", unidad.getPuerta()); 
        query.setParameter("nombre", unidad.getNombre()); 
        query.setParameter("apellido", unidad.getApellido()); 
        query.setParameter("activo", unidad.getActivo()); 
        Unidad uni=(Unidad) query.uniqueResult();
        session.close();
        if(uni!=null){
            Existe=true;
        }
        return Existe;
    }
        
    public Unidad TraeUnidadTest(int idUnidad){
        SessionFactory sf= NewHibernateUtil.getSessionFactory();
        Session session;
        session = sf.openSession();
        Query query= session.createQuery("from Unidad where IdUnidad=:id");            
        query.setParameter("id", idUnidad);        
        Unidad uni=(Unidad) query.uniqueResult();
        session.close();        
        return uni;
    }
    
     public List<Unidad> TraeUnidades(){        
        SessionFactory sf= NewHibernateUtil.getSessionFactory();
        Session session;
        session = sf.openSession();
        Query query= session.createQuery("from Unidad");        
        List<Unidad> list = query.list();                 
        session.close();        
        return list;
    }
     
     public Unidad TraeUnidadXId(int id){
         SessionFactory sf= NewHibernateUtil.getSessionFactory();
            Session session;
            session = sf.openSession();
            Transaction tx= session.beginTransaction(); 
            Unidad uni;
            uni=(Unidad)session.get(Unidad.class, id); 
            session.close();
            return uni;
     }
     
     
}
