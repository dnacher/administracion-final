package persistencia;

import java.io.File;
import javax.imageio.spi.ServiceRegistry;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class NewHibernateUtil {

    private static SessionFactory sf;
    private static ServiceRegistry serviceRegistrry;    
        
    static {
        try {            
            File file=new File("src/persistencia/hibernate.cfg.xml");
            Configuration cfg= new Configuration().configure(file);            
            StandardServiceRegistryBuilder sb= new StandardServiceRegistryBuilder();
            sb.applySettings(cfg.getProperties());
            StandardServiceRegistry standardServiceRegistry= sb.build();
            sf = cfg.buildSessionFactory(standardServiceRegistry);
        } catch (Throwable ex) {            
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sf;
    }
}