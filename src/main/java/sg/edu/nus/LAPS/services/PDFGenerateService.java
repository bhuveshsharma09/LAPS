package sg.edu.nus.LAPS.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import org.springframework.stereotype.Service;

import sg.edu.nus.LAPS.model.LeaveApplication;
@Service
public class PDFGenerateService {
    //In order to work, need to install 
    // https://jaspersoft.jfrog.io/ui/native/third-party-ce-artifacts/com/lowagie/itext/2.1.7.js1
    public void export(HttpServletResponse response,ArrayList<LeaveApplication> list) throws DocumentException, IOException {
        Document doc = new Document(PageSize.A4);
        PdfWriter.getInstance(doc, response.getOutputStream());
        doc.open();
        PdfPTable table = new PdfPTable(list.size());
        addTableHeader(table);
        for(LeaveApplication row:list){
            table.addCell(row.getLeaveId().toString());
            table.addCell(row.getApprovalStatus().toString());
            table.addCell(row.getContactDetails());
            table.addCell(row.getCoveringEmp());
            table.addCell(row.getFromDate().toString());
            table.addCell(row.getToDate().toString());
        }
        doc.add(table);
        doc.close();
    }
    private void addTableHeader(PdfPTable table) {
        Stream.of("column header 1", "column header 2", "column header 3", "column header 4", "column header 5", "column header 6")
          .forEach(columnTitle -> {
            PdfPCell header = new PdfPCell();
            table.addCell(header);
        });
    }
}
