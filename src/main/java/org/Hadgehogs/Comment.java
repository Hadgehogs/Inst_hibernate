package org.Hadgehogs;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "\"Comment\"")
@Data
@NoArgsConstructor
@RequiredArgsConstructor

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, columnDefinition = "Text")
    @NonNull
    private String text;

    @Column
    private Date created_at=new Date();

    @JoinColumn(name = "post_id",nullable = false)
    @NonNull
    @ManyToOne
    @ToString.Exclude
    private Post post;

    @JoinColumn(name = "user_id",nullable = false)
    @NonNull
    @ManyToOne
    @ToString.Exclude
    private User user;
}
