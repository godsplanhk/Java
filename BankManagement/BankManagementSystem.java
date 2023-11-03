class BankAccount{
    private String accountNo;private int amount;
    BankAccount(String no,int amount){
        this.accountNo=no;this.amount = amount;
    }
     void deposit(int amount){
        System.out.println("Depositing amount........");
        this.amount+=amount;
        System.out.println("Successfully deposited the amount of "+ amount+ " in bank account number ending with "+(accountNo.substring(accountNo.length()-4)));
    }
     void withdrawal(int amount){
        if(amount<this.amount){
            System.out.println("Withdrawing amount........");
            this.amount-=amount;
            System.out.println("Successfully withdrawal of "+ amount +"\n Current Balance: "+ this.amount);
        }
        else{
            System.out.println("Insufficient Balance.");
        }
    }
}
class TransactionThread extends Thread{
    private BankAccount account; private int amount;
    TransactionThread(BankAccount ac,int amount){
        account = ac;this.amount = amount;
    }
    public void run(){
        synchronized(account)
        {if(amount<0){
            account.withdrawal(-amount);
        }
        else{
            account.deposit(amount);
        }}
    }
}
public class BankManagementSystem{
    public static void main(String[] args){
        BankAccount hk = new BankAccount("123457442323", 10000);
        TransactionThread transactions []= new TransactionThread[20];
        for(int i = 0;i<10;i++){
            transactions[i]= new TransactionThread(hk, i*100);
            transactions[i].start();
        }
        for(int i = 10;i<20;i++){
            transactions[i]= new TransactionThread(hk, -i*100);
            transactions[i].start();
        }
    }
}