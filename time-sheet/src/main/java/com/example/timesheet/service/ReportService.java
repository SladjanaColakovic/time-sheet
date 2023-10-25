package com.example.timesheet.service;

import com.example.timesheet.CustomMapper;
import com.example.timesheet.core.model.Report;
import com.example.timesheet.core.model.ReportSearch;
import com.example.timesheet.core.model.TimeSheetItem;
import com.example.timesheet.core.repository.ITimeSheetItemRepository;
import com.example.timesheet.core.service.IReportService;
import com.example.timesheet.data.entity.TimeSheetItemEntity;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ReportService implements IReportService {

    @Autowired
    private ITimeSheetItemRepository timeSheetItemRepository;

    @Autowired
    private CustomMapper mapper;

    @Override
    public Report reportSearch(ReportSearch reportSearch) {
        List<TimeSheetItem> items = timeSheetItemRepository.reportSearch(reportSearch);
        Float totalReport = items.stream()
                .map(element -> element.getTime() + element.getOvertime())
                .reduce(0f, Float::sum);
        return new Report(items, totalReport);
    }

    @Override
    public ByteArrayOutputStream pdfReport(ReportSearch reportSearch) {
       List<TimeSheetItem> items = timeSheetItemRepository.reportSearch(reportSearch);
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();
            addDocumentTitle(document);
            PdfPTable table = createTable(document, items);
            document.add(table);
            document.close();
        } catch (DocumentException e) {
            System.out.println(e.getMessage());
        }

        return out;
    }

    private void addDocumentTitle(Document document) throws DocumentException {
        Font font = FontFactory
                .getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
        Paragraph title = new Paragraph("TimeSheet Report", font);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(Chunk.NEWLINE);
    }

    private PdfPTable createTable(Document document, List<TimeSheetItem> items){
        PdfPTable table = new PdfPTable(6);
        addTableHeader(table);
        addTableBody(table, items);
        return  table;
    }

    private void addTableHeader(PdfPTable table){
        Stream.of("Date", "TeamMember", "Project", "Category", "Description", "Time")
                .forEach(headerTitle ->
                {
                    PdfPCell header = new PdfPCell();
                    Font headFont = FontFactory.
                            getFont(FontFactory.HELVETICA_BOLD);
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(headerTitle, headFont));
                    table.addCell(header);
                });
    }

    private void addTableBody(PdfPTable table, List<TimeSheetItem> items){
        for (TimeSheetItem item : items) {
            addTableCell(item.getDate().toString(), table);
            addTableCell(item.getTeamMember().getName(), table);
            addTableCell(item.getProject().getName(), table);
            addTableCell(item.getCategory().getName(), table);
            addTableCell(item.getDescription(), table);
            addTableCell(String.valueOf(item.getTime()), table);
        }
    }

    private void addTableCell(String value, PdfPTable table){
        PdfPCell cell = new PdfPCell(new Phrase
                (String.valueOf(value)));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setPaddingRight(4);
        table.addCell(cell);
    }

}
