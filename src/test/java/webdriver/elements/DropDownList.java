package webdriver.elements;


import org.openqa.selenium.By;

public class DropDownList extends BaseElement {

    public DropDownList(By loc) {
        super(loc);
    }

    public DropDownList(String stringLocator, String nameOfElement) {
        super(stringLocator, nameOfElement);
    }

    public DropDownList(By loc, String nameOf) {
        super(loc, nameOf);
    }

    protected String getElementType() {
        return getLoc("loc.dropDownList");
    }

    public void selectItemOfDropDownList(String item) {
        String xpath = this.locator.toString().substring(10);
        Label itemOfList = new Label(By.xpath(xpath + String.format("//option[contains(text(), %s)]", item)));
        itemOfList.click();
        itemOfList.waitForIsElementPresent();
    }
}
