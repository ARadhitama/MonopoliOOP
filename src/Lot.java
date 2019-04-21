public class Lot extends Property {
    private int rumah;
    private int kompleks;

    public Lot(String name, int harga, int kompleks, LogPage log) {
        super(name, harga, 1, log);
        setRumah(0);
        setKompleks(kompleks);
    }

    public int getRumah() {
        return this.rumah;
    }

    public void setRumah(int rumah) {
        this.rumah = rumah;
    }

    public int getKompleks() {
        return this.kompleks;
    }

    public void setKompleks(int kompleks) {
        this.kompleks = kompleks;
    }

    public void addRumah() {
        // Cek punya komplek atau belum
        if (this.getOwner().cekKompleks(this.getKompleks())) {
            if (getRumah() == 4) {
                this.getLog().appendLog("Ga bisa bang, rumah lu udah 4");
            } else {
                this.getOwner().rdcMoney(this.getHarga());
                setRumah(getRumah() + 1);
                this.getLog().appendLog("Rumah " + this.getOwnerName() + " di " + this.getName() + " ada " + this.getRumah());
            }
        } else {
            this.getLog().appendLog("Belom punya satu komplek, beli dulu sana");
        }
    }

    public void buyProp(Player p) {
        if (this.getOwnerName().equals(p.getName())) {
            this.getLog().appendLog("Udah lu beli");
        } else if (this.getOwner() == null) {
            if (p.getMoney() >= this.getHarga()) {
                this.setOwner(p);
                p.getProp().add(this);
                p.getLot().add(this);
                this.getOwner().rdcMoney(this.getHarga());
                this.getLog().appendLog(p.getName() + " telah membeli " + this.getName() + " seharga " + this.getHarga());
                this.getLog().appendLog("Uang " + p.getName() + " tinggal " + p.getMoney());
            } else {
                this.getLog().appendLog(p.getName() + " tidak memiliki cukup uang");
            }
        } else {
            this.getLog().appendLog("Udah punya orang lain");
        }
    }

    public void bayarRent(Player p) {
        int hargaRent = 0;
        if (this.getOwner().cekKompleks(this.getKompleks())) {
            switch (this.getRumah()) {
                case 0:
                    hargaRent = this.getHarga() / 4;
                    break;
                case 1:
                    hargaRent = this.getHarga() / 2;
                    break;
                case 2:
                    hargaRent = this.getHarga();
                    break;
                case 3:
                    hargaRent = this.getHarga() * 2;
                    break;
                case 4:
                    hargaRent = this.getHarga() * 4;
                    break;
            }
        } else { // Ga komplek
            hargaRent = this.getHarga() / 8;
        }
        this.getOwner().addMoney(hargaRent);
        if (this.getOwnerName().equals(p.getName())) {
            this.getLog().appendLog(p.getName() + " dapet bonus sebesar " + hargaRent + " karena mendarat di property sendiri");
        } else {
            p.rdcMoney(hargaRent);
            this.getLog().appendLog(p.getName() + " telah membayar sewa " + this.getName() + " kepada " + this.getOwnerName() + " sebanyak " + hargaRent);
        }
    }

    public boolean landedMethod(Player p, Turn t) {
        if (t.getCommand().equals("beli")) {
            if (this.getOwnerName().equals("Tuhan")) {
                this.buyProp(p);
            } else if (this.getOwnerName().equals(p.getName())){
                this.bayarRent(p);
                this.addRumah();
            }   
        } else if (!this.getOwnerName().equals("Tuhan")) {
            this.bayarRent(p);
        } else {
            // 
        }
        return true;
    }
}