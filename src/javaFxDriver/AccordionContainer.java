package javaFxDriver;

import java.util.ArrayList;

import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class AccordionContainer {
	
	private Accordion parentPane = new Accordion();
	private ArrayList<TitledPane> childrenPanes = new ArrayList<TitledPane>();
	private VBox accContainer = new VBox();
	private PopulationManager popMngr;
	
	public AccordionContainer (PopulationManager popMngr) {
		this.popMngr = popMngr;
		this.accContainer.getChildren().add(parentPane);
	}
	
	public void rebuildAccordion (int popSize) {
		this.parentPane.getPanes().clear();
		this.childrenPanes.clear();
		for (int i = 0; i < popSize; i++) {
			AlgParamController apc = new AlgParamController(this.popMngr, i);
			PopParamControl ppc = new PopParamControl(this.popMngr, i);
			Button scatterButton = new Button("Scatter " + i);
			scatterButton.setOnMouseClicked((MouseEvent e) -> {
				int labelIndex = scatterButton.getText().length();
				int label = Integer.parseInt(scatterButton.getText().substring(labelIndex - 1, labelIndex));
				popMngr.getActiveDriver().getPopulation(label).scatter();
			});
			VBox cont = new VBox(scatterButton, apc.getPane(), ppc.getPane());
			this.childrenPanes.add(new TitledPane("population " + i, cont));
		}
		this.parentPane.getPanes().addAll(childrenPanes);
	}
	
	public Pane getPane () {
		return this.accContainer;
	}

}