package google.drive.external;

import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {

    /**
     * Fallback
     */
    public File getFile(Long id) {
        File file = new File();
        return file;
    }
    // keep

}
