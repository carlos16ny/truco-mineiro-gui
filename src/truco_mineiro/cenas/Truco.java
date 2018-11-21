/*
 * Licenciado a Carlos Henrique ;)
 */
package truco_mineiro.cenas;

import cartas.Carta;
import cartas.Cartas;
import java.util.ArrayList;
import java.util.Optional;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Pair;

/**
 * @author carlospereira
 */
public class Truco {

    RadioButton Fechada;
    Button Truco;
    Button Seis;
    Button Nove;
    Button Doze;
    Button Aceitar;
    Button Correr;

    Label pontos1;
    Label pontos2;

    boolean truco = false;
    boolean seis = false;
    boolean nove = false;
    boolean doze = false;
    boolean aceitar = false;
    boolean correr = false;

    boolean trucoBtn = false;
    boolean seisBtn = false;
    boolean noveBtn = false;
    boolean dozeBtn = false;
    boolean aceitarBtn = false;
    boolean correrBtn = false;

    Media media;
    MediaPlayer mediaPlayer;

    protected ArrayList<ArrayList<Carta>> deckReady = new ArrayList<>();
    protected ArrayList<ArrayList<ImageView>> monteImageJogadores = new ArrayList<>();
    protected ArrayList<Carta> monteMesaCarta = new ArrayList<>();
    protected ArrayList<ImageView> monteMesaImage = new ArrayList<>();
    protected ArrayList<Player> jogadores = new ArrayList<>();
    protected ArrayList<Player> monteMesaJogadores = new ArrayList<>();
    protected Player vencedorRodada = new Player();
    protected Carta maiorCarta = new Carta();
    protected Player jogadorAtual = new Player();
    protected Player jogadorAnterior = new Player();
    protected Player jogadorProximo = new Player();

    Integer ordemJogadores[] = new Integer[4];
    int tentos = 2;

    AudioPlayer audio = new AudioPlayer();

    Duplas dupla1;
    Duplas dupla2;

    Carta reserva = new Carta();

    int i = 0;
    int jogadas = 0;

    public Truco(ArrayList<Player> plrs, ArrayList<ArrayList<ImageView>> monteImageJogadores, ArrayList<ImageView> monteImageMesa) {
        this.jogadores = plrs;
        this.monteMesaImage = monteImageMesa;
        this.monteImageJogadores = monteImageJogadores;
    }

    public void execute() {

        this.setIndexJogadores();
        this.distribuiBaralho();
        this.zerarTruco();
        this.iniciaDuplas();
        this.setButtonsEvents();
        this.setVisibleButtons();

    }

    public void distribuiBaralho() {

        Cartas baralho = new Cartas();

        baralho.makeCartas();
        baralho.shuffleDeck();
        baralho.monte();
        deckReady = baralho.montesDuplas();

        for (int i = 0; i < 4; ++i) {
            jogadores.get(i).setMaoRodada(deckReady.get(i));
            jogadores.get(i).setImagesView(monteImageJogadores.get(i));
        }

        this.setEventosCartas();
        this.loadImages();

    }

    public void iniciaDuplas() {
        dupla1 = new Duplas(jogadores.get(0), jogadores.get(2));
        dupla2 = new Duplas(jogadores.get(1), jogadores.get(3));
    }

    public void loadImages() {
        jogadores.forEach(jogador -> {
            if (jogador.getIndex() == jogadas % 4) {
                jogador.mostrarCartas();
            } else {
                jogador.esconderCartas();
            }
        });
    }

