package org.openjfx;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

public class PrimaryController implements Initializable {

    @FXML private Label label;
    @FXML private TextField textFieldLogIn, textFieldIdSHow, textFieldBalanceSHow,  textFieldBankSHow, textFieldFirstSHow, textFieldLastSHow,
            textFieldAgeSHow, textFieldIdAdd, textFieldBalanceAdd,  textFieldBankAdd, textFieldFirstAdd, textFieldLastAdd,
            textFieldAgeAdd, textFieldAmountTransfer, textFieldAccountIdTransfer;
    @FXML private TextArea textAreaTransferMessage;
    @FXML private RadioButton radioButtonDeposit,radioButtonWithDraw,radioButtonTransfer;
    @FXML private Button buttonShow, logOutButton, buttonAdd, buttonOperations, buttonPrintOut;
    @FXML private ImageView logOutImageView;
    @FXML private ListView<String> listView = new ListView<>();
    private List<Account> accountList = new ArrayList<>();
    private List<String> operationsList = new ArrayList<>();




    public PrimaryController () {
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textFieldAccountIdTransfer.setVisible(false);
        logOutButton.setVisible(false);
        logOutImageView.setVisible(false);

        RadioButton[] radioButtons = {radioButtonDeposit, radioButtonWithDraw, radioButtonTransfer };
        ToggleGroup tools= new ToggleGroup();
        for (ToggleButton tool : radioButtons){
            tool.setToggleGroup(tools);
            tool.setCursor(Cursor.HAND);
        }

        TextField [] textFieldAdd = {textFieldIdAdd, textFieldBalanceAdd,  textFieldBankAdd, textFieldFirstAdd, textFieldLastAdd, textFieldAgeAdd, textFieldAmountTransfer, textFieldAccountIdTransfer};
        for (TextField textField : textFieldAdd) {
            textField.setText("");
            textField.setStyle("-fx-text-fill: DarkBlue; ");
        }

        TextField [] textFieldShow = {textFieldIdSHow, textFieldBalanceSHow,  textFieldBankSHow, textFieldFirstSHow, textFieldLastSHow, textFieldAgeSHow};
        for (TextField textField : textFieldShow) {
            textField.setStyle("-fx-text-fill: blue;");

        }
    }
    @FXML
    private void transferIsSelected(ActionEvent actionEvent) {
        textFieldAccountIdTransfer.setVisible(true);
    }
    @FXML
    private void depositIsSelected(ActionEvent actionEvent) {
        textFieldAccountIdTransfer.setVisible(false);
    }
    @FXML
    private void withDrawIsSelected(ActionEvent actionEvent) {
        textFieldAccountIdTransfer.setVisible(false);
    }
    @FXML
    private void addNewAccount(){
        buttonAdd.setOnAction(actionEvent -> {
            int id;
            int age;
            float balance;
            if (!textFieldIdAdd.getText().equals("") && !textFieldBalanceAdd.getText().equals("") && !textFieldBankAdd.getText().equals("") && !textFieldFirstAdd.getText().equals("")
                    && !textFieldLastAdd.getText().equals("") && !textFieldAgeAdd.getText().equals("")){
                try {
                    id = Integer.parseInt(textFieldIdAdd.getText());
                    try {
                        age = Integer.parseInt(textFieldAgeAdd.getText());
                        try {
                            balance = Float.parseFloat(textFieldBalanceAdd.getText());
                            List<Account> filterList = accountList.stream()
                                    .filter(e -> e.getId() == id)
                                    .collect(Collectors.toList());
                            if (filterList.size() == 0){
                                accountList.add(new Account(id,balance, new Bank(textFieldBankAdd.getText()), new Person(textFieldFirstAdd.getText(), textFieldLastAdd.getText(), age)));
                                confirmation();
                                textFieldIdAdd.setText("");
                                textFieldBalanceAdd.setText("");
                                textFieldBankAdd.setText("");
                                textFieldFirstAdd.setText("");
                                textFieldLastAdd.setText("");
                                textFieldAgeAdd.setText("");
                            }
                            else {
                                idDuplicated(textFieldIdAdd.getText());
                                textFieldIdAdd.setText("");
                            }
                        } catch (NumberFormatException e) {
                            balanceShouldBeFloat();
                            textFieldBalanceAdd.setText("");
                        }
                    } catch (NumberFormatException e) {
                        ageShouldBeInt();
                    }

                } catch (NumberFormatException e) {
                    idShouldBeInt();
                    textFieldIdAdd.setText("");
                }
            }
        });
    }
    @FXML
    private void opration() {
        buttonOperations.setOnAction(actionEvent -> {
            Operations operations = new Operations();

            if (radioButtonDeposit.isSelected() && !textFieldAmountTransfer.getText().equals("")){
                int id = Integer.parseInt(textFieldIdSHow.getText());
                float amount = Float.parseFloat(textFieldAmountTransfer.getText());
                String message = textAreaTransferMessage.getText();
                operations.deposit(id, amount, accountList);
                operationsList.add("[ Deposit ] - Amount :"+amount+"  "+"  Message :"+message);
                listView.getItems().add("[ Deposit ] - Amount :"+amount+"  "+"  Message :"+message);

                //empty TextField
                textFieldAmountTransfer.setText("");
                textAreaTransferMessage.setText("Your message here ...");

                //------ToShow
                List<Account> filterList = accountList.stream()
                        .filter(e -> e.getId() == id)
                        .collect(Collectors.toList());
                textFieldBalanceSHow.setText(String.valueOf(filterList.get(0).getBalance()));

            }else if (radioButtonWithDraw.isSelected() && !textFieldAmountTransfer.getText().equals("")){
                int id = Integer.parseInt(textFieldIdSHow.getText());
                float amount = Float.parseFloat(textFieldAmountTransfer.getText());
                String message = textAreaTransferMessage.getText();
                operations.withdraw(id, amount, accountList);
                operationsList.add("[ Withdraw ] - Amount :"+amount+"  "+"  Message :"+message);
                listView.getItems().add("[ Withdraw ] - Amount :"+amount+"  "+"  Message :"+message);

                //empty TextField
                textFieldAmountTransfer.setText("");
                textAreaTransferMessage.setText("Your message here ...");

                //------ToShow
                List<Account> filterList = accountList.stream()
                        .filter(e -> e.getId() == id)
                        .collect(Collectors.toList());
                textFieldBalanceSHow.setText(String.valueOf(filterList.get(0).getBalance()));

            }else if (radioButtonTransfer.isSelected() && !textFieldAmountTransfer.getText().equals("") && !textFieldAccountIdTransfer.getText().equals("")){
                int idFrom = Integer.parseInt(textFieldIdSHow.getText());
                float amount = Float.parseFloat(textFieldAmountTransfer.getText());
                int idTo = Integer.parseInt(textFieldAccountIdTransfer.getText());
                String message = textAreaTransferMessage.getText();
                operations.transfer(idFrom, idTo, amount, accountList);
                operationsList.add("[ Transfer ] - Amount :"+amount+"  "+"To AccountID :"+idTo+"  "+" Message :"+ message);
                listView.getItems().add("[ Transfer ] - Amount :"+amount+"  "+"To AccountID :"+idTo+"  "+" Message :"+ message);

                //empty TextField
                textFieldAmountTransfer.setText("");
                textFieldAccountIdTransfer.setText("");
                textAreaTransferMessage.setText("Your message here ...");

                //------ToShow
                List<Account> filterList = accountList.stream()
                        .filter(e -> e.getId() == idFrom)
                        .collect(Collectors.toList());
                textFieldBalanceSHow.setText(String.valueOf(filterList.get(0).getBalance()));

            }

        });
    }

