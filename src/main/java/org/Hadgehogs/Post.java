package org.Hadgehogs;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "\"Post\"")
@Data
@NoArgsConstructor
@RequiredArgsConstructor

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, columnDefinition = "Text")
    @NonNull
    private String text;

    @Column
    private Date created_at=new Date();

    @JoinColumn(name = "user_id",nullable = false)
    @NonNull
    @ManyToOne
    @ToString.Exclude
    private User user;

    @OneToMany(mappedBy = "post")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Comment> nestedComments;
}
