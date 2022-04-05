package com.assignment.app.rest.user.repository;

import com.assignment.app.rest.user.model.User;
import com.assignment.app.rest.user.model.QUser;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import javax.transaction.Transactional;



public interface UserRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User>, QuerydslBinderCustomizer<QUser>{

    @Override
    default void customize(QuerydslBindings bindings, QUser user) {

        // Make case-insensitive 'like' filter for all string properties
        bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
        bindings.excluding(user.email);
    }

    boolean existsByEmail(String email);

    User findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);

}
