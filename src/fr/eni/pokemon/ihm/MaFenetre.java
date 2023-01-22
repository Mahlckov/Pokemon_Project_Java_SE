package fr.eni.pokemon.ihm;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;

import fr.eni.pokemon.controller.MyListener;

/*
 * La classe MaFenetre correspond a la Vue de mon pattern MVC
 * Role : afficher la fenetre
 */
public class MaFenetre extends JFrame {
	private static final long serialVersionUID = 1L;

	/*
	 * Definir tous les elements qu'on souhaite afficher sur la fenetre
	 */
	private JLabel labelNom, labelType, labelPC, labelPV, labelShiny ; // texte non editable
	private JTextField fieldNom, fieldType, fieldPC, fieldPV, fieldShiny; // texte editable, champ de saisie
	private JButton btnAjouter; // bouton
	private JTable tablePokedex; // tableau
	private MonModele modele; // donnees affichees par mon tableau
	
	public MaFenetre(String titre) {
		super(titre);
		setSize(800, 400); //taille fenêtre
		
		//Ma methode a moi,décrite plus bas
		setupContent();
		
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); //fermeture fenetre quand clic sur la croix
	}
	
	private void setupContent() {
		/*
		 * getContentPane me permet de recuperer le panneau "global"
		 * --> celui qui prend toute la fenetre
		 * BoxLayout avec Y_AXIS : affiche mes elements de haut en bas
		 * Layout = disposition des elements dans ma fenetre
		 */
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		setupHead(); //Permet d'inserer un pokemon dans le Pokedex
		setupBody(); //pokedex
	}
	
	private void setupHead() {
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS)); //elements de gauche a droite
		topPanel.setMaximumSize(new Dimension(800, 25)); //Prend largeur de la fenetre + 25px de haut arbitraire
		
		topPanel.add(getLabelNom());
		topPanel.add(getFieldNom());
		topPanel.add(getLabelType());
		topPanel.add(getFieldType());
		topPanel.add(getLabelPC());
		topPanel.add(getFieldPC());
		topPanel.add(getLabelPV());
		topPanel.add(getFieldPV());
		topPanel.add(getLabelShiny());
		topPanel.add(getFieldShiny());
		topPanel.add(getBtnAjouter());
		
		add(topPanel); //ajout du panneau a la fenetre contenant tous les elements top
	}
	
	private void setupBody() {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		JScrollPane middlePanel = new JScrollPane(); //panneau contenant un ascenseur pour naviguer dans le tableau
		middlePanel.setViewportView(getTablePokedex()); //setViewportView = add pour avoir un scroll qui focntionne 
		add(middlePanel);
	}
	
	
	/**
	 * Getters qui initialisent et affichent a l'ecran les labels et champs de saisie
	 * Utilisation du pattern Signeton => si jamais initialise = initialise, sinon recupere l'existant = pas de duplicata
	 * 
	 */
	public JLabel getLabelNom() {
		if (labelNom == null) {
			labelNom = new JLabel("Nom :");
		}
		return labelNom;
	}
	
	public JLabel getLabelType() {
		if (labelType == null) {
			labelType = new JLabel("Type :");
		}
		return labelType;
	}
	
	public JLabel getLabelPC() {
		if (labelPC == null) {
			labelPC = new JLabel("PC :");
		}
		return labelPC;
	}
	
	public JLabel getLabelPV() {
		if (labelPV == null) {
			labelPV = new JLabel("PV :");
		}
		return labelPV;
	}
	
	public JLabel getLabelShiny() {
		if (labelShiny == null) {
			labelShiny = new JLabel("Shiny? (true/false) :");
		}
		return labelShiny;
	}
	
	public JTextField getFieldNom() {
		if (fieldNom == null) {
			fieldNom = new JTextField();
		}
		return fieldNom;
	}
	
	public JTextField getFieldType() {
		if (fieldType == null) {
			fieldType = new JTextField();
		}
		return fieldType;
	}
	
	public JTextField getFieldPC() {
		if (fieldPC == null) {
			fieldPC = new JTextField();
		}
		return fieldPC;
	}
	
	public JTextField getFieldPV() {
		if (fieldPV == null) {
			fieldPV = new JTextField();
		}
		return fieldPV;
	}
	
	public JTextField getFieldShiny() {
		if (fieldShiny == null) {
			fieldShiny = new JTextField();
		}
		return fieldShiny;
	}
	
	public JButton getBtnAjouter() {
		if (btnAjouter == null) {
			btnAjouter = new JButton("Ajouter");
			
			MyListener listener = new MyListener(this); //Fait appel au controller
			//J'affecte une action, comportement qui se declenche au clic sur le bouton
			btnAjouter.addActionListener(listener);
		}
		
		return btnAjouter;
	}
	
	public JTable getTablePokedex() {
		if (tablePokedex == null) {
			modele = new MonModele();
			tablePokedex = new JTable(modele);
		}
		return tablePokedex;
	}
	
	//Exception personnalisee geree dans le controller permettant d'afficher une pop-up si mauvaise saisie 
	public void handleException(String message) {
		JDialog dialog = new JDialog(this); //pop-up liee a this=MaFenetre ---> si pop-up ouverte et qu'on ferme la fenetre, pop-up se ferme aussi
		dialog.getContentPane().add(new JLabel(message));
		dialog.setVisible(true);
		dialog.setSize(400, 200);
	}
	
	public void updateContent() {
		modele.reloadModel();
		
		// Je signale a mon JTable que l'affichage doit s'actualiser
		modele.fireTableDataChanged();
	}
}






















