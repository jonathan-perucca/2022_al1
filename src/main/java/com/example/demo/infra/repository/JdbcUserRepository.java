package com.example.demo.infra.repository;

import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Primary
@RequiredArgsConstructor
class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public User store(User user) {
        jdbcTemplate.update("insert into users (id, username) values (?, ?)", user.getId(), user.getUsername());
        return user;
    }

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select id, username from users", new UserRowMapper());
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.execute("delete from users");
    }

    private static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User()
                    .setId(rs.getString("id"))
                    .setUsername(rs.getString("username"));
        }
    }
}