package ru.rgordeev.creationtimestamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rgordeev.creationtimestamp.model.Message2;

@Repository
public interface Message2Repository extends JpaRepository<Message2, Long> {

}
