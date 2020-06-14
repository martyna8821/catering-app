package pl.martyna.catering.app.controller.storage;

import com.azure.core.annotation.Get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.martyna.catering.app.dto.resource.RecipeResource;
import pl.martyna.catering.app.service.azurestorage.IStorageService;
import pl.martyna.catering.app.service.report.IReportService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/reports")
public class StorageController {

    private IStorageService storageService;

        //region Injection
        @Autowired
        public StorageController(@Qualifier("blobService") IStorageService storageService ){

            this.storageService = storageService;
        }
        //endregion

    @GetMapping
    public ResponseEntity<?> saveExampleFile(){

            this.storageService.saveFile("Example Text");
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
