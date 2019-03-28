public class Lot extends Property {
    private int rumah;
    private int kompleks;

    public Lot(String name, int harga, int kompleks){
        super(name, harga, 1);
        setRumah(0);
        setKompleks(kompleks);
    }

    public int getRumah(){
        return this.rumah;
    }

    public void setRumah(int rumah){
        this.rumah = rumah;
    }

    public void addRumah(){
        if (getRumah() == 4){
            System.out.println("Gabisa bang, rumahlu udah 4");
        } else {
            setRumah(getRumah() + 1);
        }
    }

    public int getKompleks(){
        return this.kompleks;
    }

    public void setKompleks(int kompleks){
        this.kompleks = kompleks;
    }
}