package Main;

import model.Pengguna;
import view.MainView;

public class main {
    public static void main(String[] args) {
        Pengguna mhs = new Pengguna("2509116097", "Khairul Ikhsan");
        
        MainView app = new MainView();
        app.olahMenu(mhs);
    }
}