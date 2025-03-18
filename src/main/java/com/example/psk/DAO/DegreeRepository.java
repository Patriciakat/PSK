package com.example.psk.DAO;

import com.example.psk.entities.Degree;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class DegreeRepository {
    @PersistenceContext(unitName = "PSKPersistence")
    private EntityManager em;
    public void save(Degree degree) {
        em.persist(degree);
    }
    public Degree findById(Long id) {
        return em.find(Degree.class, id);
    }

    public List<Degree> findAll() {
        return em.createQuery("SELECT d FROM Degree d", Degree.class).getResultList();
    }
    public Degree update(Degree degree) {
        return em.merge(degree);
    }

    public void delete(Long id) {
        Degree degree = em.find(Degree.class, id);
        if (degree != null) {
            em.remove(degree);
        }
    }

    public Degree findByTitle(String title) {
        List<Degree> results = em.createQuery(
                        "SELECT d FROM Degree d WHERE d.title = :title",
                        Degree.class)
                .setParameter("title", title)
                .getResultList();

        return results.isEmpty() ? null : results.get(0);
    }

    public Long countStudents(Long degreeId) {
        return em.createQuery(
                        "SELECT COUNT(s) FROM Student s WHERE s.degree.id = :degreeId",
                        Long.class)
                .setParameter("degreeId", degreeId)
                .getSingleResult();
    }
}
