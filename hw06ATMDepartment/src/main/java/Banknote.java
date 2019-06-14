public enum Banknote {
    fiveHundred(500), twoHundred(200), hundred(100), fifty(50);
    private int nominal;

    Banknote(int nominal){
        this.nominal = nominal;
    }

    public int getNominel(){
        return nominal;
    }
}
