package hibernateControls;

import control.ConfiguracionControl;
import control.ControlUtil;
import control.controlXML;
import static java.lang.Math.toIntExact;
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
        /*SessionFactory sf= NewHibernateUtil.getSessionFactory();
        Session session;*/
        Session session = SessionConnection.getConnection().useSession();
        try{
            if(!UnidadConstrain(unidad, session)){
                
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
            if(session.isOpen()){
            session.close();
            }
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
             if(!UnidadesConstrain(u,session)){
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
        /*SessionFactory sf= NewHibernateUtil.getSessionFactory();
        Session session;*/
        Session session = SessionConnection.getConnection().useSession();
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
        ControlUtil cu=new ControlUtil();
        List<Unidad> lista=new ArrayList<>();
       /* SessionFactory sf= NewHibernateUtil.getSessionFactory();*/
        //Session session;
        Session session = SessionConnection.getConnection().useSession();
        try{
        Query query= session.createQuery("SELECT unidad FROM Unidad unidad "
                                       + "WHERE Block=:block "
                                       + "AND Torre=:torre "
                                       + "AND unidad.idUnidad NOT IN (SELECT gastoscomunes.unidad "
                                                               + "FROM Gastoscomunes gastoscomunes "
                                                               + "WHERE gastoscomunes.periodo=:periodo "
                                                               + "AND gastoscomunes.estado=:est)");            
        query.setParameter("block", block);
        query.setParameter("torre", torre);
        query.setParameter("periodo", cu.devuelvePeriodoActual());
        //estado 2 pago al estar en el "not in" trae los que estan pagos
        query.setParameter("est", 2);
        lista=query.list();           
        session.close();       
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return lista;
    }
    
    public boolean UnidadConstrain(Unidad unidad, Session session){
       boolean Existe=false;
        //SessionFactory sf= NewHibernateUtil.getSessionFactory();
        //Session session;
        //session = SessionConnection.getConnection().useSession();
        Query query= session.createQuery("from Unidad where Block=:block and Torre=:torre and Puerta=:puerta and Nombre=:nombre and Apellido=:apellido and Activo=:activo");            
        query.setParameter("block", unidad.getBlock());
        query.setParameter("torre", unidad.getTorre()); 
        query.setParameter("puerta", unidad.getPuerta()); 
        query.setParameter("nombre", unidad.getNombre()); 
        query.setParameter("apellido", unidad.getApellido()); 
        query.setParameter("activo", unidad.getActivo()); 
        Unidad uni=(Unidad) query.uniqueResult();
        //session.close();
        if(uni!=null){
            Existe=true;
        }
        return Existe;
    }
     
    public boolean UnidadesConstrain(Unidad unidad, StatelessSession session){
       boolean Existe=false;
        //SessionFactory sf= NewHibernateUtil.getSessionFactory();
        //Session session;
        //session = sf.getCurrentSession();
        Query query= session.createQuery("from Unidad where Block=:block and Torre=:torre and Puerta=:puerta and Nombre=:nombre and Apellido=:apellido and Activo=:activo");            
        query.setParameter("block", unidad.getBlock());
        query.setParameter("torre", unidad.getTorre()); 
        query.setParameter("puerta", unidad.getPuerta()); 
        query.setParameter("nombre", unidad.getNombre()); 
        query.setParameter("apellido", unidad.getApellido()); 
        query.setParameter("activo", unidad.getActivo()); 
        Unidad uni=(Unidad) query.uniqueResult();
        //session.close();
        if(uni!=null){
            Existe=true;
        }
        return Existe;
    }
    
    public Unidad TraeUnidadxID(int idUnidad){
        //SessionFactory sf= NewHibernateUtil.getSessionFactory();
        //Session session;
        Session session = SessionConnection.getConnection().useSession();
        Query query= session.createQuery("from Unidad where IdUnidad=:id");            
        query.setParameter("id", idUnidad);        
        Unidad uni=(Unidad) query.uniqueResult();
        session.close();        
        return uni;
    }
    
     public List<Unidad> TraeUnidades(){        
        SessionFactory sf= NewHibernateUtil.getSessionFactory();
        //Session session;
        Session session = SessionConnection.getConnection().useSession();
        Query query= session.createQuery("from Unidad");        
        List<Unidad> list = query.list();                 
        session.close();        
        return list;
    }
     
    public List<Unidad> TraeUnidadesGastosComunesNoPago(){        
        List<Unidad> list= new ArrayList<>();
        /*SessionFactory sf= NewHibernateUtil.getSessionFactory();
        Session session;*/
        Session session = SessionConnection.getConnection().useSession();
        try{                
        Query query= session.createQuery("SELECT unidad FROM Unidad unidad "
                                       + "WHERE unidad.idUnidad NOT IN ("
                                                                  + "SELECT gastoscomunes.unidad "
                                                                  + "FROM Gastoscomunes gastoscomunes "
                                                                  + "WHERE gastoscomunes.periodo=:periodo "
                                                                  + "AND gastoscomunes.estado=:est)");
        ControlUtil cu= new ControlUtil();
        query.setParameter("est", 2);
        query.setParameter("periodo", cu.devuelvePeriodoActual());
        list= query.list();
                       
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        finally{
           
            session.close();
        }
        return list;
    }
     
     public Unidad TraeUnidadXId(int id){
         /*SessionFactory sf= NewHibernateUtil.getSessionFactory();
         Session session;*/
         Session session = SessionConnection.getConnection().useSession();
         //session.beginTransaction(); 
         Unidad uni;
         uni=(Unidad)session.get(Unidad.class, id); 
         session.close();
         return uni;
     }   
     
     public int totalUnidades(String block, int torre){
        Session session = SessionConnection.getConnection().useSession();
        if(block.equals("") && torre==0){        
            Query query = session.createQuery("select count(*) from Unidad unidad");
            Long count = (Long)query.uniqueResult();       
            int retorno=toIntExact(count);
            return retorno;
        }
        else{
            Query query = session.createQuery("select count(*) from Unidad unidad where unidad.block=:elBlock and unidad.torre=:laTorre");
            query.setParameter("elBlock", block);
            query.setParameter("laTorre", torre);
            Long count = (Long)query.uniqueResult();       
            int retorno=toIntExact(count);
            return retorno;
         }
     }
}
