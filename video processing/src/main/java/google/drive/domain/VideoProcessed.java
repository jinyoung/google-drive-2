package google.drive.domain;

import google.drive.domain.*;
import google.drive.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class VideoProcessed extends AbstractEvent {

    private Long id;
    private String videoUrl;
    private Long fileKey;

    public VideoProcessed(Video aggregate) {
        super(aggregate);
    }

    public VideoProcessed() {
        super();
    }
    // keep

}
