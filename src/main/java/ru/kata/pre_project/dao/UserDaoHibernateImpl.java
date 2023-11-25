package ru.kata.pre_project.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.pre_project.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoHibernateImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public List<User> getAll() {
        String hql = "from User";
        return em.createQuery(hql, User.class)
                .getResultList();
    }

    @Override
    @Transactional
    public Optional<User> getById(int id) {
        String hql = "from User u where u.id = :id";
        return Optional.of(em.createQuery(hql, User.class)
                .setParameter("id", id)
                .getSingleResult());
    }

    @Override
    @Transactional
    public void saveOrUpdate(User user) {
        em.persist(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        em.remove(user);
    }
}
