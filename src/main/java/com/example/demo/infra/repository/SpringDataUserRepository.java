package com.example.demo.infra.repository;

import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Repository
@RequiredArgsConstructor
class SpringDataUserRepository implements UserRepository {

    private final JpaUserRepo repo;
    private final UserEntityMapper mapper;

    @Override
    public User store(User user) {
        var entity = repo.save(mapper.toEntity(user));

        return mapper.toModel(entity);
    }

    @Override
    public int count() {
        return (int) repo.count();
    }

    @Override
    public List<User> findAll() {
        return repo.findAll().stream()
                .map(mapper::toModel)
                .collect(toList());
    }

    @Override
    public void deleteAll() {
        repo.deleteAll();
    }
}

@Repository
interface JpaUserRepo extends JpaRepository<UserEntity, String> {}