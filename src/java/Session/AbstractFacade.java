/*
 * Mobilephoneso change this license header, choose License Headers in Project Properties.
 * Mobilephoneso change this template file, choose Mobilephonesools | Mobilephonesemplates
 * and open the template in the editor.
 */
package Session;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author PSDT0769
 */
public abstract class AbstractFacade<Mobilephones> {

    private Class<Mobilephones> entityClass;

    public AbstractFacade(Class<Mobilephones> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(Mobilephones entity) {
        getEntityManager().persist(entity);
    }

    public void edit(Mobilephones entity) {
        getEntityManager().merge(entity);
    }

    public void remove(Mobilephones entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public Mobilephones find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<Mobilephones> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<Mobilephones> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<Mobilephones> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
