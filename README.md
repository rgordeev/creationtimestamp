### Unexpected behavior while trying to persist entity with id strategy = GenerationType.SEQUENCE and existing column marked as @CreationTimestamp

I've got an unexpected behavior while I have been trying to persist entity with id
```
strategy = GenerationType.SEQUENCE
```
and existing column marked as `@CreationTimestamp`.

Assumption: there are two models `Message1` and `Message2` defined as follows:
```
@Entity
@Getter
@Setter
public class Message1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "payload", columnDefinition = "text")
    private String payload;

    @Column(name = "created", columnDefinition = "timestamp with time zone")
    @CreationTimestamp
    private ZonedDateTime created;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Message1 message1 = (Message1) o;
        return Objects.equals(id, message1.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(41);
    }
}
```
and
```
@Entity
@Getter
@Setter
public class Message2 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "payload", columnDefinition = "text")
    private String payload;

    @Column(name = "created", columnDefinition = "timestamp with time zone")
    @CreationTimestamp
    private ZonedDateTime created;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Message2 message2 = (Message2) o;
        return id == message2.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(37);
    }
}
```

The only difference between those two models is the definition of the id generation strategy:
```
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", updatable = false)
private Long id;
```
for the first model and
```
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", updatable = false)
private Long id;
```
for the second.

And following tests behaves completely different for the cases on not null assertions on the `@CreationTimestamp` marked attributes. Test for the `Message1` model passes
```
    @Test
    @Transactional
    void message1SaveCreatedTest() {
        Message1 message1 = new Message1();
        message1.setPayload("message12");

        message1 = message1Repository.save(message1);

        Assertions.assertNotNull(message1.getCreated());
    }
```
and test for the `Model2` fails
```
    @Test
    @Transactional
    void message2SaveCreatedTest() {
        Message2 message2 = new Message2();
        message2.setPayload("message2");

        message2 = message2Repository.save(message2);

        Assertions.assertNotNull(message2.getCreated());
    }
```

Application code with configurations and tests you may find here
[https://github.com/rgordeev/creationtimestamp](https://github.com/rgordeev/creationtimestamp)