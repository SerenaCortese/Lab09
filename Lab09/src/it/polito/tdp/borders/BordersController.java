/**
 * Skeleton for 'Borders.fxml' Controller Class
 */

package it.polito.tdp.borders;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;

public class BordersController {

	Model model;
	private List<Country> countries;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="txtAnno"
	private TextField txtAnno; // Value injected by FXMLLoader
	
	@FXML // fx:id="comboBox"
    private ComboBox<Country> comboBox; // Value injected by FXMLLoader
	
	@FXML // fx:id="btnTrovaVicini"
    private Button btnTrovaVicini; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader

	@FXML
	void doCalcolaConfini(ActionEvent event) {
		txtResult.clear();
		btnTrovaVicini.setDisable(true);
		int anno =Integer.MIN_VALUE;
		try {
			anno = Integer.parseInt(txtAnno.getText());
			
		}catch(NumberFormatException e) {
			txtResult.setText("Inserire un numero tra 1816 e 2016.");
			return;
		}
		
		if(anno<1816 || anno >2016) {
			txtResult.setText("Inserire un numero tra 1816 e 2016.");
			return;
		}
		btnTrovaVicini.setDisable(false);
		txtResult.appendText(model.calcolaConfini(anno));
		txtResult.appendText(String.format("Il numero di componenti connesse del grafo per l'anno %d è %d", anno,model.componentiConnesse(anno)));
	}
	
	@FXML
    void doTrovaVicini(ActionEvent event) {
		txtResult.clear();
		Country countryScelto = comboBox.getValue();
		if(countryScelto == null) {
    		txtResult.setText("Scegliere uno Stato.");
    		return;
    	}
		
		List<Country> result = model.trovaVicini(countryScelto);
		if(result.size() == 1) {
			txtResult.setText("Questo stato non ha nazioni raggiungibili via terra.");
			return;
		}
		for(Country c : result) {
			txtResult.appendText(c.toString()+"\n");
		}

		
    }

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Borders.fxml'.";
		assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'Borders.fxml'.";
		assert btnTrovaVicini != null : "fx:id=\"btnTrovaVicini\" was not injected: check your FXML file 'Borders.fxml'.";
	    assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Borders.fxml'.";
	}
	
	public void setModel(Model model) {
		this.model = model;
		countries = model.getCountries();
		comboBox.getItems().addAll(countries);
	}
}
