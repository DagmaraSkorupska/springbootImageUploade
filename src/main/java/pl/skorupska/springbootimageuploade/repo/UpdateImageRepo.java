package pl.skorupska.springbootimageuploade.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.skorupska.springbootimageuploade.model.UpdateImage;

@Repository
public interface UpdateImageRepo extends JpaRepository<UpdateImage, Long> {
}
