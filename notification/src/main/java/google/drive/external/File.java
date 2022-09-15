package google.drive.external;

import java.util.Date;
import lombok.Data;

@Data
public class File {

    private Long id;
    private String name;
    private Long size;
    private String userId;
    // keep

}
