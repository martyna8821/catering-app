package pl.martyna.catering.app.service.diet;

import pl.martyna.catering.app.entity.diet.Label;

import java.util.List;

public interface ILabelService {

    Label save(Label labelToSave);
    List<Label> findAll();
}
