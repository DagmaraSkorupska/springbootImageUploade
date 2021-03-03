package pl.skorupska.springbootimageuploade.gui;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.skorupska.springbootimageuploade.model.UpdateImage;
import pl.skorupska.springbootimageuploade.repo.UpdateImageRepo;

import java.util.List;

@Route("gallery")
public class GalleryGui extends VerticalLayout {

    private final UpdateImageRepo updateImageRepo;

    @Autowired
    public GalleryGui(UpdateImageRepo updateImageRepo) {
        this.updateImageRepo = updateImageRepo;

        Label label = new Label();
        List<UpdateImage> all = updateImageRepo.findAll();
        all.forEach(element -> {
            Image image = new Image(element.getUrlImage(), "null");
            label.setText("Twoja galeria!");
            add(label);
            add(image);
        });
    }
}
