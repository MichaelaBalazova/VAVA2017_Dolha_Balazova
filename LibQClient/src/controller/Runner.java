package controller;

import view.Window;

/**
 * Trieda, ktora spusta klienta
 * @author Michaela, Domca
 *
 */
public class Runner {
	
	public static void main(String[] args){
					        
		Window win = new Window();
		Controller controller = new Controller(win);		
	}
}
	