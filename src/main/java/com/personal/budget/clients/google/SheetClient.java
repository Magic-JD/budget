package com.personal.budget.clients.google;

import com.personal.budget.data.BudgetLine;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class SheetClient {

//    private final Sheets sheets;
//    private final String sheetId;
//
//    public SheetClient(Sheets sheets, @Value("${application.sheet-id}") String sheetId) {
//        this.sheets = sheets;
//        this.sheetId = sheetId;
//    }
//
    public boolean updateSheet(BudgetLine budgetLine, ZonedDateTime currentTime) {
//        ValueRange body = new ValueRange()
//                .setValues(List.of(List.of(budgetLine.type().name(), budgetLine.money().amount(), budgetLine.comment(), currentTime.toLocalDate().toString())));
//        try {
//            sheets.spreadsheets().values()
//                    .append(sheetId, "A1", body)
//                    .setValueInputOption("RAW")
//                    .execute();
//            return true;
//        } catch (IOException e) {
//            return false;
//        }
//    }
        return true;
    }

}
