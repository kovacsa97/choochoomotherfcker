package visuals;

import java.util.ArrayList;

import board.Board;
import boardelements.BoardElement;
import controller.Controller;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import trainelements.Train;

/**
 * a train (vonat) rajzolasaert felelos osztaly
 *
 */
public class TrainVisual extends DynamicVisual {
	/**
	 * a vonat kirajzolt pontjainak listaja
	 */
	private ArrayList<Point> myPoints;
	/**
	 * a vonat vagonjainak szinenek listaja
	 */
	private ArrayList<color.Color> colors = new ArrayList<>();
	
	Train currenttrain = null;
	
	public TrainVisual(String id, ArrayList<Point> l, ArrayList<color.Color> c){
		super(null, null, id);
		myPoints = l;
		colors = c;
	}
	
	/**
	 * a vonat folyamatos megjelenesehez az EntryPont elhagyasakor a pontokat egymas utan adjuk a pontok listajahoz
	 * @param l az ujonnan megjeleno pontok
	 * @param colors az uj vagon szine
	 */
	public void addPoints(ArrayList<Point> l, ArrayList<color.Color> colors){
		myPoints = l;
		this.colors = colors;
	}
	
	/* (non-Javadoc)
	 * @see visuals.DynamicVisual#update(board.Board, java.util.ArrayList, controller.Controller)
	 */
	@Override
	public void update(Board board, ArrayList<Train> trains, Controller c){
		for(Train t : trains){
			if(t.getId().equals(super.getId()))
				currenttrain = t;
		}
		if(currenttrain == null){
			try {
				throw new Exception("BAJ van TrainVisual.update Nincs ilyen vonat");
			} catch (Exception e) {
				// Ez igy eleg bena, de igy a hiba legalabb nyomonkovetheto
				e.printStackTrace();
			}
		}
		
		System.out.println(currenttrain.getId() + " " + currenttrain.getStartPos().getCurrentBE().getId() + " " + currenttrain.getEndPos().getCurrentBE().getId());
		
		myPoints.clear();

		
		double weightstart=currenttrain.getStartPos().getPos()/20.0;
		double weightend=currenttrain.getEndPos().getPos()/20.0;
		double weightdelta=1;
		int j=0;
		for (int i=0;i<=currenttrain.getMyWagons().size()+2;i++) {
			BoardElement be=currenttrain.getBeFIFO().get(currenttrain.getMyWagons().size()-1-j);
			Point[] points=c.getEndpoints(be);
			Point start=points[0];
			Point end=points[1];
			
			myPoints.add(new Point((int)(end.x*weightend+start.x*(1-weightend)), (int)(end.y*weightend+start.y*(1-weightend))));
			weightend-=weightdelta;
			while(weightend>1) {
				weightend+=1;
				j++;
				if (j==currenttrain.getBeFIFO().size())
				return;
			}
		
	}
	}
	
	/* (non-Javadoc)
	 * @see visuals.DynamicVisual#draw(javafx.scene.canvas.GraphicsContext)
	 */
	@Override
	public void draw(GraphicsContext c) {
		
		int counter = 0;
		
			if (isActive && counter == 0){
			    c.setStroke(Color.GOLD);
			}
			else{
			
			c.setLineWidth(10);
			for (int j = 1; j < myPoints.size()-1; j++) {
				switch (colors.get(myPoints.size()-j-2)){
				case BLACK:  if (!isActive) c.setStroke(Color.BLACK);
							break;
				case RED: c.setStroke(Color.RED);
							break;
				case BLUE: c.setStroke(Color.BLUE);
							break;
				case PINK: c.setStroke(Color.PINK);
							break;
				case YELLOW: c.setStroke(Color.YELLOW);
							break;
				case GREEN: c.setStroke(Color.GREEN);
							break;
				case GRAY: c.setStroke(Color.LIGHTGRAY);
							break;
				}
			    c.strokeLine(myPoints.get(j-1).x, myPoints.get(j-1).y, myPoints.get(j).x, myPoints.get(j).y);
			}
		}
	}
}
