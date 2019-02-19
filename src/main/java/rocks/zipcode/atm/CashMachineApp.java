package rocks.zipcode.atm;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import rocks.zipcode.atm.bank.Bank;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;

/**
 * @author ZipCodeWilmington
 */
public class CashMachineApp extends Application {

    private TextField setIdArea = new TextField("");
    private TextField withdrawArea = new TextField("");
    private TextField depositeArea = new TextField("");
    private CashMachine cashMachine = new CashMachine(new Bank());

    private Parent createContent() {

        VBox vbox = new VBox(10);
        vbox.setPrefSize(1000, 600);

        TextArea accountInfo = new TextArea();
        TextArea depositeWindow = new TextArea();
        TextArea withdrawWindow = new TextArea();
        accountInfo.setPrefColumnCount(6);



        Button btnSubmit = new Button("Set Account ID");
        btnSubmit.setOnAction(e -> {
            int id = Integer.parseInt(setIdArea.getText());
            cashMachine.login(id);

            accountInfo.setText(cashMachine.toString());
        });

        Button btnDeposit = new Button("Deposit");
        btnDeposit.setOnAction(e -> {
            int amount = Integer.parseInt(depositeArea.getText());
            cashMachine.deposit(amount);

            depositeWindow.setText(cashMachine.toString());
        });

        Button btnWithdraw = new Button("Withdraw");
        btnWithdraw.setOnAction(e -> {
            int amount = Integer.parseInt(withdrawArea.getText());
            cashMachine.withdraw(amount);

            withdrawWindow.setText(cashMachine.toString());
        });

        Button btnLogout = new Button("Logout");
        btnLogout.setOnAction(e -> {
            cashMachine.exit();

            accountInfo.setText(cashMachine.toString());
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(10);

        GridPane.setConstraints(btnSubmit, 0, 0);
        GridPane.setConstraints(btnDeposit, 0, 2);
        GridPane.setConstraints(btnWithdraw, 0, 4);
        GridPane.setConstraints(btnLogout, 0, 6);


//        grid.addColumn(10);
//        grid.addRow(10);

        //grid.setGridLinesVisible(true);


        grid.getChildren().add(btnSubmit);
        grid.getChildren().add(btnDeposit);
        grid.getChildren().add(btnWithdraw);
        grid.getChildren().add(btnLogout);
        vbox.getChildren().addAll(setIdArea, grid, accountInfo);


        return vbox;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
