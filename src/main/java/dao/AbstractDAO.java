package dao;

import model.ModelItem;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Kirill on 07.01.2017.
 */
public abstract class AbstractDAO<E extends ModelItem> {
    private static final Logger logger = LoggerFactory.getLogger(AbstractDAO.class);

    private Class<E> entityClass;

    public AbstractDAO(Class<E> type){
        this.entityClass = type;
    }

    public void add(E entity){
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        logger.info(entityClass.getSimpleName() + " was successfully saved. Details: " + entity.toString() + ".");
    }

    public void removeById(Long id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        E entity = (E) session.load(entityClass, id);

        if(entity!=null){
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
            logger.info(entityClass.getSimpleName() + " was successfully removed. Details: " + entity.toString() + ".");
        } else {
            logger.info("Can't delete " + entityClass.getSimpleName() + " with id = " + id);
        }
    }

    public void update(E entity){
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(entity);
        session.getTransaction().commit();
        logger.info(entityClass.getSimpleName() + " was successfully updated. Details: " + entity.toString());
    }

    public E getById(Long id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        E entity = (E) session.load(entityClass, id);
        if(entity!=null){
            logger.info(entityClass.getSimpleName() + " was successfully loaded. Details: " + entity.toString());
        } else {
            logger.info(entityClass.getSimpleName() + " with id = " + id + " doesn't exist, null will be returned.");
        }

        return entity;
    }

    public List<E> getAll(){
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<E> entityList = session.createQuery("SELECT up FROM " + entityClass.getSimpleName() + " up").list();

        if(entityList!=null) {
            for (E entity : entityList) {
                logger.info(entity.toString());
            }
        } else {
            logger.info("Entity list is empty, null will be returned.");
        }
        return entityList;
    }

}
