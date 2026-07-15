package game.gui;

import java.io.IOException;
import java.util.PriorityQueue;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import game.engine.Battle;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;
import game.engine.titans.AbnormalTitan;
import game.engine.titans.ArmoredTitan;
import game.engine.titans.PureTitan;
import game.engine.titans.Titan;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class Main extends Application implements EventHandler<ActionEvent> {

	private Battle battle;
	private Stage stageone;
	private Stage stagepopup;

	private AnchorPane anchor;
	private Label anchorlabel;
	private Button anchorButton;
	private AnchorPane anchorpopup;

	private Scene sceneone;
	private Scene scenepopup;
	private Button gamestart;
	private Pane[] titans;
	private Label instructions;
	private Scene easy;
	private Scene hard;

	private HBox laneweapons2;
	private HBox laneweapons1;
	private HBox laneweapons3;
	private HBox laneweapons4;
	private HBox laneweapons5;
	private HBox[] laneWeaponBoxes;
	private Label currentscore;
	private Label currentturn;
	private Label currentphase;
	private Label currentresources;

	// weaponshop info

	private MenuButton menuButton;
	private Button popup_ok;
	private Button popup_cancel;
	private RadioButton option1;
	private RadioButton option2;
	private RadioButton option3;
	private RadioButton option4;
	private RadioButton option5;
	private ImageView imageView;

	private Button PassTurn;

	private Label[] laneslabeld;
	private Label[] laneslabelh;

	private ToggleButton easyB;
	private ToggleButton hardB;
	private Label titleLabel;
	private MenuItem menuItem1;
	private MenuItem menuItem2;
	private MenuItem menuItem3;
	private MenuItem menuItem4;

	public void start(Stage stageone) {

		this.stageone = stageone;
		stageone.setTitle("AOT");
		stageone.getIcons()
				.add(new Image("file:///C:/Users/HP/OneDrive/Desktop/year%202/game.gui/src/game/gui/AOTmain.jpg"));

		anchor = new AnchorPane();
		sceneone = new Scene(anchor, 328, 600);
		stageone.setScene(sceneone);
		anchor.centerShapeProperty();
		stageone.show();
		easyB = new ToggleButton("Easy(3 lanes)");
		easyB.setPrefHeight(30);
		easyB.setPrefWidth(150);
		easyB.setLayoutX(10);
		easyB.setLayoutY(499);
		easyB.setStyle("-fx-background-color: transparent;-fx-text-fill: black;-fx-font-weight: bold");
		easyB.setFont(new Font("Times New Roman", 18));
		hardB = new ToggleButton("Hard(5 lanes)");
		hardB.setPrefHeight(30);
		hardB.setPrefWidth(150);
		hardB.setLayoutX(152);
		hardB.setLayoutY(499);
		hardB.setStyle("-fx-background-color: transparent;-fx-text-fill: black;-fx-font-weight: bold");
		hardB.setFont(new Font("Times New Roman", 18));
		easyB.setOnAction(this::handleEasyButton);
		hardB.setOnAction(this::handleHardButton);
		gamestart = new Button("Start");
		gamestart.setPrefHeight(30);
		gamestart.setPrefWidth(100);
		gamestart.setLayoutX(110);
		gamestart.setLayoutY(550);
		gamestart.setStyle("-fx-background-color: transparent;-fx-text-fill: black;-fx-font-weight: bold");
		gamestart.setFont(new Font("Times New Roman", 20));
		gamestart.setOnAction(this);
		titleLabel = new Label("SELECT YOUR MODE");
		titleLabel.setPrefHeight(0);
		titleLabel.setPrefWidth(200);
		titleLabel.setLayoutX(60);
		titleLabel.setLayoutY(100);
		titleLabel.setFont(new Font("Times New Roman", 20));
		titleLabel.setStyle("-fx-text-fill: white;");
		instructions = new Label("This is a tower defence game, your main goal is protect your lane walls\r\n"
				+ " from the approaching titans by buying weapons into the lanes to \r\n "
				+ "attack the titans present in it.");
		instructions.setPrefHeight(200);
		instructions.setPrefWidth(300);
		instructions.setLayoutX(10);
		instructions.setLayoutY(150);
		instructions.setFont(new Font("Times New Roman", 10));
		instructions.setStyle("-fx-text-fill: white;");
		Image backgroundImage = new Image(
				"file:///C:/Users/HP/OneDrive/Desktop/year%202/game.gui/src/game/gui/AOTmain.jpg");

		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

		Background backgroundObj = new Background(background);

		;
		anchor.setBackground(backgroundObj);

		anchor.getChildren().addAll(gamestart, titleLabel, easyB, hardB, instructions);

	}

	public void toggleButtons1(ToggleButton selected, ToggleButton unselected) {
		selected.setSelected(true);
		unselected.setSelected(false);
	}

	// Easy button handler
	public void handleEasyButton(ActionEvent event) {
		toggleButtons1(easyB, hardB);
		easyB.setStyle("-fx-background-color: green; -fx-text-fill: black;");
		hardB.setStyle("-fx-background-color: transparent;");
	}

	// Hard button handler
	public void handleHardButton(ActionEvent event) {
		toggleButtons1(hardB, easyB);
		hardB.setStyle("-fx-background-color: red; -fx-text-fill: black;");
		easyB.setStyle("-fx-background-color: transparent;");
	}

	public void handle(ActionEvent m) {
		if (m.getSource() == gamestart && (easyB.isSelected() || hardB.isSelected())) {
			anchor = new AnchorPane();
			Image backgroundImage = new Image(
					"file:///C:/Users/HP/OneDrive/Desktop/year%202/game.gui/src/game/gui/AOTm.jpg");

			BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

			Background backgroundObj = new Background(background);

			;
			anchor.setBackground(backgroundObj);

			if (easyB.isSelected()) {
				try {
					battle = new Battle(1, 0, 460, 3, 250);
					easy = new Scene(anchor, 970, 600);
					stageone.setScene(easy);
					stageone.centerOnScreen();
					build_easy(battle);
				} catch (IOException e) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setContentText("error");
					alert.showAndWait();
				}

			}
		}

		if (hardB.isSelected()) {
			try {

				battle = new Battle(1, 0, 460, 5, 125);
			} catch (IOException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("error");
				alert.showAndWait();
			}

			hard = new Scene(anchor, 1100, 600);
			stageone.setScene(hard);
			stageone.centerOnScreen();
			build_hard();

		}

	}

	public void is_game_over(Battle battle) {
		if (battle.isGameOver()) {
			anchor.getChildren().clear();
			anchor.setStyle("-fx-background-color: black;");
			anchorlabel = new Label("GAME OVER!");
			anchorlabel.setFont(new Font("Times New Roman", 30));
			anchorlabel.setStyle("-fx-background-color: transparent;-fx-text-fill: red ;-fx-font-weight: bold\"");
			anchorlabel.setPrefHeight(66);
			anchorlabel.setPrefWidth(296);
			anchorlabel.setLayoutX(338);
			anchorlabel.setLayoutY(267);
			anchorButton = new Button("close");
			anchorButton.setPrefHeight(66);
			anchorButton.setPrefWidth(135);
			anchorButton.setLayoutX(418);
			anchorButton.setLayoutY(367);
			anchorButton.setStyle("-fx-background-color: transparent;-fx-text-fill: red;-fx-font-weight: bold");
			anchorButton.setFont(new Font("Times New Roman", 25));
			anchorButton = new Button("close");
			anchorlabel.setPrefHeight(66);
			anchorlabel.setPrefWidth(135);
			anchorlabel.setLayoutX(418);
			anchorlabel.setLayoutY(367);
			anchorButton.setOnAction(event -> {
				anchor.getChildren().clear();
				stageone.close();
			});
			stageone.showAndWait();
		}
	}