    private void recebeCarta(Player p, Carta c, ImageView im) {

        System.out.println(jogadas);

        setVisibleButtons();

        jogadorAtual = p;
        jogadorProximo = jogadores.get((jogadores.indexOf(p) + 1) % 4);
        jogadorAnterior = jogadores.get((jogadores.indexOf(p) + 3) % 4);
       

        if (truco) {

            jogadas--;

            jogadorAtual.esconderCartas();
            jogarCarta(jogadorProximo);
            audio.gritarTruco();

            int vf = verifyTruco(4);

            if (vf == 1) {

                tentos = 4;
                truco = false;
                jogadorProximo.esconderCartas();
                jogarCarta(jogadorAtual);
                audio.gritarAceitar();

            } else if (vf == 0) {

                zerarTruco();
                audio.gritarCorrer();

                if (jogadorAtual.getIndex() % 2 == 0) {
                    dupla1.recebeTento(2);
                } else {
                    dupla2.recebeTento(2);
                }

                iniciarNovaRodada();
                reloadPontos();

            } else if (vf == 2) {

                tentos = 6;
                truco = false;
                seis = true;
                jogadorProximo.esconderCartas();
                jogarCarta(jogadorAtual);
                audio.gritarSeis();

                int vf2 = verifyTruco(6);

                if (vf2 == 0) {

                    zerarTruco();
                    audio.gritarCorrer();

                    if (jogadorProximo.getIndex() % 2 == 0) {
                        dupla1.recebeTento(4);
                    } else {
                        dupla2.recebeTento(4);
                    }

                    iniciarNovaRodada();
                    reloadPontos();

                } else if (vf2 == 1) {

                    seis = false;
                    tentos = 8;
                    audio.gritarAceitar();
                    jogadorAtual.esconderCartas();
                    jogarCarta(jogadorProximo);

                } else if (vf2 == 2) {

                    seis = false;
                    nove = true;
                    tentos = 10;
                    jogadorAtual.esconderCartas();
                    jogarCarta(jogadorProximo);
                    audio.gritarNove();

                    int vf3 = this.verifyTruco(9);

                    if (vf3 == 0) {

                        zerarTruco();
                        audio.gritarCorrer();

                        if (jogadorAtual.getIndex() % 2 == 0) {
                            dupla1.recebeTento(6);
                        } else {
                            dupla2.recebeTento(6);
                        }

                        iniciarNovaRodada();
                        reloadPontos();

                    } else if (vf3 == 1) {

                        nove = false;
                        audio.gritarAceitar();
                        jogadorProximo.esconderCartas();
                        jogarCarta(jogadorAtual);

                    } else if (vf3 == 2) {

                        tentos = 12;
                        nove = false;
                        doze = true;
                        jogadorProximo.esconderCartas();
                        jogarCarta(jogadorAtual);
                        audio.gritarDoze();

                        int vf4 = verifyTruco(12);

                        if (vf4 == 0) {

                            zerarTruco();
                            audio.gritarCorrer();

                            if (jogadorProximo.getIndex() % 2 == 0) {
                                dupla1.recebeTento(10);
                            } else {
                                dupla2.recebeTento(10);
                            }

                            iniciarNovaRodada();
                            reloadPontos();

                        } else if (vf4 == 1) {

                            doze = false;
                            audio.gritarAceitar();
                            jogadorAtual.esconderCartas();
                            jogarCarta(jogadorProximo);

                        }

                    }
                }

            }

        } else if (seis) {

            jogadas--;

            jogadorAtual.esconderCartas();
            jogarCarta(jogadorProximo);
            audio.gritarSeis();

            int vf2 = verifyTruco(6);

            if (vf2 == 0) {

                zerarTruco();
                audio.gritarCorrer();

                if (jogadorAtual.getIndex() % 2 == 0) {
                    dupla1.recebeTento(4);
                } else {
                    dupla2.recebeTento(4);
                }

                iniciarNovaRodada();

            } else if (vf2 == 1) {

                seis = false;
                tentos = 8;
                audio.gritarAceitar();
                jogadorProximo.esconderCartas();
                jogarCarta(jogadorAtual);

            } else if (vf2 == 2) {

                seis = false;
                nove = true;
                tentos = 10;
                jogadorProximo.esconderCartas();
                jogarCarta(jogadorAtual);
                audio.gritarNove();

                int vf3 = this.verifyTruco(9);

                if (vf3 == 0) {

                    zerarTruco();
                    audio.gritarCorrer();

                    if (jogadorProximo.getIndex() % 2 == 0) {
                        dupla1.recebeTento(6);
                    } else {
                        dupla2.recebeTento(6);
                    }

                    iniciarNovaRodada();

                } else if (vf3 == 1) {

                    nove = false;
                    audio.gritarAceitar();
                    jogadorAtual.esconderCartas();
                    jogarCarta(jogadorProximo);

                } else if (vf3 == 2) {

                    tentos = 12;
                    nove = false;
                    doze = true;
                    jogadorAtual.esconderCartas();
                    jogarCarta(jogadorProximo);
                    audio.gritarDoze();

                    int vf4 = verifyTruco(12);

                    if (vf4 == 0) {

                        audio.gritarCorrer();
                        zerarTruco();

                        if (jogadorAtual.getIndex() % 2 == 0) {
                            dupla1.recebeTento(10);
                        } else {
                            dupla2.recebeTento(10);
                        }

                        iniciarNovaRodada();

                        reloadPontos();

                    } else if (vf4 == 1) {

                        doze = false;
                        audio.gritarAceitar();
                        jogadorProximo.esconderCartas();
                        jogarCarta(jogadorAtual);

                    }

                }

            }

        } else if (nove) {

            jogadas--;

            seis = false;
            nove = true;
            tentos = 10;
            jogadorAtual.esconderCartas();
            jogarCarta(jogadorProximo);
            audio.gritarNove();

            int vf3 = this.verifyTruco(9);

            if (vf3 == 0) {

                zerarTruco();
                audio.gritarCorrer();

                if (jogadorAtual.getIndex() % 2 == 0) {
                    dupla1.recebeTento(8);
                } else {
                    dupla2.recebeTento(8);
                }

                iniciarNovaRodada();
                reloadPontos();

            } else if (vf3 == 1) {

                nove = false;
                audio.gritarAceitar();
                jogadorProximo.esconderCartas();
                jogarCarta(jogadorAtual);

            } else if (vf3 == 2) {

                tentos = 12;
                nove = false;
                doze = true;
                jogadorProximo.esconderCartas();
                jogarCarta(jogadorAtual);
                audio.gritarDoze();

                int vf4 = verifyTruco(12);

                if (vf4 == 0) {

                    zerarTruco();
                    audio.gritarCorrer();

                    if (jogadorProximo.getIndex() % 2 == 0) {
                        dupla1.recebeTento(10);
                    } else {
                        dupla2.recebeTento(10);
                    }

                    iniciarNovaRodada();
                    reloadPontos();

                } else if (vf4 == 1) {

                    doze = false;
                    audio.gritarAceitar();
                    jogadorAtual.esconderCartas();
                    jogarCarta(jogadorProximo);

                }

            }

        } else if (doze) {

            jogadas--;

            tentos = 12;
            nove = false;
            doze = true;
            jogadorAtual.esconderCartas();
            jogarCarta(jogadorProximo);
            audio.gritarDoze();

            int vf4 = verifyTruco(12);

            if (vf4 == 0) {

                zerarTruco();
                audio.gritarCorrer();

                if (jogadorAtual.getIndex() % 2 == 0) {
                    dupla1.recebeTento(10);
                } else {
                    dupla2.recebeTento(10);
                }

                iniciarNovaRodada();
                reloadPontos();

            } else if (vf4 == 1) {

                doze = false;
                audio.gritarAceitar();
                jogadorProximo.esconderCartas();
                jogarCarta(jogadorAtual);

            }

        } else {

            if (jogadas % 4 != 3) {

                if (Fechada.isSelected()) {
                    cartaFechada(im);
                    monteMesaJogadores.add(jogadorAtual);
                    jogadorAtual.esconderCartas();
                    jogarCarta(jogadorProximo);

                } else {
                    monteMesaJogadores.add(jogadorAtual);
                    monteMesaCarta.add(c);
                    monteMesaImage.get(jogadas % 4).setImage(im.getImage());
                    im.setVisible(false);
                    jogadorAtual.esconderCartas();
                    jogarCarta(jogadorProximo);
                }

            } else {

                if (Fechada.isSelected()) {
                    cartaFechada(im);
                    monteMesaJogadores.add(jogadorAtual);
                    jogadorAtual.esconderCartas();
                    jogarCarta(jogadorProximo);

                } else {
                    monteMesaJogadores.add(jogadorAtual);
                    monteMesaCarta.add(c);
                    monteMesaImage.get(jogadas % 4).setImage(im.getImage());
                    im.setVisible(false);
                    jogadorAtual.esconderCartas();

                }
            }
        }
        

        if (jogadas % 4 == 3) {
            this.reloadPontos();
            this.verifyMesa();
        }

        jogadas++;

    
    }
    public void jogarCarta(Player p) {
//        verifyPlayer(p);
        p.mostrarCartas();
    }

