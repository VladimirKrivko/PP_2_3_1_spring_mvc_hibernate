package ru.kata.pre_project.dao;

import org.springframework.stereotype.Repository;
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
    public List<User> getAll() {
        String hql = "from User";
        return em.createQuery(hql, User.class)
                .getResultList();
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.of(em.find(User.class, id));
    }

    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public void delete(User user) {
        em.remove(user);
    }
}
