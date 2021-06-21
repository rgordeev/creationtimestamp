package ru.rgordeev.creationtimestamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rgordeev.creationtimestamp.model.Message1;

@Repository
public interface Message1Repository extends JpaRepository<Message1, Long> {

}
