package lab6;

import lab6sortedlist.*;

/*  VirtualTeller.java */

/**
 *  An implementation of a virtual automated teller machine.
 **/
public class VirtualTeller {
  private static int nextAccountID = 100;
  private SortedList accounts;

  /**
   *  Constructs a new virtual teller.
   **/
  public VirtualTeller() {
    accounts = new SortedList();
  }

  /**
   *  openAccount() creates a new account for the customer "name".
   *  @param name the customer's name.
   *  @return the new account's ID number.
   **/
  public int openAccount(String name) {
    AccountData newData = new AccountData(name, nextAccountID);
    accounts.insert(newData);

    nextAccountID++;
    return newData.getNumber();
  }

  /**
   *  withdraw() withdraws "amount" dollars from the account whose number is
   *  "acct".  Assumes that amount >= 0.  If "acct" is invalid, no action is
   *  taken.
   *  @param acct is an account number.
   *  @param amount an amount of money.
 * @throws BadAccountException 
   */
  public void withdraw(int acct, int amount) {
   try{ 
	  AccountData account = findAccount(acct);
	  if(account == null){
		  throw new BadAccountException(acct);
	  }
	  if (amount < 0) {   // Didn't find the account.
//      System.out.println("Error:  Couldn't find account number `" +
//                         acct + "'" );
		  throw new BadTransactionException(amount);
    	
	  } else {
		  account.withdraw(amount);
	  }
   }catch(BadTransactionException e1){
//	   System.out.println("BadTransactionException");
	   System.err.println("BadTransactionException");
   }catch(BadAccountException e2){
	   System.err.println("BadAccountException");
   }
  }

  /**
   *  deposit() deposits "amount" dollars into the bank account whose number is
   *  "acct".  Assumes that amount >= 0.  If "acct" is invalid, no action is
   *  taken.
   *  @param acct is an account number.
   *  @param amount an amount of money.
 * @throws BadAccountException 
   */
  public void deposit(int acct, int amount){
    try{
	  AccountData account = findAccount(acct);
	  if (account == null) { 
//      System.out.println("Error:  Couldn't find account number `" +
//                         acct + "'");
		  throw new BadAccountException(acct);
	  }
      account.deposit(amount);
//    }
    }catch(BadAccountException e){
 	   System.err.println("BadAccountException");
    }
  }

  /**
   *  balanceInquiry() finds the balance on the account whose number is "acct".
   *  If "acct" is an invalid number, returns -1.
   *  @param acct an account number.
   *  @return the balance, or -1 if the account number is invalid.
   */
  public int balanceInquiry(int acct){
    
	  AccountData account = null;
	  try{
	   account = findAccount(acct);
    }catch(BadAccountException e){
  	   System.err.println("BadAccountException");
     }

    if (account == null) {
//    	System.out.println("Error:  Couldn't find account number `" +
//                acct + "'" );
      return -1;
    } else {
      return account.getBalance();
    }
  }

  /**
   *  findAccount() gets the AccountData object associated with account number
   *  "acct".  If "acct" does not refer to a valid account, returns null.
   *  @param acct is an account number.
   *  @return the AccountData object associated with the account number.
   */
  private AccountData findAccount(int acct) throws BadAccountException{
    AccountData account = null;
    try{
    	account = (AccountData) accounts.find(acct);
    	if(account == null){
    		throw new BadAccountException(acct);
    	}
    }catch(BadAccountException e){
 	   System.out.println("Invalid account number: " + acct);
    }
    return account;
  }
}