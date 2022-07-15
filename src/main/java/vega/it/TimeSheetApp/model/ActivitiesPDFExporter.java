package vega.it.TimeSheetApp.model;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class ActivitiesPDFExporter {

	private List<TimeSheetActivity> timeSheetActivities;

	
	
	public ActivitiesPDFExporter(List<TimeSheetActivity> timeSheetActivities) {
		super();
		this.timeSheetActivities = timeSheetActivities;
	}

	public ActivitiesPDFExporter() {
		super();
	}

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		
		cell.setBackgroundColor(Color.YELLOW);
		cell.setPadding(6);
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.BLACK);
		
		cell.setPhrase(new Phrase("Description", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("TeamMember", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Client", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Project", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Category", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Time", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Overtime", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Date", font));
		table.addCell(cell);
		
	}
	
	private void writeTableData(PdfPTable table) {
		for(TimeSheetActivity activity : timeSheetActivities) {
			table.addCell(String.valueOf(activity.getId()));
			table.addCell(activity.getDescription());
			table.addCell(String.valueOf(activity.getTeamMember()));
			table.addCell(String.valueOf(activity.getProject().getClient()));
			table.addCell(String.valueOf(activity.getProject()));
			table.addCell(String.valueOf(activity.getCategory()));

			table.addCell(String.valueOf(activity.getTime()));
			table.addCell(String.valueOf(activity.getOvertime()));
			table.addCell(String.valueOf(activity.getDate()));
		}
	}
	
	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setColor(Color.BLACK);
		font.setSize(18);
		
		
		Paragraph title = new Paragraph("PDF List of Reports",font);
		title.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(title);
		
		PdfPTable table = new PdfPTable(8);
		table.setWidthPercentage(100);
		table.setSpacingBefore(15);
		table.setWidths(new float[] {2.7f, 3.0f, 3.0f, 3.0f, 3.0f,3.0f,3.0f,3.0f});
		
		
		writeTableHeader(table);
		writeTableData(table);

		document.add(table);
		
		document.close();
	}
	
}