    public void setIndexJogadores() {
        for (int i = 0; i < 4; ++i) {
            jogadores.get(i).setIndexJogador(i);
        }
    }

    public void cartaFechada(ImageView im) {
        monteMesaCarta.add(new Carta("FECHADA", 0, "images/cartas/background-carta.png"));
        monteMesaImage.get(jogadas % 4).setImage(new Image("images/cartas/background-carta.png"));
        im.setVisible(false);
    }

    public void setEventosCartas() {
        for (int i = 0; i < 4; ++i) {
            Player p = this.jogadores.get(i);
            for (int j = 0; j < 3; ++j) {
                Carta c = p.getCartas().get(j);
                ImageView im = p.getImagesView().get(j);
                im.setOnMouseClicked((event) -> {
                    recebeCarta(p, c, im);
                });
            }
        }
    }

    public int verifyTruco(int i) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Truco no cê");
        alert.setGraphic(new ImageView("images/background/truco-symb.png"));

        ButtonType AceitarBtn = new ButtonType("Aceitar");
        ButtonType CorrerBtn = new ButtonType("Correr");
        ButtonType SeisBtn = new ButtonType("Seis");
        ButtonType NoveBtn = new ButtonType("Nove");
        ButtonType DozeBtn = new ButtonType("Doze");

