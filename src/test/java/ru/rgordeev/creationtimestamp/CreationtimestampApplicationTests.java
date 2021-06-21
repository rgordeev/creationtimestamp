package ru.rgordeev.creationtimestamp;

import javax.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.rgordeev.creationtimestamp.model.Message1;
import ru.rgordeev.creationtimestamp.model.Message2;
import ru.rgordeev.creationtimestamp.repository.Message1Repository;
import ru.rgordeev.creationtimestamp.repository.Message2Repository;

@SpringBootTest
class CreationtimestampApplicationTests {

    @Autowired
    Message1Repository message1Repository;

    @Autowired
    Message2Repository message2Repository;

    @Test
    @Transactional
    void message1SaveIdTest() {
        Message1 message1 = new Message1();
        message1.setPayload("message11");

        message1 = message1Repository.save(message1);

        Assertions.assertNotNull(message1.getId());
    }

    @Test
    @Transactional
    void message1SaveCreatedTest() {
        Message1 message1 = new Message1();
        message1.setPayload("message12");

        message1 = message1Repository.save(message1);

        Assertions.assertNotNull(message1.getCreated());
    }

    @Test
    @Transactional
    void message2SaveIdTest() {
        Message2 message2 = new Message2();
        message2.setPayload("message2");

        message2 = message2Repository.save(message2);

        Assertions.assertNotNull(message2.getId());
    }

    @Test
    @Transactional
    void message2SaveCreatedTest() {
        Message2 message2 = new Message2();
        message2.setPayload("message2");

        message2 = message2Repository.save(message2);

        Assertions.assertNotNull(message2.getCreated());
    }
}
