import javax.swing.JFrame;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame window = new JFrame();
		window.setVisible(true);
		//D�finit un titre pour notre fen�tre
		window.setTitle("Ma premi�re fen�tre Java");
		//D�finit sa taille : 400 pixels de large et 100 pixels de haut
		window.setSize(400, 100);
		//Nous demandons maintenant � notre objet de se positionner au centre
		window.setLocationRelativeTo(null);
		//Termine le processus lorsqu'on clique sur la croix rouge
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Et enfin, la rendre visible        
		window.setVisible(true);
	}
}
