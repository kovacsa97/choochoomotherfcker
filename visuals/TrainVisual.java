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
	private ArrayList<Point> myPoints = new ArrayList<>();
	/**
	 * a vonat vagonjainak szinenek listaja
	 */
	private ArrayList<color.Color> colors = new ArrayList<>();
	
	Train currenttrain = null;
	
	private Point initPos;
	
	public TrainVisual(String id, ArrayList<color.Color> c){
		super(null, null, id);
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
		if(myPoints.size() == 0){
			String searchFor = board.getEntryPoint().getNextElement().getId();
			StaticVisual sv = null;
			for(StaticVisual s : c.getStaticVisuals()){
				if(s.getId().equals(searchFor)){
					sv = s;
					break;
				}
			}
			if(sv == null){
				System.out.println("no static visual found for entryPoint.nextElement (from train visual)");
			}
			initPos = sv.startPos;
			myPoints.add(new Point(initPos.x, initPos.y));
		}

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
		
		if(myPoints.size() <= currenttrain.getMyWagons().size() +2){
			myPoints.add(new Point(initPos.x, initPos.y));
		}
		
		for(int i = 0; i < myPoints.size() -1; i++){
			myPoints.get(i).x = myPoints.get(i + 1).x;
			myPoints.get(i).y = myPoints.get(i + 1).y;
		}
		
		BoardElement be= currenttrain.getBeFIFO().get(currenttrain.getBeFIFO().size() - 1);
		
		Point[] points=c.getEndpoints(be);		
		Point start=points[0];
		Point end=points[1];
			
		if(currenttrain.getStartPos().getCurrentBE().getLength() != 0){
			double progress=(double)currenttrain.getStartPos().getPos()/currenttrain.getStartPos().getCurrentBE().getLength();
			if(!be.getNextElement().isOccupied()){
				myPoints.get(myPoints.size() -1).x = new Double(end.x * progress + start.x * (1 - progress)).intValue();
				myPoints.get(myPoints.size() -1).y = new Double(end.y * progress + start.y * (1 - progress)).intValue();
			}else if (!be.getPrevElement().isOccupied()){
				myPoints.get(myPoints.size() -1).x = new Double(start.x * progress + end.x * (1 - progress)).intValue();
				myPoints.get(myPoints.size() -1).y = new Double(start.y * progress + end.y * (1 - progress)).intValue();
			}
		}else{
			if(!be.getNextElement().isOccupied()){
				myPoints.get(myPoints.size() -1).x = c.getEndpoints(be.getNextElement())[1].x;
				myPoints.get(myPoints.size() -1).y = c.getEndpoints(be.getNextElement())[1].y;
			}else if (!be.getPrevElement().isOccupied()){
				myPoints.get(myPoints.size() -1).x = c.getEndpoints(be.getNextElement())[0].x;
				myPoints.get(myPoints.size() -1).y = c.getEndpoints(be.getNextElement())[0].y;
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
