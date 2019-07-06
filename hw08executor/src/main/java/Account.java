public class Account {

    @ID
    private int no;
    @TypeString
    private String type;
    @TypeInt
    private int rest;

    public int getNo() {
        return no;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRest() {
        return rest;
    }

    public void setRest(int rest) {
        this.rest = rest;
    }

    public Account(String type, int rest) {
        this.type = type;
        this.rest = rest;
    }
}
