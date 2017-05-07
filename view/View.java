package view;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import visuals.DynamicVisual;
import visuals.StaticVisual;

/**
 * Megjelenitest megvalosito osztaly
 */
public class View {
	@FXML
	TreeView<String> tvElements;
	
	@FXML
	Canvas boardCanvas;
	
	
	/**
	 * Palya betoltesenel egyszer inicializalando elemek listaja
	 */
	private ArrayList<StaticVisual> staticVisuals;
	
	public View(){
	}
	
	/**
	 * Palya betoltese a megfelelo statikus elemek atadasaval
	 * @param list a palya statikus elemeinek visualjai
	 */
	public void initBoard(ArrayList<StaticVisual> list){
		staticVisuals=list;
		for (StaticVisual staticVisual : list) {
			staticVisual.draw(boardCanvas.getGraphicsContext2D());
		}
	}
	
	/**
	 * Palyanezet frissitese a megfelelo dinamikus elemek atadasaval
	 * @param list a palya dinamikus elemeinek visualjai
	 */
	public void update(ArrayList<DynamicVisual> list){
		GraphicsContext boardContext=boardCanvas.getGraphicsContext2D();
		
		boardContext.clearRect(0, 0, boardCanvas.getWidth(), boardCanvas.getHeight());
		
		for (StaticVisual staticVisual : staticVisuals) {
			staticVisual.draw(boardContext);
		}
		
		for (DynamicVisual dynamicVisual : list) {
			dynamicVisual.draw(boardContext);
		}
	}
	
	/**
	 * A vezerlo treeview elemeit allitja be
	 * @param i a beallitando treeitem
	 */
	public void updateTreeView(TreeItem<String> i) {
		tvElements.setRoot(i);
		tvElements.setShowRoot(false);
	}
}
