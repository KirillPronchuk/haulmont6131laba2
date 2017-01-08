package service;

import dao.AbstractDAO;
import model.ModelItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Kirill on 07.01.2017.
 */
public abstract class AbstractService<E extends ModelItem> {
    private AbstractDAO<E> abstractDAO;

    public void setAbstractDAO(AbstractDAO<E> abstractDAO) {
        this.abstractDAO = abstractDAO;
    }

    @Transactional
    public void add(E entity) {
        abstractDAO.add(entity);
    }

    @Transactional
    public List<E> getAll() {
        return abstractDAO.getAll();
    }

    @Transactional
    public void remove(Long id) {
        abstractDAO.removeById(id);
    }

    @Transactional
    public E get(Long id) {
        return abstractDAO.getById(id);
    }

    @Transactional
    public void update(E entity) {
        abstractDAO.update(entity);
    }
}
