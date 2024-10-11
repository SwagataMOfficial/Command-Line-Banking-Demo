public class Bank {
    private double amount = 5000.0;

    public boolean transferMoney(double money) {
        if (money < this.amount) {
            amount -= money;
            return true;
        } else {
            return false;
        }
    }

    public double checkBalance() {
        return this.amount;
    }

    public boolean deposit(double amount)
    {
        this.amount += amount;
        return true;
    }
}
