package com.personal.budget.clients.google;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.personal.budget.data.BudgetLine;
import com.personal.budget.data.LineType;
import com.personal.budget.data.Money;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class SheetClient {

    private final Sheets sheets;
    private final String sheetId;

    public SheetClient(Sheets sheets, @Value("${application.sheet-id}") String sheetId) {
        this.sheets = sheets;
        this.sheetId = sheetId;
    }

    public boolean updateSheet(BudgetLine budgetLine, ZonedDateTime currentTime) {
        ValueRange body = new ValueRange()
                .setValues(List.of(List.of(budgetLine.type().name(), budgetLine.money().amount(), budgetLine.comment(), currentTime.toLocalDate().toString())));
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

    public List<BudgetLine> getCurrentData() {
        try {
            return sheets.spreadsheets()
                    .values()
                    .get(sheetId, "Sheet1")
                    .execute()
                    .getValues()
                    .stream()
                    .map(list -> new BudgetLine(
                            LineType.valueOf((String) list.get(0)),
                            new Money(Long.parseLong((String) list.get(1))),
                            (String) list.get(2)))
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
