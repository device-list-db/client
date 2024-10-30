package com.holo.utils;

public class Debt {
    private Person debtor;
    private Person debtee;
    private double amountOwed;
    private double amountPaid;

    public Debt() {
        debtor = new Person();
        debtee = new Person();
        amountOwed = 0.0;
        amountPaid = 0.0;
    }

    public void registerDebt(Person debtor, Person debtee, double amountOwed, double amountPaid) {
        this.debtor = debtor;
        this.debtee = debtee;
        this.amountOwed = amountOwed;
        this.amountPaid = amountPaid;
    }

    public String getDebtor() {
        if (debtor.getName().contains("_"))
            return debtor.getName().replace("_", " ");
        return debtor.getName();
    }

    public String getDebtee() {
        if (debtee.getName().contains("_"))
            return debtee.getName().replace("_", " ");
        return debtee.getName();
    }

    public double getAmountOwed() {
        return amountOwed;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    /**
     * Should not be used, used only for displaying the table correctly.
     * @return number of fields in a device
     */
    public static int getFieldCount() { return 4; }
}
