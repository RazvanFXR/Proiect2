package app.Interfaces;

public interface Account {


	boolean setName(String name);
    boolean setLastName(String lastName);
    boolean setBirthDate(String birthDate);
    boolean setSIN(int SIN);
    boolean setID(int ID);
    boolean setBalLeft(int balLeft);
    boolean setBalRight(int balRight);
    boolean setCurrency(String currency);
    boolean setLastActivity(String lastActivity);


	String getName();
	String getLastName();
	String getBirthDate();
	int getSIN();
	int getID();
	int getBalLeft();
	int getBalRight();
    String getCurrency();
    String getLastActivity();

	boolean withdraw(int amount);
	
	boolean deposit(int amount);
	
	boolean createAccount(int ID);
	
	boolean deleteAccount(int accountID);




	
}
