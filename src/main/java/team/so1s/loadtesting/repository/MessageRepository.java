package team.so1s.loadtesting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.so1s.loadtesting.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Message findMessageByContent(String message);
}
