// W02C02 Activity3b Toni Bernetic

public class WeatherUIs {
   public static void main(String[]args) {
      KelvinTempSensor kts = new KelvinTempSensor();
      ITempSensor tS = new KelvinTempSensorAdapter(kts);
      Barometer barometer = new Barometer();
      WeatherStation ws = new WeatherStation(tS, barometer) ;
      Thread thread = new Thread(ws);
      
      TextUI tUI = new TextUI(ws);
      SwingUI sUI = new SwingUI(ws);
      AWTUI aUI = new AWTUI(ws);
      
      thread.start();
   }
}