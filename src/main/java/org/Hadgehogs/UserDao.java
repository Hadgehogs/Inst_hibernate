package org.Hadgehogs;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.List;

public class UserDao {
    public String getUserName(Session session, Integer userId) {
        session.beginTransaction();
        String name = session.get(User.class, userId).getName();
        session.getTransaction().commit();
        session.clear();
        return name;
    }

    public List<Post> getNestedPosts(Session session, Integer userId) {
        session.beginTransaction();
        User human = session.get(User.class, userId);
        Hibernate.initialize(human.getNestedPosts());
        List<Post> posts = human.getNestedPosts();
        session.getTransaction().commit();
        session.clear();
        return posts;
    }

    public List<Comment> getNestedComments(Session session, Integer userId) {
        session.beginTransaction();
        User human = session.get(User.class, userId);
        Hibernate.initialize(human.getNestedComments());
        List<Comment> comments = human.getNestedComments();
        session.getTransaction().commit();
        session.clear();
        return comments;
    }
}
