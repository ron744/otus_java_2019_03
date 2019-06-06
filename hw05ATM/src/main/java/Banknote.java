public enum Banknote {
    fifty(500), hundred(200), twoHundred(100), fiveHundred(50);
    private int nominal;

    Banknote(int nominal){
        this.nominal = nominal;
    }

    public int getNominel(){
        return nominal;
    }
}
