package com.io.deutsch_steuerrechner.service;

import com.io.deutsch_steuerrechner.config.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static com.io.deutsch_steuerrechner.service.Variables.*;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    final BotConfig config;

    public TelegramBot(BotConfig config) throws FileNotFoundException {
        this.config = config;
        List<BotCommand> botCommands = new ArrayList<>();
        botCommands.add(new BotCommand("/start", "Bot starts to work."));
        botCommands.add(new BotCommand("/infotaxes", "info about taxes."));
        botCommands.add(new BotCommand("/help", "Information about bot."));
        botCommands.add(new BotCommand("/count", "Bot starts to indefy and count your taxes."));
        try {
            this.execute(new SetMyCommands(botCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return config.botName;
    }

    @Override
    public String getBotToken() {
        return config.botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            switch (messageText) {
                case "/start" -> startCommandRecived(chatId, update.getMessage().getChat().getFirstName());
                case "/help" -> sendMessage(chatId, COMMAND_HELP_TEXT);
                case "/count" -> usersFamily(chatId, messageText);
                case "/infotaxes" -> sendMessage(chatId, taxesAllInfo);
                default -> sendMessage(chatId, DEFAULT_UNKNOWN_COMMAND_MESSEGE);
            }
        }
    }

    private void startCommandRecived(long chatId, String usersName) {
        String answer = "Hallo, " + usersName + ". I'm Steuerrechner. I can help you to find out your tax group in Germany and how much money will you pay" +
                "from your brutto-salary for taxes, " +
                "and i will also calculate your netto-salary";
        sendMessage(chatId, answer);
    }


    public void sendMessage(long chatId, String textToSend){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

       //ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
       // List<KeyboardRow> keyboardRows =new ArrayList<>();
       // KeyboardRow row = new KeyboardRow();
       // keyboardRows.add(row);
       // row.add("Married");
        //row.add("Single");
       // keyboardMarkup.setKeyboard(keyboardRows);
        // message.setReplyMarkup(keyboardMarkup);

        try {
            execute(message);
        }catch (TelegramApiException e){
            throw new RuntimeException(e);
        }

    }


    public void usersFamily (long chatId, String messageText){
       //sendMessage(chatId, QUESTION_ABOUT_FAMILY_STATUS);
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Are u married?");

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> inlineKeyboardButtonsList = new ArrayList<>();
        List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();

        InlineKeyboardButton marriedButtton = new InlineKeyboardButton();
        marriedButtton.setText("Married");
        marriedButtton.setCallbackData("User_is_Married");

        InlineKeyboardButton singleButton = new InlineKeyboardButton();
        singleButton.setText("Single");
        singleButton.setCallbackData("User_is_Single");

        inlineKeyboardButtons.add(marriedButtton);
        inlineKeyboardButtons.add(singleButton);

        inlineKeyboardButtonsList.add(inlineKeyboardButtons);
        inlineKeyboardMarkup.setKeyboard(inlineKeyboardButtonsList);
        message.setReplyMarkup(inlineKeyboardMarkup);

        switch (messageText){
            case "Married":
                boolean isMarried = true;
                sendMessage(chatId, QUESTION_ABOUT_SALARY_OF_PARTNER);
                moreOrLessSalary(chatId, messageText);
                break;


            case "Single":
                isMarried = false;
                usersChild(chatId, messageText);
                break;
        }

    }
    public void moreOrLessSalary(long chatId, String messageText){
        switch (messageText){
            case "More":
                boolean usersSalaryMore = true;
                usersChild(chatId, messageText);
                break;

            case "Less":
                usersSalaryMore = false;
                usersChild(chatId, messageText);
                break;

            case "Same":
                boolean usersSalarySame = true;
                usersChild(chatId, messageText);
                break;
        }

    }

    public void usersChild(long chatId, String messageText){
        sendMessage(chatId, QUESTION_ABOUT_CHILDREN);
        switch (messageText){
            case "Yes":
                boolean children = true;
                usersExtraWork(chatId, messageText);
                break;
            case "No":
                children = false;
                usersExtraWork(chatId, messageText);
                break;
        }
    }

    public void usersExtraWork (long chatId, String messageText){
        sendMessage(chatId, QUESTION_ABOUT_MINIJOB);
        switch (messageText){
            case "Yes":
                boolean extraWork = true;
                sendMessage(chatId, QUESTION_ABOUT_SALARY_BY_MINIJOB);
                double salaryByMinijob = Integer.parseInt(messageText);
                usersVisitsToChurch(chatId,messageText);
                break;
            case "No":
                extraWork = false;
                usersVisitsToChurch(chatId,messageText);
                break;
        }

    }
    public void usersVisitsToChurch(long chatId, String messageText){
        sendMessage(chatId, QUESTION_ABOUT_CHURCH);
        switch (messageText){
            case "Yes":
                boolean visitChurch = true;
                usersMedicalInsurace(chatId, messageText);
                break;
            case "No":
                visitChurch = false;
                usersMedicalInsurace(chatId, messageText);
                break;
        }

    }

    public void usersMedicalInsurace(long chatId, String messageText){
        sendMessage(chatId, QUESTION_ABOUT_MEDICAL_INSURACE);
        switch (messageText){
            case "private":
                boolean publicInsurace = false;
                usersSalary(chatId, messageText);
                break;
            case "public":
                publicInsurace = true;
                usersSalary(chatId, messageText);
                break;
        }

    }

    public void usersSalary(long chatId, String messageText){
        sendMessage(chatId, QUESTION_ABOUT_SALARY);
        double usersSalaryData = Integer.parseInt(messageText);
        usersProfession(chatId, messageText);
    }
    public void usersProfession(long chatId, String messageText){
        sendMessage(chatId, QUESTION_ABOUT_PROFESSION);
        String usersProfession = messageText;
    }
    private void usersTaxGroup1(boolean isMarried, boolean children, long chatId, double usersSalaryData){
    if((isMarried=false) && (children=false)){
        boolean usersTaxClass1 = true;
        sendMessage(chatId, TAX_CLASS1);
        countTaxesFromSalary1(chatId, usersSalaryData, children);
    }
    }
    private void usersTaxGroup2(boolean isMarried, boolean children, long chatId, double usersSalaryData){
        if((isMarried=false) && (children=true)){
            boolean usersTaxClass2 = true;
            sendMessage(chatId, TAX_CLASS2);
            countTaxesFromSalary2(chatId, usersSalaryData);
        }
    }
    private void usersTaxGroup3(boolean isMarried, long chatId, boolean usersSalaryMore, double usersSalaryData){
        if((isMarried=true) && (usersSalaryMore = true) ){
            boolean usersTaxClass3 = true;
            sendMessage(chatId, TAX_CLASS3);
            countTaxesFromSalary3and5(chatId, usersSalaryData);
        }
    }private void usersTaxGroup4(boolean isMarried, boolean usersSalarySame, long chatId, boolean children, double usersSalaryData){
        if((isMarried=true) && (usersSalarySame = true)){
            boolean usersTaxClass4 = true;
            sendMessage(chatId, TAX_CLASS4);
            countTaxesFromSalary4(chatId, usersSalaryData, children);
        }
    }
    private void usersTaxGroup5(boolean isMarried, boolean usersSalaryMore, long chatId, double usersSalaryData){
        if((isMarried=true) && (usersSalaryMore=false)){
            boolean usersTaxClass5 = true;
            sendMessage(chatId, TAX_CLASS5);
            countTaxesFromSalary3and5(chatId, usersSalaryData);
        }
    }
    private void usersTaxGroup6(boolean extraWork, long chatId, double usersSalaryData){
        if(extraWork = true){
            boolean usersTaxClass6 = true;
            sendMessage(chatId, TAX_CLASS6);
            countTaxesFromExtraworkSalary(chatId, usersSalaryData);
        }
    }

    private void countTaxesFromSalary1 (long chatId, double usersSalaryData, boolean children){
       double solid_fromSalary1 = (usersSalaryData * solidatitatSteuerPercent) / 100;
       double kirch_fromSalary1 = (usersSalaryData * kirchSteuerPercent) / 100;
       double renteVer_fromSalary1 = (usersSalaryData * renteVersicherungPercent) / 100;
       double arbeitlosVer_fromSalary1 = (usersSalaryData * arbeitlosVersicherungPercent) / 100;
       double lohnSteuer_fromSalary1 = (usersSalaryData * lohnSteuerPercentBis58thausend ) /100;
       double krankVer_fromSalary1 = (usersSalaryData * 7.3) / 100;
        if(children = true){
            double pflegeWithChildren_fromSalary1 = (usersSalaryData * 1.7) / 100;
            double amountOfTaxesWithChildren1 = solid_fromSalary1 + kirch_fromSalary1 + renteVer_fromSalary1 + arbeitlosVer_fromSalary1 + krankVer_fromSalary1 + pflegeWithChildren_fromSalary1 + lohnSteuer_fromSalary1;
            double nettoSalaryWithChildren1 = usersSalaryData - amountOfTaxesWithChildren1;
            String theEndOfCount_class1_withChild = "Your brutto-slary is " + usersSalaryData + " and your netto-salary is " + nettoSalaryWithChildren1 + ".";
            sendMessage(chatId, theEndOfCount_class1_withChild);
        } else if (children = false) {
            double pflegeWithoutChildren_fromSalary1 = (usersSalaryData * (1.7 + 0.35)) / 100;
            double amountOfTaxesWithoutChildren1 = solid_fromSalary1 + kirch_fromSalary1 + renteVer_fromSalary1 + arbeitlosVer_fromSalary1 + krankVer_fromSalary1 + pflegeWithoutChildren_fromSalary1 + lohnSteuer_fromSalary1;
            double nettoSalaryWithoutChildren1 = usersSalaryData - amountOfTaxesWithoutChildren1;
            String theEndOfCount_class1_withoutChild = "Your brutto-slary is " + usersSalaryData + " and your netto-salary is " + nettoSalaryWithoutChildren1 + ".";
            sendMessage(chatId, theEndOfCount_class1_withoutChild);

        }

    }
    private void countTaxesFromSalary2 (long chatId, double usersSalaryData){
        double solid_fromSalary2 = (usersSalaryData * solidatitatSteuerPercent) / 100;
        double kirch_fromSalary2 = (usersSalaryData * kirchSteuerPercent) / 100;
        double renteVer_fromSalary2 = (usersSalaryData * renteVersicherungPercent) / 100;
        double arbeitlosVer_fromSalary2 = (usersSalaryData * arbeitlosVersicherungPercent) / 100;
        double lohnSteuer_fromSalary2 = (usersSalaryData * lohnSteuerPercentBis58thausend ) /100;
        double krankVer_fromSalary2 = (usersSalaryData * 7.3) / 100;
        double pflegeWithChildren_fromSalary2 = (usersSalaryData * 1.525) / 100;
        double amountOfTaxes2 = solid_fromSalary2 + kirch_fromSalary2 + renteVer_fromSalary2 + arbeitlosVer_fromSalary2 + lohnSteuer_fromSalary2 + krankVer_fromSalary2 + pflegeWithChildren_fromSalary2;
        double nettoSalary2 = usersSalaryData - amountOfTaxes2;
        String theEndOfCount_class2 = "Your brutto-slary is " + usersSalaryData + " and your netto-salary is " + nettoSalary2 + ".";
        sendMessage(chatId, theEndOfCount_class2);
        }
    private void countTaxesFromSalary3and5(long chatId, double usersSalaryData){
        double taxesFromSalary3 = (usersSalaryData * 31.8) / 100;
        double nettoSalary3 = usersSalaryData - taxesFromSalary3;
        String theEndOfCount_class3 = "Your brutto-slary is " + usersSalaryData + " and your netto-salary is " + nettoSalary3 + ".";
        sendMessage(chatId, theEndOfCount_class3);
    }

    private void countTaxesFromSalary4 (long chatId, double usersSalaryData, boolean children){

        double kirch_fromSalary4 = (usersSalaryData * kirchSteuerPercent) / 100;
        double renteVer_fromSalary4 = (usersSalaryData * 18.6) / 100;
        double arbeitlosVer_fromSalary4 = (usersSalaryData * 2.5) / 100;
        double krankVer_fromSalary4 = (usersSalaryData * 14.6) / 100;
        if(children = true){
            double pflegeWithChildren_fromSalary4 = (usersSalaryData * 3.05) / 100;
            double amountOfTaxesWithChildren4 = kirch_fromSalary4 + renteVer_fromSalary4 + arbeitlosVer_fromSalary4 + krankVer_fromSalary4 + pflegeWithChildren_fromSalary4;
            double nettoSalaryWithChildren4 = usersSalaryData - amountOfTaxesWithChildren4;
            String theEndOfCount_class4_withChild = "Your brutto-slary is " + usersSalaryData + " and your netto-salary is " + nettoSalaryWithChildren4 + ".";
            sendMessage(chatId, theEndOfCount_class4_withChild);
        } else if (children = false) {
            double pflegeWithoutChildren_fromSalary4 = (usersSalaryData * (3.05 + 0.35)) / 100;
            double amountOfTaxesWithoutChildren4 = kirch_fromSalary4 + renteVer_fromSalary4 + arbeitlosVer_fromSalary4 + krankVer_fromSalary4 + pflegeWithoutChildren_fromSalary4;
            double nettoSalaryWithoutChildren4 = usersSalaryData - amountOfTaxesWithoutChildren4;
            String theEndOfCount_class4_withoutChild = "Your brutto-slary is " + usersSalaryData + " and your netto-salary is " + nettoSalaryWithoutChildren4 + ".";
            sendMessage(chatId, theEndOfCount_class4_withoutChild);
        }

    }
    private void countTaxesFromExtraworkSalary (long chatId, double salaryByMinijob){
        if(salaryByMinijob > 520){
            double taxesFromExtraWorkSalary = salaryByMinijob / 2;
            String theEndOfCount_class6_more520 = "Your brutto-slary of extra work is " + salaryByMinijob + " and your netto-salary is " + taxesFromExtraWorkSalary + ".";
            sendMessage(chatId, theEndOfCount_class6_more520);
        }else {
           double nettoSalary6 = salaryByMinijob;
           String theEndOfCount_class6 = "Your salary is less, than 520 euro and you will get " + nettoSalary6 + ".";
            sendMessage(chatId, theEndOfCount_class6);
        }
    }

}




