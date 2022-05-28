package team.so1s.loadtesting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.so1s.loadtesting.entity.Message;
import team.so1s.loadtesting.repository.MessageRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    public Message findMessageByContent(String message) {
        return messageRepository.findMessageByContent(message);
    }

    public Message saveAndFlush(Message entity) {
        return messageRepository.saveAndFlush(entity);
    }
}
