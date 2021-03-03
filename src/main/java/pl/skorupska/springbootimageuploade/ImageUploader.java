package pl.skorupska.springbootimageuploade;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.skorupska.springbootimageuploade.model.UpdateImage;
import pl.skorupska.springbootimageuploade.repo.UpdateImageRepo;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class ImageUploader {

    private final Cloudinary cloudinary;
    private final UpdateImageRepo updateImageRepo;

    @Value("${cloudNameValue}")
    private String cloudNameValue;
    @Value("${apiKeyValue}")
    private String apiKeyValue;
    @Value("${apiSecretValue}")
    private String apiSecretValue;


    @Autowired
    public ImageUploader(UpdateImageRepo updateImageRepo,
                         @Value("${cloudNameValue}") String cloudNameValue,
                         @Value("${apiKeyValue}") String apiKeyValue,
                         @Value("${apiSecretValue}") String apiSecretValue) {
        this.updateImageRepo = updateImageRepo;
        cloudinary = new Cloudinary((ObjectUtils.asMap(
                "cloud_name", cloudNameValue,
                "api_key", apiKeyValue,
                "api_secret", apiSecretValue)));
    }

    public String uploadAndSaveFileToDB(String path) {
        File file = new File(path);
        Map result = null;
        try {
            result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            updateImageRepo.save(new UpdateImage(result.get("url").toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.get("url").toString();
    }


}
