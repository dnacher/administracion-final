package hibernateControls;

import control.ConfiguracionControl;
import control.controlXML;
import java.util.ArrayList;
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
    
    public void guardarUnidad(Unidad unidad)throws Exception{ 
        SessionFactory sf= NewHibernateUtil.getSessionFactory();
        Session session;
        session = sf.openSession();
        try{
            if(!UnidadConstrain(unidad)){
                
                Transaction tx= session.beginTransaction(); 
                session.save(unidad); 
                tx.commit();
                
                ConfiguracionControl.ActualizaId("Unidad");
            }
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
    }
    
    public void guardarUnidades(List<Unidad> lista, int idUnidadViejo) throws Exception{
        SessionFactory sf= NewHibernateUtil.getSessionFactory();
        StatelessSession session = sf.openStatelessSession(); 
        try{
        String str="\n\n           Unidad              ";
        str+= String.format("%n%-5s%5s%20s","Id","Insertado","Error");
        str+= String.format("%n%-5s%5s%20s","-----","-----","--------------------");             
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
        
        idUnidad+=idUnidadViejo;
        ConfiguracionControl.ActualizaIdXId("Unidad", idUnidad);
        controlXML.creaArchivo(str);  
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        finally{
            session.close();
        }
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
    
    public List<Unidad> TraeUnidadesXBlockTorreNoPago(String block, int torre){
        List<Unidad> lista;
        SessionFactory sf= NewHibernateUtil.getSessionFactory();
        Session session;
        session = sf.openSession();
        Query query= session.createQuery("SELECT unidad FROM Unidad unidad "
                                       + "WHERE Block=:block "
                                       + "AND Torre=:torre "
                                       + "AND unidad.idUnidad NOT IN (SELECT gastoscomunes.unidad "
                                                               + "FROM Gastoscomunes gastoscomunes "
                                                               + "WHERE gastoscomunes.periodo=:periodo)");            
        query.setParameter("block", block);
        query.setParameter("torre", torre);
        query.setParameter("periodo", "2016-09");
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
        
    public Unidad TraeUnidadxID(int idUnidad){
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
     
    public List<Unidad> TraeUnidadesGastosComunesNoPago(){        
        List<Unidad> list= new ArrayList<>();
        try{
        SessionFactory sf= NewHibernateUtil.getSessionFactory();
        Session session;
        session = sf.openSession();
        
        Query query= session.createQuery("SELECT e1 FROM Unidad e1 "
                                       + "WHERE e1.idUnidad NOT IN ("
                                                                  + "SELECT e2.unidad "
                                                                  + "FROM Gastoscomunes e2 "
                                                                  + "WHERE e2.periodo=:periodo)");
        query.setParameter("periodo", "2016-09");
        list= query.list();
        session.close();               
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
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
