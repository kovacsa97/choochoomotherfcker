package visuals;

import java.util.ArrayList;

import board.Board;
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
		Train currenttrain = null;
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
		int length = 0;
		for (int i=0; i<currenttrain.getBeFIFO().size(); i++){
			length+=currenttrain.getBeFIFO().get(i).getLength();
		}
		
		String startPosId = currenttrain.getStartPos().getCurrentBE().getId();
		String endPosId = currenttrain.getEndPos().getCurrentBE().getId();
		
		boolean dinamicsp = false;
		boolean dinamicep = false;
		
		DynamicVisual sp = null;
		DynamicVisual ep = null;
		StaticVisual sp2 = null;
		StaticVisual ep2 = null;
		
		for (DynamicVisual dv: c.getDynamicVisuals()){
			if(dv.getId().equals(startPosId)){
				sp = dv;
				dinamicsp = true;
				break;
			}
		}
		
		if(sp == null){
			for (StaticVisual sv: c.getStaticVisuals()){
				if(sv.getId().equals(startPosId)){
					sp2 = sv;
					break;
				}
			}
		}
		
		for (DynamicVisual dv: c.getDynamicVisuals()){
			if(dv.getId().equals(endPosId)){
				dinamicsp = true;
				ep = dv;
				break;
			}
		}
		
		if(ep == null){
			for (StaticVisual sv: c.getStaticVisuals()){
				if(sv.getId().equals(endPosId)){
					ep2 = sv;
					break;
				}
			}
		}
		
		int posInStartpos = currenttrain.getStartPos().getPos();
		int posInEndpos = currenttrain.getEndPos().getPos();
		
		int x1, x2, y1, y2;
		int xstartpos, ystartpos, xendpos, yendpos;
		int scalesp, scaleep;
		
		if(sp != null){
			x1 = sp.getStartPos().x;
			y1 = sp.getStartPos().y;
		}
		else{
			x1 = sp2.getStartPos().x;
			y1 = sp2.getStartPos().y;
		}
		
		if(ep != null){
			x2 = ep.getStartPos().x;
			y2 = ep.getStartPos().y;
		}
		else{
			x2 = ep2.getStartPos().x;
			y2 = ep2.getStartPos().y;
		}
		
		scalesp = posInStartpos / currenttrain.getBeFIFO().get(0).getLength();
		scaleep = posInEndpos / currenttrain.getBeFIFO().get(currenttrain.getBeFIFO().size()-1).getLength();
		
		xstartpos = (int) Math.abs(x1-20)*scalesp;
		ystartpos = (int) Math.abs(y1-20)*scalesp;
		xendpos = (int) Math.abs(x2-20)*scaleep;
		yendpos = (int) Math.abs(y2-20)*scaleep;
		
		int elementlength =(int) Math.sqrt((xstartpos-xendpos)*(xstartpos-xendpos)+(ystartpos-yendpos)*(ystartpos-yendpos));
		
		myPoints.clear();
		
		
		for(int i=0; i<(currenttrain.getMyWagons().size()+1);i++){
			if (i==0){
				myPoints.add(new Point(xstartpos, ystartpos));
			}
			else if(i==currenttrain.getMyWagons().size()){
				myPoints.add(new Point(xstartpos, ystartpos));
			}
			else{
				myPoints.add(new Point(xstartpos, ystartpos));
				myPoints.add(new Point(xstartpos, ystartpos));
			}
			System.out.println(i + " point added");
			xstartpos += elementlength;
			ystartpos += elementlength;
		}

	}
	
	/* (non-Javadoc)
	 * @see visuals.DynamicVisual#draw(javafx.scene.canvas.GraphicsContext)
	 */
	@Override
	public void draw(GraphicsContext c) {
		
		int counter = 0;
		
		for(int i=0; i<myPoints.size(); i+=2){
			int x1 = this.myPoints.get(i).x;
			int y1 = this.myPoints.get(i).y;
			int x2 = this.myPoints.get(i+1).x;
			int y2 = this.myPoints.get(i+1).y;
			
			if (myPoints.get(i + 1).x-myPoints.get(i).x<0){
				x1 = this.myPoints.get(i+1).x;
				y1 = this.myPoints.get(i+1).y;
				x2 = this.myPoints.get(i).x;
				y2 = this.myPoints.get(i).y;
			}
			
			int a = x2 - x1;
			int b = y2 - y1;
			
			boolean isNegative = false;
			
			if (b<0){
				b*=-1;
				isNegative = true;
			}
			
			int d = (int)Math.sqrt(a*a+b*b);

			double sina = (double) b / (double)d;
			double alpha;
			if (isNegative){
				alpha = Math.asin(sina) * -57;
			}
			else{
				alpha = Math.asin(sina) * 57;
			}
			
			if (isActive && counter == 0){
				c.save();
			    c.transform(new Affine(new Rotate(alpha, x1, y1+5)));
			    c.setFill(Color.GOLD);
			    c.fillOval(x1, y1, d, 12);
			    c.restore();
			    counter++;
			}
			else{
			
				switch (colors.get(counter)){
				case BLACK:  if (!isActive) c.setFill(Color.BLACK);
							break;
				case RED: c.setFill(Color.RED);
							break;
				case BLUE: c.setFill(Color.BLUE);
							break;
				case PINK: c.setFill(Color.PINK);
							break;
				case YELLOW: c.setFill(Color.YELLOW);
							break;
				case GREEN: c.setFill(Color.GREEN);
							break;
				case GRAY: c.setFill(Color.LIGHTGRAY);
							break;
				}
				
				c.save();
			    c.transform(new Affine(new Rotate(alpha, x1, y1+5)));
			    c.fillOval(x1, y1, d, 12);
			    c.restore();
			    counter++;
			}
		}
	}
}
