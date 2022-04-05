package com.assignment.app.rest.comment.model;

import com.assignment.app.rest.post.model.Post;
import com.assignment.app.rest.user.model.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;

@Entity(name = "user_comment")
@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    private Post post;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("userId")
    private User user;


}
