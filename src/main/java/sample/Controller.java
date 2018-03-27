package sample;

import annotations.abstraction.FunctionSymbol;
import annotations.concrete.OutputFunction;
import annotations.setup.PostQuery;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Controller {

    private ObjectProperty<MusicBoxState> state;
    private ObjectProperty<MusicBoxOutput> output;
    public Button rhythmButton;
    public Button songButton;
    public ImageView songImageView;
    public ImageView rhythmImageView;
    public ImageView smallRhythmImageView;
    public ImageView errorImageView;
    public Rectangle screen;
    public Button quitButton;

    @FXML
    public void initialize() {

        state = new SimpleObjectProperty<MusicBoxState>();
        output = new SimpleObjectProperty<MusicBoxOutput>();

        state.addListener(new ChangeListener<MusicBoxState>() {
            public void changed(ObservableValue<? extends MusicBoxState> observable, MusicBoxState oldValue, MusicBoxState newValue) {
                switch (state.getValue()){
                    case OFF:
                        songImageView.setVisible(false);
                        rhythmImageView.setVisible(false);
                        smallRhythmImageView.setVisible(false);
                        errorImageView.setVisible(false);
                        screen.setFill(Paint.valueOf("GRAY"));
                        break;
                    case RHYTHM:
                        songImageView.setVisible(false);
                        rhythmImageView.setVisible(true);
                        smallRhythmImageView.setVisible(false);
                        errorImageView.setVisible(false);
                        screen.setFill(Paint.valueOf("WHITE"));
                        break;
                    case SONG:
                        songImageView.setVisible(true);
                        rhythmImageView.setVisible(false);
                        smallRhythmImageView.setVisible(false);
                        errorImageView.setVisible(false);
                        screen.setFill(Paint.valueOf("WHITE"));
                        break;
                    case BOTH:
                        songImageView.setVisible(true);
                        rhythmImageView.setVisible(false);
                        smallRhythmImageView.setVisible(true);
                        errorImageView.setVisible(false);
                        screen.setFill(Paint.valueOf("WHITE"));
                        break;
                    default:
                        songImageView.setVisible(false);
                        rhythmImageView.setVisible(false);
                        smallRhythmImageView.setVisible(false);
                        errorImageView.setVisible(true);
                        screen.setFill(Paint.valueOf("WHITE"));
                        break;
                }
            }
        });
        state.set(MusicBoxState.OFF);
        output.set(MusicBoxOutput.NONE);
    }

    @FunctionSymbol(inputSymbolID = "PushRhythm", inputFunctionID = "rhythmPushed", outputFunctionID = "output")
    public void rhythmPushed(ActionEvent actionEvent) {
        switch (state.getValue()){
            case OFF:
                state.set(MusicBoxState.RHYTHM);
                output.set(MusicBoxOutput.STARTRHYTHM);
                break;
            case RHYTHM:
                output.set(MusicBoxOutput.NONE);
                break;
            case SONG:
                state.set(MusicBoxState.ERROR);
                output.set(MusicBoxOutput.ERROR);
                break;
            case BOTH:
                output.set(MusicBoxOutput.NONE);
                break;
            default:
                state.set(MusicBoxState.ERROR);
                output.set(MusicBoxOutput.ERROR);
        }
    }

    @FunctionSymbol(inputSymbolID = "PushSong", inputFunctionID = "songPushed", outputFunctionID = "output")
    public void songPushed(ActionEvent actionEvent) {
        switch (state.getValue()){
            case OFF:
                state.set(MusicBoxState.SONG);
                output.set(MusicBoxOutput.STARTSONG);
                break;
            case RHYTHM:
                state.set(MusicBoxState.BOTH);
                output.set(MusicBoxOutput.STARTSONG);
                break;
            case SONG:
                output.set(MusicBoxOutput.NONE);
                break;
            case BOTH:
                output.set(MusicBoxOutput.NONE);
                break;
            default:
                state.set(MusicBoxState.ERROR);
                output.set(MusicBoxOutput.ERROR);
        }
    }

    @FunctionSymbol(inputSymbolID = "PushQuit", inputFunctionID = "quitPushed", outputFunctionID = "output")
    public void quitPushed(ActionEvent actionEvent) {
        switch (state.getValue()){
            case OFF:
                output.set(MusicBoxOutput.NONE);
                break;
            case RHYTHM:
                state.set(MusicBoxState.OFF);
                output.set(MusicBoxOutput.QUIT);
                break;
            case SONG:
                state.set(MusicBoxState.OFF);
                output.set(MusicBoxOutput.QUIT);
                break;
            case BOTH:
                state.set(MusicBoxState.OFF);
                output.set(MusicBoxOutput.QUIT);
                break;
            default:
                state.set(MusicBoxState.ERROR);
                output.set(MusicBoxOutput.ERROR);
        }
    }

    @OutputFunction(id="output")
    public String getOutput(){
        return output.getValue().toString();
    }

    public String getState() {
        return state.getValue().toString();
    }

    @PostQuery(order = 0)
    public void reset(){
        state.set(MusicBoxState.OFF);
        output.set(MusicBoxOutput.NONE);
    }
}
