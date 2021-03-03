package pl.skorupska.springbootimageuploade.gui;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.skorupska.springbootimageuploade.ImageUploader;

@Route("upload")
public class UploaderGui extends VerticalLayout {

    private final ImageUploader imageUploader;

    @Autowired
    public UploaderGui(ImageUploader imageUploader) {
        this.imageUploader = imageUploader;

        Label label = new Label();
        TextField textFieldUrl = new TextField("podaj scieżkę url");
        Button buttonAdd = new Button("Upload");

        buttonAdd.addClickListener(click -> {
            String myImage = imageUploader.uploadAndSaveFileToDB(textFieldUrl.getValue());
            Image image = new Image(myImage, "nie ma obrazka :(");
            label.setText("Udało sie wrzucic obrazek !!!");

            add(label);
            add(image);
        });

        add(textFieldUrl);
        add(buttonAdd);
    }
}
