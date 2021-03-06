package com.ucp.hibernate;

import com.ucp.dao.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


// TODO MAKE IT SINGLETON, INSTANTIATE IT AT THE START OF THE PROGRAM
public class DBConnection {
    private SessionFactory sessionFactory;
    private Configuration configuration;

    public DBConnection() {

        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure()
                .build();


        // Mapping classes
        this.configuration = new Configuration()
                .addAnnotatedClass(TouristicSiteDao.class)
                .addAnnotatedClass(ActivityPlaceDao.class)
                .addAnnotatedClass(HistoricPlaceDao.class)
                .addAnnotatedClass(TransportDao.class)
                .addAnnotatedClass(BusDao.class)
                .addAnnotatedClass(BoatDao.class)
                .addAnnotatedClass(HotelDao.class)
                .configure();

        Metadata metadata = new MetadataSources(standardRegistry)
                .addAnnotatedClass(TouristicSiteDao.class)
                .addAnnotatedClass(ActivityPlaceDao.class)
                .addAnnotatedClass(HistoricPlaceDao.class)
                .addAnnotatedClass(TransportDao.class)
                .addAnnotatedClass(BusDao.class)
                .addAnnotatedClass(BoatDao.class)
                .addAnnotatedClass(HotelDao.class)
                .getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build();


        this.sessionFactory = metadata.getSessionFactoryBuilder()
                .applyBeanManager(getBeanManager())
                .build();


    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    private Object getBeanManager() {
        return null;
    }
}
