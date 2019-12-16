package org.openjfx;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PrimaryController implements Initializable {

    @FXML private Label label;
    @FXML private Label labelLoginName = new Label();
    @FXML private TextField textFieldLogIn, textFieldIdSHow, textFieldBalanceSHow,  textFieldBankSHow, textFieldFirstSHow, textFieldLastSHow,
            textFieldAgeSHow, textFieldIdAdd, textFieldBalanceAdd,  textFieldBankAdd, textFieldFirstAdd, textFieldLastAdd,
            textFieldAgeAdd, textFieldAmountTransfer, textFieldAccountIdTransfer, textFieldFilter;
    @FXML private TextArea textAreaTransferMessage;
    @FXML private RadioButton radioButtonDeposit,radioButtonWithDraw,radioButtonTransfer, radioButtonAgeFilter, radioButtonBankFilter, radioButtonNameFilter, radioButtonIdFilter;
    @FXML private Button buttonShow, logOutButton, buttonAdd, buttonOperations, buttonPrintOut, filterButton;
    @FXML private ListView<String> listView = new ListView<>();
    @FXML private TableView<Account> tableView = new TableView<>();
    @FXML private TableColumn<Account, Integer> idCol = new TableColumn<>();
    @FXML private TableColumn<Account, Float> balanceCol = new TableColumn<>();
    @FXML private TableColumn<Account, String> bankCol = new TableColumn<>();
    @FXML private TableColumn<Account, String> firstNameCol = new TableColumn<>();
    @FXML private TableColumn<Account, String> lastNameCol = new TableColumn<>();
    @FXML private TableColumn<Account, Integer> ageCol = new TableColumn<>();
    @FXML private ImageView searchImage, logInImage, logOutImageView, operationDoneId ,printImageId, saveImageId;
    private List<Account> accountList = new ArrayList<>();
    private List<String> operationsList = new ArrayList<>();




    public PrimaryController () {
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelLoginName.setText("");
        textFieldAccountIdTransfer.setVisible(false);
        logOutButton.setVisible(false);
        logOutImageView.setVisible(false);
        labelLoginName.setVisible(false);

        Button [] buttons = {buttonShow, logOutButton, buttonAdd, buttonOperations, buttonPrintOut, filterButton};
        for (Button button : buttons){
            button.setCursor(Cursor.HAND);
        }
        ImageView [] imageViews = {searchImage, logInImage, logOutImageView, operationDoneId ,printImageId, saveImageId};
        for (ImageView imageView : imageViews){
            imageView.setCursor(Cursor.HAND);
        }


        RadioButton[] radioButtons = {radioButtonDeposit, radioButtonWithDraw, radioButtonTransfer };
        ToggleGroup tools= new ToggleGroup();
        for (ToggleButton tool : radioButtons){
            tool.setToggleGroup(tools);
            tool.setCursor(Cursor.HAND);
        }
        RadioButton[] radioButtonsFilter = {radioButtonAgeFilter, radioButtonBankFilter, radioButtonNameFilter, radioButtonIdFilter};
        ToggleGroup group= new ToggleGroup();
        for (ToggleButton tool : radioButtonsFilter){
            tool.setToggleGroup(group);
            tool.setCursor(Cursor.HAND);
        }

        TextField [] textFieldAdd = {textFieldIdAdd, textFieldBalanceAdd,  textFieldBankAdd, textFieldFirstAdd, textFieldLastAdd, textFieldAgeAdd, textFieldAmountTransfer, textFieldAccountIdTransfer, textFieldFilter};
        for (TextField textField : textFieldAdd) {
            textField.setText("");
            textField.setStyle("-fx-text-fill: DarkBlue; ");
        }

        TextField [] textFieldShow = {textFieldIdSHow, textFieldBalanceSHow,  textFieldBankSHow, textFieldFirstSHow, textFieldLastSHow, textFieldAgeSHow};
        for (TextField textField : textFieldShow) {
            textField.setStyle("-fx-text-fill: blue;");

        }

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        balanceCol.setCellValueFactory(new PropertyValueFactory<>("balance"));
        bankCol.setCellValueFactory(bankName -> new ReadOnlyObjectWrapper<>( bankName.getValue().getBank().getName()));
        firstNameCol.setCellValueFactory(firstName -> new ReadOnlyObjectWrapper<>( firstName.getValue().getPerson().getFirstName()));
        lastNameCol.setCellValueFactory(lastNameGet -> new ReadOnlyObjectWrapper<>(lastNameGet.getValue().getPerson().getLastName()));
        ageCol.setCellValueFactory(ageGet ->  new ReadOnlyObjectWrapper<>(ageGet.getValue().getPerson().getAge()));
        createAccount();

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
    private void logIn(){
        if (!textFieldIdSHow.getText().equals("")){
            textFieldLogIn.setVisible(false);
            buttonShow.setVisible(false);
            logInImage.setVisible(false);
        }
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
    private void operation() {
        buttonOperations.setOnAction(actionEvent -> {
            Operations operations = new Operations();

            if (radioButtonDeposit.isSelected() && !textFieldAmountTransfer.getText().equals("")){
                int id = Integer.parseInt(textFieldIdSHow.getText());
                float amount = Float.parseFloat(textFieldAmountTransfer.getText());
                String message = textAreaTransferMessage.getText();
                operations.deposit(id, amount, accountList, message, operationsList);

                if (listView.getItems().size()>= 1){
                    listView.getItems().clear();
                    for (String str : operationsList){
                        listView.getItems().add(str);
                    }
                }else {
                    for (String str : operationsList){
                        listView.getItems().add(str);
                    }
                }
                textFieldAmountTransfer.setText("");
                textAreaTransferMessage.setText("");
                textAreaTransferMessage.setPromptText("Your message here ...");
                List<Account> filterList = accountList.stream()
                        .filter(e -> e.getId() == id)
                        .collect(Collectors.toList());
                textFieldBalanceSHow.setText(String.valueOf(filterList.get(0).getBalance()));

            }else if (radioButtonWithDraw.isSelected() && !textFieldAmountTransfer.getText().equals("")){
                int id = Integer.parseInt(textFieldIdSHow.getText());
                float amount = Float.parseFloat(textFieldAmountTransfer.getText());
                String message = textAreaTransferMessage.getText();
                operations.withdraw(id, amount, accountList, message, operationsList);
                if (listView.getItems().size()>= 1){
                    listView.getItems().clear();
                    for (String str : operationsList){
                        listView.getItems().add(str);
                    }
                }else {
                    for (String str : operationsList){
                        listView.getItems().add(str);
                    }
                }
                textFieldAmountTransfer.setText("");
                textAreaTransferMessage.setText("");
                textAreaTransferMessage.setPromptText("Your message here ...");
                List<Account> filterList = accountList.stream()
                        .filter(e -> e.getId() == id)
                        .collect(Collectors.toList());
                textFieldBalanceSHow.setText(String.valueOf(filterList.get(0).getBalance()));

            }else if (radioButtonTransfer.isSelected() && !textFieldAmountTransfer.getText().equals("") && !textFieldAccountIdTransfer.getText().equals("")){
                int idFrom = Integer.parseInt(textFieldIdSHow.getText());
                float amount = Float.parseFloat(textFieldAmountTransfer.getText());
                int idTo = Integer.parseInt(textFieldAccountIdTransfer.getText());
                String message = textAreaTransferMessage.getText();
                operations.transfer(idFrom, idTo, amount, accountList,message, operationsList);
                if (listView.getItems().size()>= 1){
                    listView.getItems().clear();
                    for (String str : operationsList){
                        listView.getItems().add(str);
                    }
                }else {
                    for (String str : operationsList){
                        listView.getItems().add(str);
                    }
                }
                textFieldAmountTransfer.setText("");
                textFieldAccountIdTransfer.setText("");
                textAreaTransferMessage.setText("");
                textAreaTransferMessage.setPromptText("Your message here ...");
                List<Account> filterList = accountList.stream()
                        .filter(e -> e.getId() == idFrom)
                        .collect(Collectors.toList());
                textFieldBalanceSHow.setText(String.valueOf(filterList.get(0).getBalance()));

            }

        });
    }

    @FXML
    private void selectAccount(MouseEvent mouseEvent){
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
                    labelLoginName.setText(" Welcome:"+" "+filterList.get(0).getPerson().getFirstName()+" "+filterList.get(0).getPerson().getLastName());
                    logOutButton.setVisible(true);
                    logOutImageView.setVisible(true);
                    labelLoginName.setVisible(true);
                    textFieldLogIn.setText("");
                    logIn();
                }else {
                    wrongId(textFieldLogIn.getText());
                    textFieldLogIn.setText("");
                }
            } catch (NumberFormatException e) {
                wrongIdFormat(textFieldLogIn.getText());
                textFieldLogIn.setText("");
            }
    }
    @FXML
    private void logOut(ActionEvent actionEvent) {
        TextField [] textFieldShow = {textFieldIdSHow, textFieldBalanceSHow,  textFieldBankSHow, textFieldFirstSHow, textFieldLastSHow, textFieldAgeSHow};
        for (TextField textField : textFieldShow) {
            textField.setText("");
            textField.setPromptText("-");
        }
        if (listView.getItems().size() >=1){
                listView.getItems().clear();
        }
        labelLoginName.setText("");
        logOutButton.setVisible(false);
        logOutImageView.setVisible(false);
        labelLoginName.setVisible(false);
        textFieldLogIn.setVisible(true);
        buttonShow.setVisible(true);
        logInImage.setVisible(true);


    }
    @FXML
    private void filter(){
        filterButton.setOnAction(actionEvent -> {
            if (radioButtonAgeFilter.isSelected() && !textFieldFilter.getText().equals("")){
                try {
                    int age = Integer.parseInt(textFieldFilter.getText());
                    List<Account> filterList = accountList.stream().filter(e -> e.getPerson().getAge() == age).collect(Collectors.toList());
                    ObservableList<Account> data = FXCollections.observableArrayList();
                            for (Account account : filterList){
                                data.add(new Account(account.getId(), account.getBalance(), new Bank(account.getBank().getName()),new Person(account.getPerson().getFirstName(), account.getPerson().getLastName(), account.getPerson().getAge())));
                            }
                    tableView.setItems(data);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }else if (radioButtonBankFilter.isSelected()){
                String bank = textFieldFilter.getText();
                List<Account> filterList = accountList.stream().filter(e -> e.getBank().getName().startsWith(bank)).collect(Collectors.toList());
                ObservableList<Account> data = FXCollections.observableArrayList();
                for (Account account : filterList){
                    data.add(new Account(account.getId(), account.getBalance(), new Bank(account.getBank().getName()),new Person(account.getPerson().getFirstName(), account.getPerson().getLastName(), account.getPerson().getAge())));
                }
                tableView.setItems(data);
            } else if (radioButtonNameFilter.isSelected() && !textFieldFilter.getText().equals("")){
                String name = textFieldFilter.getText();
                List<Account> filterList = accountList.stream().filter(e -> e.getPerson().getLastName().startsWith(name)).collect(Collectors.toList());
                ObservableList<Account> data = FXCollections.observableArrayList();
                for (Account account : filterList){
                    data.add(new Account(account.getId(), account.getBalance(), new Bank(account.getBank().getName()),new Person(account.getPerson().getFirstName(), account.getPerson().getLastName(), account.getPerson().getAge())));
                }
                tableView.setItems(data);
            }else if (radioButtonIdFilter.isSelected() && !textFieldFilter.getText().equals("")){
                try {
                    int id = Integer.parseInt(textFieldFilter.getText());
                    List<Account> filterList = accountList.stream().filter(e -> e.getId() == id).collect(Collectors.toList());
                    ObservableList<Account> data = FXCollections.observableArrayList();
                    for (Account account : filterList){
                        data.add(new Account(account.getId(), account.getBalance(), new Bank(account.getBank().getName()),new Person(account.getPerson().getFirstName(), account.getPerson().getLastName(), account.getPerson().getAge())));
                    }
                    tableView.setItems(data);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
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

    @FXML
    public void saveTextFile() {
        buttonPrintOut.setOnAction(actionEvent -> {
            int counter = 0;
            if (!textFieldIdSHow.getText().equals("")) {
                        int id = Integer.parseInt(textFieldIdSHow.getText());
                        List<Account> filterList = accountList.stream()
                                .filter(e -> e.getId() == id)
                                .collect(Collectors.toList());
                        int index = accountList.indexOf(filterList.get(0));
                        String path = System.getProperty("user.home") +
                                File.separator + "Documents" +
                                File.separator + "CustomFolder";

                        File dir = new File(path);

                        if (dir.exists())
                            System.out.println("Folder exist");
                        else if (dir.mkdir())
                            System.out.println("Folder created");
                        else
                            System.out.println("Folder not created");

                        File filePath = new File(path + File.separator + "Kvittot.txt");
                        try (FileWriter out = new FileWriter(filePath + ".txt")) {
                            out.write("--------------------------------------------------------------------------------------" + "\n");
                            out.write("AccountID :" + id + "  " + "|" + " " + "Firstname :" + accountList.get(index).getPerson().getFirstName() + "  " + "|" + " " + "Lastname :" + accountList.get(index).getPerson().getLastName() + "\n");

                            out.write("--------------------------------------------------------------------------------------" + "\n");
                            for (String str : operationsList) {
                                counter++;
                                out.write("Operation number : " + counter + " | " + str + "\n");
                            }
                            out.write("--------------------------------------------------------------------------------------" + "\n");
                            out.write("Amount available :" + accountList.get(index).getBalance() + "kr" + "\n");
                            out.write("--------------------------------------------------------------------------------------" + "\n");
                        } catch (IOException ex) {
                            System.out.println("Error!");
                        }
            }
        });

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
