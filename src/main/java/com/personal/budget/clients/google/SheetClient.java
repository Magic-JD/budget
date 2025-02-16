package com.personal.budget.clients.google;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.personal.budget.data.BudgetLine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;

@Component
public class SheetClient {

    private final Sheets sheets;
    private final String sheetId;

    public SheetClient(Sheets sheets, @Value("${application.sheet-id}") String sheetId) {
        this.sheets = sheets;
        this.sheetId = sheetId;
    }

    public boolean updateSheet(BudgetLine budgetLine) {
        ValueRange body = new ValueRange()
                .setValues(List.of(List.of(budgetLine.type().name(), budgetLine.money().amount(), budgetLine.comment(), ZonedDateTime.now().toLocalDate().toString())));
        try {
            sheets.spreadsheets().values()
                    .append(sheetId, "A1", body)
                    .setValueInputOption("RAW")
                    .execute();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
