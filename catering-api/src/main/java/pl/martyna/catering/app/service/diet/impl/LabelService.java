package pl.martyna.catering.app.service.diet.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.martyna.catering.app.entity.diet.Label;
import pl.martyna.catering.app.repository.diet.ILabelRepository;
import pl.martyna.catering.app.service.diet.ILabelService;

import java.util.List;

@Service
public class LabelService implements ILabelService {

    private ILabelRepository lableRepository;

    @Autowired
    public LabelService(ILabelRepository lableRepository){
        this.lableRepository= lableRepository;
    }

    @Override
    public Label save(Label labelToSave) {
        return this.lableRepository.save(labelToSave);
    }

    @Override
    public List<Label> findAll() {
        return this.lableRepository.findAll();
    }
}
