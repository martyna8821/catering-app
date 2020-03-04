package pl.martyna.catering.app.service.pdf.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import pl.martyna.catering.app.service.pdf.IPDFService;

import java.io.FileOutputStream;

@Service
public class PDFService implements IPDFService {

    @Override
    public void createHelloPdf(){
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("iTextHelloWorld.pdf"));

            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Chunk chunk = new Chunk("Hello World", font);

            document.add(chunk);
        }catch (Exception ignored){

        }
        document.close();
    }
}
