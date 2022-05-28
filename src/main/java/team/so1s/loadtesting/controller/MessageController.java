package team.so1s.loadtesting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import team.so1s.loadtesting.dto.MessageDto;
import team.so1s.loadtesting.entity.Message;
import team.so1s.loadtesting.service.MessageService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/messages")
public class MessageController {
    private final MessageService messageService;

    @GetMapping
    public ResponseEntity<List<Message>> getMessages() {
        final var result = messageService.findAll();

        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Message> saveMessage(@Valid @RequestBody @NonNull MessageDto messageDto) {
        var entity = Message.builder()
                .content(messageDto.getMessage())
                .build();

        entity = messageService.saveAndFlush(entity);

        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }


}
