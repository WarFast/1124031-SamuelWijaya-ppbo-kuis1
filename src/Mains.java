import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
enum device {
SmartTv,SmartSpeaker,SmartDoorLocker
}
interface Switchable{
    void turnOn();
    void turnOff();
}
interface Connectable{
    void Connect();
    void disconnect();
}
interface Lockable{
    void lock();
    void unlock();
}
abstract class Perangkat implements Switchable,Connectable,Lockable{
    private String id;
    private String nama;
    private String daya;
    private int volume;
    private String status;
    private device dvc;
    public Perangkat(String id, String nama, String daya, int volume, String status, device dvc) {
        this.id = id;
        this.nama = nama;
        this.daya = daya;
        this.volume = volume;
        this.status = status;
        this.dvc = dvc;
    }
    public String getId() {
        return id;
    }
    public String getNama() {
        return this.nama;
    }
    public String getDaya() {
        return this.daya;
    }
    public int getVolume() {
        return this.volume;
    }
    public String getStatus(){
        return this.status;
    }
    public device getDvc() {
        return this.dvc;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public void setDaya(String daya) {
        this.daya = daya;
    }
    public void setVolume(int volume) {
        this.volume = volume;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setDvc(device dvc) {
        this.dvc = dvc;
    }
    public abstract String getDeviceDetails();

    @Override
    public void Connect() {
        System.out.println(dvc+"berhasil tersambung");
    }
    @Override
    public void disconnect() {
        System.out.println(dvc+"berhasil terputus");
    }
    @Override
    public void lock() {
        System.out.println(dvc+"berhasil lock");
    }
    @Override
    public void unlock() {
        System.out.println(dvc+"berhasil unlock");
    }

    @Override
    public void turnOn() {
        System.out.println(dvc+"berhasil dinyalakan");
    }
    @Override
    public void turnOff() {
        System.out.println(dvc+"berhasil dimatikan");
    }
}
class SmartTV extends Perangkat{
    private String Channel;
    public SmartTV(String id, String nama, String daya, int volume, String status, String Channel, device dvc) {
        super(id, nama, daya, volume, status,dvc);
    }

    @Override
    public String getDeviceDetails() {
        return " SmarTv [" + getNama() + "](ID;" + getId() + ")- Daya: " + getDaya() +".0W"+ "|Status: " + getStatus() + "| Koneksi:WIFI|Channel:" + Channel + "|Volume:"+getVolume() ;
    }
}
class SmartSpeaker extends Perangkat {
    public SmartSpeaker(String id, String nama, String daya, int volume, String status, device dvc) {
        super(id, nama, daya, volume, status, dvc);
    }

    @Override
    public String getDeviceDetails() {
        return " SmarTv [" + getNama() + "](ID;" + getId() + ")- Daya: " + getDaya() + ".0W" + "|Status: " + getStatus() + "| Koneksi:Bluetooth|Channel:"+ "|Volume:" + getVolume();
    }
}
class SmartDoorLocker extends Perangkat {
    private String PIN;
    public SmartDoorLocker(String PIN,String id, String nama, String daya, int volume, String status,device dvc) {
        super(id, nama, daya, volume, status, dvc);
        this.PIN = PIN;
    }
    @Override
    public String getDeviceDetails() {
        return " SmarTv [" + getNama() + "](ID;" + getId() + ")- Daya: " + getDaya() + ".0W" + "|Status: " + getStatus() + "| Koneksi:WIFI|Channel:"  +"|Volume:" + getVolume();
    }
}
class PerangkatControllers {
    private List<Perangkat> perangkatList;

    public PerangkatControllers() {
        this.perangkatList = new ArrayList<>();
    }

    public void addPerangkat(Perangkat perangkat) {
        perangkatList.add(perangkat);
    }

    public List<Perangkat> getAllPerangkat() {
        return perangkatList;
    }


    class MainViews {
        private PerangkatControllers controllers;
        private Scanner scan;

        public MainViews() {
            this.controllers = new PerangkatControllers();
            this.scan = new Scanner(System.in);
        }

        public void showMenu() {
            int choice = 0;
            do {
                System.out.println("\n=== MENU DAFTAR ===");
                System.out.println("1. Tambah Device");
                System.out.println("2. Tampilkan Device");
                System.out.println("3. Keluar");
                System.out.print("Pilih menu: ");

                if (scan.hasNextInt()) {
                    choice = scan.nextInt();
                    scan.nextLine();
                    switch (choice) {
                        case 1:
                            menuTambahDevice();
                            break;
                        case 2:
                            menuTampilkanDevice();
                            break;
                        case 3:
                            System.out.println("Terima kasih!");
                            break;
                        default:
                            System.out.println("Pilihan tidak valid!");
                    }
                } else {
                    System.out.println("Input harus angka!");
                    scan.nextLine();
                }
            } while (choice != 3);
        }

        private void menuTambahDevice() {
            System.out.println("\n--- Pilih Tipe Device ---");
            System.out.println("1. Smart Tv");
            System.out.println("2. Smart Speaker");
            System.out.println("3. Smart Door");
            System.out.print("Pilih tipe: ");
            int type = scan.nextInt();
            scan.nextLine();

            System.out.print("id: ");
            String id = scan.nextLine();
            System.out.print("nama: ");
            String nama = scan.nextLine();
            System.out.print("daya:(W)");
            String daya = scan.nextLine();
            System.out.print("volume: ");
            int volume = scan.nextInt();
            scan.nextLine();


            if (type == 1) {
                System.out.println("Channel berapa");
                String channel = scan.nextLine();
                scan.nextLine();


                SmartTV tv = new SmartTV(id,nama,daya,volume,"mati",channel,device.SmartTv);
                controllers.addPerangkat(tv);
                System.out.println("Smart tv berhasil ditambahkan!");

            } else if (type == 2) {
                SmartSpeaker speaker = new SmartSpeaker(id, nama, daya,volume,"mati",device.SmartSpeaker);
                controllers.addPerangkat(speaker);
                System.out.println("Smart Speaker berhasil ditambahkan!");

            } else if (type == 3) {
                System.out.println("berapa pinya");
                String pin = scan.nextLine();
                scan.nextLine();

                SmartDoorLocker doorLocker = new SmartDoorLocker(pin,id,nama,daya,volume,"mati",device.SmartDoorLocker);
                controllers.addPerangkat(doorLocker);
                System.out.println("Komputer berhasil ditambahkan!");
            } else {
                System.out.println("Tipe device tidak valid!");
            }
        }

        private void menuTampilkanDevice() {
            List<Perangkat> perangkats = controllers.getAllPerangkat();
            if (perangkats.isEmpty()) {
                System.out.println("\nBelum ada perangkat yang terdaftar.");
                return;
            }

            System.out.println("\n--- DAFTAR Perangkat ---");
            for (Perangkat perangkat : perangkats) {
                System.out.println(Perangkat.getDeviceDetails());
            }
        }
    }
public class Mains {
    public static void main(String[] args) {
        MainView view = new MainViews();
        view.showMenu();
    }
}