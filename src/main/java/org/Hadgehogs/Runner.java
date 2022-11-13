package org.Hadgehogs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Map;

public class Runner {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateConfig.createSessionFactory();
             Session session = sessionFactory.openSession();) {

            session.beginTransaction();

            User BuhoyEferill = new User("BuhoyEferill", "128500");
            session.save(BuhoyEferill);

            Post post1 = new Post("Мы живём ради Высшего Блага.", BuhoyEferill);
            session.save(post1);

            Post post2 = new Post("Ни кто не устоит перед Высшим Благом!", BuhoyEferill);
            session.save(post2);

            User DarkTemplar = new User("DarkTemplar", "228500");
            session.save(DarkTemplar);

            Post post3 = new Post("Высшее Благо - это не конец, а путь.", DarkTemplar);
            session.save(post3);

            Comment comment1 = new Comment("Простите, был пьян.", post1, BuhoyEferill);
            session.save(comment1);

            Comment comment2 = new Comment("Даже Эферилу надо закусывать.", post1, DarkTemplar);
            session.save(comment2);

            Comment comment3 = new Comment("Но мы здорово вчера погудели, даже блинк на мэйн под обсервером сделать сейчас не смогу.", post1, DarkTemplar);
            session.save(comment3);

            session.getTransaction().commit();
            session.clear();

            System.out.println("-------------------------------------");
            System.out.println("Результаты выполнения:");

            session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            criteria.from(User.class);
            List<User> usersList = session.createQuery(criteria).getResultList();

            for (User currentUser : usersList
            ) {
                List<Post> nestedPosts = currentUser.getNestedPosts();
                List<Comment> nestedComments = currentUser.getNestedComments();
                System.out.println("");
                System.out.println(String.format("Посты пользователя %s:", currentUser.getName()));
                System.out.println(nestedPosts.toString());
                System.out.println(String.format("Камметы пользователя %s:", currentUser.getName()));
                System.out.println(nestedComments.toString());
            }
            session.getTransaction().commit();
        }
    }
}
