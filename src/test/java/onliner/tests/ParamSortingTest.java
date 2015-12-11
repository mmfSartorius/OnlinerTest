package onliner.tests;

import onliner.forms.OnlinerCatalogForm;
import onliner.forms.OnlinerMainForm;
import onliner.forms.OnlinerTvCatalogForm;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import webdriver.BaseTest;

public class ParamSortingTest extends BaseTest {
    private String mark;
    private String date;
    private String price;
    private String diagonalFrom;
    private String diagonalTo;

    @Test
    @Parameters({"mark", "date", "price", "diagonalFrom", "diagonalTo"})
    public void readParams(String mark, String date, String price, String diagonalFrom, String diagonalTo) throws Throwable {
        this.mark = mark;
        this.date = date;
        this.price = price;
        this.diagonalFrom = diagonalFrom;
        this.diagonalTo = diagonalTo;
        xTest();
    }

    @Override
    public void runTest() {
        logStep();
        OnlinerMainForm onlinerMainForm = new OnlinerMainForm();
        onlinerMainForm.assertLabel();

        logStep();
        OnlinerCatalogForm onlinerCatalogForm = onlinerMainForm.navigateToCatalog();
        onlinerCatalogForm.assertIsOpen();

        logStep();
        OnlinerTvCatalogForm onlinerTvCatalogForm = onlinerCatalogForm.navigateCatalogTv();

        logStep();
        onlinerTvCatalogForm.selektMark(mark);

        logStep();
        onlinerTvCatalogForm.inputPriceTo(price);

        logStep();
        onlinerTvCatalogForm.inputReleaseDateFrom(date);

        logStep();
        onlinerTvCatalogForm.selectDiagonalTo(diagonalTo);

        logStep();
        onlinerTvCatalogForm.selectDiagonalFrom(diagonalFrom);

        logStep();
        onlinerTvCatalogForm.checkCompliance(mark, date, price, diagonalFrom, diagonalTo);

    }
}