    @FXML
    private void selectAccount(){
        buttonShow.setOnAction(actionEvent -> {
            try {
                int id = Integer.parseInt(textFieldLogIn.getText());
                List<Account> filterList = accountList.stream()
                        .filter(e -> e.getId() == id)
                        .collect(Collectors.toList());
                if (filterList.size() == 1){
                    textFieldIdSHow.setText(String.valueOf(filterList.get(0).getId()));
                    textFieldBalanceSHow.setText(String.valueOf(filterList.get(0).getBalance()));
                    textFieldBankSHow.setText(filterList.get(0).getBank().getName());
                    textFieldFirstSHow.setText(filterList.get(0).getPerson().getFirstName());
                    textFieldLastSHow.setText(filterList.get(0).getPerson().getLastName());
                    textFieldAgeSHow.setText(String.valueOf(filterList.get(0).getPerson().getAge()));
                    logOutButton.setVisible(true);
                    logOutImageView.setVisible(true);
                    textFieldLogIn.setText("");
                }else {
                    wrongId(textFieldLogIn.getText());
                    textFieldLogIn.setText("");
                }
            } catch (NumberFormatException e) {
                wrongIdFormat(textFieldLogIn.getText());
                textFieldLogIn.setText("");
            }
        });
    }

    private void idDuplicated(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("This ID ["+message+"] is already used!");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    private void idShouldBeInt() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("The ID should be digits!");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    private void ageShouldBeInt() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("The age should be digits!");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    private void balanceShouldBeFloat() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("The Balance should be digits!");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    private void confirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure? if YES click OK!");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    private void wrongId(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("There is no account has this ID :["+message+"]");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    private void wrongIdFormat(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("The ID contains digits!");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private void createAccount() {
        Person personA = new Person("FirstA","LastA",20);
        Person personB = new Person("FirstB","LastB",21);
        Person personC = new Person("FirstC","LastC",22);
        Person personD = new Person("FirstD","LastD",23);
        Person personE = new Person("FirstE","LastE",24);
        Person personF = new Person("FirstF","LastF",25);
        Person personG = new Person("FirstG","LastG",26);
        Bank bankA = new Bank("SEB");
        Bank bankB = new Bank("Swedbank");
        Bank bankC = new Bank("Handelsbanken");
        Bank bankD = new Bank("Nordea");
        accountList.add(new Account(1, 30000.0f, bankA, personA));
        accountList.add(new Account(2, 10000.0f, bankC, personB));
        accountList.add(new Account(3, 20000.0f, bankB, personC));
        accountList.add(new Account(4, 50000.0f, bankD, personD));
        accountList.add(new Account(5, 20000.0f, bankB, personE));
        accountList.add(new Account(6, 5000.0f, bankB, personF));
        accountList.add(new Account(7, 2000.0f, bankC, personF));
        accountList.add(new Account(8, 0.0f, bankC, personG));
        accountList.add(new Account(9, 1200000.0f, bankC, personG));
    }

}