        switch (i) {
            case 4:
                alert.getDialogPane().getButtonTypes().addAll(AceitarBtn, CorrerBtn, SeisBtn);
                alert.setHeaderText("Truco");
                break;
            case 6:
                alert.getDialogPane().getButtonTypes().addAll(AceitarBtn, CorrerBtn, NoveBtn);
                alert.setHeaderText("Seis");
                break;
            case 9:
                alert.getDialogPane().getButtonTypes().addAll(AceitarBtn, CorrerBtn, DozeBtn);
                alert.setHeaderText("Nove");
                break;
            case 12:
                alert.getDialogPane().getButtonTypes().addAll(AceitarBtn, CorrerBtn);
                alert.setHeaderText("Doze");
                break;
            default:
                break;
        }

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == AceitarBtn) {
            return 1;
        } else if (result.get() == CorrerBtn) {
            return 0;
        } else {
            return 2;
        }

    }

    public void verifyMesa() {

        this.verificaGanhadores();

        monteMesaCarta.forEach(carta -> {
            if (carta.getPeso() > maiorCarta.getPeso()) {
                maiorCarta = carta;
            }
        });

        //index out of bound exception //
        vencedorRodada = monteMesaJogadores.get(monteMesaCarta.indexOf(maiorCarta));

        vencedorRodada.setPontos();

        if (dupla1.getPontos() == 2) {
            dupla1.recebeTento(tentos);
            iniciarNovaRodada();

        } else if (dupla2.getPontos() == 2) {
            dupla2.recebeTento(tentos);
            iniciarNovaRodada();

        } else {

            System.out.println(maiorCarta.getName());

            reloadPontos();

            jogarCarta(vencedorRodada);

            this.limpaMesa();

        }

    }

    public void verificaGanhadores() {
        if (dupla1.getPlacar() >= 12) {
            displayWinner(dupla1);

        } else if (dupla2.getPlacar() >= 12) {
            displayWinner(dupla2);
        }
    }

    public void displayWinner(Duplas d) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("PARABENS, VOCES GANHARAM");
        alert.setGraphic(new ImageView("images/background/truco-symb.png"));

        Label nome1 = d.jogador1.nomePosition;
        Label nome2 = d.jogador2.nomePosition;

        alert.setContentText(d.jogador1.getName() + " e " + d.jogador2.getName());

        audio.comemorarVitoria();

        alert.showAndWait();
    }

    public void verifyPlayer(Player p) {

        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Confirme identidade");
        dialog.setHeaderText("Digite sua senha para ver seu baralho");

        ButtonType loginButtonType = new ButtonType("Ver Cartas", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        Label username = new Label();
        username.setText(p.getName());
        PasswordField password = new PasswordField();
        password.setPromptText("Senha");

        grid.add(new Label("Usuário:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Senha:"), 0, 1);
        grid.add(password, 1, 1);

        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        password.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);
        Platform.runLater(() -> username.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            if (usernamePassword.getValue().equals(p.getSenha())) {
                System.out.println("senha confere");
            } else {
                this.verifyPlayer(p);
            }

        });
    }

    public void setButtonsEvents() {
        Truco.setOnAction((event) -> {
            this.trucoVerify(jogadorAtual);
        });

        Seis.setOnAction((event) -> {
            this.seisVerify(jogadorAtual);
        });

        Nove.setOnAction((event) -> {
            this.noveVerify(jogadorAtual);
        });

        Doze.setOnAction((event) -> {
            this.dozeVerify(jogadorAtual);
        });

        Aceitar.setOnAction((event) -> {
            this.aceitarVerify(jogadorAtual);
        });

        Correr.setOnAction((event) -> {
            this.correrVerify(jogadorAtual);
        });

    }

    public void setVisibleButtons() {
        Truco.setVisible(false);
        Seis.setVisible(false);
        Nove.setVisible(false);
        Doze.setVisible(false);
        Aceitar.setVisible(false);
        Correr.setVisible(false);
        if (trucoBtn) {
            Aceitar.setVisible(true);
            Correr.setVisible(true);
            Seis.setVisible(true);
        } else if (seisBtn) {
            Aceitar.setVisible(true);
            Correr.setVisible(true);
            Nove.setVisible(true);
        } else if (noveBtn) {
            Aceitar.setVisible(true);
            Correr.setVisible(true);
            Nove.setVisible(true);
        } else if (dozeBtn) {
            Aceitar.setVisible(true);
            Correr.setVisible(true);
        } else {
            Truco.setVisible(true);
        }
    }

    public void setAllImageviewVisible() {
        monteImageJogadores.forEach(monte -> {
            monte.forEach(image -> {
                image.setVisible(true);
            });
        });
    }

    public void limpaMesa() {

        vencedorRodada = null;
        maiorCarta = new Carta("o", 0, "images/background/truco-symb.png");
        this.monteMesaCarta.clear();
        this.monteMesaImage.forEach(cartaImage -> {
            cartaImage.setImage(null);
        });
        this.monteMesaJogadores.clear();
    }

    public void zerarTruco() {

        tentos = 2;

        truco = false;
        seis = false;
        nove = false;
        doze = false;
        aceitar = false;
        correr = false;

        trucoBtn = false;
        seisBtn = false;
        noveBtn = false;
        dozeBtn = false;
        aceitarBtn = false;
        correrBtn = false;

    }

    public void reloadPontos() {

        this.pontos1.setText(Integer.toString(dupla1.getPlacar()));
        this.pontos2.setText(Integer.toString(dupla2.getPlacar()));

        verificaGanhadores();

    }

    public void trucoVerify(Player p) {
        truco = true;
        trucoBtn = true;
    }

    public void seisVerify(Player p) {
        truco = false;
        trucoBtn = false;
        seis = true;
        seisBtn = true;

    }

    public void noveVerify(Player p) {
        truco = false;
        trucoBtn = false;
        seis = false;
        seisBtn = false;
        nove = true;
        noveBtn = true;
    }

    public void dozeVerify(Player p) {
        truco = false;
        trucoBtn = false;
        seis = false;
        seisBtn = false;
        nove = false;
        noveBtn = false;
        doze = true;
        dozeBtn = true;
    }

    public void aceitarVerify(Player p) {
        aceitar = true;
    }

    public void correrVerify(Player p) {
        correr = true;
    }

    void setButtons(RadioButton Fechada, Button Truco, Button Seis, Button Nove, Button Doze, Button Aceitar, Button Correr) {
        this.Fechada = Fechada;
        this.Truco = Truco;
        this.Seis = Seis;
        this.Nove = Nove;
        this.Doze = Doze;
        this.Aceitar = Aceitar;
        this.Correr = Correr;
    }

    void setLabels(Label pontos1, Label pontos2) {
        this.pontos1 = pontos1;
        this.pontos2 = pontos2;
    }

    void esconderTodasCartas() {
        jogadores.forEach(jogador -> {
            jogador.esconderCartas();
        });
    }

    void zerarPontos() {
        jogadores.forEach(jogador -> {
            jogador.pontos = 0;
        });
    }

    public void iniciarNovaRodada() {

        jogadas = -1;
        i++;

        this.reloadPontos();
        this.esconderTodasCartas();
        this.setAllImageviewVisible();
        this.limpaMesa();
        this.zerarTruco();
        this.setVisibleButtons();
        this.zerarPontos();
        this.distribuiBaralho();

        jogarCarta(jogadores.get(i % 4));

    }

}
