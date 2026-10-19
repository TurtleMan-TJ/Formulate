package radhakrishnan.tijeshudit;

//Import Section
import java.time.LocalDate;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class GUI extends Application {
    //Variables needed for this APP
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage){
        /**************************************************************************************************************************/
        /**************************************************************************************************************************/
        //This makes it so that the window doesnt have the default minimize, maximize, and close buttons, and the title bar is removed. This allows for a custom close button to be added.
        stage.initStyle(StageStyle.UNDECORATED);//I also want this to be a rounded 
        
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);
        scene.setFill(Color.TRANSPARENT);//This makes the background of the window transparent, so that the rounded corners can be seen.

        Rectangle clip = new Rectangle(800, 600);
        clip.setArcWidth(36);//This sets the width of the rounded corners to 36 pixels.
        clip.setArcHeight(36);//And this likewise
        root.setClip(clip);
        //This enables the rounded corners to be maintained when the window is resized, by adding a listener to the width and height properties of the root node.
        root.widthProperty().addListener((obs, oldVal, newVal) ->
            clip.setWidth(newVal.doubleValue())
        );

        root.heightProperty().addListener((obs, oldVal, newVal) ->
            clip.setHeight(newVal.doubleValue())
        );
        //However, I want to move the window around, so I am going write logic that allows the user to click and drag the window around. This will be done by adding a mouse event listener to the root node of the scene.
        root.setOnMousePressed(event -> {
            //Store the initial position of the mouse when it is pressed
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            //Calculate the new position of the window based on the mouse movement
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
        root.setBackground(new Background(new BackgroundFill(Color.web("#d2c179"), new CornerRadii(18, 18, 0, 0, false), Insets.EMPTY)));
        /**************************************************************************************************************************/
        /**************************************************************************************************************************/
        
        //I am goint separate the window into a title bar and the content area where the buttons will be added.
        VBox titleBar = new VBox();
        titleBar.setPadding(new Insets(10));//This adds padding to the title bar so that the close button is not right up against the edge of the window.
        HBox contentArea = new HBox();

        //Without the default close button, the window will be , so a custome X button will be added to the window.
        Button closeButton = new Button("X");
        closeButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 5px 10px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        closeButton.setOnMouseEntered(event -> closeButton.setStyle("-fx-background-color: darkred; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 5px 10px; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        closeButton.setOnMouseExited(event -> closeButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 5px 10px; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        closeButton.setOnMousePressed(event -> closeButton.setStyle("-fx-background-color: darkred; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 5px 10px; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        closeButton.setOnAction(event -> stage.close());

        //Now time to add the Log In Button and the Create Account Button to the content area, and style them so that they are more visually appealing.
        Button loginButton = new Button("Log In"); //These buttons should be the same size and have the same styling
        loginButton.setPrefWidth(200);
        loginButton.setStyle("-fx-background-color: #008CBA; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10px 20px; -fx-border-radius: 5px; -fx-background-radius: 20px;");
        loginButton.setOnMouseEntered(event -> loginButton.setStyle("-fx-background-color: #007bb5; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10px 20px; -fx-border-radius: 5px; -fx-background-radius: 20px;"));
        loginButton.setOnMouseExited(event -> loginButton.setStyle("-fx-background-color: #008CBA; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10px 20px; -fx-border-radius: 5px; -fx-background-radius: 20px;"));
        Button createAccountButton = new Button("Create Account");
        createAccountButton.setPrefWidth(200);
        createAccountButton.setStyle("-fx-background-color: #008CBA; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10px 20px; -fx-border-radius: 5px; -fx-background-radius: 20px;");
        createAccountButton.setOnMouseEntered(event -> createAccountButton.setStyle("-fx-background-color: #007bb5; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10px 20px; -fx-border-radius: 5px; -fx-background-radius: 20px;"));
        createAccountButton.setOnMouseExited(event -> createAccountButton.setStyle("-fx-background-color: #008CBA; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10px 20px; -fx-border-radius: 5px; -fx-background-radius: 20px;"));

        //Because I want the Log in button and the Create button to be side bt side, I will add them to the content area, and then add the content area to the root node of the scene.
        contentArea.getChildren().addAll(loginButton, createAccountButton);
        contentArea.setAlignment(Pos.CENTER);
        contentArea.setSpacing(50);//This adds spacing between the buttons so that they are not right up against each other.
        root.setCenter(contentArea);
        titleBar.getChildren().add(closeButton);
        titleBar.setAlignment(Pos.CENTER_RIGHT);
        root.setTop(titleBar);
        /**************************************************************************************************************************/
        /**************************************************************************************************************************/
        loginButton.setOnAction(event -> showLoginPage());
        createAccountButton.setOnAction(event -> showCreateAccountPage());

        stage.setTitle("Login/Sign Up");
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }
    /**************************************************************************************************************************/
    /**************************************************************************************************************************/

    //I also need to give these two buttons two different pages; this will be done by giving them two classes
    //The reason why they are two different methods is because the Log In page and the Create Account page will have different functionality, and I want to keep the code organized.
    private void showLoginPage(){
        VBox loginPage = new VBox(); //This Vbox will be the size of the content area so that the "X" button will be there 
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        loginPage.getChildren().addAll(usernameField, passwordField);
    }
    private void showCreateAccountPage(){ //This page should consist of a username field, a password field, and a confirm password field, DOB, and a create account button. The create account button should be disabled until the password and confirm password fields match.
        VBox createAccountPage = new VBox();
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirm Password");
        DatePicker dobPicker = new DatePicker();
        dobPicker.setPromptText("Date of Birth");
        dobPicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.isAfter(LocalDate.now()));
            }
        });        
        Button createAccountButton = new Button("Create Account");
        createAccountButton.setDisable(true);
        createAccountPage.getChildren().addAll(usernameField, passwordField, confirmPasswordField, dobPicker, createAccountButton);
    }
}
