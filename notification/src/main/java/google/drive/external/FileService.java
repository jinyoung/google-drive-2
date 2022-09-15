package google.drive.external;

import java.util.Date;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(
    name = "drive",
    url = "${api.url.drive}",
    fallback = FileServiceImpl.class
)
public interface FileService {
    @RequestMapping(method = RequestMethod.GET, path = "/files/{id}")
    public File getFile(@PathVariable("id") Long id);
    // keep

}
