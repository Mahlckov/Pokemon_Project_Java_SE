package fr.eni.pokemon.dal;

public class DAOException extends Exception{
	private static final long serialVersionUID = 1L;

		//Constructeurs
		public DAOException() {
			super();
		}
		
		public DAOException(String message) {
			super(message);
		}
		
		public DAOException(String message, Throwable exception) {
			super(message, exception);
		}

		//Méthodes
		@Override
		public String getMessage() {
			StringBuffer sb = new StringBuffer("Couche DAL - ");
			sb.append(super.getMessage()); //renvoie une String = contenu du message d'erreur (notre message personnalise + message Exception)
			
			return sb.toString() ;
		}
}
