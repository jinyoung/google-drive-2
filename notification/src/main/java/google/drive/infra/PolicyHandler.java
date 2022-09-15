package google.drive.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import google.drive.config.kafka.KafkaProcessor;
import google.drive.domain.*;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PolicyHandler {

    @Autowired
    NotificationRepository notificationRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @Autowired
    google.drive.external.FileService fileService;

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Indexed'"
    )
    public void wheneverIndexed_NotifyToUser(@Payload Indexed indexed) {
        Indexed event = indexed;
        System.out.println(
            "\n\n##### listener NotifyToUser : " + indexed + "\n\n"
        );

        // REST Request Sample

        // fileService.getFile(/** mapping value needed */);

        // Sample Logic //
        Notification.notifyToUser(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='VideoProcessed'"
    )
    public void wheneverVideoProcessed_NotifyToUser(
        @Payload VideoProcessed videoProcessed
    ) {
        VideoProcessed event = videoProcessed;
        System.out.println(
            "\n\n##### listener NotifyToUser : " + videoProcessed + "\n\n"
        );

        // REST Request Sample

        // fileService.getFile(/** mapping value needed */);

        // Sample Logic //
        Notification.notifyToUser(event);
    }
    // keep

}
