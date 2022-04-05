package com.assignment.app.rest.post.model;

import com.assignment.app.rest.comment.model.Comment;
import com.assignment.app.rest.user.model.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "user_post")
@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany()
    private List<Comment> comments;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("userId")
    private User user;
}
