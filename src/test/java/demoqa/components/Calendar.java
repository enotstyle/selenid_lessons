package demoqa.components;

import static com.codeborne.selenide.Selenide.$;

public class Calendar {


    //TODO сдеать формат даты с помощью класса или енама
    public void setDate(String day, String month, String year) {
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day--0" + day + ":not(.react-datepicker__day--outside-month)")
                .click();
    }


}