//allignment of stage , titans ,    

	public void build_weaponshop(int mode, Battle battle) {
		menuButton = new MenuButton("Weapon shop");
		DropShadow shadow = new DropShadow();
		shadow.setColor(Color.DARKGRAY);

		menuButton.setPrefHeight(51);
		menuButton.setPrefWidth(120);
		menuButton.setLayoutX(810);
		menuButton.setLayoutY(21);
		menuButton.setStyle("-fx-background-color: transparent;-fx-text-fill: white;-fx-font-weight: bold");
		menuButton.setFont(new Font("Times New Roman", 18));
		menuButton.setEffect(shadow);
		if (mode == 5) {
			menuButton.setPrefHeight(51);
			menuButton.setPrefWidth(120);
			menuButton.setLayoutX(963);
			menuButton.setLayoutY(21);
			menuButton.setStyle("-fx-background-color: transparent;-fx-text-fill: white;-fx-font-weight: bold");
			menuButton.setFont(new Font("Times New Roman", 18));
			menuButton.setEffect(shadow);
		}

		menuItem1 = new MenuItem("piercing cannon" + "\n" + "price:25" + "damage:10");
		menuItem1.setStyle("-fx-background-color: transparent;-fx-text-fill: black ;-fx-font-weight: bold");
		// pc= battle.getWeaponFactory ().getWeaponShop().get (1);

		menuItem2 = new MenuItem("sniper cannon" + "\n" + "price:25" + "damage:35");
		menuItem2.setStyle("-fx-background-color: transparent;-fx-text-fill: black ;-fx-font-weight: bold");
		// sc= battle.getWeaponFactory ().getWeaponShop().get (2);

		menuItem3 = new MenuItem("volleyspread cannon" + "\n" + "price:100" + "damage:5");
		menuItem3.setStyle("-fx-background-color: transparent;-fx-text-fill: black ;-fx-font-weight: bold");
		// vs = battle.getWeaponFactory ().getWeaponShop().get (3);

		menuItem4 = new MenuItem("walltrap" + "\n" + "price:75" + "damage:100");
		menuItem4.setStyle("-fx-background-color: transparent;-fx-text-fill: black ;-fx-font-weight: bold");
		// wt = battle.getWeaponFactory ().getWeaponShop().get (4);

		menuButton.getItems().addAll(menuItem1, menuItem2, menuItem3, menuItem4);
		anchor.getChildren().addAll(menuButton);
		if (mode == 3) {
			menuItem1.setOnAction(e -> {
				lanepopupeasy(1, battle);
				is_game_over(battle);
			});
			menuItem2.setOnAction(e -> {
				lanepopupeasy(2, battle);
				is_game_over(battle);
			});
			menuItem3.setOnAction(e -> {
				lanepopupeasy(3, battle);
				is_game_over(battle);
			});
			menuItem4.setOnAction(e -> {
				lanepopupeasy(4, battle);
				is_game_over(battle);
			});
		}

		if (mode == 5) {
			menuItem1.setOnAction(e -> lanepopuphard(1, battle));
			menuItem2.setOnAction(e -> lanepopuphard(2, battle));
			menuItem3.setOnAction(e -> lanepopuphard(3, battle));
			menuItem4.setOnAction(e -> lanepopuphard(4, battle));
		}

	}

	public void lanepopupeasy(int weaponcode, Battle battle) {

		stagepopup = new Stage();
		stagepopup.setTitle("select your lane ");
		stagepopup.getIcons()
				.add(new Image("file:///C:/Users/HP/OneDrive/Desktop/year%202/game.gui/src/game/gui/AOTmain.jpg"));

		anchorpopup = new AnchorPane();
		scenepopup = new Scene(anchorpopup, 325, 377);
		VBox content = new VBox();
		content.setPrefHeight(76);
		content.setPrefWidth(184);
		content.setLayoutX(41);
		content.setLayoutY(155);
		content.setSpacing(15);
		content.setStyle("-fx-background-color: transparent;-fx-text-fill: black;-fx-font-weight: bold");

		option1 = new RadioButton("1");
		option2 = new RadioButton("2");
		option3 = new RadioButton("3");
		ToggleGroup toggleGroup = new ToggleGroup();
		option1.setToggleGroup(toggleGroup);
		option2.setToggleGroup(toggleGroup);
		option3.setToggleGroup(toggleGroup);
		content.getChildren().addAll(option1, option2, option3);
		popup_ok = new Button("Ok");
		popup_ok.setPrefHeight(31);
		popup_ok.setPrefWidth(100);
		popup_ok.setLayoutX(163);
		popup_ok.setLayoutY(308);
		popup_ok.setStyle("-fx-background-color: transparent;-fx-text-fill: black;-fx-font-weight: bold");
		popup_ok.setFont(new Font("Times New Roman", 18));
		popup_cancel = new Button("cancel");
		popup_cancel.setPrefHeight(31);
		popup_cancel.setPrefWidth(100);
		popup_cancel.setLayoutX(225);
		popup_cancel.setLayoutY(308);
		popup_cancel.setStyle("-fx-background-color: transparent;-fx-text-fill: black;-fx-font-weight: bold");
		popup_cancel.setFont(new Font("Times New Roman", 18));
		Label popuptitle = new Label("please select your lane ");
		popuptitle.setPrefHeight(60);
		popuptitle.setPrefWidth(250);
		popuptitle.setLayoutX(44);
		popuptitle.setLayoutY(66);
		popuptitle.setFont(new Font("Times New Roman", 16));
		popuptitle.setStyle("-fx-text-fill: black;-fx-font-weight: bold");

		Lane lane1 = battle.getOriginalLanes().get(0);
		Lane lane2 = battle.getOriginalLanes().get(1);
		Lane lane3 = battle.getOriginalLanes().get(2);

		popup_ok.setOnAction(event -> {
			if (option1.isSelected()) {
				try {
					battle.purchaseWeapon(weaponcode, lane1);// add update
					update_hbox(weaponcode, 0, battle);
					update(battle, 3);

				} catch (InsufficientResourcesException d) {
					System.out.println("insufficient resource exeption");
					Alert alert = new Alert(AlertType.ERROR);
					alert.setContentText("error");
					alert.showAndWait();
					d.printStackTrace();
				} catch (InvalidLaneException d) {
					d.printStackTrace();
				}
			
			} else if (option2.isSelected()) {

				try {
					battle.purchaseWeapon(weaponcode, lane2);// add update
					update_hbox(weaponcode, 1, battle);
					update(battle, 3);
				} catch (InsufficientResourcesException d) {
					System.out.println("insufficient resource exeption");
					Alert alert = new Alert(AlertType.ERROR);
					alert.setContentText("error");
					d.printStackTrace();
				} catch (InvalidLaneException d) {
					d.printStackTrace();
				}
			
			} else if (option3.isSelected()) {
				try {
					battle.purchaseWeapon(weaponcode, lane3);// add update
					update_hbox(weaponcode, 2, battle);
					update(battle, 3);

				} catch (InsufficientResourcesException d) {
					System.out.println("insufficient resource exeption");
					Alert alert = new Alert(AlertType.ERROR);
					alert.setContentText("error");
					alert.showAndWait();
					d.printStackTrace();
				} catch (InvalidLaneException d) {
					d.printStackTrace();
				}
				
			
			}
			anchorpopup.getChildren().clear();
			stagepopup.close();
			is_game_over(battle);

		});
		popup_cancel.setOnAction(event -> {
			anchorpopup.getChildren().clear();
			stagepopup.close();
			is_game_over(battle);
		});
		stagepopup.setScene(scenepopup);
		anchorpopup.getChildren().addAll(content, popup_ok, popup_cancel, popuptitle);
		stagepopup.showAndWait();

	}

	public void lanepopuphard(int weaponcode, Battle battle) {

		stagepopup = new Stage();
		stagepopup.setTitle("select your lane ");
		stagepopup.getIcons()
				.add(new Image("file:///C:/Users/HP/OneDrive/Desktop/year%202/game.gui/src/game/gui/AOTmain.jpg"));

		anchorpopup = new AnchorPane();
		scenepopup = new Scene(anchorpopup, 325, 377);
		stagepopup.setScene(scenepopup);

		VBox content = new VBox();
		content.setPrefHeight(76);
		content.setPrefWidth(184);
		content.setLayoutX(41);
		content.setLayoutY(155);
		content.setSpacing(15);
		content.setStyle("-fx-background-color: transparent;-fx-text-fill: black;-fx-font-weight: bold");

		option1 = new RadioButton("1");
		option2 = new RadioButton("2");
		option3 = new RadioButton("3");
		option4 = new RadioButton("4");
		option5 = new RadioButton("5");

		ToggleGroup toggleGroup = new ToggleGroup();
		option1.setToggleGroup(toggleGroup);
		option2.setToggleGroup(toggleGroup);
		option3.setToggleGroup(toggleGroup);
		content.getChildren().addAll(option1, option2, option3, option4, option5);
		popup_ok = new Button("Ok");
		popup_ok.setPrefHeight(31);
		popup_ok.setPrefWidth(100);
		popup_ok.setLayoutX(163);
		popup_ok.setLayoutY(308);
		popup_ok.setStyle("-fx-background-color: transparent;-fx-text-fill: black;-fx-font-weight: bold");
		popup_ok.setFont(new Font("Times New Roman", 18));
		popup_cancel = new Button("cancel");
		popup_cancel.setPrefHeight(31);
		popup_cancel.setPrefWidth(100);
		popup_cancel.setLayoutX(225);
		popup_cancel.setLayoutY(308);
		popup_cancel.setStyle("-fx-background-color: transparent;-fx-text-fill: black;-fx-font-weight: bold");
		popup_cancel.setFont(new Font("Times New Roman", 18));
		Label popuptitle = new Label("please select your lane ");
		popuptitle.setPrefHeight(60);
		popuptitle.setPrefWidth(250);
		popuptitle.setLayoutX(44);
		popuptitle.setLayoutY(66);
		popuptitle.setFont(new Font("Times New Roman", 16));
		popuptitle.setStyle("-fx-text-fill: black;-fx-font-weight: bold");
		anchorpopup.getChildren().addAll(content, popup_ok, popup_cancel, popuptitle);
		Lane lane1 = battle.getOriginalLanes().get(0);
		Lane lane2 = battle.getOriginalLanes().get(1);
		Lane lane3 = battle.getOriginalLanes().get(2);
		Lane lane4 = battle.getOriginalLanes().get(2);
		Lane lane5 = battle.getOriginalLanes().get(2);

		popup_ok.setOnAction(event -> {
			if (option1.isSelected()) {
				try {
					battle.purchaseWeapon(weaponcode, lane1);// add update
					update_hbox(weaponcode, 0, battle);
					update(battle, 5);

				} catch (InsufficientResourcesException d) {
					System.out.println("insufficient resource exeption");
					Alert alert = new Alert(AlertType.ERROR);
					alert.setContentText("error");
					alert.showAndWait();
				
					d.printStackTrace();
				} catch (InvalidLaneException d) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setContentText("error");
					alert.showAndWait();
				
					d.printStackTrace();
				}
			} else if (option2.isSelected()) {

				try {
					battle.purchaseWeapon(weaponcode, lane2);// add update
					update_hbox(weaponcode, 1, battle);
					update(battle, 5);
				} catch (InsufficientResourcesException d) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setContentText("error");
					alert.showAndWait();
					System.out.println("insufficient resource exeption");

					d.printStackTrace();
				} catch (InvalidLaneException d) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setContentText("error");
					alert.showAndWait();
					d.printStackTrace();
				}
			
			} else if (option3.isSelected()) {
				try {
					battle.purchaseWeapon(weaponcode, lane3);// add update
					update_hbox(weaponcode, 2, battle);
					update(battle, 5);

				} catch (InsufficientResourcesException d) {
					System.out.println("insufficient resource exeption");
					Alert alert = new Alert(AlertType.ERROR);
					alert.setContentText("error");
					alert.showAndWait();
					d.printStackTrace();
				} catch (InvalidLaneException d) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setContentText("error");
					alert.showAndWait();
					d.printStackTrace();
				}
			} else if (option4.isSelected()) {
				try {
					battle.purchaseWeapon(weaponcode, lane4);// add update
					update_hbox(weaponcode, 3, battle);
					update(battle, 5);

				} catch (InsufficientResourcesException d) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setContentText("error");
					alert.showAndWait();
					d.printStackTrace();
				} catch (InvalidLaneException d) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setContentText("error");
					alert.showAndWait();
					d.printStackTrace();
				}
			
			} else if (option5.isSelected()) {
				try {
					battle.purchaseWeapon(weaponcode, lane5);// add update
					update_hbox(weaponcode, 4, battle);
					update(battle, 5);

				} catch (InsufficientResourcesException d) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setContentText("error");
					alert.showAndWait();

					d.printStackTrace();
				} catch (InvalidLaneException d) {
					d.printStackTrace();
					Alert alert = new Alert(AlertType.ERROR);
					alert.setContentText("error");
					alert.showAndWait();}
			
			}
			anchorpopup.getChildren().clear();
			stagepopup.close();
			is_game_over(battle);

		});
		popup_cancel.setOnAction(event -> {
			anchorpopup.getChildren().clear();
			stagepopup.close();
			is_game_over(battle);
		});
		stagepopup.showAndWait();

	}

	public void update_hbox(int weaponcode, int lanenum, Battle battle) {

		Lane lane = battle.getOriginalLanes().get(lanenum);
		if (!lane.isLaneLost()) {

			if (weaponcode == 1) {
				Image image = new Image(
						"file:///C:/Users/HP/OneDrive/Desktop/year%202/game.gui/src/game/gui/WhatsApp%20Image%202024-05-17%20at%2018.46.55_4eb5c656.jpg");
				imageView = new ImageView(image);
				imageView.setFitWidth(25);
				imageView.setFitHeight(25);

			}
			if (weaponcode == 2) {
				Image image = new Image(
						"file:///C:/Users/HP/OneDrive/Desktop/year%202/game.gui/src/game/gui/WhatsApp%20Image%202024-05-17%20at%2018.36.44_1c64cae3.jpg");
				imageView = new ImageView(image);
				imageView.setFitWidth(25);
				imageView.setFitHeight(25);
			}
			if (weaponcode == 3) {
				Image image = new Image(
						"file:///C:/Users/HP/OneDrive/Desktop/year%202/game.gui/src/game/gui/WhatsApp%20Image%202024-05-17%20at%2018.34.20_e19e87fd.jpg");
				imageView = new ImageView(image);
				imageView.setFitWidth(25);
				imageView.setFitHeight(25);
			}
			if (weaponcode == 4) {
				Image image = new Image(
						"file:///C:/Users/HP/OneDrive/Desktop/year%202/game.gui/src/game/gui/WhatsApp%20Image%202024-05-17%20at%2018.22.19_8485831d.jpg");
				imageView = new ImageView(image);
				imageView.setFitWidth(25);
				imageView.setFitHeight(25);
			}

			if (lanenum == 0) {
				laneweapons1.getChildren().add(imageView);
			}

			if (lanenum == 1) {
				laneweapons2.getChildren().add(imageView);
			}

			if (lanenum == 2) {
				laneweapons3.getChildren().add(imageView);
			}

			if (lanenum == 4) {
				laneweapons4.getChildren().add(imageView);
			}

			if (lanenum == 5) {
				laneweapons5.getChildren().add(imageView);
			}
		}

	}

	public void build_easy(Battle battle) {

		PassTurn = new Button("passturn");
		PassTurn.setPrefHeight(59);
		PassTurn.setPrefWidth(92);
		PassTurn.setLayoutX(23);
		PassTurn.setLayoutY(517);
		PassTurn.setStyle("-fx-background-color: transparent;-fx-text-fill: black;-fx-font-weight: bold");
		PassTurn.setFont(new Font("Times New Roman", 18));
		PassTurn.setOnAction(e -> handlePassTurn(e, 3));

		currentscore = new Label("score:");
		currentscore.setPrefHeight(21);
		currentscore.setPrefWidth(100);
		currentscore.setLayoutX(5);
		currentscore.setLayoutY(30);
		currentscore.setFont(new Font("Times New Roman", 10));
		currentscore.setStyle("-fx-text-fill: white;-fx-font-weight: bold");
		currentscore.applyCss();
		currentturn = new Label("turn:");
		currentturn.setPrefHeight(21);
		currentturn.setPrefWidth(100);
		currentturn.setLayoutX(5);
		currentturn.setLayoutY(71);
		currentturn.setFont(new Font("Times New Roman", 12));
		currentturn.setStyle("-fx-text-fill: white;-fx-font-weight: bold");
		currentturn.applyCss();
		currentphase = new Label("phase:");
		currentphase.setPrefHeight(21);
		currentphase.setPrefWidth(120);
		currentphase.setLayoutX(5);
		currentphase.setLayoutY(111);
		currentphase.setFont(new Font("Times New Roman", 12));
		currentphase.setStyle("-fx-text-fill: white;-fx-font-weight: bold");
		currentphase.applyCss();
		currentresources = new Label(" resources:");
		currentresources.setPrefHeight(21);
		currentresources.setPrefWidth(100);
		currentresources.setLayoutX(5);
		currentresources.setLayoutY(146);
		currentresources.setFont(new Font("Times New Roman", 12));
		currentresources.setStyle("-fx-text-fill: white;-fx-font-weight: bold");
		currentresources.applyCss();
		laneweapons1 = new HBox();
		laneweapons1.setLayoutX(205);
		laneweapons1.setLayoutY(450);
		laneweapons2 = new HBox();
		laneweapons2.setLayoutX(405);
		laneweapons2.setLayoutY(450);
		laneweapons3 = new HBox();
		laneweapons3.setLayoutX(605);
		laneweapons3.setLayoutY(450);
		laneWeaponBoxes = new HBox[3];
		laneWeaponBoxes[0] = laneweapons1;
		laneWeaponBoxes[1] = laneweapons2;
		laneWeaponBoxes[2] = laneweapons3;
		titans = new Pane[3];
		Pane titansp1 = new Pane();
		titansp1.setPrefSize(150, 460);
		titansp1.setLayoutX(205);
		titansp1.setLayoutY(480);
		Pane titansp2 = new Pane();
		titansp2.setPrefSize(150, 460);
		titansp2.setLayoutX(405);
		titansp2.setLayoutY(480);
		Pane titansp3 = new Pane();
		titansp3.setPrefSize(150, 460);
		titansp3.setLayoutX(605);
		titansp3.setLayoutY(480);
		titans[0] = titansp1;
		titans[1] = titansp2;
		titans[2] = titansp3;

		anchor.getChildren().addAll(PassTurn, currentscore, currentturn, currentphase, currentresources, laneweapons1,
				laneweapons2, laneweapons3, titansp1, titansp2, titansp3);

		laneslabeld = new Label[3];
		laneslabelh = new Label[3];// chanhe in hard

		int i = 0;
		int x = 5;
		while (i < 3) {
			x += 200;

			Label lanedangerlevel = new Label("lanedangerlevel:");
			lanedangerlevel.setPrefHeight(21);
			lanedangerlevel.setPrefWidth(150);
			lanedangerlevel.setLayoutX(x);
			lanedangerlevel.setLayoutY(500);
			lanedangerlevel.setFont(new Font("Times New Roman", 16));
			lanedangerlevel.setStyle("-fx-text-fill: black;");
			laneslabeld[i] = lanedangerlevel;

			Label lanehealth = new Label(" lanehealth:");
			lanehealth.setPrefHeight(21);
			lanehealth.setPrefWidth(150);
			lanehealth.setLayoutX(x);
			lanehealth.setLayoutY(540);
			lanehealth.setFont(new Font("Times New Roman", 16));
			lanehealth.setStyle("-fx-text-fill: black;");
			laneslabelh[i] = lanehealth;
			i++;

			anchor.getChildren().addAll(lanedangerlevel, lanehealth);

		}
		build_weaponshop(3, battle);
	}

	public void handlePassTurn(ActionEvent event, int x) {
		if (event.getSource() == PassTurn) {
			battle.passTurn();
			update(battle, x);
			PassTurn.setStyle("-fx-background-color: green;");
			is_game_over(battle);
		}
	}

	public void build_hard() {

		PassTurn = new Button("passturn");
		PassTurn.setPrefHeight(59);
		PassTurn.setPrefWidth(92);
		PassTurn.setLayoutX(23);
		PassTurn.setLayoutY(517);
		PassTurn.setStyle("-fx-background-color: transparent;-fx-text-fill: black;-fx-font-weight: bold");
		PassTurn.setFont(new Font("Times New Roman", 18));
		PassTurn.setOnAction(e -> handlePassTurn(e, 5));

		currentscore = new Label("score:");
		currentscore.setPrefHeight(21);
		currentscore.setPrefWidth(100);
		currentscore.setLayoutX(5);
		currentscore.setLayoutY(30);
		currentscore.setFont(new Font("Times New Roman", 10));
		currentscore.setStyle("-fx-text-fill: white;-fx-font-weight: bold");
		currentscore.applyCss();
		currentturn = new Label("turn:");
		currentturn.setPrefHeight(21);
		currentturn.setPrefWidth(100);
		currentturn.setLayoutX(5);
		currentturn.setLayoutY(71);
		currentturn.setFont(new Font("Times New Roman", 12));
		currentturn.setStyle("-fx-text-fill: white;-fx-font-weight: bold");
		currentturn.applyCss();
		currentphase = new Label("phase:");
		currentphase.setPrefHeight(21);
		currentphase.setPrefWidth(120);
		currentphase.setLayoutX(5);
		currentphase.setLayoutY(111);
		currentphase.setFont(new Font("Times New Roman", 12));
		currentphase.setStyle("-fx-text-fill: white;-fx-font-weight: bold");
		currentphase.applyCss();
		currentresources = new Label(" resources:");
		currentresources.setPrefHeight(21);
		currentresources.setPrefWidth(100);
		currentresources.setLayoutX(5);
		currentresources.setLayoutY(146);
		currentresources.setFont(new Font("Times New Roman", 12));
		currentresources.setStyle("-fx-text-fill: white;-fx-font-weight: bold");
		currentresources.applyCss();

		laneweapons1 = new HBox();

		laneweapons1.setPrefHeight(70);
		laneweapons1.setPrefWidth(200);
		laneweapons1.setLayoutX(155);
		laneweapons1.setLayoutY(454);
		laneweapons2 = new HBox();

		laneweapons2.setPrefHeight(70);
		laneweapons2.setPrefWidth(200);
		laneweapons2.setLayoutX(305);
		laneweapons2.setLayoutY(454);
		laneweapons3 = new HBox();

		laneweapons3.setPrefHeight(70);
		laneweapons3.setPrefWidth(200);
		laneweapons3.setLayoutX(455);
		laneweapons3.setLayoutY(454);
		laneweapons4 = new HBox();

		laneweapons4.setPrefHeight(70);
		laneweapons4.setPrefWidth(200);
		laneweapons4.setLayoutX(605);
		laneweapons4.setLayoutY(454);
		laneweapons5 = new HBox();

		laneweapons5.setPrefHeight(70);
		laneweapons5.setPrefWidth(200);
		laneweapons5.setLayoutX(755);
		laneweapons5.setLayoutY(454);
		laneWeaponBoxes = new HBox[5];
		laneWeaponBoxes[0] = laneweapons1;
		laneWeaponBoxes[1] = laneweapons2;
		laneWeaponBoxes[2] = laneweapons3;
		laneWeaponBoxes[3] = laneweapons4;
		laneWeaponBoxes[4] = laneweapons5;

		titans = new Pane[5];
		Pane titansp1 = new Pane();
		titansp1.setPrefSize(150, 460);
		titansp1.setLayoutX(155);
		titansp1.setLayoutY(454);
		Pane titansp2 = new Pane();
		titansp2.setPrefSize(150, 460);
		titansp2.setLayoutX(305);
		titansp2.setLayoutY(454);
		Pane titansp3 = new Pane();
		titansp3.setPrefSize(150, 460);
		titansp3.setLayoutX(455);
		titansp3.setLayoutY(454);
		Pane titansp4 = new Pane();
		titansp4.setPrefSize(150, 460);
		titansp4.setLayoutX(605);
		titansp4.setLayoutY(454);
		Pane titansp5 = new Pane();
		titansp5.setPrefSize(150, 460);
		titansp5.setLayoutX(755);
		titansp5.setLayoutY(454);
		titans[0] = titansp1;
		titans[1] = titansp2;
		titans[2] = titansp3;
		titans[3] = titansp4;
		titans[4] = titansp5;

		anchor.getChildren().addAll(PassTurn, currentscore, currentturn, currentphase, currentresources, laneweapons1,
				laneweapons2, laneweapons3, laneweapons4, laneweapons5, titansp1, titansp2, titansp3, titansp4,
				titansp5);

		laneslabeld = new Label[5];
		laneslabelh = new Label[5];

		int i = 0;
		int x = 5;
		while (i < 5) {
			x += 150;

			Label lanedangerlevel = new Label("lanedangerlevel:");
			lanedangerlevel.setPrefHeight(21);
			lanedangerlevel.setPrefWidth(150);
			lanedangerlevel.setLayoutX(x);
			lanedangerlevel.setLayoutY(500);
			lanedangerlevel.setFont(new Font("Times New Roman", 16));
			lanedangerlevel.setStyle("-fx-text-fill: black;-fx-font-weight: bold;");
			laneslabeld[i] = lanedangerlevel;
			Label lanehealth = new Label(" lanehealth:");
			lanehealth.setPrefHeight(21);
			lanehealth.setPrefWidth(150);
			lanehealth.setLayoutX(x);
			lanehealth.setLayoutY(540);
			lanehealth.setFont(new Font("Times New Roman", 16));
			lanehealth.setStyle("-fx-text-fill: black;-fx-font-weight: bold;");
			laneslabelh[i] = lanehealth;
			i++;

			anchor.getChildren().addAll(lanedangerlevel, lanehealth);
			build_weaponshop(5, battle);
		}

	}

	public void titan(Battle battle, int x) {
		
		for (int j = 0; j < x; j++) {
			Lane lane = battle.getOriginalLanes().get(j);
			titans[j].getChildren().clear();

			PriorityQueue<Titan> titansQueue = new PriorityQueue<>(lane.getTitans());

			while (!titansQueue.isEmpty()) {
				Titan titan = titansQueue.remove();

				String imagePath = "";
				if (titan.getClass() == PureTitan.class) {
					imagePath = "file:///C:/Users/HP/OneDrive/Desktop/year%202/game.gui/src/game/gui/pure.jpg";
					Image image = new Image(imagePath);
					imageView = new ImageView(image);
					imageView.setFitWidth(25);
					imageView.setFitHeight(titan.getHeightInMeters());

				} else if (titan.getClass() == AbnormalTitan.class) {
					imagePath = "file:///C:/Users/HP/OneDrive/Desktop/year%202/game.gui/src/game/gui/abnormal.jpg";
					Image image = new Image(imagePath);
					imageView = new ImageView(image);
					imageView.setFitWidth(25);
					imageView.setFitHeight(titan.getHeightInMeters());
				} else if (titan.getClass() == ArmoredTitan.class) {
					imagePath = "file:///C:/Users/HP/OneDrive/Desktop/year%202/game.gui/src/game/gui/armored.jpg";
					Image image = new Image(imagePath);
					imageView = new ImageView(image);
					imageView.setFitWidth(25);
					imageView.setFitHeight(titan.getHeightInMeters());
				} else  {
					imagePath = "file:///C:/Users/HP/OneDrive/Desktop/year%202/game.gui/src/game/gui/colosal.jpg";
					Image image = new Image(imagePath);
					imageView = new ImageView(image);
					imageView.setFitWidth(25);
					imageView.setFitHeight(titan.getHeightInMeters());
				}

				Text text = new Text("" + (titan.getCurrentHealth()));
				text.setFont(new Font(14));
				text.setStyle("-fx-fill:Black;");

				StackPane stackPane = new StackPane();
				stackPane.setLayoutY(20-titan.getDistance());
				stackPane.applyCss();
				stackPane.setLayoutX(Math.random() * 50);
				stackPane.getChildren().addAll(imageView, text);

				titans[j].getChildren().add(stackPane);

			}
		}
	}

	public void update(Battle battle, int x) {

		for (int i = 0; i < x; i++) {

			Lane lane = battle.getOriginalLanes().get(i);
			if (!lane.isLaneLost()) {
				laneslabeld[i].setText("lanedangerlevel:" + lane.getDangerLevel());
				System.out.print(lane.getDangerLevel());

				laneslabelh[i].setText("lanewallhealth:" + lane.getLaneWall().getCurrentHealth());
			}
		}

		currentscore.setText(" Score: " + battle.getScore());

		currentturn.setText("Turn: " + battle.getNumberOfTurns());

		currentphase.setText("Phase: " + battle.getBattlePhase());

		currentresources.setText(" Resources: " + battle.getResourcesGathered());

		titan(battle, x);
		PassTurn.setStyle("-fx-background-color: transparent;");
		PassTurn.applyCss();
		is_game_over(battle);

	}

	public static void main(String[] args) {
		launch(args);
	}

}
