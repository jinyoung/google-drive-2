package google.drive.domain;

import google.drive.NotificationApplication;
import google.drive.external.File;
import google.drive.external.FileService;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Notification_table")
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private String message;

    public static NotificationRepository repository() {
        NotificationRepository notificationRepository = NotificationApplication.applicationContext.getBean(
            NotificationRepository.class
        );
        return notificationRepository;
    }

    public static void notifyToUser(Indexed indexed) {
        /** Example 1:  new item 
        */
        Notification notification = new Notification();
        notification.setUserId(indexed.getUserId());
        notification.setMessage("인덱싱되었습니다: "+ indexed.getFileId());

        repository().save(notification);


        /** Example 2:  finding and process
        
        repository().findById(indexed.get???()).ifPresent(notification->{
            
            notification // do something
            repository().save(notification);


         });
        */

    }

    public static void notifyToUser(VideoProcessed videoProcessed, FileService fileService) {
        /** Example 1:  new item   */
        Notification notification = new Notification();

        File file = fileService.getFile(videoProcessed.getFileKey());

        notification.setUserId(file.getUserId());
        notification.setMessage("비디오 처리가 완려되었습니다: "+videoProcessed.getVideoUrl());
        
        repository().save(notification);

      

        /** Example 2:  finding and process
        
        repository().findById(videoProcessed.get???()).ifPresent(notification->{
            
            notification // do something
            repository().save(notification);


         });
        */

    }
}
