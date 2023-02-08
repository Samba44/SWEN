// W02C02 Activity3b Toni Bernetic

public class WeatherUIs {
   public static void main(String[]args) {
      WeatherStation ws = new WeatherStation() ;
      Thread thread = new Thread(ws);
      
      TextUI tUI = new TextUI(ws);
      SwingUI sUI = new SwingUI(ws);
      AWTUI aUI = new AWTUI(ws);
      
      thread.start();
   }
}