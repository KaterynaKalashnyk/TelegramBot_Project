package com.io.deutsch_steuerrechner.service;

public class Variables {
    static final String COMMAND_HELP_TEXT = "This bot was created so that people living in Germany could understand which tax class they belong to and what salary after taxes they will receive in their hands. Also, with the help of this bot, a person can get information about his tax class, what the conditions are for each tax class, and also see how much money he spends on a particular tax.\n\n"
            + "Type /start to see welcome messege and to begin to work.\n\n"
            + "Type /steuerrechnen - Bot starts to ask question for tax class definitions and count netto-salary.\n\n"
            + "Type /newcount - User can input new information und start new count of taxes.\n\n"
            + "Type /infotaxes - Bot shows informaion about taxes group.\n\n"
            + "Type /help - Information how to use commands and bot.\n\n";

    static final String DEFAULT_UNKNOWN_COMMAND_MESSEGE = "Sorry, command wasn't recognized";
    static final String QUESTION_ABOUT_FAMILY_STATUS = "Are you married or single?";
    static final String QUESTION_ABOUT_SALARY_OF_PARTNER = "Earn you more, less or the same as your partner?";
    static final String QUESTION_ABOUT_CHILDREN = "Do you have children?";
    static final String QUESTION_ABOUT_MINIJOB = "Do you have Minijob or extra work?";
    static final String QUESTION_ABOUT_SALARY_BY_MINIJOB = "How much do you earn by minijob or extra work?";
    static final String QUESTION_ABOUT_CHURCH = "Do you visit the church, mosque, cathedral?";
    static final String QUESTION_ABOUT_MEDICAL_INSURACE = "Do you have privat or public medical insurace?";
    static final String QUESTION_ABOUT_PROFESSION = "What is your profession?";
    static final String QUESTION_ABOUT_SALARY = "How much do you earn?";
    static final String TAX_CLASS1 = "Your tax class is class 1.";
    static final String TAX_CLASS2 = "Your tax class is class 2.";
    static final String TAX_CLASS3 = "Your tax class is class 3.";
    static final String TAX_CLASS4 = "Your tax class is class 4.";
    static final String TAX_CLASS5 = "Your tax class is class 5.";
    static final String TAX_CLASS6 = "Your tax class is class 6 for your extra works salary.";
    static final double solidatitatSteuerPercent = 5.5;
    static final double kirchSteuerPercent = 8;
    static final double arbeitlosVersicherungPercent = 1.3;
    static final double lohnSteuerPercentBis58thausend = 14;
    static final double getLohnSteuerPercentNach58thausend = 45;
    static final double renteVersicherungPercent = 9.3;
    static final String tax_class1_info = "Employees who are classified in tax class 1 pay almost the most taxes and are burdened the highest. The following employees are classified in tax class 1: Single, Married people, Widowed (one year after the death of the spouse), Divorced, Childless. In addition, the requirements for tax class 3 or 4 must not be met. Tax class 1 is also assigned to those whose partners live abroad.\n";
    static final String tax_class2_info = "Tax class 2 is the income tax class for single parents. Strictly speaking, single parents belong to tax class 1 because they are also considered single. However, the state takes into account the tax advantage through the relief contribution for single parents in accordance with Section 24b of the Income Tax Act (EStG).\n" +
            "The prerequisite for tax class 2 is that at least one minor child lives in the household for whom child benefit or a child allowance is claimed. In addition, no other adult person may live in the same household who could also act as a legal guardian.\n";
    static final String tax_class3_info = "Tax class 3 applies to married employees with children (parental benefit recipients) and only if the spouse has chosen tax class 5.\n" +
            "\n" +
            "Tax class 3 includes employees\n" +
            "\n" +
            "with a registered civil partnership,\n" +
            "who are married (minimum income of €520),\n" +
            "widowed (in the year of death + the following year).\n" +
            "For registered civil partnerships and married couples, the requirement also applies that one of the two has chosen tax class 5 or earns less or does not work.\n" +
            "\n" +
            "Furthermore, tax class 3 applies to everyone whose marriage has been dissolved. Provided that in the calendar year in which the marriage was dissolved, both spouses were subject to unlimited income tax and did not live permanently. The same applies if the other spouse has remarried, is not permanently separated from his new spouse and he and his new spouse are subject to unlimited income tax. All requirements only apply to the calendar year in which the marriage was dissolved.\n";
    static final String tax_class4_info = "Tax class 4 is for married couples with the same income (e.g. both earn €35,000 each year). Both spouses must be subject to unlimited income tax and may not live separately permanently. Since January 1, 2009, spouses who belong to this tax class and who previously chose the tax class combination 3/5 can apply to the tax office for tax fund 4 and the determination of a factor for both spouses. This is intended to avoid higher taxation on the less earning spouse.\n";
    static final String tax_class5_info = "Married people choose tax class 5 if both spouses are employed and one of them has chosen tax class 3. Tax class 5 is the counterpart to tax class 3. The prerequisite is that both spouses are employed, one of them has chosen tax class 3 and there is a minimum income of €520. However, anyone who chooses tax class 5 cannot claim a basic allowance or a child allowance. However, the pension allowance, the employee allowance and the special expenses allowance remain unaffected.\n";
    static final String tax_class6_info = "Married employees and married pensioners can use the calculator to calculate the ideal combination of their tax brackets. Our tax class calculator also shows the financial differences between tax class combinations for married couples.\n";
    static final String taxesAllInfo = tax_class1_info + tax_class2_info + tax_class3_info + tax_class4_info + tax_class5_info + tax_class6_info;
    static final String theEndOfCount_class2 = "";
    static final String theEndOfCount_class3 = "";
    static final String theEndOfCount_class4 = "";
    static final String theEndOfCount_class5 = "";
    static final String theEndOfCount_class6 = "";

}
