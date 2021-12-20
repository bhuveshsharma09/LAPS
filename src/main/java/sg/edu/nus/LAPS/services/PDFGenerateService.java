package sg.edu.nus.LAPS.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.LAPS.model.LeaveApplication;
import sg.edu.nus.LAPS.repo.LeaveApplicationRepository;
@Service
public class PDFGenerateService {
	
	@Autowired
	LeaveApplicationRepository leaveApplicationRepo;
	
    //In order to work, need to install 
    // https://jaspersoft.jfrog.io/ui/native/third-party-ce-artifacts/com/lowagie/itext/2.1.7.js1
    public void export(HttpServletResponse response,ArrayList<LeaveApplication> list) throws DocumentException, IOException {
        Document doc = new Document(PageSize.A4);
        PdfWriter.getInstance(doc, response.getOutputStream());
        doc.open();
        PdfPTable table = new PdfPTable(leaveApplicationRepo.countColumns());
        addTableHeader(table);
        for(LeaveApplication row:list){
            table.addCell(row.getLeaveId().toString());
            table.addCell(row.getLeaveType().getLeaveName());
            table.addCell(row.getFromDate().toString());
            table.addCell(row.getToDate().toString());
            table.addCell(row.getEmployee().getEmployeeId().toString());
            table.addCell(row.getContactDetails());
            table.addCell(row.getRemarks());
            table.addCell(row.getCoveringEmp());
            table.addCell(row.getApprovalStatus().toString());
            table.addCell(row.getManagerComment());
            
        }
        doc.add(table);
        doc.close();
    }
    private void addTableHeader(PdfPTable table) {
        Stream.of("Leave Id", "Leave Type",  "From Date", "To Date", "Employee Id", "Contact Details", "Remarks", "Covering Employee", "Approval Status", "Manager's Comment")
          .forEach(columnTitle -> {
            PdfPCell header = new PdfPCell();
            header.setPhrase(new Phrase(columnTitle));
            table.addCell(header);
        });
    }
}
