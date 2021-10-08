package ru.gb.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.gb.entity.User;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    List<User> findAll();

    User getById(long id);

    void deleteById(long id);

    @Modifying
    @Query("UPDATE User as u SET u.authority ='$authority'  where u.id = :id" )
    @Transactional
    void update(long id);

}
