package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Summa extends Komento {
    
    public Summa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }
    
    @Override
    public void suorita() {
        
        this.edellinen = this.sovellus.tulos();
        
        this.sovellus.plus(Integer.parseInt(this.syotekentta.getText()));
        this.syotekentta.setText("");
        this.tuloskentta.setText("" + this.sovellus.tulos());
        
        if (this.sovellus.tulos() == 0) {
            this.nollaa.disableProperty().set(true);
        } else {
            this.nollaa.disableProperty().set(false);
        }
        this.undo.disableProperty().set(false);
    }
}